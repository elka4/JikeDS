package _4_Tree.Path_Sum;

import lib.TreeNode;

public class Binary_Tree_Maximum_Path_Sum_II {
    /**
     * @param root the root of binary tree.
     * @return an integer
     */
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        //先无脑分为左右两部分
        int left = maxPathSum2(root.left);
        int right = maxPathSum2(root.right);
        
        //要和0比较，以避免负数情况
        //root -> any node
        return root.val + Math.max(0, Math.max(left, right));
        
        //root -> leaf
        //return Math.max(left, right) + root.val;
        
      //any node -> any node
    }
}

/*
 * Given a binary tree, find the maximum path sum from root.

The path may end at any node in the tree and contain at 
least one node in it.

Example: Given the below binary tree:

  1
 / \
2   3
return 4. (1->3)

Tags: Binary Tree
Related Problems: Medium Binary Tree Maximum Path Sum 24 %
 * */