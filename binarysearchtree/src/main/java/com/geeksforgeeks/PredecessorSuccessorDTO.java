package com.geeksforgeeks;

/**
 * Created by adarsh on 25/12/2016.
 */
public class PredecessorSuccessorDTO<T> {
    final BinarySearchTree.Node<T> predecessor;
    final BinarySearchTree.Node<T> successor;

    public PredecessorSuccessorDTO(BinarySearchTree.Node<T> predecessor, BinarySearchTree.Node<T> successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }
}
