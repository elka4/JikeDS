package ch9J.chapter3_BianryTree;

//root -> depth Divide & Conquer
@SuppressWarnings("all")

public class _97Maximum_Depth_of_Binary_Tree_DivideConquer {
  public int maxDepth(TreeNode root) {
	  //出口
        if (root == null) {
            return 0;
        }
        //不管三七二十，先分成左右子树
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        //conquer
        return Math.max(left, right) + 1;
    }
}

/*
 * Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest
 path from the root node down to the farthest leaf node.

Example: Given a binary tree as follow:
  1
 / \ 
2   3
   / \
  4   5
The maximum depth is 3.
Tags: Divide and Conquer, Recursion, Binary Tree, Uber
Related Problems: Easy Minimum Depth of Binary Tree 31 %
 * */

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
