package ss;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 反转二叉树
 */
public class _226InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		postOrder(root);
		return root;
	}
	public void postOrder(TreeNode p) {
		if (p != null) {
			postOrder(p.left);
			postOrder(p.right);
			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
		}
	}
	public void preOrder(TreeNode p) {
		if (p != null) {
			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
			preOrder(p.left);
			preOrder(p.right);
		}
	}
	public void inOrder(TreeNode p) {
		if (p != null) {
			inOrder(p.left);
			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
			inOrder(p.left);
		}
	}
	public TreeNode levelOrder(TreeNode root) {
		if (root == null) {
			return null;
		} else {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				TreeNode p = queue.poll();
				if (p.left != null) {
					queue.offer(p.left);
				}
				if (p.right != null) {
					queue.offer(p.right);
				}
				TreeNode temp = p.left;
				p.left = p.right;
				p.right = temp;
			}
			return root;
		}
	}
}
