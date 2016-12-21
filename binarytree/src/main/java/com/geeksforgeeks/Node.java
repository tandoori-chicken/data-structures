package com.geeksforgeeks;

/**
 * Created by adarsh on 19/12/2016.
 */
public class Node<T> {
    public Object data;
    public Node left;
    public Node right;

    public Node(T data) {
        this.data = data;
    }
}
