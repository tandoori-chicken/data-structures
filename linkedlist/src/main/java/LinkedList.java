/**
 * Created by adarsh on 13/12/2016.
 */
public class LinkedList {
    public Node head;

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public int countNodesIterative() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }


    public int countNodesFrom(Node node) {
        if (node == null)
            return 0;

        return 1 + countNodesFrom(node.next);

    }

    public int countNodeRecursive() {
        return countNodesFrom(head);
    }

    public Node insertFirst(int dataToInsert) {
        Node node = new Node(dataToInsert);
        if (head == null) {
            head = node;
            return head;
        }
        node.next = head;
        head = node;

        return node;
    }

    public Node insertAfterNode(Node previousNode, int dataToInsert) {
        Node insertedNode = new Node(dataToInsert);
        if (previousNode == null) {
            head = insertedNode;
            return insertedNode;
        }

        insertedNode.next = previousNode.next;
        previousNode.next = insertedNode;

        return insertedNode;
    }

    public Node append(int dataToInsert) {
        Node insertedNode = new Node(dataToInsert);
        if (head == null) {
            head = insertedNode;
            return insertedNode;
        }

        //Below if block is useless
        if (head.next == null) {
            head.next = insertedNode;
            return insertedNode;
        }

        Node temp  = head.next;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = insertedNode;
        return insertedNode;
    }

    public void deleteNode(int dataToDelete) {
        if (head == null)
            return;

        if (head.data == dataToDelete) {
            head = head.next;
            return;
        }

        Node prev = null;
        Node curr = head;
        while (curr != null && curr.data != dataToDelete) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) //not found
            return;

        if (prev != null) {
            prev.next = curr.next;
        }
        return;


    }

    public void deleteNodeByIndex(int index) {
        if (head == null)
            return;

        if (index == 0) {
            head = head.next;
            return;
        }
    }

    public void reverse() {
        if (head == null)
            return;

        Node curr = head;
        Node prev = null;
        Node next = null;

        do {

            next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;

        } while (next != null);

        head = prev;

    }

    public void reverseRecursive() {

        reverseRecursiveFrom(head, null);

    }

    private void reverseRecursiveFrom(Node curr, Node prev) {

        if (curr == null) {
            head = prev;
            return;
        }
        Node next = curr.next;
        curr.next = prev;
        reverseRecursiveFrom(next, curr);

    }

    public static String print(Node head) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (head == null)
            return stringBuilder.toString();

        Node temp = head;

        while (temp != null) {
            stringBuilder.append(temp.data);
            temp = temp.next;
        }

        return stringBuilder.toString();
    }

    public static Node merge(Node headA, Node headB) {
        if (headA == null)
            return headB;
        if (headB == null)
            return headA;

        Node p = new Node(-1);
        Node tempHead = p;

        while (headA != null && headB != null) {
            if (headA.data <= headB.data) {
                p.next = headA;
                headA = headA.next;
            } else {
                p.next = headB;
                headB = headB.next;
            }
            p = p.next;
        }
        if (headA == null)
            p.next = headB;
        if (headB == null)
            p.next = headA;

        return tempHead.next;
    }

    public static Node getNodeAt(Node head, int length) {
        if(length<0)
            return null;
        if (length == 0)
            return head;

        Node temp = head;
        while (--length >= 0 && temp != null) {
            temp = temp.next;
        }
        return temp;
    }

    public static int getLength(Node head) {
        int count = 0;

        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public int getLength() {
        return getLength(head);
    }

    public void mergeSort() {
        head = mergeSort(head);
    }

    public static Node mergeSort(Node head) {
        // System.out.println("Sorting : "+print(head));

        Node oldHead = head;

        if (head == null || head.next == null) {
            return head;
        }
        int len = getLength(head);
        int mid = (len / 2) - 1;
        Node midNode = getNodeAt(head, mid);
        Node newHead = midNode.next;
        midNode.next = null;

        return merge(mergeSort(oldHead), mergeSort(newHead));
    }


    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder("");
        if (head == null)
            return stringBuilder.toString();

        Node temp = head;

        while (temp != null) {
            stringBuilder.append(temp.data);
            temp = temp.next;
        }

        return stringBuilder.toString();
    }

    public static Node getSum(Node headA, Node headB) {
        return getSum(headA, headB, 0);
    }

    public static Node getSum(Node headA, Node headB, int carry) {
        if (headA == null && headB == null) {
            if (carry != 0) {
                return new Node(carry);
            } else {
                return null;
            }
        }
        if (headA == null) {
            int sum = headB.data + carry;
            int digit = sum % 10;
            int newCarry = sum / 10;
            Node subSolutionHead = getSum(null, headB.next, newCarry);
            Node myHead = new Node(digit);
            myHead.next = subSolutionHead;
            return myHead;
        }
        if (headB == null) {
            int sum = headA.data + carry;
            int digit = sum % 10;
            int newCarry = sum / 10;
            Node subSolutionHead = getSum(null, headA.next, newCarry);
            Node myHead = new Node(digit);
            myHead.next = subSolutionHead;
            return myHead;
        }

        int sum = headA.data + headB.data + carry;
        int digit = sum % 10;
        int newCarry = sum / 10;

        Node subSolutionHead = getSum(headA.next, headB.next, newCarry);

        Node myHead = new Node(digit);
        myHead.next = subSolutionHead;
        return myHead;
    }

    public static Node rotate(Node oldHead, int k) {
        
        int len = getLength(oldHead);

        Node newTail = getNodeAt(oldHead, k - 1);
        Node newHead = newTail.next;
        Node oldTail = getNodeAt(newHead,len-k-1);
        oldTail.next=oldHead;
        newTail.next=null;
        return newHead;
    }
}