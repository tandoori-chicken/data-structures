package doublylinkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by adarsh on 12/14/16.
 */
public class LinkedListTest {

    @Test
    public void testInsert() {
        LinkedList linkedList = new LinkedList();
        assertEquals("", linkedList.toString());
        linkedList.append(3);
        assertEquals("3", linkedList.toString());
        linkedList.append(6);
        assertEquals("36", linkedList.toString());
    }

    @Test
    public void testReverse() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(3);
        linkedList.append(6);
        linkedList.append(9);

        linkedList.reverse();

        assertEquals("963", linkedList.toString());
    }

    @Test
    public void testConvertTreeToCDLL() {
        BinaryTree.Node root = new BinaryTree.Node(10);
        BinaryTree.Node leftRoot = new BinaryTree.Node(12);
        BinaryTree.Node rightRoot = new BinaryTree.Node(15);
        root.left = leftRoot;
        root.right = rightRoot;

        leftRoot.left = new BinaryTree.Node(25);
        leftRoot.right = new BinaryTree.Node(30);
        rightRoot.left = new BinaryTree.Node(36);

        LinkedList.Node head = BinaryTree.convertToList(root);
        Assert.assertEquals("251230103615", CircularLinkedList.toString(head));
    }

    @Test
    public void testAppendCDLL() {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        assertEquals("", circularLinkedList.toString());
        circularLinkedList.append(3);
        assertEquals("3", circularLinkedList.toString());
        circularLinkedList.append(6);
        assertEquals("63", circularLinkedList.toString());
        circularLinkedList.append(9);
        assertEquals("963", circularLinkedList.toString());
        assertEquals("963", CircularLinkedList.toString(circularLinkedList.head));
    }

    @Test
    public void testMergeCDLL() {
        LinkedList.Node head1 = constructCDLL(3, 6);
        LinkedList.Node head2 = constructCDLL(2, 4);

        Assert.assertEquals("2436", CircularLinkedList.toString(CircularLinkedList.merge(head2, head1)));

        Assert.assertEquals("36", CircularLinkedList.toString(CircularLinkedList.merge(constructCDLL(3, 6), null)));
        Assert.assertEquals("36", CircularLinkedList.toString(CircularLinkedList.merge(null, constructCDLL(3, 6))));

        Assert.assertEquals("", CircularLinkedList.toString(CircularLinkedList.merge(null, null)));


    }

    @Test
    public void testQuickSortDLL() {
        LinkedList ll = constructDLL(3, 7, 8, 5, 2, 1, 9, 5, 4);

        ll.quickSort();
        Assert.assertEquals("123455789", ll.toString());

    }

    private LinkedList constructDLL(Integer... data) {

        if (data.length == 0)
            return new LinkedList();

        List<Integer> dataList = Arrays.asList(data);

        LinkedList linkedList = new LinkedList();

        dataList.stream().forEach(linkedList::append);


        return linkedList;
    }

    private LinkedList.Node constructCDLL(Integer... data) {

        if (data.length == 0)
            return null;

        List<Integer> dataList = Arrays.asList(data);

        Collections.reverse(dataList);

        CircularLinkedList circularLinkedList = new CircularLinkedList();

        dataList.stream().forEach(circularLinkedList::append);


        return circularLinkedList.head;
    }

    @Test
    public void testQuickSortArray() {
        int[] array = new int[]{3, 7, 8, 5, 2, 1, 9, 5, 4};

        print(array);

        quickSort(array);
//        partition(array, 0, array.length - 1);

        print(array);
    }

    private void print(int[] array) {
        Arrays.stream(array).boxed().forEach(System.out::print);
        System.out.println();
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition - 1);
            quickSort(array, partition + 1, high);
        }
    }

    private void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low;

        for (int j = low; j <= high - 1; j++) {
            if (array[j] <= pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }

        int temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        return i;

    }


}
