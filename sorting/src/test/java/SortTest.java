import org.junit.Test;

/**
 * Created by adarsh on 11/04/2017.
 */
public class SortTest {

    @Test
    public void testMergeSort() {
        Sorter sorter = new MergeSorter();

        sortAndTest(sorter,1, 2, 5, 4, 8, 9);
        sortAndTest(sorter,8,9,10,1,2,3);
        sortAndTest(sorter,1);
        sortAndTest(sorter,9,-8,-14);

    }

    private void sortAndTest(Sorter sorter,int... array)
    {
        sorter.sort(array);
        TestUtils.assertSorted(array);
    }
}
