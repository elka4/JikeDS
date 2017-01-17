package ch9J.chapter3_BianryTree;

//Version 2: without ResultType
//coding style不好，不鼓励使用

public class _06Balanced_Binary_Tree_NoResultType {
	public boolean isBalanced(TreeNode root) {
		//这定义太绕了
	   return maxDepth(root) != -1;
	}
	
	//用－1表示不平衡，用其他数字表示高度，这定义太绕了
	private int maxDepth(TreeNode root) {
	   if (root == null) {
	       return 0;
	   }
	
	   int left = maxDepth(root.left);
	   int right = maxDepth(root.right);
	   if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
	       return -1;
	   }
	   return Math.max(left, right) + 1;
	}
}