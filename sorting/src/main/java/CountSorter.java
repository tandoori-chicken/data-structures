/**
 * Created by adarsh on 12/04/2017.
 */
public class CountSorter extends Sorter {
    @Override
    public void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : array) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }

        int[] countArray = new int[max - min + 1];
        for (int a : array) {
            countArray[a - min]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
//array 12, 35, 23, 15, 17, 17
//        System.out.println(Arrays.toString(countArray)); //min 12 max 35
        int[] auxiliaryArray = new int[array.length];
       for(int i=array.length-1;i>=0;i--)
       {
           auxiliaryArray[countArray[array[i]-min]-1]=array[i];
           countArray[array[i]-min]--;
       }


        System.arraycopy(auxiliaryArray,0,array,0,array.length);


    }
}
