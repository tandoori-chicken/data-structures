package stack;

/**
 * Created by adarsh on 27/03/2017.
 */
public class MultipleStackArray<T> { //Space-efficient implementation of k stacks.
    int[] top; //stores top of all stacks
    int[] next; //stores next index for each index
    int free; //stores next available free index. -1 means full;
    Object[] elements; //array of elements

    public MultipleStackArray(int size, int k) {
        elements = new Object[size];
        top = new int[k];
        next = new int[size];

        for (int i = 0; i < k; i++) {
            top[i] = -1;
        }
        for (int i = 0; i < size - 1; i++) {
            next[i] = i + 1;
        }
        next[size - 1] = -1;
        free = 0; //when initialising, first available free slot is at 0
    }

    public boolean isEmpty(int sIndex) {
        return top[sIndex] == -1;
    }

    public boolean isFull() {
        return free == -1;
    }

    public void push(int sIndex, T item) {
        if (isFull()) {
            throw new UnsupportedOperationException("Stack already full");
        }

        int i = free; //'i' is where we're going to store this item. Received from 'free'
        free = next[i]; //'free' now points to wherever i's next was
        next[i] = top[sIndex]; //Since 'i' is going to be top, it's next points to old 'top'
        top[sIndex] = i; //top is now 'i'
        elements[i] = item; //store item at index 'i'
    }

    @SuppressWarnings("unchecked")
    public T pop(int sIndex) {
        if (isEmpty(sIndex))
            throw new UnsupportedOperationException("Cannot pop from empty stack");
        int i = top[sIndex]; //get old top
        top[sIndex] = next[i]; //top is now next of old top
        next[i] = free; //i is going to be free. Store old 'free' in next[i]
        free = i; //free is i
        return (T) elements[i];
    }

    @SuppressWarnings("unchecked")
    public T top(int sIndex) {
        if (isEmpty(sIndex))
            throw new UnsupportedOperationException("Cannot peek into empty stack");
        return (T) elements[top[sIndex]];
    }

}
