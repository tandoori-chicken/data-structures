package doublylinkedlist;

/**
 * Created by adarsh on 12/14/16.
 */
public class CircularLinkedList extends LinkedList {


    @Override
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = newNode;
            head.prev = newNode;
            return;
        }

        Node oldTail = head.prev;

        oldTail.next = newNode;
        newNode.prev = oldTail;

        newNode.next = head;
        head.prev = newNode;

        head = newNode;

    }

    public static Node merge(Node parentHead, Node toAppendHead) {

        if (parentHead == null && toAppendHead == null)
            return null;

        if (parentHead == null)
            return toAppendHead;

        if (toAppendHead == null) {
            return parentHead;
        }

        Node parentTail = parentHead.prev;
        Node toAppendTail = toAppendHead.prev;


        parentTail.next = toAppendHead;
        toAppendHead.prev = parentTail;


        toAppendTail.next = parentHead;
        parentHead.prev = toAppendTail;

        return parentHead;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        if (head == null)
            return sb.toString();


        Node curr = head;
        do {
            sb.append(curr.data);
            curr = curr.next;
        } while (curr.data != head.data);


        return sb.toString();
    }

    public static String toString(Node head) {
        final StringBuilder sb = new StringBuilder("");
        if (head == null)
            return sb.toString();


        Node curr = head;
        do {
            sb.append(curr.data);
            curr = curr.next;
        } while (curr.data != head.data);


        return sb.toString();
    }
}
