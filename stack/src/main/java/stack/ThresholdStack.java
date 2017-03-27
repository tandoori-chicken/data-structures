package stack;

/**
 * Created by adarsh on 27/03/2017.
 */
public class ThresholdStack<T> { //Stack that has threshold and creates a new stack once threshold is reached
    NodeStack<T> headStack;

    static class NodeStack<T> extends Stack<T> {
        NodeStack<T> nextStack;
        NodeStack<T> prevStack;

        public NodeStack(int size, NodeStack<T> prevStack) {
            super(size);
            this.prevStack = prevStack;
        }


        @Override
        public void push(T data) {
            if (this.isFull()) {
                NodeStack<T> nextStack = new NodeStack<>(this.size, this);
                this.nextStack = nextStack;
                nextStack.push(data);
            } else {
                super.push(data);
            }
        }

        @Override
        public T pop() {

            if(this.isEmpty()) //will only be called when popping from empty head stack
                throw new UnsupportedOperationException("Cannot pop from empty stack");

            T popped = super.pop();
            if (this.isEmpty()&&this.prevStack!=null) {
                this.prevStack.nextStack = null;
            }
            return popped;
        }
    }

    public ThresholdStack(int threshold) {
        headStack = new NodeStack<>(threshold, null);
    }

    public boolean isEmpty() {
        return this.headStack.isEmpty();
    }

    private NodeStack<T> getLastStack() {
        NodeStack<T> curStack = this.headStack;
        while (curStack.nextStack != null) {
            curStack = curStack.nextStack;
        }
        return curStack;
    }

    public void push(T data) {
        NodeStack<T> currStack = this.headStack;
        while(currStack.nextStack!=null&&currStack.isFull())
        {
            currStack=currStack.nextStack;
        }
        currStack.push(data);
    }

    public T pop() {
        NodeStack<T> lastNodeStack = getLastStack();
        return lastNodeStack.pop();
    }

    public T top()
    {
        NodeStack<T> lastNodeStack = getLastStack();
        return lastNodeStack.top();
    }

    public T popAt(int stackIndex)
    {
        NodeStack<T> curStack = this.headStack;
        while(curStack.nextStack!=null&&stackIndex-->0)
        {
            curStack=curStack.nextStack;
        }
        if(stackIndex>0)
            throw new UnsupportedOperationException("Cannot find stack at index : "+stackIndex);
        return curStack.pop();
    }
}
