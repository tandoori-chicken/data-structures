import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
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
        int aIndex=A.length-B.length-1;
        int bIndex=B.length-1;
        int index=A.length-1;
        while(aIndex>=0&&bIndex>=0)
        {
            if(A[aIndex]<B[bIndex])
            {
                A[index--]=B[bIndex--];
            }
            else
            {
                A[index--]=A[aIndex--];
            }
        }

        //Below commented code not needed. If aIndex>=0, this means all of B has already been placed correctly. So no need to merge further

//        while (aIndex>=0)
//        {
//            A[index--]=A[aIndex--];
//        }

        while(bIndex>=0)
        {
            A[index--]=B[bIndex--];
        }
    }
}
