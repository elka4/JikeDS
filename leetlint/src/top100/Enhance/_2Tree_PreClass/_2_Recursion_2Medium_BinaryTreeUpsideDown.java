package top100.Enhance._2Tree_PreClass;

import java.util.Deque;
import java.util.LinkedList;

public class _2_Recursion_2Medium_BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree1(TreeNode root) {
		if (root == null) {
			return root;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		
		while(root != null) {
			stack.offerLast(root);
			root = root.left;
		}
		
		TreeNode newRoot = stack.pollLast();
		TreeNode cur = newRoot;
		while (!stack.isEmpty()) {
			TreeNode oriParent = stack.pollLast();
			cur.right = oriParent;
			cur.left = oriParent.right;
			
			cur = oriParent;
			oriParent.left = null;
			oriParent.right = null;
		}
		return newRoot;
	}
	
	public TreeNode upsideDownBinaryTree2(TreeNode root) {
		if (root == null || root.left == null) 
			return root;
			//Assume all lower levels are handled
			TreeNode newRoot = upsideDownBinaryTree2(root.left);
			//Handle current level
			root.left.left = root.right;
			root.left.right = root;
			root.left = null;
			root.right = null;
			return newRoot;
	}
}
	

