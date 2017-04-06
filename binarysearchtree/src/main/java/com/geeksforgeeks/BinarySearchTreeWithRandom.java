package com.geeksforgeeks;

import java.util.Random;

/**
 * Created by adarsh on 29/03/2017.
 */
public class BinarySearchTreeWithRandom<T extends Comparable<T>> extends BinarySearchTree<T> {

    public BinarySearchTreeWithRandom(NodeWithRandom<T> root) {
        super(root);
    }

    public BinarySearchTreeWithRandom() {
    }

    @Override
    public void insert(T dataToInsert) {
        root = insertRecursive(getRoot(), dataToInsert);
    }

    private NodeWithRandom<T> insertRecursive(NodeWithRandom<T> root, T dataToInsert) {
        if (root == null) {
            root = new NodeWithRandom<>(dataToInsert);
            return root;
        } else if (root.data.compareTo(dataToInsert) > 0) {
            root.leftDescendantsCount++;
            root.left = insertRecursive(getLeft(root), dataToInsert);
        } else {
            root.rightDescendantsCount++;
            root.right = insertRecursive(getRight(root), dataToInsert);
        }
        return root;
    }


    private void markDeletion(NodeWithRandom<T> root, T dataToInsert) {
        if (root == null)
            return;
        if (dataToInsert.compareTo(root.data) < 0) {
            root.leftDescendantsCount--;
            markDeletion(getLeft(root), dataToInsert);
        } else {
            root.rightDescendantsCount--;
            markDeletion(getRight(root), dataToInsert);
        }
    }

    private NodeWithRandom<T> getLeft(Node<T> root) {
        return (NodeWithRandom<T>) root.left;
    }

    private NodeWithRandom<T> getRight(Node<T> root) {
        return (NodeWithRandom<T>) root.right;
    }


    NodeWithRandom<T> getRoot() {
        return (NodeWithRandom<T>) root;
    }

    @Override
    public void delete(T dataToDelete) {
        markDeletion(getRoot(), dataToDelete);
        super.delete(dataToDelete);
    }


    public NodeWithRandom<T> getRandomNode() {
        int totalDescendants = getRoot().leftDescendantsCount+getRoot().rightDescendantsCount;
        Random random = new Random();
        int randomIndex = random.nextInt(totalDescendants+1);
        return getNodeAt(getRoot(), randomIndex);
    }


    private NodeWithRandom<T> getNodeAt(NodeWithRandom<T> root, int index) {
        if(index>root.leftDescendantsCount+root.rightDescendantsCount+1)
            return null;

        if(index==root.leftDescendantsCount)
            return root;
        if(index<root.leftDescendantsCount)
            return getNodeAt(getLeft(root),index);
        else
            return getNodeAt(getRight(root),index-root.leftDescendantsCount-1);
    }

    public Node<T> getNodeAt(int index)
    {
        return getNodeAt(getRoot(),index);
    }

    static class NodeWithRandom<T> extends Node<T> {
        int leftDescendantsCount = 0, rightDescendantsCount = 0;

        public NodeWithRandom(T data) {
            super(data);
        }
    }
}
