import java.util.stream.IntStream;

/**
 * Created by adarsh on 26/12/2016.
 */
public class Heap<T extends Comparable<T>> { //declare max-heap
    protected Object[] elements;
    private int maxSize;
    private int size;

    public Heap(T[] elements) {
        this.elements = elements;
        this.maxSize = this.size = elements.length;
        buildHeap();
    }

    public Heap(int maxSize) {
        this.size = 0;
        this.maxSize = maxSize;
        this.elements = new Object[maxSize];
    }

    /**
     * 1. remove element at index 0
     * 2. copy last element to index 0
     * 3. perform heapify at index 0
     * 4. reduce size
     */
    public T removeMaxElement() {
        if (size == 0)
            throw new UnsupportedOperationException("Cannot remove from empty heap");
        T toReturn = (T) elements[0];
        elements[0] = elements[size - 1];
        maxHeapify(0);
        this.size--;
        return toReturn;
    }

    /**
     * 1. add element in last position
     * 2. perform swap as needed with parent
     */
    public void addElement(T toAdd) {
        if (size == maxSize)
            throw new UnsupportedOperationException("Cannot add to already full heap");
        elements[size] = toAdd;
        this.size++;
        int curIndex = size - 1;
        while (getParentIndex(curIndex) != -1 && ((T) elements[getParentIndex(curIndex)]).compareTo((T) elements[curIndex]) < 0) {
            HeapUtil.swap(elements, curIndex, getParentIndex(curIndex));
            curIndex = getParentIndex(curIndex);
        }
    }

    /**
     * 1. increase value for appropriate index
     * 2. perform swap as needed with parent
     */
    public void increaseKey(int index, T toAdd) {
        doAdd(index, toAdd);
        while (getParentIndex(index) != -1 && ((T) elements[getParentIndex(index)]).compareTo((T) elements[index]) < 0) {
            HeapUtil.swap(elements, getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * 1. decrease value for appropriate index
     * 2. call heapify at this index
     */
    public void decreaseKey(int index, T toSubtract) {
        doSubtract(index, toSubtract);
        maxHeapify(index);

    }


    protected void doAdd(int index, T toAdd) {
        throw new UnsupportedOperationException("Cannot perform add. Please override in child class.");
    }

    protected void doSubtract(int index, T toSubtract) {
        throw new UnsupportedOperationException("Cannot perform subtract. Please override in child class.");
    }


    private void buildHeap() {
        for (int i = size - 1; i >= 0; i--) {
            if (!isLeaf(i))
                maxHeapify(i);
        }
    }

    /**
     * 1. if parent is less than child, swap with biggest child
     * 2. if swap occurred, recursively call {@linkplain Heap#maxHeapify(int)} with swapped child
     */
    private void maxHeapify(int index) {
        if (index == -1)
            return;
        if (!isLeaf(index)) {
            int lIndex = getLeftChildIndex(index);
            int rIndex = getRightChildIndex(index);

            int largestIndex = index;
            if (lIndex != -1) {
                if (elements[largestIndex] == null || ((T) elements[largestIndex]).compareTo((T) elements[lIndex]) < 0)
                    largestIndex = lIndex;
            }
            if (rIndex != -1) {
                if (elements[largestIndex] == null || ((T) elements[largestIndex]).compareTo((T) elements[rIndex]) < 0) {
                    largestIndex = rIndex;
                }
            }
            if (largestIndex != index) {
                HeapUtil.swap(elements, index, largestIndex);
                maxHeapify(largestIndex);
            }

        }
    }

    private boolean isLeaf(int index) {
        return HeapUtil.isLeaf(index, this.size);
    }

    private int getParentIndex(int index) {
        return index == 0 ? -1 : (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return HeapUtil.getLeftChildIndex(index, this.size);
    }

    private int getRightChildIndex(int index) {
        return HeapUtil.getRightChildIndex(index, this.size);
    }

    public T getMaxElement() {
        if (this.size == 0)
            throw new UnsupportedOperationException("Cannot retrieve from empty heap");
        return (T) elements[0];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        IntStream.range(0, this.size).boxed().map(i -> elements[i]).forEach(sb::append);
        return sb.toString();
    }


}
