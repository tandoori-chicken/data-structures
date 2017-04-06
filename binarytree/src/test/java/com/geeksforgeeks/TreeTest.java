package com.geeksforgeeks;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        Assert.assertEquals("BDAGECHFI", inorderWithoutRecursion(root2));
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


    private Node<Character> constructCharacterTree2() {
        Node<Character> root = new Node<>('A');
        root.left = new Node<>('B');
        root.left.right = new Node<>('D');
        root.right = new Node<>('C');
        root.right.left = new Node<>('E');
        root.right.left.left = new Node<>('G');
        root.right.right = new Node<>('F');
        root.right.right.left = new Node<>('H');
        root.right.right.right = new Node<>('I');
        root.left.left = new Node<>('J');
        root.left.left.right = new Node<>('K');

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

        Node<Character> recreatedRoot = constructInLevel(inorder, levelorder);
        Assert.assertEquals(inorder, inorder(recreatedRoot));
        Assert.assertEquals(levelorder, levelorder(recreatedRoot));
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

    @Test
    public void testMaxWidth() {
        Node<Character> root = constructCharacterTree();

        Assert.assertEquals(3, getMaxWidth(root));
    }

    private int getMaxWidth(Node<Character> root) {
        if (root == null)
            return 0;

        int result = Integer.MIN_VALUE;
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        int count = queue.size();

        while (!queue.isEmpty()) {
            while (count-- > 0) {
                Node removed = queue.remove();
                if (removed.left != null)
                    queue.add(removed.left);
                if (removed.right != null)
                    queue.add(removed.right);
            }
//            queue.stream().map(n -> n.data).forEach(System.out::print);
//            System.out.println();
            count = queue.size();
            result = result > count ? result : count;
        }
        return result;
    }

    @Test
    public void testPrintAncestor() {
        Node<Character> root = constructCharacterTree();
        Assert.assertEquals(getAncestors(root, 'E'), "CA");
        Assert.assertEquals(getAncestors(root, 'I'), "FCA");
        Assert.assertEquals(getAncestors(root, 'B'), "A");

    }

    private String getAncestors(Node<Character> root, char e) {
        if (root == null)
            return null;
        if (root.data.equals(e))
            return "";

        String leftString = getAncestors(root.left, e);
        if (leftString != null)
            return leftString + root.data;
        String rightString = getAncestors(root.right, e);
        if (rightString != null)
            return rightString + root.data;

        return null;
    }

    @Test
    public void testConnectSameLevelNodes() {
        Node<Character> root = constructCharacterTree2();

        connectSameLevelNodes(root);

        Assert.assertNull(root.nextRight);
        Assert.assertNull(root.right.nextRight);
        Assert.assertEquals(root.left.nextRight, root.right);
        Assert.assertEquals(root.left.right.nextRight, root.right.left);

        Assert.assertEquals(root.left.left.nextRight, root.left.right);
        Assert.assertEquals(root.left.left.right.nextRight, root.right.left.left);
    }

    private void connectSameLevelNodes(Node<Character> root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        while (!queue.isEmpty()) {
            while (count-- > 0) {
                Node removed = queue.remove();
                if (removed.left != null) {
                    queue.add(removed.left);
                }
                if (removed.right != null) {
                    queue.add(removed.right);
                }
            }
            connectQueueElements(queue);
            count = queue.size();
        }
    }


    private void connectQueueElements(Queue<Node> queue) {

        if (queue.isEmpty())
            return;

        Node temp = queue.peek();

        for (Node curr : queue) {
            temp.nextRight = curr;
            temp = curr;
        }
    }

    @Test
    public void testPrintVerticalOrder() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);
        root.right.left.right = new Node<>(8);
        root.right.right.right = new Node<>(9);

        printVerticalOrder(root);
    }

    private <T> void printVerticalOrder(Node<T> root) {
        MinMaxDTO dto = new MinMaxDTO(0, 0);
        findMinMax(root, dto, 0);

        for (int i = dto.min; i <= dto.max; i++) {
            printVerticalLine(root, i, 0);
            System.out.println();
        }

    }

    private <T> void findMinMax(Node<T> node, MinMaxDTO dto, int curDistance) {
        if (node == null)
            return;
        if (curDistance < dto.min)
            dto.min = curDistance;
        if (curDistance > dto.max)
            dto.max = curDistance;

        findMinMax(node.left, dto, curDistance - 1);
        findMinMax(node.right, dto, curDistance + 1);

    }

    private <T> void printVerticalLine(Node<T> root, int lineNumber, int curDistance) {
        if (root == null)
            return;
        if (curDistance == lineNumber)
            System.out.print(root.data + " ");
        printVerticalLine(root.left, lineNumber, curDistance - 1);
        printVerticalLine(root.right, lineNumber, curDistance + 1);

    }

    @Test
    public void testSameLevelLinkedList() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> node5 = root.left = new Node<>(5);
        Node<Integer> node20 = root.right = new Node<>(20);
        Node<Integer> node3 = node20.left = new Node<>(3);
        node20.right = new Node<>(7);
        node5.right = new Node<>(12);
        node3.left = new Node<>(9);
        node3.right = new Node<>(18);

        List<LinkedList<Node<Integer>>> linkedLists = constructDepthWiseLists(root);
        Assert.assertEquals(4, linkedLists.size());
        Assert.assertEquals("10", getStringRepresentation(linkedLists.get(0)));
        Assert.assertEquals("520", getStringRepresentation(linkedLists.get(1)));
        Assert.assertEquals("1237", getStringRepresentation(linkedLists.get(2)));
        Assert.assertEquals("918", getStringRepresentation(linkedLists.get(3)));
    }

    private String getStringRepresentation(LinkedList<Node<Integer>> linkedList) {
        return linkedList.stream().map(n -> n.data + "").collect(Collectors.joining());
    }

    private List<LinkedList<Node<Integer>>> constructDepthWiseLists(Node<Integer> root) {
        if (root == null)
            return new ArrayList<>();
        List<LinkedList<Node<Integer>>> listOfLists = new ArrayList<>();
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int addedCount = 1;
        while (!queue.isEmpty()) {
            LinkedList<Node<Integer>> linkedList = new LinkedList<>();
            while (addedCount-- > 0) {
                linkedList.offer(queue.poll());
            }
            if (!linkedList.isEmpty()) {
                listOfLists.add(new LinkedList<>(linkedList));
            }
            while (!linkedList.isEmpty()) {
                Node<Integer> curNode = linkedList.poll();
                if (curNode.left != null)
                    queue.offer(curNode.left);
                if (curNode.right != null)
                    queue.offer(curNode.right);
            }
            addedCount = queue.size();
        }
        return listOfLists;
    }

    @Test
    public void testBinaryTreeBalanced() {
        Node<Integer> root = new Node<>(2);
        Node<Integer> node7 = root.left = new Node<>(7);
        Node<Integer> node5 = root.right = new Node<>(5);
        node7.left = new Node<>(2);
        Node<Integer> node6 = node7.right = new Node<>(6);
        node6.left = new Node<>(5);
        node6.right = new Node<>(11);
        Node<Integer> node9 = node5.right = new Node<>(9);
        node9.left = new Node<>(4);
        Assert.assertFalse(isBalanced(root));

        root = new Node<>(10);
        Node<Integer> node5_2 = root.left = new Node<>(5);
        Node<Integer> node20 = root.right = new Node<>(20);
        Node<Integer> node3 = node20.left = new Node<>(3);
        node20.right = new Node<>(7);
        node5_2.right = new Node<>(12);
        node3.left = new Node<>(9);
        node3.right = new Node<>(18);
        Assert.assertTrue(isBalanced(root));
    }

    private boolean isBalanced(Node<Integer> root) {
        int height = getHeight2(root);
        return height != Integer.MAX_VALUE;
    }

    private int getHeight2(Node<Integer> node) {
        //returns absolute value of height of node. returns Integer.MAX_VALUE if one of the subtrees is unbalanced;
        if (node == null)
            return 0;
        int leftHeight = getHeight2(node.left);
        int rightHeight = getHeight2(node.right);
        if (leftHeight == Integer.MAX_VALUE || rightHeight == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        int diff = Math.abs(leftHeight - rightHeight);
        if (diff > 1)
            return Integer.MAX_VALUE;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Test
    public void testBinaryTreeIsBST() {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(5);
        root.right = new Node<>(20);
        root.left.left = new Node<>(9);
        root.left.right = new Node<>(18);
        root.right.left = new Node<>(3);
        root.right.right = new Node<>(7);
        Assert.assertFalse(isBST(root));

        root = new Node<>(2);
        Node<Integer> node7 = root.left = new Node<>(7);
        Node<Integer> node5 = root.right = new Node<>(5);
        node7.left = new Node<>(2);
        Node<Integer> node6 = node7.right = new Node<>(6);
        node6.left = new Node<>(5);
        node6.right = new Node<>(11);
        Node<Integer> node9 = node5.right = new Node<>(9);
        node9.left = new Node<>(4);
        Assert.assertFalse(isBST(root));

        root = new Node<>(10);
        Node<Integer> node5_2 = root.left = new Node<>(5);
        Node<Integer> node20 = root.right = new Node<>(20);
        Node<Integer> node3 = node20.left = new Node<>(3);
        node20.right = new Node<>(7);
        node5_2.right = new Node<>(12);
        node3.left = new Node<>(9);
        node3.right = new Node<>(18);
        Assert.assertFalse(isBST(root));

        root = new Node<>(10);
        root.left = new Node<>(5);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(7);
        root.right = new Node<>(20);
        root.right.right = new Node<>(30);
        Assert.assertTrue(isBST(root));

    }

    private boolean isBST(Node<Integer> root) {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> boolean isBSTHelper(Node<T> node, T lowerBound, T upperBound) {
        if (node == null)
            return true;
        if (node.left == null && node.right == null) {
            T t = (T) node.data;
            return t.compareTo(lowerBound) > 0 && t.compareTo(upperBound) < 0;
        }
        return isBSTHelper(node.left, lowerBound, (T) node.data) && isBSTHelper(node.right, (T) node.data, upperBound);
    }

    @Test
    public void testInorderSuccessorUsingParent() {
        Node<Integer> root = new Node<>(10);
        root.addLeftChild(5);
        root.addRightChild(20);
        root.left.addLeftChild(9);
        root.left.addRightChild(18);
        root.right.addLeftChild(3);
        root.right.addRightChild(7);
        validateInorderSuccessor(root);

        root = new Node<>(2);
        Node<Integer> node7 = root.addLeftChild(7);
        Node<Integer> node5 = root.addRightChild(5);
        node7.addLeftChild(2);
        Node<Integer> node6 = node7.addRightChild(6);
        node6.addLeftChild(5);
        node6.addRightChild(11);
        Node<Integer> node9 = node5.addRightChild(9);
        node9.addLeftChild(4);
        validateInorderSuccessor(root);

        root = new Node<>(10);
        Node<Integer> node5_2 = root.addLeftChild(5);
        Node<Integer> node20 = root.addRightChild(20);
        Node<Integer> node3 = node20.addLeftChild(3);
        node20.addRightChild(7);
        node5_2.addRightChild(12);
        node3.addLeftChild(9);
        node3.addRightChild(18);
        validateInorderSuccessor(root);

        root = new Node<>(10);
        root.addLeftChild(5);
        root.left.addLeftChild(3);
        root.left.addRightChild(7);
        root.addRightChild(20);
        root.right.addRightChild(30);
        validateInorderSuccessor(root);
    }

    private void validateInorderSuccessor(Node<Integer> root) {
        Node<Integer> curr = root;
        String inorder = inorder(root);
        while (curr.left != null)
            curr = curr.left;
        StringBuilder inorderSuccessorStringBuilder = new StringBuilder();
        while (curr != null) {
            inorderSuccessorStringBuilder.append(curr.data);
            curr = getInorderSuccessor(curr);
        }
        Assert.assertEquals(inorder, inorderSuccessorStringBuilder.toString());
    }

    private <T extends Comparable<T>> Node<T> getInorderSuccessor(Node<T> node) {
        if (node.right != null || node.parent == null) {
            Node<T> curr = node.right;
            if (curr == null)
                return null;
            while (curr.left != null)
                curr = curr.left;
            return curr;
        }

        if (isLeftChild(node)) //if left child, return parent
            return node.parent;
        //if right child, keep going up until you find a parent that is left child. print it's parent. if such a left child cant be found return null
        Node<T> curr = node.parent;
        while (curr != null && !isLeftChild(curr)) {
            curr = curr.parent;
        }
        if (curr == null)
            return null;
        return curr.parent;
    }

    private boolean isLeftChild(Node<?> node) {
        return node.parent != null && node.parent.left != null && node.parent.left.equals(node);
    }

    @Test
    public void testLowestCommonAncestor() {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(5);
        root.right = new Node<>(20);
        root.left.left = new Node<>(9);
        root.left.right = new Node<>(18);
        root.right.left = new Node<>(3);
        root.right.right = new Node<>(7);

        Assert.assertEquals(root.right.data, getLowestCommonAncestor(root, root.right.left, root.right.right).data);
        Assert.assertEquals(root.data, getLowestCommonAncestor(root, root.left.right, root.right.left).data);
        Assert.assertEquals(root.data, getLowestCommonAncestor(root, root.left.right, root.right.right).data);
        Assert.assertEquals(root.left.data, getLowestCommonAncestor(root, root.left.right, root.left.left).data);
        Assert.assertEquals(root.left.data, getLowestCommonAncestor(root, root.left, root.left.right).data);
        validateLowestCommonAncestor(root, 10, 9, 7);
        validateLowestCommonAncestor(root, 10, 20, 5);

        root = new Node<>(2);
        Node<Integer> node7 = root.left = new Node<>(7);
        Node<Integer> node5 = root.right = new Node<>(5);
        node7.left = new Node<>(2);
        Node<Integer> node6 = node7.right = new Node<>(6);
        node6.left = new Node<>(8);
        node6.right = new Node<>(11);
        Node<Integer> node9 = node5.right = new Node<>(9);
        node9.left = new Node<>(4);
        validateLowestCommonAncestor(root, 2, 7, 5);
        validateLowestCommonAncestor(root, 2, 2, 5);
        validateLowestCommonAncestor(root, 2, 2, 4);


        root = new Node<>(10);
        Node<Integer> node5_2 = root.left = new Node<>(5);
        Node<Integer> node20 = root.right = new Node<>(20);
        Node<Integer> node3 = node20.left = new Node<>(3);
        node20.right = new Node<>(7);
        node5_2.right = new Node<>(12);
        node3.left = new Node<>(9);
        node3.right = new Node<>(18);
        validateLowestCommonAncestor(root, 10, 10, 10);

        root = new Node<>(10);
        root.left = new Node<>(5);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(7);
        root.right = new Node<>(20);
        root.right.right = new Node<>(30);
        validateLowestCommonAncestor(root, 10, 30, 7);
    }

    private void validateLowestCommonAncestor(Node<Integer> root, int expectedNodeData, int node1Data, int node2Data) {
        Node<Integer> expectedNode = getNodeWithValue(root, expectedNodeData);
        Assert.assertNotNull(expectedNode);
        Node<Integer> node1 = getNodeWithValue(root, node1Data);
        Assert.assertNotNull(node1);
        Node<Integer> node2 = getNodeWithValue(root, node2Data);
        Assert.assertNotNull(node2);
        Node<Integer> LCA = getLowestCommonAncestor(root, node1, node2);
        Assert.assertNotNull(LCA);
        Assert.assertEquals(expectedNode, LCA);
    }

    private <T> Node<T> getNodeWithValue(Node<T> root, T data) {
        if (root == null)
            return null;
        if (root.data.equals(data))
            return root;
        Node<T> lValue = getNodeWithValue(root.left, data);
        if (lValue != null)
            return lValue;
        Node<T> rValue = getNodeWithValue(root.right, data);
        if (rValue != null)
            return rValue;
        return null;
    }


    private <T> Node<T> getLowestCommonAncestor(Node<T> root, Node<T> node1, Node<T> node2) {
        if (node1.equals(node2))
            return node2;
        AncestorDTO<T> dto = new AncestorDTO<>();
        LCAHelper2(root, node1, node2, dto);
        return dto.lowestAncestor;
    }

    private <T> void LCAHelper(Node<T> node, Node<T> node1, Node<T> node2, AncestorDTO<T> dto) {
        int beforeCount = dto.foundCount;
        if (node.left != null) {
            LCAHelper(node.left, node1, node2, dto);
        }
        if (dto.lowestAncestor != null)
            return;

        if (node.equals(node1) || node.equals(node2)) {
            dto.foundCount++;
        }

        if (node.right != null) {
            LCAHelper(node.right, node1, node2, dto);
        }
        int afterCount = dto.foundCount;
        if (beforeCount == 0 && afterCount == 2 && dto.lowestAncestor == null)
            dto.lowestAncestor = node;


    }

    private <T> void LCAHelper2(Node<T> node, Node<T> node1, Node<T> node2, AncestorDTO<T> dto) {
        int beforeCount = dto.foundCount;
        if (node.left != null) {
            LCAHelper(node.left, node1, node2, dto);
        }

        if (node.right != null) {
            LCAHelper(node.right, node1, node2, dto);
        }

        if (dto.lowestAncestor != null)
            return;

        if (node.equals(node1) || node.equals(node2)) {
            dto.foundCount++;
        }

        int afterCount = dto.foundCount;
        if (beforeCount == 0 && afterCount == 2 && dto.lowestAncestor == null)
            dto.lowestAncestor = node;

    }

    private static class AncestorDTO<T> {
        Node<T> lowestAncestor;
        int foundCount = 0;
    }


    @Test
    public void testPathSum() {
        Node<Integer> root = new Node<>(10);
        root.right = new Node<>(-3);
        root.right.right = new Node<>(11);
        Node<Integer> node5 = root.left = new Node<>(5);
        node5.right = new Node<>(2);
        node5.right.right = new Node<>(1);
        node5.left = new Node<>(3);
        node5.left.left = new Node<>(3);
        node5.left.right = new Node<>(-2);

        List<Stack<Node<Integer>>> paths = getPaths(root, 8);
//        System.out.println(paths.size());
//        paths.stream().map(s->
//            s.stream().map(n->n.data.toString()).collect(Collectors.joining("->"))
//        ).forEach(System.out::println);
        Assert.assertEquals(3, paths.size()); //O(N^2) time and O(1) space

        Assert.assertEquals(3, getPathCount(root, 8)); //O(N) time and O(logN) space

    }

    private int getPathCount(Node<Integer> root, int sum) {

        return getPathCount(root, sum, 0, new HashMap<>()); //O(N) time and O(logN) space
    }

    private int getPathCount(Node<Integer> node, int targetSum, int runningSum, Map<Integer, Integer> map) {
        if (node == null)
            return 0;

        runningSum += (int) node.data;

        int totalPaths = map.getOrDefault(runningSum - targetSum, 0); //count paths that start after root and end here

        //Check if there is path from root to this node
        if (runningSum == targetSum)
            totalPaths++;
        modifyMap(map, runningSum, 1);
        totalPaths += getPathCount(node.left, targetSum, runningSum, map);
        totalPaths += getPathCount(node.right, targetSum, runningSum, map);
        modifyMap(map, runningSum, -1);
        return totalPaths;


    }

    private void modifyMap(Map<Integer, Integer> map, int key, int delta) {
        int newValue = map.getOrDefault(key, 0) + delta;
        if (newValue == 0)
            map.remove(key);
        else
            map.put(key, newValue);
    }


    private List<Stack<Node<Integer>>> getPaths(Node<Integer> root, int sum) {
        List<PathDTO<Integer>> paths = new ArrayList<>();
        getPaths(root, sum, sum, paths);
        return paths.stream().filter(p -> p.sum == sum).map(p -> p.path).collect(Collectors.toList());
    }

    private <T> boolean isChild(Node<T> parent, Node<T> child) {
        return parent != null && child != null && ((parent.left != null && parent.left.equals(child)) || (parent.right != null && parent.right.equals(child)));
    }

    private static int callCounter = 0;

    private void getPaths(Node<Integer> node, int sum, int actualSum, List<PathDTO<Integer>> paths) {
        callCounter++;
        if (node == null)
            return;
        getPaths(node.left, actualSum, actualSum, paths);
        getPaths(node.left, sum - (int) node.data, actualSum, paths);
        getPaths(node.right, actualSum, actualSum, paths);
        getPaths(node.right, sum - (int) node.data, actualSum, paths);

        paths.stream()
                .filter(path -> !path.isComplete)
                .filter(path -> isChild(node, path.path.peek()))
                .forEach(path -> {
                    path.sum += (int) node.data;
                    path.isComplete = (path.sum == actualSum);
                    path.path.push(node);
                });

        if (node.data.equals(actualSum)) {
            Stack<Node<Integer>> stack = new Stack<>();
            stack.push(node);
            paths.add(new PathDTO<>(stack, true, (int) node.data));
        }
        if (node.data.equals(sum)) {
            Stack<Node<Integer>> stack = new Stack<>();
            stack.push(node);
            paths.add(new PathDTO<>(stack, false, (int) node.data));
        }


    }

    private static class PathDTO<T> {
        Stack<Node<T>> path;
        boolean isComplete;
        int sum = Integer.MAX_VALUE;

        public PathDTO(Stack<Node<T>> path, boolean isComplete, int sum) {
            this.path = path;
            this.isComplete = isComplete;
            this.sum = sum;
        }
    }


}
