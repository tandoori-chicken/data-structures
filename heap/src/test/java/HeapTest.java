import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by adarsh on 26/12/2016.
 */
public class HeapTest {

    @Test
    public void testGetChildren() {
        assertEquals(-1, HeapUtil.getLeftChildIndex(0, 0));
        assertEquals(-1, HeapUtil.getLeftChildIndex(0, 1));

        assertEquals(1, HeapUtil.getLeftChildIndex(0, 2));
        assertEquals(1, HeapUtil.getLeftChildIndex(0, 3));

        assertEquals(1, HeapUtil.getLeftChildIndex(0, 4));
        assertEquals(3, HeapUtil.getLeftChildIndex(1, 4));
        assertEquals(-1, HeapUtil.getLeftChildIndex(2, 4));
        assertEquals(-1, HeapUtil.getLeftChildIndex(3, 4));

        assertEquals(-1, HeapUtil.getRightChildIndex(0, 0));
        assertEquals(-1, HeapUtil.getRightChildIndex(0, 1));

        assertEquals(-1, HeapUtil.getRightChildIndex(0, 2));
        assertEquals(2, HeapUtil.getRightChildIndex(0, 3));

        assertEquals(2, HeapUtil.getRightChildIndex(0, 4));
        assertEquals(-1, HeapUtil.getRightChildIndex(1, 4));
        assertEquals(-1, HeapUtil.getRightChildIndex(2, 4));
        assertEquals(-1, HeapUtil.getRightChildIndex(3, 4));

    }

    @Test
    public void testLeaf() {
        assertTrue(HeapUtil.isLeaf(0, 1));
        assertFalse(HeapUtil.isLeaf(1, 1));

        assertFalse(HeapUtil.isLeaf(0, 2));
        assertTrue(HeapUtil.isLeaf(1, 2));

        assertFalse(HeapUtil.isLeaf(0, 3));
        assertTrue(HeapUtil.isLeaf(1, 3));
        assertTrue(HeapUtil.isLeaf(2, 3));

        assertTrue(HeapUtil.isLeaf(7, 10));
        assertTrue(HeapUtil.isLeaf(8, 10));
        assertTrue(HeapUtil.isLeaf(9, 10));
        assertFalse(HeapUtil.isLeaf(6, 10));
    }

    @Test
    public void testBuildHeap() {
        Integer[] input = new Integer[]{5, 10, 7};

        Heap<Integer> heap = new Heap<>(input);
        assertEquals("1057", heap.toString());

        input = new Integer[]{30, 10, 20, 5, 8};
        heap = new Heap<>(input);
        assertEquals("30102058", heap.toString());


        input = new Integer[]{20, 10, 30, 5, 8};
        heap = new Heap<>(input);
        assertEquals("30102058", heap.toString());

        input = new Integer[]{5, 10, 20, 30, 8};
        heap = new Heap<>(input);
        assertEquals("30102058", heap.toString());

    }


    @Test(expected = UnsupportedOperationException.class)
    public void testDeleteFromHeap() {

        Heap<Integer> heap = new Heap<>(new Integer[]{5, 10, 20, 30, 8});
        int maxElement = heap.removeMaxElement();
        assertEquals(30, maxElement);
        assertEquals("201085", heap.toString());

        maxElement = heap.removeMaxElement();
        assertEquals(20, maxElement);
        assertEquals("1058", heap.toString());

        heap.removeMaxElement();
        assertEquals("85", heap.toString());

        heap.removeMaxElement();
        assertEquals("5", heap.toString());
        heap.removeMaxElement();
        assertTrue(StringUtils.isBlank(heap.toString()));

        heap.removeMaxElement();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToHeapWithException() {
        Heap<Integer> heap = new Heap<>(new Integer[]{5, 10, 20, 30, 8});
        heap.removeMaxElement();
        heap.addElement(50);
        assertEquals("50208510", heap.toString());

        heap.removeMaxElement();
        assertEquals("201085", heap.toString());
        heap.addElement(15);
        assertEquals("20158510", heap.toString());
        heap.addElement(9);
    }

    @Test
    public void testAddToHeap() {
        Heap<Integer> heap = new Heap<>(new Integer[]{5, 10, 20, 30, 8, 15});//30;10,20;5,8,15
        heap.removeMaxElement(); //20;10,15;5,8
        heap.addElement(12);
        assertEquals("2010155812", heap.toString());
    }

    @Test
    public void testIncreaseKey() {
        IntegerHeap heap = new IntegerHeap(5, 10, 20, 30, 8, 15);//30;10,20;5,8,15
        heap.increaseKey(2, 20);
        assertEquals("4010305815", heap.toString());//40;10,30;5,8,15
        heap.increaseKey(5, 2);
        assertEquals("4010305817", heap.toString());//40;10,30;5,8,17
        heap.decreaseKey(2, 23);
        assertEquals("401017587", heap.toString());//40;10,30;5,8,17
    }

    @Test
    public void testSortAlmost() {
        Integer[] inputArr = new Integer[]{18, 14, 17, 8, 4, 12};
        HeapUtil.sortAlmostSortedArray(inputArr, 3);

        String reducedString = Arrays.stream(inputArr).map(Object::toString).reduce((a, b) -> a + "-" + b).get();
        assertEquals("18-17-14-12-8-4", reducedString);
    }


}
