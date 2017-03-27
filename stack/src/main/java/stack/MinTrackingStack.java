package stack;

import java.util.Comparator;

/**
 * Created by adarsh on 27/03/2017.
 */
public class MinTrackingStack<T> extends Stack<T> { //stack that tracks minimum value stored and returns in O(1) time
    Stack<T> minStack;
    Comparator<T> comparator;
    public MinTrackingStack(int size,Comparator<T> comparator) {
        super(size);
        this.minStack = new Stack<>(size);
        this.comparator=comparator;
    }

    @Override
    public void push(T data) {
        if(minStack.isEmpty()||comparator.compare(minStack.top(),data)>0)
            minStack.push(data);
        super.push(data);
    }

    @Override
    public T pop() {
        T popped =  super.pop();
        if(comparator.compare(minStack.top(), popped)==0)
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
