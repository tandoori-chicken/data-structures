package com.geeksforgeeks;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
        Assert.assertEquals(expectedTree,tree);
    }

    @Test
    public void testInorderPreSucc() {
        BinarySearchTree<Integer> tree = buildTree(50, 30, 70, 20, 40, 60, 80);

        //for 65, predecessor is 60, successor is 70
        Pair<BinarySearchTree.Node<Integer>,BinarySearchTree.Node<Integer>> result =  tree.findInorderPreSucc(65);

        Assert.assertEquals(60,result.getLeft().data.longValue());
        Assert.assertEquals(70,result.getRight().data.longValue());

        tree = buildBigIntegerTree();

        //for 44, it's 29 and 54
        result =  tree.findInorderPreSucc(44);

        Assert.assertEquals(32,result.getLeft().data.longValue());
        Assert.assertEquals(54,result.getRight().data.longValue());

        //for 47, it's 44 and 54
        result =  tree.findInorderPreSucc(47);

        Assert.assertEquals(44,result.getLeft().data.longValue());
        Assert.assertEquals(54,result.getRight().data.longValue());

        //for 97, it's 88 and null
        result =  tree.findInorderPreSucc(97);

        Assert.assertEquals(88, result.getLeft().data.longValue());
        Assert.assertNull(result.getRight());

        //for 100, it's 97 and null
        result =  tree.findInorderPreSucc(100);

        Assert.assertEquals(97, result.getLeft().data.longValue());
        Assert.assertNull(result.getRight());

        //for 17, it's null and 28
        result =  tree.findInorderPreSucc(17);

        Assert.assertNull(result.getLeft());
        Assert.assertEquals(28, result.getRight().data.longValue());

        //for -7, it's 17 and null
        result =  tree.findInorderPreSucc(-7);

        Assert.assertNull(result.getLeft());
        Assert.assertEquals(17, result.getRight().data.longValue());
    }

}