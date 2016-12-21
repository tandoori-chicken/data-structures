package com.geeksforgeeks;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by adarsh on 19/12/2016.
 */
public class TreeTest {
    @Test
    public void testTreeTraversal() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        Assert.assertEquals("42513", inorder(root));
        Assert.assertEquals("12453", preorder(root));
        Assert.assertEquals("45231", postorder(root));

        Assert.assertEquals("12345", levelorder(root));

        Node<Character> root2 = constructCharacterTree();
//        System.out.println(inorderWithoutRecursion(root2));
        Assert.assertEquals("BDAGECHFI", inorderWithoutRecursion(root2));
//        System.out.println(preorder(root2));
//        Assert.assertEquals("42513",inorderWithoutRecursion(root));
    }

    private Node<Character> constructCharacterTree() {
        Node<Character> root = new Node<>('A');
        root.left = new Node<>('B');
        root.left.right = new Node<>('D');
        root.right = new Node<>('C');
        root.right.left = new Node<>('E');
        root.right.left.left = new Node<>('G');
        root.right.right = new Node<>('F');
        root.right.right.left = new Node<>('H');
        root.right.right.right = new Node<>('I');
        return root;
    }


    /**
     * 1) Create an empty stack S.
     * 2) Initialize current node as root
     * 3) Push the current node to S and set current = current->left until current is NULL
     * 4) If current is NULL and stack is not empty then
     * a) Pop the top item from stack.
     * b) Print the popped item, set current = popped_item->right
     * c) Go to step 3.
     * 5) If current is NULL and stack is empty then we are done.
     */
    private String inorderWithoutRecursion(Node root) {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            if (!stack.isEmpty()) {
                Node temp = stack.pop();
                sb.append(temp.data);
                curr = temp.right;
            }

        }

        return sb.toString();

    }

    @Test
    public void testTreeDiameter() {
        Node root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        Assert.assertEquals(3, getHeight(root));
        Assert.assertEquals(4, getDiameter(root));
    }


    private int getHeight(Node root) {
        if (root == null)
            return 0;

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private int getDiameter(Node root) {
        if (root == null)
            return 0;

        return Math.max(Math.max(getDiameter(root.left), getDiameter(root.right)), getHeight(root.left) + getHeight(root.right) + 1);
    }

    private String levelorder(Node root) {
        if (root == null)
            return "";

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        return levelorder(queue);

    }

    private String levelorder(Queue<Node> queue) {
        if (queue.isEmpty())
            return "";
        Node curNode = queue.remove();
        if (curNode.left != null)
            queue.add(curNode.left);
        if (curNode.right != null)
            queue.add(curNode.right);
        return curNode.data + levelorder(queue);

    }

    private String inorder(Node root) {
        StringBuilder sb = new StringBuilder("");
        if (root == null)
            return sb.toString();

        sb.append(inorder(root.left));

        sb.append(root.data);

        sb.append(inorder(root.right));

        return sb.toString();

    }

    private String preorder(Node root) {
        StringBuilder sb = new StringBuilder("");
        if (root == null)
            return sb.toString();

        sb.append(root.data);

        sb.append(preorder(root.left));


        sb.append(preorder(root.right));

        return sb.toString();

    }

    private String postorder(Node root) {
        StringBuilder sb = new StringBuilder("");
        if (root == null)
            return sb.toString();


        sb.append(postorder(root.left));


        sb.append(postorder(root.right));

        sb.append(root.data);

        return sb.toString();

    }

    @Test
    public void testRecreateInPre() {
        Node<Character> root = constructCharacterTree();
        String inorder = inorder(root);
        String preorder = preorder(root);
        Node<Character> recreatedRoot = constructInPre(inorder, preorder);
        Assert.assertEquals(inorder, inorder(recreatedRoot));
        Assert.assertEquals(preorder, preorder(recreatedRoot));
    }


    private Node<Character> constructInPre(String inorder, String preorder) {
        if (inorder.trim().isEmpty() || preorder.trim().isEmpty())
            return null;

        int inorderIndex = inorder.indexOf(preorder.charAt(0));

        String leftInorder = inorder.substring(0, inorderIndex);
        String leftPreorder = getMatchingString(preorder, leftInorder);

        String rightInorder = inorder.substring(inorderIndex + 1, inorder.length());
        String rightPreorder = getMatchingString(preorder, rightInorder);

        Node<Character> root = new Node<>(preorder.charAt(0));
        root.left = constructInPre(leftInorder, leftPreorder);
        root.right = constructInPre(rightInorder, rightPreorder);
        return root;
    }

    private String getMatchingString(String bigString, String inorderSubstring) {
        StringBuilder sb = new StringBuilder();

        for (char c : bigString.toCharArray()) {
            if (inorderSubstring.contains(c + "")) {
                sb.append(c);
                if (sb.length() == inorderSubstring.length())
                    break;
            }
        }
        return sb.toString();
    }

    @Test
    public void testRecreateInPost() {
        Node<Character> root = constructCharacterTree();
        String inorder = inorder(root);
        String postorder = postorder(root);

        Node<Character> recreatedRoot = constructInPost(inorder, postorder);
        Assert.assertEquals(inorder, inorder(recreatedRoot));
        Assert.assertEquals(postorder, postorder(recreatedRoot));
    }

    private Node<Character> constructInPost(String inorder, String postOrder) {
        if (inorder.trim().isEmpty() || postOrder.trim().isEmpty())
            return null;

        int inorderIndex = inorder.indexOf(postOrder.charAt(postOrder.length() - 1));
        String leftInorder = inorder.substring(0, inorderIndex);
        String leftPostorder = getMatchingString(postOrder, leftInorder);

        String rightInorder = inorder.substring(inorderIndex + 1, inorder.length());
        String rightPostorder = getMatchingString(postOrder, rightInorder);

        Node<Character> root = new Node<>(inorder.charAt(inorderIndex));
        root.left = constructInPost(leftInorder, leftPostorder);
        root.right = constructInPost(rightInorder, rightPostorder);

        return root;
    }

    @Test
    public void testRecreateInLevel() {
        Node<Character> root = constructCharacterTree();
        String inorder = inorder(root);
        String levelorder = levelorder(root);

//        System.out.println(inorder);
//        System.out.println(levelorder);
        Node<Character> recreatedRoot = constructInLevel(inorder, levelorder);
        Assert.assertEquals(inorder,inorder(recreatedRoot));
        Assert.assertEquals(levelorder,levelorder(recreatedRoot));
    }

    private Node<Character> constructInLevel(String inorder, String levelorder) {
        if (inorder.trim().isEmpty() || levelorder.trim().isEmpty())
            return null;

        int inorderIndex = inorder.indexOf(levelorder.charAt(0));

        String leftInorder = inorder.substring(0, inorderIndex);
        String leftLevelorder = getMatchingString(levelorder, leftInorder);

        String rightInorder = inorder.substring(inorderIndex + 1, inorder.length());
        String rightLevelorder = getMatchingString(levelorder, rightInorder);

        Node<Character> root = new Node<>(levelorder.charAt(0));
        root.left = constructInLevel(leftInorder, leftLevelorder);
        root.right = constructInLevel(rightInorder, rightLevelorder);

        return root;
    }
}
