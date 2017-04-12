import org.junit.Test;
import sorter.*;

/**
 * Created by adarsh on 11/04/2017.
 */
public class SortingAlgorithmsTest {

    @Test
    public void testMergeSort() {
        Sorter sorter = new MergeSorter();

        sortAndTest(sorter, 1, 2, 5, 4, 8, 9);
        sortAndTest(sorter, 8, 9, 10, 1, 2, 3);
        sortAndTest(sorter, 1);
        sortAndTest(sorter, 9, -8, -14);

    }


    @Test
    public void testQuickSort() {
        Sorter sorter = new QuickSorter();

        sortAndTest(sorter, 1, 2, 5, 4, 8, 9);
        sortAndTest(sorter, 8, 9, 10, 1, 2, 3);
        sortAndTest(sorter, 1);
        sortAndTest(sorter, 9, -8, -14);


    }


    @Test
    public void testBucketSort() {
        Sorter sorter = new BucketSorter();
        sortAndTest(sorter, 1, 4, 6, 3, 3, 9, 2, 2, 1, 6, 4);
    }

    @Test
    public void testCountSort() {
        Sorter sorter = new CountSorter();
        sortAndTest(sorter, 1, 4, 6, 3, 3, 9, 2, 2, 1, 6, 4);
        sortAndTest(sorter, 12, 35, 23, 15, 17, 17);
    }

    private void sortAndTest(Sorter sorter, int... array) {
        sorter.sort(array);
        TestUtils.assertSorted(array);
    }

    @Test
    public void testSortDoubleArray() {
        double[] array = new double[]{0.12, 0, 0.9, 0.47, 0.43, 0.45, 0.6};
        new BucketSorter().sort(array);
        TestUtils.assertSorted(array);
    }


}
