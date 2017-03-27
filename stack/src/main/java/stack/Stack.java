package stack;

/**
 * Created by adarsh on 15/12/2016.
 */
public class Stack<T> {
    public int top = -1;
    private Object[] stack;
    public int size;


    public Stack(int size) {
        this.size = size;
        stack = new Object[size];
    }

    public boolean isFull()
    {
        return top==(size-1);
    }

    public void push(T data) {
        if (top == size - 1)
            throw new UnsupportedOperationException("Stack full");

        top++;
        stack[top] = data;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            throw new UnsupportedOperationException("Stack empty");
        }
        return (T) stack[top--];
    }

    @SuppressWarnings("unchecked")
    public T top() {
        if (top == -1) {
            throw new UnsupportedOperationException("Stack empty");
        }

        return (T) stack[top];
    }


    @Override
    public String toString() {
        if (top == -1)
            return "";
        StringBuilder sb = new StringBuilder("");
        for (int i = top; i >= 0; i--) {
            sb.append(stack[i]);
        }
        return sb.toString();

    }


}
