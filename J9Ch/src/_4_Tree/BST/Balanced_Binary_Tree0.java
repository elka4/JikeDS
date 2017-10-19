package _4_Tree.BST;

import lib.*;
import org.junit.Test;

/*
LeetCode – Balanced Binary Tree (Java)

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
Analysis

This is a typical tree problem that can be solve by using recursion.


 */


//Devide and Conquer
public class Balanced_Binary_Tree0 {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        if (getHeight(root) == -1)
            return false;

        return true;
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }

    @Test
    public void test(){
        int[] arr = {1,2,3,4,5,6,7};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.print();

        System.out.println(isBalanced(root));
    }


/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
