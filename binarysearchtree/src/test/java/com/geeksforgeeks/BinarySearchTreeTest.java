package com.geeksforgeeks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adarsh on 22/12/2016.
 */
public class BinarySearchTreeTest {
    @Test
    public void testInsert() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        Assert.assertEquals("", tree.traverseInorder());
        tree.insert(1);
        Assert.assertEquals("1", tree.traverseInorder());
        tree.insert(2);
        Assert.assertEquals("12", tree.traverseInorder());
        tree.insert(0);
        Assert.assertEquals("012", tree.traverseInorder());
    }

    @Test
    public void testSearch() {
        BinarySearchTree<Integer> tree = buildIntegerTree();

        BinarySearchTree.Node<Integer> result = tree.searchNode(13);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.data.longValue(), 13);

        result = tree.searchNode(-4);
        Assert.assertNull(result);
    }

    private BinarySearchTree<Integer> buildIntegerTree() {
        return buildTree(7, 10, 1, 13, 4, 16);
    }

    private BinarySearchTree<Integer> buildBigIntegerTree() {
        return buildTree(44, 17, 32, 28, 29, 88, 65, 97, 54, 82, 76, 80, 78);
    }

    private <T extends Comparable<T>> BinarySearchTree<T> buildTree(T... tArr) {
        if (tArr.length == 0)
            return null;
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        Arrays.stream(tArr).forEach(tree::insert);
        return tree;
    }

    private <T extends Comparable<T>> BinarySearchTree<T> buildTree(List<T> tList) {
        if (tList.isEmpty())
            return null;
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        tList.stream().forEach(tree::insert);
        return tree;
    }

    @Test
    public void testTreeEquality() {
        BinarySearchTree<Integer> tree1 = buildTree(50, 30, 70, 20, 40, 60, 80);
        BinarySearchTree<Integer> tree2 = buildTree(50, 30, 70, 20, 40, 60, 80);
        Assert.assertEquals(tree1, tree2);

        BinarySearchTree<Integer> tree3 = buildTree(50, 30, 70, 20, 40, 60);
        Assert.assertNotEquals(tree1, tree3);
    }

    @Test
    public void testDeletion() {
        BinarySearchTree<Integer> tree = buildTree(50, 30, 70, 20, 40, 60, 80);

        //delete 40 and verify leaf deletion

        tree.delete(40);
        BinarySearchTree<Integer> expectedTree = buildTree(50, 30, 70, 20, 60, 80);
        Assert.assertEquals(expectedTree, tree);


        //delete 70 and verify inner node deletion

        tree = buildTree(50, 30, 70, 20, 40, 60, 80);
        tree.delete(70);
        expectedTree = buildTree(50, 30, 20, 40, 80, 60);
        Assert.assertEquals(expectedTree, tree);

        //delete 50 and verify root deletion

        tree = buildTree(50, 30, 70, 20, 40, 60, 80);
        tree.delete(50);
        expectedTree = buildTree(60, 30, 70, 20, 40, 80);
        Assert.assertEquals(expectedTree, tree);

        tree = buildBigIntegerTree();
        tree.delete(65);
        expectedTree = buildTree(44, 17, 32, 28, 29, 88, 76, 97, 54, 82, 80, 78);
        Assert.assertEquals(expectedTree, tree);
    }

    @Test
    public void testInorderPreSucc() {
        BinarySearchTree<Integer> tree = buildTree(50, 30, 70, 20, 40, 60, 80);

        //for 65, predecessor is 60, successor is 70
        PredecessorSuccessorDTO<Integer> result = tree.findInorderPreSucc(65);

        Assert.assertEquals(60, result.predecessor.data.longValue());
        Assert.assertEquals(70, result.successor.data.longValue());

        tree = buildBigIntegerTree();

        //for 44, it's 32 and 54
        result = tree.findInorderPreSucc(44);

        Assert.assertEquals(32, result.predecessor.data.longValue());
        Assert.assertEquals(54, result.successor.data.longValue());

        //for 47, it's 44 and 54
        result = tree.findInorderPreSucc(47);

        Assert.assertEquals(44, result.predecessor.data.longValue());
        Assert.assertEquals(54, result.successor.data.longValue());

        //for 97, it's 88 and null
        result = tree.findInorderPreSucc(97);

        Assert.assertEquals(88, result.predecessor.data.longValue());
        Assert.assertNull(result.successor);

        //for 100, it's 97 and null
        result = tree.findInorderPreSucc(100);

        Assert.assertEquals(97, result.predecessor.data.longValue());
        Assert.assertNull(result.successor);

        //for 17, it's null and 28
        result = tree.findInorderPreSucc(17);

        Assert.assertNull(result.predecessor);
        Assert.assertEquals(28, result.successor.data.longValue());

        //for -7, it's 17 and null
        result = tree.findInorderPreSucc(-7);

        Assert.assertNull(result.predecessor);
        Assert.assertEquals(17, result.successor.data.longValue());
    }

    @Test
    public void testMergeBST() {
        BinarySearchTree<Integer> tree1 = buildTree(3, 1, 5);
        BinarySearchTree<Integer> tree2 = buildTree(4, 2, 6);

        String mergedResult = tree1.merge(tree2);
        String expectedResult = "123456";
        Assert.assertEquals(expectedResult, mergedResult);

        tree1 = buildTree(8, 2, 1, 4, 10);
        tree2 = buildTree(5, 3, 0, 6);
        mergedResult = tree1.merge(tree2);
        expectedResult = "0123456810";
        Assert.assertEquals(expectedResult, mergedResult);

        tree1 = buildTree(8, 10, 12);
        tree2 = buildTree(11, 9, 7);
        mergedResult = tree1.merge(tree2);
        expectedResult = "789101112";
        Assert.assertEquals(expectedResult, mergedResult);
    }

    @Test
    public void testUnswapBST() {
        BinarySearchTree<Integer> tree = buildTree(10, 5, 8, 7, 3, 15, 20, 25);
        Assert.assertEquals("357810152025", tree.traverseInorder());
        tree.swap(3, 15);
        Assert.assertEquals("155781032025", tree.traverseInorder());
        tree.unswap();
        Assert.assertEquals("357810152025", tree.traverseInorder());
        tree.swap(15, 10);
        Assert.assertEquals("357815102025", tree.traverseInorder());
        tree.unswap();
        Assert.assertEquals("357810152025", tree.traverseInorder());
        tree.swap(5, 10);
        Assert.assertEquals("310785152025", tree.traverseInorder());
        tree.unswap();
        Assert.assertEquals("357810152025", tree.traverseInorder());

    }

    @Test
    public void testConstructBSTFromDLL() {
        DoublyLinkedList<Integer> dll = constructDLL(1, 2, 3);
        Assert.assertEquals("123", dll.toString());

        dll.constructBST(); //constructs BST in place using next and prev pointers
        Assert.assertEquals(buildTree(2, 1, 3), dll.mapToBST()); //verifies if this constructed tree is identical to the correct BST

        dll = constructDLL(1, 2, 3, 4, 5, 6, 7);
        dll.constructBST();
        Assert.assertEquals(buildTree(4, 2, 6, 1, 3, 5, 7), dll.mapToBST());

        dll = constructDLL(1, 2, 3, 4);
        dll.constructBST();
        Assert.assertEquals(buildTree(3, 2, 1, 4), dll.mapToBST());

    }

    private <T extends Comparable<T>> DoublyLinkedList<T> constructDLL(T... inputs) {
        if (inputs.length == 0)
            return null;

        DoublyLinkedList<T> dll = new DoublyLinkedList<>();
        Arrays.stream(inputs).forEach(dll::append);
        return dll;
    }

    @Test
    public void testSumInBST() {
        BinarySearchTree<Integer> tree = buildTree(15, 10, 20, 8, 12, 16, 25);

        int sumToSearch = 24;
        NodePairDTO<Integer> expectedResult = new NodePairDTO<>(tree.searchNode(8), tree.searchNode(16));

        NodePairDTO<Integer> actualResult = BinarySearchTree.findSumPair(tree.root, sumToSearch);
        Assert.assertEquals(expectedResult.node1, actualResult.node1);
        Assert.assertEquals(expectedResult.node2, actualResult.node2);

        sumToSearch = 33;
        expectedResult = new NodePairDTO<>(tree.searchNode(8), tree.searchNode(25));
        actualResult = BinarySearchTree.findSumPair(tree.root, sumToSearch);
        Assert.assertEquals(expectedResult.node1, actualResult.node1);
        Assert.assertEquals(expectedResult.node2, actualResult.node2);

        sumToSearch = 18;
        expectedResult = new NodePairDTO<>(tree.searchNode(8), tree.searchNode(10));
        actualResult = BinarySearchTree.findSumPair(tree.root, sumToSearch);
        Assert.assertEquals(expectedResult.node1, actualResult.node1);
        Assert.assertEquals(expectedResult.node2, actualResult.node2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumInBSTError() {
        BinarySearchTree<Integer> tree = buildTree(15, 10, 20, 8, 12, 16, 25);
        BinarySearchTree.findSumPair(tree.root, 29);
    }

    @Test
    public void testUnShuffle() {
        BinarySearchTree<Integer> tree = buildTree(8, 4, 10, 2, 7);
        tree.root.data = 10;
        tree.root.left.data = 2;
        tree.root.left.left.data = 8;
        tree.root.left.right.data = 4;
        tree.root.right.data = 7;
        tree.unShuffle();
        Assert.assertEquals(tree, buildTree(8, 4, 10, 2, 7));

        tree = buildTree(15, 10, 20, 5, 30);
        tree.root.data = 10;
        tree.root.left.data = 30;
        tree.root.right.data = 15;
        tree.root.left.left.data = 20;
        tree.root.right.right.data = 5;
        tree.unShuffle();
        Assert.assertEquals(tree, buildTree(15, 10, 20, 5, 30));
    }


    @Test
    public void testBuildBSTFromSortedArray() {
        BinarySearchTree<Integer> tree = BinarySearchTree.buildFromArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("123456789", tree.traverseInorder());

        tree = BinarySearchTree.buildFromArray(1, 2, 3, 4, 5, 6, 7, 8);
        Assert.assertEquals("12345678", tree.traverseInorder());

    }

    @Test
    public void testTreeBuildingArrayGeneration() {
        //Given a tree, the aim is to generate the set of all arrays (seeds) that could generate it.
        Assert.assertEquals(buildTree(2, 1, 3), buildTree(2, 3, 1));
        //Here both 2,3,1 and 2,1,3 produce the sam tree

        BinarySearchTree<Integer> tree = buildBigIntegerTree();
        List<List<Integer>> seeds = tree.findSeeds();

        Assert.assertTrue(!seeds.isEmpty()); //wow!! 17,325 seeds

        for (List<Integer> seed : seeds) {
            Assert.assertEquals(tree, buildTree(seed));
        }
    }

    @Test
    public void testTreeWithRandomPointer() {
        BinarySearchTreeWithRandom<Integer> tree = new BinarySearchTreeWithRandom<>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(0);

        int[] countArray=new int[5];
        int N=10000;
        for(int i=0;i<N;i++)
        {
            int randomData = tree.getRandomNode().data;
            countArray[randomData]++;
        }

        for(int i=0;i<5;i++)
        {
            System.out.println(i+" : "+countArray[i]);
        }

    }
}