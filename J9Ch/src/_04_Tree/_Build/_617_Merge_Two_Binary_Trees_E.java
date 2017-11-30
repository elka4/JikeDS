package _04_Tree._Build;
import lib.TreeNode;

//  617. Merge Two Binary Trees
//  https://leetcode.com/problems/merge-two-binary-trees/description/
public class _617_Merge_Two_Binary_Trees_E {
    //  https://leetcode.com/articles/merge-two-binary-trees/

    //Approach #1 Using Recursion [Accepted]

    //  Approach #2 Iterative Method [Accepted]

    // all in java, need to be typed

//-------------------------------------------------------------------------------
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
        TreeNode newNode = new TreeNode(val);

        newNode.left = mergeTrees1(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        newNode.right = mergeTrees1(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return newNode;
    }

//-------------------------------------------------------------------------------
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) return null;
        int sum = 0;
        if(t1 != null) sum+= t1.val;
        if(t2 != null) sum+= t2.val;
        TreeNode root = new TreeNode(sum);
        root.left = mergeTrees2(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        root.right = mergeTrees2(t1 == null ? null : t1.right ,t2 == null ? null : t2.right);
        return root;
    }


//-------------------------------------------------------------------------------
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if(t1 == null || t2 == null) return t1==null?t2:t1;
        TreeNode root = new TreeNode(t1.val+t2.val);
        root.left = mergeTrees3(t1.left,t2.left);
        root.right = mergeTrees3(t1.right,t2.right);
        return root;
    }


//-------------------------------------------------------------------------------
}
/*
Given two binary trees and imagine that when you put one of them to cover the other,
some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
then sum node values up as the new value of the merged node. Otherwise,
the NOT null node will be used as the node of new tree.

Example 1:
Input:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
 */


