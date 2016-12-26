package doublylinkedlist;

/**
 * Created by adarsh on 12/14/16.
 */
public class LinkedList {

    Node head;


    public static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            prev = temp;
            temp = temp.next;
        }

        prev.next = newNode;
        newNode.prev = prev;
    }

    public void reverse() {

        if (head == null || head.next == null)
            return;

        Node prev = null;
        Node curr = head;
        Node next = null;

        while (curr != null) {
            next = curr.next;
            curr.prev = next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
        }
        return sb.toString();
    }


    public Node retrieveLastNode() {
        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head;
        Node prev = null;

        while (temp != null) {
            prev = temp;
            temp = temp.next;
        }
        return prev;

    }

    private int getIndex(Node node) {
        int count = 0;
        Node curr = head;
        while (curr != node) {
            curr = curr.next;
            count++;
        }

        return count;
    }

    private void quickSort(Node low, Node high) {



        if (high==null||low == high || high.next == low) {
            return;
        }
        Node partitionNode = partition(low, high);
            quickSort(low, partitionNode.prev);
            quickSort(partitionNode.next, high);

    }

    private Node partition(Node low, Node high) {
        Node pivotNode = high;
        Node i = low;

        for (Node j = low; j != high; j = j.next) {
            if (j.data <= pivotNode.data) {
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
                i = i.next;
            }
        }
        int temp = i.data;
        i.data = high.data;
        high.data = temp;
        return i;
    }

    public void quickSort() {
        quickSort(head, retrieveLastNode());
    }

    public static String toString(Node head) {
        final StringBuilder sb = new StringBuilder("");
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
        }
        return sb.toString();
    }
}
