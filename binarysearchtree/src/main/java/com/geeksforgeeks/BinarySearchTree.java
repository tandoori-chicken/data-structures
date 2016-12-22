package com.geeksforgeeks;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by adarsh on 22/12/2016.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    public Node<T> root;

    public void insert(T dataToInsert) {
        root = insertRecursive(root, dataToInsert);
    }

    private Node<T> insertRecursive(Node<T> root, T dataToInsert) {
        if (root == null) {
            root = new Node<T>(dataToInsert);
            return root;
        } else if (root.data.compareTo(dataToInsert) > 0) {
            root.left = insertRecursive(root.left, dataToInsert);
        } else {
            root.right = insertRecursive(root.right, dataToInsert);
        }
        return root;
    }

    public String traverseInorder() {
        return traverseInorder(root);
    }

    private String traversePreorder(Node<T> root) {
        if (root == null)
            return "";
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(root.data);
        stringBuilder.append(traversePreorder(root.left));
        stringBuilder.append(traversePreorder(root.right));

        return stringBuilder.toString();
    }

    public String traversePreorder() {
        return traversePreorder(root);
    }

    private String traverseInorder(Node<T> root) {
        if (root == null)
            return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(traverseInorder(root.left));
        stringBuilder.append(root.data);
        stringBuilder.append(traverseInorder(root.right));

        return stringBuilder.toString();
    }

    public Node<T> searchNode(T searchData) {
        return searchNode(root, searchData);
    }

    private Node<T> searchNode(Node<T> root, T searchData) {
        if (root == null)
            return null;

        if (root.data.equals(searchData))
            return root;

        if (root.data.compareTo(searchData) > 0) {
            return searchNode(root.left, searchData);
        } else {
            return searchNode(root.right, searchData);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BinarySearchTree<T> that = (BinarySearchTree<T>) o;

        return equalsRecurring(root,that.root);
    }

    private boolean equalsRecurring(Node<T> root1, Node<T> root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        return root1.data.equals(root2.data) && equalsRecurring(root1.left, root2.left) && equalsRecurring(root1.right, root2.right);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(root.data)
                .toHashCode();
    }

    public static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }


}
