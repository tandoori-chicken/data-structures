package com.geeksforgeeks;

/**
 * Created by adarsh on 19/12/2016.
 */
public class Node<T> {
    public Object data;
    public Node<T> left;
    public Node<T> right;
    public Node<T> nextRight; //used for same level connect problem
    public Node<T> parent; //used in inorder successor problem

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addLeftChild(T data) {
        Node<T> leftChild = new Node<>(data);
        leftChild.parent = this;
        this.left=leftChild;
        return leftChild;
    }
    public Node<T> addRightChild(T data) {
        Node<T> rightChild = new Node<>(data);
        rightChild.parent = this;
        this.right=rightChild;
        return rightChild;
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
