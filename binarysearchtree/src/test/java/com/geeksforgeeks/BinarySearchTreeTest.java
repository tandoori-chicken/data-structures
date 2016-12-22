package com.geeksforgeeks;

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

    private <T extends Comparable<T>> BinarySearchTree<T> buildTree(T... tArr) {
        if (tArr.length == 0)
            return null;
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        Arrays.stream(tArr).forEach(tree::insert);
        return tree;
    }


    @Test
    public void testTreeEquality()
    {
        BinarySearchTree<Integer> tree1 = buildTree(50,30,70,20,40,60,80);
        BinarySearchTree<Integer> tree2 = buildTree(50,30,70,20,40,60,80);
        Assert.assertEquals(tree1,tree2);

        BinarySearchTree<Integer> tree3 = buildTree(50,30,70,20,40,60);
        Assert.assertNotEquals(tree1,tree3);
    }
    @Test
    public void testDeletion(){
        BinarySearchTree<Integer> tree = buildTree(50,30,70,20,40,60,80);

    }
}
