package ch9J.chapter3_BianryTree;

import java.util.ArrayList;

//Version 1: Traverse
public class _01Preorder_Traversal_Recursion {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
	    ArrayList<Integer> result = new ArrayList<>();
	    traverse(root, result);
	    return result;
	}
	// 1递归定义：把root为根的preorder加入result里面
	private void traverse(TreeNode root, ArrayList<Integer> result) {
		// 3.递归的出口：极端小的问题
		  // 以root为null还是leaf作为出口？
	    if (root == null) {
	        return;
	    }
	 // 2.如何拆分为更小的情况（如何扒皮）。一边走一边构造。
	    result.add(root.val);
	    traverse(root.left, result);
	    traverse(root.right, result);
	}
}
/* inorder
traverse(root.left, result);
result.add(root.val);
traverse(root.right, result);
 */

/* postorder
traverse(root.left, result);
traverse(root.right, result);
result.add(root.val);
 */