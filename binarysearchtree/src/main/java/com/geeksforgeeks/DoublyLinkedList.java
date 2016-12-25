package com.geeksforgeeks;

/**
 * Created by adarsh on 25/12/2016.
 */
public class DoublyLinkedList<T extends Comparable<T>> {
    Node<T> head;

    public DoublyLinkedList(Node<T> head) {
        this.head = head;
    }

    public DoublyLinkedList() {
    }

    public Node<T> append(T dataToInsert) {
        if (head == null) {
            head = new Node<>(dataToInsert);
            return head;
        }
        Node<T> nodeToAppend = new Node<>(dataToInsert);
        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = nodeToAppend;
        nodeToAppend.prev = curr;
        return nodeToAppend;
    }

    public BinarySearchTree<T> mapToBST() {
        BinarySearchTree.Node<T> root = mapToBST(head);
        return new BinarySearchTree<>(root);
    }

    private BinarySearchTree.Node<T> mapToBST(Node<T> head) {
        if (head == null)
            return null;

        BinarySearchTree.Node<T> root = new BinarySearchTree.Node<>(head.data);
        root.left = mapToBST(head.prev);
        root.right = mapToBST(head.next);

        return root;
    }

    public void constructBST() {
        DLLToBSTDTO<T> dto = new DLLToBSTDTO<>();
        dto.head = head;

        constructBST(dto, countNodes(head));
        head = dto.root;
    }

    private void constructBST(DLLToBSTDTO<T> dto, int n) {
        if (n <= 0) {
            dto.root = null;
            return;
        }

        constructBST(dto, n / 2);

        Node<T> root = dto.head;
        root.prev = dto.root;
        dto.head = dto.head.next; //moves n nodes after n BST nodes are created, like a curr pointer
        constructBST(dto, n - (n / 2) - 1);
        root.next = dto.root;
        dto.root = root;
    }

    private int countNodes(Node<T> head) {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    @Override
    public String toString() {
        if (head == null)
            return "";
        final StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            curr = curr.next;
        }
        return sb.toString();
    }

    static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
