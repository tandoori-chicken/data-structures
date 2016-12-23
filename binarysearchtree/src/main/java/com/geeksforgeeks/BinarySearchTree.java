package com.geeksforgeeks;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

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

        return equalsRecurring(root, that.root);
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

    public void delete(T dataToDelete) {
        root = deleteRecurring(root, dataToDelete);
    }

    private Node<T> deleteRecurring(Node<T> root, T dataToDelete) {
        if (root == null)
            return null;
        if (root.data.equals(dataToDelete)) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                //means root.right!=null
                root = root.right;
                return root;
            }
            if (root.right == null) {
                //means root.left!=null
                root = root.left;
                return root;
            }
            /**
             * Means both children are present.
             * Find inorder successor and replace root with successor
             */

            T successorData = getMinValue(root.right);
            root.data = successorData;
            root.right = deleteRecurring(root.right, successorData);
        }
        if (root.data.compareTo(dataToDelete) > 0) {
            //means dataTo delete will be in left tree
            root.left = deleteRecurring(root.left, dataToDelete);
        } else {
            root.right = deleteRecurring(root.right, dataToDelete);
        }
        return root;
    }

    private T getMinValue(Node<T> root) {
        Node<T> curr = root;
        T minValue = null;
        while (curr != null) {
            minValue = curr.data;
            curr = curr.left;
        }
        return minValue;
    }

    public MutablePair<Node<T>, Node<T>> findInorderPreSucc(T dataToSearch) {
        return findInorderPreSucc(dataToSearch, root);
    }

    /**
     * 1. If root is NULL then return
     * 2. if key is found then
     * a. If its left subtree is not null
     * Then predecessor will be the right most
     * child of left subtree or left child itself.
     * b. If its right subtree is not null
     * The successor will be the left most child
     * of right subtree or right child itself.
     * return
     * 3. If key is smaller then root node
     * set the successor as root
     * search recursively into left subtree
     * else
     * set the predecessor as root
     * search recursively into right subtree
     */
    private MutablePair<Node<T>, Node<T>> findInorderPreSucc(T dataToSearch, Node<T> root) {
        Node<T> predecessor = null;
        Node<T> successor = null;


        if (root == null)
            return new MutablePair<>(null, null);


        if (root.data.equals(dataToSearch)) {

            if (root.left != null) {
                Node<T> curr = root.left;
                Node<T> prev = null;
                while (curr != null) {
                    prev = curr; //keeps track of the result
                    curr = curr.right;
                }
                predecessor = prev;
            }
            if (root.right != null) {
                Node<T> curr = root.right;
                Node<T> prev = null;
                while (curr != null) {
                    prev = curr;
                    curr = curr.left;
                }
                successor = prev;
            }
            return new MutablePair<>(predecessor,successor);
        }
        if (root.data.compareTo(dataToSearch) > 0) {
            //searching in left subtree, set successor as root
//            successor = root;
            Pair<Node<T>, Node<T>> leftTreeResult = findInorderPreSucc(dataToSearch, root.left);
            predecessor = leftTreeResult.getLeft();
            successor = leftTreeResult.getRight();
            if (successor == null)
                successor = root;

        } else {
//            predecessor = root;
            Pair<Node<T>, Node<T>> rightTreeResult = findInorderPreSucc(dataToSearch, root.right);
            predecessor = rightTreeResult.getLeft();
            successor = rightTreeResult.getRight();
            if (predecessor == null)
                predecessor = root;

        }
        return new MutablePair<>(predecessor, successor);
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
