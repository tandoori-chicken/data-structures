/**
 * Created by adarsh on 11/04/2017.
 */
public class QuickSorter extends Sorter {
    @Override
    public void sort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    private void quickSortHelper(int[] array, int left, int right) {
        int index = partition(array, left, right);
        if (left < index - 1) {
            quickSortHelper(array, left, index - 1);
        }
        if (index < right) {
            quickSortHelper(array, index, right);
        }

    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[(left + right) / 2];

        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(int[] array, int left, int right) {
        int buff = array[left];
        array[left] = array[right];
        array[right] = buff;
    }

}
