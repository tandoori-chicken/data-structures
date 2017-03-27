package stack;

/**
 * Created by adarsh on 27/03/2017.
 */
public class MinTrackingStack<T extends Comparable<T>> extends Stack<T> { //stack that tracks minimum value stored and returns in O(1) time
    Stack<T> minStack;

    public MinTrackingStack(int size) {
        super(size);
        this.minStack = new Stack<>(size);
    }

    @Override
    public void push(T data) {
        if(minStack.isEmpty()||data.compareTo(minStack.top())<0)
            minStack.push(data);
        super.push(data);
    }

    @Override
    public T pop() {
        T popped =  super.pop();
        if(popped.compareTo(minStack.top())==0)
        {
            minStack.pop();
        }
        return popped;
    }

    public T min()
    {
        if(minStack.isEmpty())
            throw new UnsupportedOperationException("Stack is empty. Cannot find min");
        return minStack.top();
    }
}
