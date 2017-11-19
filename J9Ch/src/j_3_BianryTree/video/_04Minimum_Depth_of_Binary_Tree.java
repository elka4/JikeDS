package j_3_BianryTree.video;

import lib.TreeNode;

//技巧：求最小值，那就先都把默认设成无穷大。最后用Max.min（）来滤过无穷大的值。
@SuppressWarnings("all")

public class _04Minimum_Depth_of_Binary_Tree {
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

//-------------------------------------------------------------------------


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
//-------------------------------------------------------------------------

//-------------------------------------------------------------------------
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
