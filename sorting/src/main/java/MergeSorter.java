/**
 * Created by adarsh on 11/04/2017.
 */
public class MergeSorter extends Sorter {
    @Override
    public void sort(int[] array) {
        int[] helper = new int[array.length];
        mergeSortHelper(array, helper, 0, array.length - 1);
    }

    private void mergeSortHelper(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSortHelper(array, helper, low, mid);
            mergeSortHelper(array, helper, mid + 1, high);
            merge(array, helper, low, mid, high);
        }
    }

    private void merge(int[] array, int[] helper, int low, int mid, int high) {
        System.arraycopy(array, low, helper, low, high + 1 - low);

        int helperLeft = low;
        int helperRight = mid + 1;
        int current = low;
        while (helperLeft <= mid && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current++] = helper[helperLeft++];
            } else {
                array[current++] = helper[helperRight++];
            }
        }

        while (helperLeft <= mid) {
            array[current++] = helper[helperLeft++];
        }

    }
}
