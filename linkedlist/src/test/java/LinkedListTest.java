import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by adarsh on 13/12/2016.
 */
public class LinkedListTest {

    @Test
    public void testGetCountEmpty() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(0, linkedList.countNodesIterative());
        Assert.assertEquals(0, linkedList.countNodeRecursive());
    }

    @Test
    public void testGetCountNonEmpty() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertFirst(5);
        linkedList.insertFirst(10);
        Assert.assertEquals(2, linkedList.countNodesIterative());
        Assert.assertEquals(2, linkedList.countNodeRecursive());
    }

    @Test
    public void testToString() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals("", linkedList.convertToString());
        linkedList.insertFirst(5);
        linkedList.insertFirst(10);
        Assert.assertEquals("105", linkedList.convertToString());
    }

    @Test
    public void testInsertAfter() {
        LinkedList linkedList = new LinkedList();
        LinkedList.Node head = linkedList.insertAfterNode(null, 3);
        Assert.assertEquals("3", linkedList.convertToString());
        LinkedList.Node sixNode = linkedList.insertAfterNode(head, 6);
        Assert.assertEquals("36", linkedList.convertToString());
        linkedList.insertAfterNode(sixNode, 9);
        Assert.assertEquals("369", linkedList.convertToString());
        linkedList.insertAfterNode(sixNode, 12);
        Assert.assertEquals("36129", linkedList.convertToString());
    }

    @Test
    public void testInsertEnd() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(3);
        Assert.assertEquals("3", linkedList.convertToString());
        linkedList.append(6);
        Assert.assertEquals("36", linkedList.convertToString());
        linkedList.append(12);
        Assert.assertEquals("3612", linkedList.convertToString());
        linkedList.append(9);
        Assert.assertEquals("36129", linkedList.convertToString());
    }

    @Test
    public void testDeleteWithKey() {
        LinkedList linkedList = new LinkedList();
        linkedList.deleteNode(5);
        Assert.assertEquals("", linkedList.convertToString());
        linkedList.append(3);
        linkedList.deleteNode(5);
        Assert.assertEquals("3", linkedList.convertToString());
        linkedList.deleteNode(3);
        Assert.assertEquals("", linkedList.convertToString());
        linkedList.append(6);
        linkedList.append(9);
        linkedList.deleteNode(6);
        Assert.assertEquals("9", linkedList.convertToString());
        linkedList.append(12);
        linkedList.append(3);
        linkedList.deleteNode(12);
        Assert.assertEquals("93", linkedList.convertToString());
        linkedList.append(6);
        linkedList.deleteNode(6);
        Assert.assertEquals("93", linkedList.convertToString());


    }


    @Test
    public void testReverse() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(3);
        linkedList.reverse();
        Assert.assertEquals("3", linkedList.convertToString());
        linkedList.append(6);
        linkedList.reverse();
        Assert.assertEquals("63", linkedList.convertToString());
        linkedList.append(9);
        linkedList.reverse();
        Assert.assertEquals("936", linkedList.convertToString());
    }

    @Test
    public void testReverseRecursive() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(3);
        linkedList.reverseRecursive();
        Assert.assertEquals("3", linkedList.convertToString());
        linkedList.append(6);
        linkedList.reverseRecursive();
        Assert.assertEquals("63", linkedList.convertToString());
        linkedList.append(9);
        linkedList.reverseRecursive();
        Assert.assertEquals("936", linkedList.convertToString());
    }

    @Test
    public void testMerge() {
        LinkedList linkedList1 = new LinkedList();
        Assert.assertEquals(0, linkedList1.getLength());
        linkedList1.append(5);
        Assert.assertEquals(1, linkedList1.getLength());
        linkedList1.append(10);
        Assert.assertEquals(2, linkedList1.getLength());
        linkedList1.append(15);
        LinkedList.Node head1 = linkedList1.head;
        Assert.assertEquals(5, LinkedList.getNodeAt(head1, 0).data);
        Assert.assertEquals(15, LinkedList.getNodeAt(head1, 2).data);

        LinkedList linkedList2 = new LinkedList();
        linkedList2.append(2);
        linkedList2.append(3);
        linkedList2.append(20);
        LinkedList.Node head2 = linkedList2.head;

        LinkedList.Node newHead = LinkedList.merge(head1, head2);

        Assert.assertEquals("235101520", LinkedList.print(newHead));

    }

    @Test
    public void testMergeSort() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(9);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(2);
        linkedList.append(5);
        linkedList.append(1);
        linkedList.mergeSort();
        Assert.assertEquals("123459", linkedList.convertToString());
    }

    @Test
    public void addLinkedList() {
        LinkedList linkedList1 = new LinkedList();
        linkedList1.append(5);
        linkedList1.append(6);
        linkedList1.append(3);
        LinkedList.Node head1 = linkedList1.head;


        LinkedList linkedList2 = new LinkedList();
        linkedList2.append(8);
        linkedList2.append(4);
        linkedList2.append(2);
        LinkedList.Node head2 = linkedList2.head;

        LinkedList.Node sumHead = LinkedList.getSum(head1, head2);

        Assert.assertEquals("316", LinkedList.print(sumHead));

        linkedList1 = new LinkedList();
        linkedList1.append(7);
        linkedList1.append(5);
        linkedList1.append(9);
        linkedList1.append(4);
        linkedList1.append(6);
        head1 = linkedList1.head;

        linkedList2 = new LinkedList();
        linkedList2.append(8);
        linkedList2.append(4);
        head2 = linkedList2.head;

        sumHead = LinkedList.getSum(head1, head2);

        Assert.assertEquals("50056", LinkedList.print(sumHead));

    }

    @Test
    public void testRotate() {
        LinkedList linkedList = new LinkedList();
        linkedList.append(10);
        linkedList.append(20);
        linkedList.append(30);
        linkedList.append(40);
        linkedList.append(50);
        linkedList.append(60);

        LinkedList.Node newHead = LinkedList.rotate(linkedList.head, 4);
        Assert.assertEquals("506010203040", LinkedList.print(newHead));

    }

    @Test
    public void testDeleteNodeGivenMiddleNode() {
        LinkedList list = new LinkedList();
        IntStream.rangeClosed(1, 5).boxed().forEach(list::append);

        list.removeNode(list.head.next.next); //Delete 3
        assertEquals("1245", list.toString());

        list = new LinkedList();
        IntStream.rangeClosed(1, 5).boxed().forEach(list::append);
        list.removeNode(list.head); //Delete 1
        assertEquals("2345", list.toString());
    }

    @Test
    public void testRemoveDuplicateValues() {
        LinkedList list = buildList(1, 2, 3, 4, 5, 1, 4, 6, 7, 1, 6);
        list.removeDuplicatesHashing();
        assertEquals("1234567", list.toString());

        list = buildList(1, 1, 2, 2, 3, 3, 3, 4);
        list.removeDuplicatesHashing();
        assertEquals("1234", list.toString());

        list = buildList(1, 2, 3, 4, 5, 1, 4, 6, 7, 1, 6);
        list.removeDuplicatesNoHash();
        assertEquals("1234567", list.toString());

        list = buildList(1, 2, 3, 4, 5, 1, 4, 6, 7, 1, 6, 8);
        list.removeDuplicatesNoHash();
        assertEquals("12345678", list.toString());

        list = buildList(1, 1, 2, 2, 3, 3, 3, 4);
        list.removeDuplicatesNoHash();
        assertEquals("1234", list.toString());
    }


    @Test
    public void testKthFromLast()
    {
        LinkedList list = buildList(1,2,3,4,5,6,7,8,9,10);

        LinkedList.Node node = list.retrieveKthToLast(0);
        assertEquals(10,node.data);

        node = list.retrieveKthToLast(3);
        assertEquals(7,node.data);

        node = list.retrieveKthToLast(9);
        assertEquals(1,node.data);
    }

    @Test
    public void testPartitionUnordered()
    {
        LinkedList list = buildList(3,5,8,5,10,2,1);
        LinkedList.Node partitionedListHead = list.partitionUnordered(5);
        LinkedList partitionedList = new LinkedList(partitionedListHead);

        System.out.println(partitionedList);

        partitionedList = new LinkedList(list.partitionUnordered(7));

        System.out.println(partitionedList);

        System.out.println(new LinkedList(list.partitionUnordered(11)));
        System.out.println(new LinkedList(list.partitionUnordered(0)));

    }


    @Test
    public void testSumLinkedLists() {
        LinkedList linkedListA = buildList(7, 1, 7);
        LinkedList linkedListB = buildList(5, 9, 2);

        LinkedList.Node summedHead = LinkedList.getSumNew(linkedListA.head, linkedListB.head);
        LinkedList summedList = new LinkedList(summedHead);
        Assert.assertEquals("2101", summedList.convertToString());

        linkedListA = buildList(7, 1, 6);
        linkedListB = buildList(5, 9, 2);

        summedHead = LinkedList.getSumNew(linkedListA.head, linkedListB.head);
        summedList = new LinkedList(summedHead);
        Assert.assertEquals("219", summedList.convertToString());

        linkedListA = buildList(7, 1, 6);
        linkedListB = buildList(5, 9);

        summedHead = LinkedList.getSumNew(linkedListA.head, linkedListB.head);
        summedList = new LinkedList(summedHead);
        Assert.assertEquals("217", summedList.convertToString());
    }

    private LinkedList buildList(int... data) {
        LinkedList linkedList = new LinkedList();
        Arrays.stream(data).boxed().forEach(linkedList::append);
        return linkedList;
    }

    @Test
    public void testSumLinkedListReversed() {
        LinkedList linkedListA = buildList(7, 1, 7);
        LinkedList linkedListB = buildList(2, 9, 5);

        LinkedList.Node summedHead = LinkedList.getSumReversed(linkedListA.head, linkedListB.head);
        LinkedList summedList = new LinkedList(summedHead);
        Assert.assertEquals("1012", summedList.convertToString());

        linkedListA = buildList(6, 1, 7);
        linkedListB = buildList(2, 9, 5);

        summedHead = LinkedList.getSumReversed(linkedListA.head, linkedListB.head);
        summedList = new LinkedList(summedHead);
        Assert.assertEquals("912", summedList.convertToString());

        linkedListA = buildList(6, 1, 7);
        linkedListB = buildList(5);

        summedHead = LinkedList.getSumReversed(linkedListA.head, linkedListB.head);
        summedList = new LinkedList(summedHead);
        Assert.assertEquals("622", summedList.convertToString());
    }

    @Test
    public void testPalindrome() {

        Assert.assertTrue(buildList(1, 4, 6, 4, 1).isPalindrome());
        Assert.assertFalse(buildList(1, 2, 3, 1).isPalindrome());
        Assert.assertTrue(buildList(1, 2, 2, 1).isPalindrome());
        Assert.assertFalse(buildList(0, 2, 2, 1).isPalindrome());
        Assert.assertFalse(buildList(2, 2, 1).isPalindrome());
        Assert.assertTrue(buildList(2, 2, 2).isPalindrome());
        Assert.assertTrue(buildList(1, 1).isPalindrome());
        Assert.assertFalse(buildList(1, 2).isPalindrome());
    }


    @Test
    public void testIntersection() {
        LinkedList l1 = buildList(1, 2, 3, 4, 5, 6, 7);

        LinkedList l2 = buildList(20, 19);

        l2.nodeAt(2).next = l1.nodeAt(5);
        Assert.assertEquals("2019567", l2.convertToString());

        Assert.assertNotNull(LinkedList.getIntersection(l1.head, l2.head));

        Assert.assertNull(LinkedList.getIntersection(
                buildList(1, 2, 3, 4, 5, 6).head
                , buildList(4, 5, 6).head
        ));

        l1 = buildList(1, 2, 3, 4, 5, 6, 7);
        l2.head = l1.nodeAt(7);
        Assert.assertNotNull(LinkedList.getIntersection(l1.head, l2.head));

    }

    @Test
    public void testRemoveLoop()
    {
        LinkedList list = buildList(1,2,3,4,5,6,7,8,9,10);

        list.nodeAt(10).next=list.nodeAt(6);
        list.removeLoop();
        Assert.assertEquals("12345678910",list.convertToString());

        list.nodeAt(10).next=list.nodeAt(1);
        list.removeLoop();
        Assert.assertEquals("12345678910",list.convertToString());

        list.nodeAt(10).next=list.nodeAt(10);
        list.removeLoop();
        Assert.assertEquals("12345678910",list.convertToString());
    }

}