package stack;

/**
 * Created by adarsh on 27/03/2017.
 */
public class QueueWithStacks<T>{ //Queue implementation using 2 stacks. This version makes Enqueue costly and Dequeue quick
    Stack<T> elementStack;
    Stack<T> bufferStack;

    public QueueWithStacks(int size) {
        elementStack = new Stack<>(size);
        bufferStack=new Stack<>(size);
    }

    public void add(T data) //O(n)
    {
        if(elementStack.isFull())
            throw new UnsupportedOperationException("Queue is full");
        while(!elementStack.isEmpty())
        {
            bufferStack.push(elementStack.pop());
        }
        bufferStack.push(data);
        while(!bufferStack.isEmpty())
        {
            elementStack.push(bufferStack.pop());
        }
    }

    public T remove()
    {
        if(elementStack.isEmpty())
            throw new UnsupportedOperationException("Cannot remove from empty queue");

        return elementStack.pop();
    }

    public T peek()
    {
        if(elementStack.isEmpty())
            throw new UnsupportedOperationException("Cannot peek at empty queue");
        return elementStack.top();
    }

    public boolean isEmpty()
    {
        return elementStack.isEmpty();
    }


}
