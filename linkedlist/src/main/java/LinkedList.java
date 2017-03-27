import java.util.HashSet;
import java.util.Objects;

/**
 * Created by adarsh on 13/12/2016.
 */
public class LinkedList {
    public Node head;

    public LinkedList() {
    }

    public LinkedList(Node head) {
        this.head = head;
    }

    public void removeLoop() {
        removeLoop(this.head);
    }

    private void removeLoop(Node head) {
        Node fastNode = head;
        Node slowNode = head;
        while (fastNode.next != null && fastNode.next.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode.equals(slowNode))
                break;
        }
        Node curr = head;
        while (!curr.equals(slowNode)) {
            curr = curr.next;
            slowNode = slowNode.next;
        }
        while (!fastNode.next.equals(slowNode)) {
            fastNode = fastNode.next;
        }
        fastNode.next = null;
    }

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

        Node temp = head.next;

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
        if (length < 0)
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
        Node oldTail = getNodeAt(newHead, len - k - 1);
        oldTail.next = oldHead;
        newTail.next = null;
        return newHead;
    }

    public void removeNode(Node node) {
        Node curr = node;

        if (curr.next == null)
            throw new IllegalArgumentException("Cannot delete last node in this method");

        while (curr.next.next != null) {
            curr.data = curr.next.data;
            curr = curr.next;
        }
        curr.data = curr.next.data;
        curr.next = null;
    }

    public void removeDuplicatesHashing() {
        HashSet<Integer> set = new HashSet<>();
        Node curr = this.head;
        set.add(curr.data);
        while (curr.next != null) {
            if (set.contains(curr.next.data)) {
                curr.next = curr.next.next;
            } else {
                set.add(curr.next.data);
                curr = curr.next;
            }
        }

    }

    public void removeDuplicatesNoHash() {
        Node curr1 = this.head;
        while (curr1 != null) {
            Node curr2 = curr1;
            while (curr2.next != null) {
                if (curr2.next.data == curr1.data) {
                    curr2.next = curr2.next.next;
                }
                else
                {
                    curr2=curr2.next;
                }
            }
            curr1=curr1.next;
        }
    }

    public Node retrieveKthToLast(int k)
    {
        return retrieveKthToLast(this.head,k).node;
    }

    public KthNodeDTO retrieveKthToLast(Node node, int k)
    {
        if(node==null)
            return new KthNodeDTO(null,0);
        KthNodeDTO dto = retrieveKthToLast(node.next,k);
        if(dto.node!=null)
            return dto;
        if(dto.index==k)
            return new KthNodeDTO(node,dto.index+1);
        dto.index++;
        return dto;
    }

    private static class KthNodeDTO{
        Node node;
        int index;

        public KthNodeDTO(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public Node partitionUnordered(int partitionValue)
    {
        Node headToReturn = new Node(this.head.data);
        return partitionUnordered(this.head.next, headToReturn,headToReturn,partitionValue);
    }

    private Node partitionUnordered(Node node, Node newHead, Node newTail, int partitionValue) {
        while(node!=null)
        {
            if(node.data<partitionValue)
            {
                Node before = new Node(node.data);
                before.next=newHead;
                newHead=before;
            }
            else
            {
                Node after = new Node(node.data);
                newTail.next=after;
                newTail=after;
            }
            node=node.next;
        }
        return newHead;
    }

    public static Node getSumNew(Node headA, Node headB) {
        return getSumNew(headA, headB, 0);
    }

    private static Node getSumNew(Node nodeA, Node nodeB, int carry) {
        if (nodeA == null && nodeB == null)
            return carry == 0 ? null : new Node(carry);

        int nodeAData = nodeA == null ? 0 : nodeA.data;
        int nodeBData = nodeB == null ? 0 : nodeB.data;

        Node nodeANext = nodeA == null ? null : nodeA.next;
        Node nodeBNext = nodeB == null ? null : nodeB.next;

        int sum = nodeAData + nodeBData + carry;
        Node digit = new Node(sum % 10);
        digit.next = getSumNew(nodeANext, nodeBNext, sum / 10);
        return digit;
    }

    public static Node getSumReversed(Node headA, Node headB) {
        int aLength = getLength(headA);
        int bLength = getLength(headB);

        if (aLength == bLength) {
            return getSumReversedHelper(headA, headB);
        }

        Node headAClone = headA;
        Node headBClone = headB;

        if (aLength < bLength) {
            headAClone = doPadding(headAClone, bLength - aLength);
        } else {
            headBClone = doPadding(headBClone, aLength - bLength);
        }

        return getSumReversedHelper(headAClone, headBClone);
    }

    private static Node doPadding(Node head, int padCount) {
        while (padCount-- > 0) {
            Node node = new Node(0);
            node.next = head;
            head = node;
        }
        return head;
    }

    private static Node getSumReversedHelper(Node headA, Node headB) {
        GetSumDTO dto = new GetSumDTO(null, 0);
        getSumReversedHelper(headA, headB, true, dto);
        return dto.resultHead;
    }

    private static void getSumReversedHelper(Node nodeA, Node nodeB, boolean isHead, GetSumDTO dto) {

        if (nodeA.next == null) {
            int sum = nodeA.data + nodeB.data;
            dto.resultHead = new Node(sum % 10);
            dto.carry = sum / 10;
        } else {
            getSumReversedHelper(nodeA.next, nodeB.next, false, dto);
            int sum = nodeA.data + nodeB.data + dto.carry;
            Node newHead = new Node(sum % 10);
            newHead.next = dto.resultHead;
            dto.resultHead = newHead;
            dto.carry = sum / 10;
            if (isHead && dto.carry > 0) {
                newHead = new Node(1);
                newHead.next = dto.resultHead;
                dto.resultHead = newHead;
            }
        }
    }

    private static class GetSumDTO {
        Node resultHead;
        int carry;

        public GetSumDTO(Node resultHead, int carry) {
            this.resultHead = resultHead;
            this.carry = carry;
        }
    }

    public boolean isPalindrome() {
        if (this.head == null || this.head.next == null)
            return true;

        PalindromeDTO dto = new PalindromeDTO();
        isPalindrome(this.head, dto);
        return dto.isPalindrome;
    }

    private static void isPalindrome(Node node, PalindromeDTO dto) { //Checks palindrome in O(n) time and O(1) space


        dto.curIndex++;
        if (node.next == null) {
            dto.node = node;
            return;
        }
        int myIndex = dto.curIndex;
        isPalindrome(node.next, dto);

        if (myIndex == ((int) Math.ceil(dto.curIndex / 2.0))) //if total node is 7, returns 4. If total node is 6, returns 3
        {
            if ((dto.curIndex & 1) == 0) {
                if (node.data == node.next.data) {
                    dto.isPalindrome = true;
                    dto.node = node.next.next;
                }
            } else {
                dto.isPalindrome = true;
                dto.node = node.next;
            }
        } else {
            if (dto.isPalindrome) {
                if (node.data == dto.node.data)
                    dto.node = dto.node.next;
                else
                    dto.isPalindrome = false;
            }
        }

    }

    private static class PalindromeDTO {
        boolean isPalindrome = false;
        int curIndex;
        Node node = null;
    }

    public Node nodeAt(int k) {
        LinkedList.Node curr = this.head;
        while (curr != null && k-- > 1) {
            curr = curr.next;
        }
        return curr;
    }

    public static Node getIntersection(Node nodeA, Node nodeB) {
        if (nodeA.next == null && nodeB.next == null) {
            if (Objects.equals(nodeA, nodeB))
                return nodeA;
            return null;
        }
        if (nodeA.next == null) {
            while (nodeB.next != null)
                nodeB = nodeB.next;
            return getIntersection(nodeA, nodeB);
        }
        if (nodeB.next == null) {
            while (nodeA.next != null)
                nodeA = nodeA.next;
            return getIntersection(nodeA, nodeB);
        }
        Node intersection = getIntersection(nodeA.next, nodeB.next);
        if (Objects.equals(nodeA, nodeB))
            return nodeA;
        return intersection;

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


}