package top100.Enhance._2Tree_PreClass;

public class _2_BinaryTree_2Medium_3ValidBinaryTree {
	public boolean isValidBST(TreeNode root) {
		if(root == null)
			return true;
		return isValidBST(root, Long.MAX_VALUE, Long.MAX_VALUE);
	}
	private boolean isValidBST(TreeNode root, long max, long min) {
		if(root == null)
			return true;
		//current level: check root.val
		if(root.val >= max || root.val <= min)
			return false;
		//recurse down 
		return isValidBST(root.left, root.val, min) &&
				isValidBST(root.right, max, root.val);
	}
}
