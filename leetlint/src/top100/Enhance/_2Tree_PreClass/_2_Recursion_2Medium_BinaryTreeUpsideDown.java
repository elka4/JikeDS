package top100.Enhance._2Tree_PreClass;

import java.util.Deque;
import java.util.LinkedList;
@SuppressWarnings("all")

public class _2_Recursion_2Medium_BinaryTreeUpsideDown {

	public TreeNode upsideDownBinaryTree1(TreeNode root) {
		if (root == null) {
			return root;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();

		//pull left nodes into stack
		while(root != null) {
			stack.offerLast(root);
			root = root.left;
		}
		
		TreeNode newRoot = stack.pollLast();
		TreeNode cur = newRoot;

		//change pointers
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
/////////////////////////////////////////////////////////
	//recurtion
	public TreeNode upsideDownBinaryTree2(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}

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
	
/*

all right nodes are either 1 leaf nodes with a left sibling or 2 empty
reverse it into a new tree where the original right nodes becoming new left leaf nodes.
                     1
                   2   3
                 4   5

                     4
                   5   2
                     3  1







 */
