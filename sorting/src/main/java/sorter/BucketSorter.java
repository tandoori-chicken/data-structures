package sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adarsh on 12/04/2017.
 */
public class BucketSorter extends Sorter {
    @Override
    public void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            if (max < a)
                max = a;
        }

        int[] countArray = new int[max + 1];
        for (int a : array) {
            countArray[a]++;
        }

        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[index++] = i;
            }
        }
    }

    public void sort(double[] array) //works if doubles lie between 0 and 1
    {
        List<LinkedList<Double>> linkedLists = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            linkedLists.add(new LinkedList<>());
        }

        for (double entry : array) {
            linkedLists.get(((int) (entry * 10))).push(entry);
        }

        int index = 0;
        for (LinkedList<Double> linkedList : linkedLists) {

            Collections.sort(linkedList);
            for (Double entry : linkedList) {
                array[index++] = entry;
            }
        }
    }
}
