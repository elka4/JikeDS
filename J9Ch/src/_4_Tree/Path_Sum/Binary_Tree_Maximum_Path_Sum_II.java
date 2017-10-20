package _4_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

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

    /*
  1
 / \
2   3
return 6.
 */

    @Test
    public void test01(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }

    @Test
    public void test02(){
        int[] arr = {-1,-5,11,1,2,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.left.setLeftChild(new TreeNode(-8));
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
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