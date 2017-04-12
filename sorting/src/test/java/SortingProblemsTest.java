import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adarsh on 11/04/2017.
 */
public class SortingProblemsTest {

    /**
     * Given 2 sorted arrays A and B, merge both assuming A has big enough buffer at end to hold B
     */
    @Test
    public void testMergeSortedArrays() {
        String input = "1,5,9,10;2,4,6";
        String targetOutput = "1,2,4,5,6,9,10";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));

        input = "2,4,6;1,5,9,10";
        targetOutput = "1,2,4,5,6,9,10";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));

        input = "1,2,3;7,8,9";
        targetOutput = "1,2,3,7,8,9";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));

        input = "7,8,9;1,2,3";
        targetOutput = "1,2,3,7,8,9";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));

        input = "1,3,5,7;2,4,6";
        targetOutput = "1,2,3,4,5,6,7";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));

        input = "2,4,6;1,3,5,7";
        targetOutput = "1,2,3,4,5,6,7";
        Assert.assertEquals(targetOutput, TestUtils.stringify(mergeArrays(input)));
    }

    private Integer[] mergeArrays(String input) {
        String[] inputs = input.split(";");
        List<Integer> aList = Arrays.stream(inputs[0].split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(inputs[1].split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        Integer[] A = new Integer[aList.size() + bList.size()];
        Arrays.fill(A, Integer.MAX_VALUE);
        A = aList.toArray(A);

        Integer[] B = new Integer[bList.size()];
        B = bList.toArray(B);

        mergeArrays(A, B);
        return A;

    }

    private void mergeArrays(Integer[] A, Integer[] B) {
        int aIndex = A.length - B.length - 1;
        int bIndex = B.length - 1;
        int index = A.length - 1;
        while (aIndex >= 0 && bIndex >= 0) {
            if (A[aIndex] < B[bIndex]) {
                A[index--] = B[bIndex--];
            } else {
                A[index--] = A[aIndex--];
            }
        }

        //Below commented code not needed. If aIndex>=0, this means all of B has already been placed correctly. So no need to merge further

//        while (aIndex>=0)
//        {
//            A[index--]=A[aIndex--];
//        }

        while (bIndex >= 0) {
            A[index--] = B[bIndex--];
        }
    }


    /**
     * Sort a list of strings so that anagrams are next to one another
     */

    @Test
    public void testSortStringAnagrams() {
        List<String> testStrings = Arrays.asList("as", "by", "sa", "yb", "tx");

        Collections.sort(testStrings, (s1, s2) -> {
            String a = sortChars(s1);
            String b = sortChars(s2);
            return a.compareTo(b);
        });

        String targetOutput = "as;sa;by;yb;tx";
        String output = StringUtils.join(testStrings, ';');
        Assert.assertEquals(targetOutput, output);


    }


    private String sortChars(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Search for a key in a rotated sorted array
     */

    @Test
    public void testSearchRotatedArray() {

        int[] array = new int[]{1, 3, 4, 5, 7, 10, 14, 15, 16, 19, 20, 25};
        int[] rotatedArray = rotate(array, 5);
        int[] targetRotatedArray = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};

        Assert.assertEquals(TestUtils.stringify(targetRotatedArray), TestUtils.stringify(rotatedArray));

        Assert.assertEquals(3, routineSearch(array, 0, array.length - 1, 5));
        Assert.assertEquals(-1, routineSearch(array, 0, array.length - 1, 6));


        for (int i = 0; i < array.length; i++) {
            Assert.assertEquals( i, findPivot(rotate(array, i)));
        }

        for(int i=0;i<array.length;i++)
        {
            Assert.assertEquals(""+i,(3+i)%array.length, rotatedSearch(rotate(array, i), 5));
            Assert.assertEquals(-1, rotatedSearch(rotate(array, i), 6));
        }

    }

    /**
     * Calculates rotational offset using binary search
     */
    private int findPivot(int[] array) {
        if (array[0] <= array[array.length - 1])
            return 0;
        return findPivot(array, 0, array.length - 1);
    }

    private int findPivot(int[] array, int low, int high) {

        if (low <= high) {
            if ((high - low) == 1 && array[high] < array[low])
                return high;
            if (high < array.length - 1 && array[high + 1] < array[high])
                return high + 1;
            if (low > 0 && array[low - 1] > array[low])
                return low;

            int mid = (low + high) / 2;
            if (array[mid] <= array[low]) //pivot is at mid or to the left
            {
                return findPivot(array, low, mid);
            } else if (array[mid] >= array[high]) //pivot is to the right
            {
                return findPivot(array, mid + 1, high);
            }
        }
        return 0;
    }

    private int rotatedSearch(int[] array, int key) {
        int pivotIndex = findPivot(array);
        if (pivotIndex == 0)
            return routineSearch(array, 0, array.length - 1, key);

        if(key<array[pivotIndex]||key>array[pivotIndex-1]) //key is less than smallest or greater than largest
            return -1;

        if(key>=array[0]&&key<=array[pivotIndex-1])
            return routineSearch(array,0,pivotIndex-1,key);
        return routineSearch(array,pivotIndex,array.length-1,key);

    }


    private int[] rotate(int[] array, int rotateOffset) {
        int[] rotatedArray = new int[array.length];
        System.arraycopy(array, array.length - rotateOffset, rotatedArray, 0, rotateOffset);
        System.arraycopy(array, 0, rotatedArray, rotateOffset, array.length - rotateOffset);
        return rotatedArray;
    }

    private int routineSearch(int[] array, int low, int high, int key) //does binary search
    {
        if(key<array[low]||key>array[high]) //key is less than smallest or greater than largest
            return -1;

        if (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == key)
                return mid;
            if (key < array[mid])
                return routineSearch(array, low, mid - 1, key);
            if (key > array[mid])
                return routineSearch(array, mid + 1, high, key);
        }
        return -1;
    }

    /**
     * You are given an array-like data structure Listy which lacks a size method.
     * It does, however, have an elementAt(i) method that returns the element at index i in 0(1) time.
     * If i is beyond the bounds of the data structure, it returns -1. (For this reason, the data structure only supports positive integers.)
     * Given a Listy which contains sorted, positive integers, find the index at which an element x occurs.
     * If x occurs multiple times, you may return any index.
     */
    @Test
    public void testBlackBoxSearch()
    {
Listy listy = new Listy(4,5,5,5,5,5,5,5,5,6,7,8,9,10,56,208,415);

        Assert.assertEquals(9,getListyIndex(listy,6));
        Assert.assertEquals(0,getListyIndex(listy,4));
        Assert.assertEquals(16,getListyIndex(listy,415));
        Assert.assertEquals(-1,getListyIndex(listy,405));
        int fiveIndex = getListyIndex(listy,5);
        Assert.assertTrue(fiveIndex>=1&&fiveIndex<=8);
    }

    private int getListyIndex(Listy listy, int key) {
        int low=0,high=Integer.MAX_VALUE;
        return getListyIndex(listy,low,high,key);
    }

    private int getListyIndex(Listy listy, int low, int high, int key) {

        if(low<=high)
        {
            int mid = (low+high)/2;
            if(listy.elementAt(mid)==key)
                return mid;
            if(listy.elementAt(mid)==-1||key<listy.elementAt(mid))
            {
                return getListyIndex(listy,low,mid-1,key);
            }
            else
            {
                return getListyIndex(listy,mid+1,high,key);
            }
        }
        return -1;
    }


}
