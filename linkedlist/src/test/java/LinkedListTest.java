import org.junit.Assert;
import org.junit.Test;

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
    public void testRotate()
    {
        LinkedList linkedList = new LinkedList();
        linkedList.append(10);
        linkedList.append(20);
        linkedList.append(30);
        linkedList.append(40);
        linkedList.append(50);
        linkedList.append(60);

        LinkedList.Node newHead = LinkedList.rotate(linkedList.head,4);
        Assert.assertEquals("506010203040",LinkedList.print(newHead));

    }



}