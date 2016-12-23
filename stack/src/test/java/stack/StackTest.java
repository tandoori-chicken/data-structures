package stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by adarsh on 16/12/2016.
 */
public class StackTest {

    @Test
    public void testStackPush() {
        Stack<Character> stack = new Stack<>(7);
        stack.push('1');
        stack.push('2');
        stack.push('4');
        Assert.assertEquals("421", stack.toString());
        stack.pop();
        Assert.assertEquals("21", stack.toString());
    }

    @Test
    public void testConvertInfixToPostfix() {
        String input = "a+b*c+d";
        String expectedOutput = "abc*d++";
        Assert.assertEquals(expectedOutput, convertInfixToPostfix(input));
    }

    @Test
    public void testStackReverse() {
        Stack<Integer> stack = buildStack(1, 2, 3, 4);
        reverseStack(stack);
        Assert.assertEquals("1234", stack.toString());
    }

    private Stack<Integer> buildStack(Integer... data) {
        Stack<Integer> stack = new Stack<>(data.length);

        Arrays.stream(data).forEach(stack::push);

        return stack;
    }

    private <T> void reverseStack(Stack<T> inputStack) {
        if (inputStack.isEmpty())
            return;

        T bottom = popBottom(inputStack);

        reverseStack(inputStack);

        inputStack.push(bottom);


    }


    public <T> T popBottom(Stack<T> stack) {

        if (stack.isEmpty())
            throw new UnsupportedOperationException("Stack empty");

        T top = stack.pop();

        if (stack.isEmpty())
            return top;

        T bottom = popBottom(stack);

        stack.push(top);


        return bottom;

    }

    /**
     * Algorithm
     * 1. Scan the infix expression from left to right.
     * 2. If the scanned character is an operand, output it.
     * 3. Else,
     * …..3.1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty), push it.
     * …..3.2 Else, Pop the operator from the stack until the precedence of the scanned operator is less-equal to the precedence of the operator residing on the top of the stack. Push the scanned operator to the stack.
     * 4. If the scanned character is an ‘(‘, push it to the stack.
     * 5. If the scanned character is an ‘)’, pop and output from the stack until an ‘(‘ is encountered.
     * 6. Repeat steps 2-6 until infix expression is scanned.
     * 7. Pop and output from the stack until it is not empty.
     */

    private String convertInfixToPostfix(String infixInput) {
        Stack<Character> stack = new Stack<>(infixInput.length());
        StringBuilder postfixOutputBuilder = new StringBuilder();
        char[] infixArray = infixInput.toCharArray();

        for (char c : infixArray) {
            if (Character.isLetter(c)) {
                postfixOutputBuilder.append(c);
                continue;
            }
            if (stack.isEmpty() || getPrecedence(c) > getPrecedence(stack.top())) {
                stack.push(c);
            } else {
                do {
                    if (stack.isEmpty())
                        break;
                    postfixOutputBuilder.append(stack.pop());
                } while (getPrecedence(c) > getPrecedence(stack.top()));
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfixOutputBuilder.append(stack.pop());
        }

        return postfixOutputBuilder.toString();
    }


    private int getPrecedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    @Test
    public void testStackSort() {
        Stack<Integer> stack = buildStack(9, 3, 4, 2, 5, 5, 1);
        sortStack(stack);

        Assert.assertEquals("1234559", stack.toString());
    }

    private <T extends Comparable> void sortStack(Stack<T> stack) {
        if (stack.isEmpty())
            return;

        T top = stack.pop();

        sortStack(stack);
        sortedInsert(stack, top);

    }

    private <T extends Comparable> void sortedInsert(Stack<T> stack, T toInsert) {
        if (stack.isEmpty()) {
            stack.push(toInsert);
            return;
        }
        T top = stack.top();
        if (toInsert.compareTo(top) < 0) {
            stack.push(toInsert);
            return;
        }

        stack.pop();
        sortedInsert(stack, toInsert);
        stack.push(top);
    }

    @Test
    public void testNextGreaterElement() {
        Integer[] array1 = new Integer[]{4, 5, 2, 25};
        Integer[] array2 = new Integer[]{13, 7, 6, 12};

        Integer[] output1 = nextGreaterElement(array1);
        String expectedOutput1 = "52525-1";
        Assert.assertEquals(expectedOutput1, convertArrayToString(output1));

        Integer[] output2 = nextGreaterElement(array2);
        String expectedOutput2 = "-11212-1";
        Assert.assertEquals(expectedOutput2, convertArrayToString(output2));
    }


    /**
     * Add first element to stack;
     * For every element scanned, if element less than top of stack, add to stack
     * else, pop stack till element to be added is less than top, then add element to stack
     * every popped element's nge is the current element
     * after all characters are scanned, pop all elements in stack and mark their nge as -1
     * we add index to stack instead of element as we're using arrays
     */
    private Integer[] nextGreaterElement(Integer[] input) {
        Integer[] output = new Integer[input.length];
        Stack<Integer> stack = new Stack<>(input.length);
        stack.push(0); //first element

        for (int i = 1; i < input.length; i++) {

            if (input[i] > input[stack.top()]) {
                do {
                    int poppedIndex = stack.pop();
                    output[poppedIndex] = input[i];
                } while (!stack.isEmpty() && input[i] > input[stack.top()]);
                stack.push(i);
            } else {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int poppedIndex = stack.pop();
            output[poppedIndex] = -1;
        }

        return output;
    }

    private String convertArrayToString(Integer[] inp) {
        StringBuilder sb = new StringBuilder("");
        Arrays.stream(inp).forEach(sb::append);

        return sb.toString();
    }

    @Test
    public void testStockSpan() {
        int[] inputArray = new int[]{100, 80, 60, 70, 60, 75, 85};
        Integer[] outputArrayExpected = new Integer[]{1, 1, 1, 2, 1, 4, 6};

        Integer[] output = getStockSpan(inputArray);

        Assert.assertEquals(convertArrayToString(outputArrayExpected), convertArrayToString(output));
    }

    /**
     *
     */
    private Integer[] getStockSpan(int[] input) {
        Integer[] result = new Integer[input.length];

        Arrays.fill(result, 1);

        Stack<Integer> stack = new Stack<>(input.length);
        stack.push(0);

        for (int i = 1; i < input.length; i++) {
            if (input[i] > input[stack.top()]) {
                do {
                    int poppedIndex = stack.pop();
                    result[i] += result[poppedIndex];
                } while (!stack.isEmpty() && input[i] > input[stack.top()]);
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        return result;
    }
}
