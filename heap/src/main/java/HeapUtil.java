/**
 * Created by adarsh on 13/02/2017.
 */
public class HeapUtil {

    protected static int getLeftChildIndex(int index, int size) {
        if (index < size / 2) {
            return 2 * index + 1;
        }
        return -1;
    }

    protected static int getRightChildIndex(int index, int size) {
        if (index < (size - 1) / 2) {
            return 2 * index + 2;
        }
        return -1;
    }

    protected static boolean isLeaf(int index, int size) {
        if (index == -1 || index >= size)
            return false;
        int halfwayIndex = (int) Math.pow(2, (int) (Math.log(size) / Math.log(2)));
        return index >= halfwayIndex - 1;
    }

    protected static void swap(Object[] arr, int index1, int index2) {
        Object buffer = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = buffer;
    }

    /**
     * 1. create a heap of size sortDistance+1
     * 2. a. remove max element from heap and load in array
     * 2. b. if possible add next element from array into heap
     * 3. repeat step 2 till all elements have been processed
     */
    public static <T extends Comparable<T>> void sortAlmostSortedArray(T[] inputArr, int sortDistance) {
        Heap<T> heap = new Heap<>(sortDistance + 1);
        for (int i = 0; i <= sortDistance; i++) {
            heap.addElement(inputArr[i]);
        }
        int length = inputArr.length;
        int curIndex = sortDistance + 1;
        int newIndex = 0;
        while (newIndex < length) {
            T maxElement = heap.removeMaxElement();
            inputArr[newIndex] = maxElement;
            newIndex++;
            if (curIndex < length) {
                heap.addElement(inputArr[curIndex]);
                curIndex++;
            }
        }
    }

}
