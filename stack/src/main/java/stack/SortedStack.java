package stack;

/**
 * Created by adarsh on 27/03/2017.
 */
public class SortedStack<T extends Comparable<T>> extends Stack<T> {

    public SortedStack(int size) {
        super(size);
    }

    @Override
    public void push(T data) {
        if(this.isFull())
            throw new UnsupportedOperationException("Cannot add to full stack");
        if(this.isEmpty()||this.top().compareTo(data)>0) {
            super.push(data);
            return;
        }
        Stack<T> tempStack = new Stack<>(this.size);
        while(!this.isEmpty()&&top().compareTo(data)<0)
        {
            tempStack.push(this.pop());
        }
        super.push(data);
        while(!tempStack.isEmpty())
            super.push(tempStack.pop());
    }

    @Override
    public T pop() {
        return super.pop();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public T top() {
        return super.top();
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }
}
