package com.geeksforgeeks;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


/**
 * Created by adarsh on 22/12/2016.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    public BinarySearchTree(Node<T> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    public static NodePairDTO<Integer> findSumPair(Node<Integer> root, int sum) {
        Node<Integer> curr1 = root;
        Node<Integer> curr2 = root;
        Stack<Node<Integer>> stack1 = new Stack<>();
        Stack<Node<Integer>> stack2 = new Stack<>();

        while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            if (curr1 != null || curr2 != null) {
                if (curr1 != null) {
                    stack1.push(curr1);
                    curr1 = curr1.left;
                }
                if (curr2 != null) {
                    stack2.push(curr2);
                    curr2 = curr2.right;
                }
            } else if (!stack1.isEmpty() && !stack2.isEmpty()) {
                curr1 = stack1.pop();
                curr2 = stack2.pop();
                if (curr1.equals(curr2))
                    throw new IllegalArgumentException("Sum : " + sum + " not found");
                Integer currSum = curr1.data + curr2.data;
                if (currSum < sum) {
                    //we move to next inorder element from left
                    curr1 = curr1.right;
                    stack2.push(curr2);
                    curr2 = null;
                } else if (currSum > sum) {
                    //we move to previous inorder element from right
                    curr2 = curr2.left;
                    stack1.push(curr1);
                    curr1 = null;
                } else {
                    return new NodePairDTO<>(curr1, curr2);
                }
            }
        }
        throw new IllegalArgumentException("Sum : " + sum + " not found");
    }

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

    public PredecessorSuccessorDTO<T> findInorderPreSucc(T dataToSearch) {
        PredecessorSuccessorDTO<T> dto = new PredecessorSuccessorDTO<>();
        findInorderPreSucc(dataToSearch, root, dto);
        return dto;
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
    private void findInorderPreSucc(T dataToSearch, Node<T> root, PredecessorSuccessorDTO<T> dto) {

        if (root == null)
            return;


        if (root.data.equals(dataToSearch)) {

            if (root.left != null) {
                Node<T> curr = root.left;
                while (curr.right != null) {
                    curr = curr.right; //keeps track of the result
                }
                dto.predecessor = curr;
            }
            if (root.right != null) {
                Node<T> curr = root.right;
                while (curr.left != null) {
                    curr = curr.left;
                }
                dto.successor = curr;
            }
        } else if (root.data.compareTo(dataToSearch) > 0) {
            //searching in left subtree, set successor as root
//            successor = root;
            dto.successor = root;
            findInorderPreSucc(dataToSearch, root.left, dto);

        } else {
            dto.predecessor = root;
            findInorderPreSucc(dataToSearch, root.right, dto);
        }

    }

    public String merge(BinarySearchTree<T> treeToMerge) {
        return merge(this, treeToMerge);
    }

    private String merge(BinarySearchTree<T> tree1, BinarySearchTree<T> tree2) {
        if (tree1 == null && tree2 == null)
            return "";

        if (tree2 == null)
            return tree1.traverseInorder();
        if (tree1 == null)
            return tree2.traverseInorder();

        Stack<Node<T>> stack1 = new Stack<>();
        Stack<Node<T>> stack2 = new Stack<>();

        Node<T> curr1 = tree1.root;
        Node<T> curr2 = tree2.root;
        StringBuilder mergedStringBuilder = new StringBuilder();

        while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            //push as long as there is something to push
            if (curr1 != null || curr2 != null) {
                if (curr1 != null) {
                    stack1.push(curr1);
                    curr1 = curr1.left;
                }
                if (curr2 != null) {
                    stack2.push(curr2);
                    curr2 = curr2.left;
                }
            } else {
                //if nothing to push in both stacks, check if any of the stacks are empty.
                // this means we have visited every node in one of the trees.
                // print inorder of remaining tree, taking precaution not to print something already printed.
                if (stack1.isEmpty()) {
                    while (!stack2.isEmpty()) {
                        curr2 = stack2.pop();
                        curr2.left = null;
                        //if there was something in left, we would have already printed it, so set it as null
                        mergedStringBuilder.append(traverseInorder(curr2));
                    }
                    return mergedStringBuilder.toString();
                }
                if (stack2.isEmpty()) {
                    while (!stack1.isEmpty()) {
                        curr1 = stack1.pop();
                        curr1.left = null;
                        mergedStringBuilder.append(traverseInorder(curr1));
                    }
                    return mergedStringBuilder.toString();
                }
                // if neither of the stacks are empty, pop the top, compare
                // print lesser value and set cur as it's right child.
                // push the bigger node back into the stack
                curr1 = stack1.pop();
                curr2 = stack2.pop();
                if (curr1.data.compareTo(curr2.data) < 0) {
                    mergedStringBuilder.append(curr1.data);
                    curr1 = curr1.right;
                    stack2.push(curr2);
                    curr2 = null;
                } else {
                    mergedStringBuilder.append(curr2.data);
                    curr2 = curr2.right;
                    stack1.push(curr1);
                    curr1 = null;
                }

            }
        }

        return mergedStringBuilder.toString();

    }

    public void swap(T data1, T data2) {
        Node<T> node1 = searchNode(data1);
        Node<T> node2 = searchNode(data2);
        if (node1 == null || node2 == null)
            throw new IllegalArgumentException("Data not found in tree");
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public void swap(Node<T> node1, Node<T> node2) {
        if (node1 == null || node2 == null)
            throw new IllegalArgumentException("Data not found in tree");
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public void unswap() {
        NodeSwapDTO<T> nodeSwapDTO = new NodeSwapDTO<>();

        parseForUnswap(root, nodeSwapDTO);
        if (nodeSwapDTO.first != null && nodeSwapDTO.last != null) {
            swap(nodeSwapDTO.first, nodeSwapDTO.last);
        } else if (nodeSwapDTO.first != null && nodeSwapDTO.middle != null) {
            swap(nodeSwapDTO.first, nodeSwapDTO.middle);
        }
    }

    private void parseForUnswap(Node<T> root, NodeSwapDTO<T> nodeSwapDTO) {
        if (root == null)
            return;
        parseForUnswap(root.left, nodeSwapDTO);

        if (nodeSwapDTO.prev != null && root.data.compareTo(nodeSwapDTO.prev.data) < 0) {
            if (nodeSwapDTO.first == null) {
                nodeSwapDTO.first = nodeSwapDTO.prev;
                nodeSwapDTO.middle = root;
            } else {
                nodeSwapDTO.last = root;
            }
        }
        nodeSwapDTO.prev = root;
        parseForUnswap(root.right, nodeSwapDTO);

    }

    public void unShuffle() {
        unShuffle(root);
    }

    private void unShuffle(Node<T> root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        ArrayList<T> inorder = traverseInorderAndList(root);

        Collections.sort(inorder); //T is comparable, so this is OK

        buildTree(root, inorder, 0);
    }

    private int buildTree(Node<T> root, ArrayList<T> inorder, int index) {

        if (root.left != null)
            index = buildTree(root.left, inorder, index);
        root.data = inorder.get(index);
        index++;
        if (root.right != null)
            index = buildTree(root.right, inorder, index);

        return index;
    }

    private ArrayList<T> traverseInorderAndList(Node<T> root) {
        if (root == null)
            return null;

        ArrayList<T> leftList = traverseInorderAndList(root.left);
        if (leftList == null)
            leftList = new ArrayList<>(0);
        leftList.add(root.data);
        ArrayList<T> rightList = traverseInorderAndList(root.right);
        if (rightList != null)
            leftList.addAll(rightList);
        return leftList;
    }

    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }


}
