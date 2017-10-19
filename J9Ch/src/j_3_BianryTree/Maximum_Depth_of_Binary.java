package j_3_BianryTree;

import lib.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

//root -> depth Divide & Conquer
@SuppressWarnings("all")

public class Maximum_Depth_of_Binary {
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

////////////////////////////////////////////////////////////////////
    //non-recursion
    @SuppressWarnings("all")
    public int maxDepth2(TreeNode root) {
        if (root == null){
            return 0;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size-- > 0) {
                TreeNode cur = stack.pop();
                if (cur.left != null){
                    stack.addLast(cur.left);
                }
                if (cur.right != null){
                    stack.addLast(cur.right);
                }
            }
            count++;

        }

        return count;
    }



////////////////////////////////////////////////////////////////////
//version 2: Traverse
@SuppressWarnings("all")

class _97Maximum_Depth_of_Binary_Tree_Traverse {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    private int depth;//用了一个全局变量，不鼓励这么做

    public int maxDepth(TreeNode root) {
        depth = 0;
        helper(root, 1);
        return depth;
    }

    private void helper(TreeNode node, int curtDepth) {
        if (node == null) {
            return;
        }
        if (curtDepth > depth) {
            depth = curtDepth;
        }
        helper(node.left, curtDepth + 1);
        helper(node.right, curtDepth + 1);
    }
}


////////////////////////////////////////////////////////////////////
//技巧：求最小值，那就先都把默认设成无穷大。最后用Max.min（）来滤过无穷大的值。
@SuppressWarnings("all")

class _155Minimum_Depth_of_Binary_Tree_1 {
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

//////////////////////////////////////////////////////////////


    public int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.left == null){
            return 1;
        }

        //让权。非法情况下就把结果变得无穷大。
        int left = root.left == null ? Integer.MAX_VALUE : minDepth2(root.left);
        int right = root.right == null ? Integer.MAX_VALUE : minDepth2(root.right);

        return Math.min(left, right) + 1;
    }
}


/*
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest
path from the root node down to the nearest leaf node.

Example: Given a binary tree as follow:

  1
 / \
2   3
   / \
  4   5
The minimum depth is 2.

Tags: Binary Tree Depth First Search
Related Problems: Easy Maximum Depth of Binary Tree 55 %
 * */


////////////////////////////////////////////////////////////////////



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
