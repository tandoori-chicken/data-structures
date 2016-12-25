package com.geeksforgeeks;

/**
 * Created by adarsh on 25/12/2016.
 */
public class PredecessorSuccessorDTO<T> {
    BinarySearchTree.Node<T> predecessor;
    BinarySearchTree.Node<T> successor;

    public PredecessorSuccessorDTO() {
    }

    public PredecessorSuccessorDTO(BinarySearchTree.Node<T> predecessor, BinarySearchTree.Node<T> successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }
}
