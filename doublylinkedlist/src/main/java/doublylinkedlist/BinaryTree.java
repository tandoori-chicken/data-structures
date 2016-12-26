package doublylinkedlist;

/**
 * Created by adarsh on 12/14/16.
 */
public class BinaryTree {
    public Node root;

    static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static LinkedList.Node convertToList(Node treeHead) {
        if (treeHead == null) {
            return null;
        }

        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.append(treeHead.data);

        CircularLinkedList.Node rootHead = circularLinkedList.head;
        CircularLinkedList.Node leftListHead = convertToList(treeHead.left);
        CircularLinkedList.Node rightSideHead = convertToList(treeHead.right);

        leftListHead = CircularLinkedList.merge(leftListHead, rootHead);
        leftListHead = CircularLinkedList.merge(leftListHead, rightSideHead);

        return leftListHead;
    }


}
