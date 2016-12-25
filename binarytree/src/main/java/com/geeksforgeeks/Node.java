package com.geeksforgeeks;

/**
 * Created by adarsh on 19/12/2016.
 */
public class Node<T> {
    public Object data;
    public Node<T> left;
    public Node<T> right;
    public Node<T> nextRight; //used for same level connect problem

    public Node(T data) {
        this.data = data;
    }
}
