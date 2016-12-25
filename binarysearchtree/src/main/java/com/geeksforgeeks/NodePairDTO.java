package com.geeksforgeeks;

/**
 * Created by adarsh on 25/12/2016.
 */
public class NodePairDTO<T> {
    BinarySearchTree.Node<T> node1;
    BinarySearchTree.Node<T> node2;

    public NodePairDTO(BinarySearchTree.Node<T> node1, BinarySearchTree.Node<T> node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}
