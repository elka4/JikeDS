package top100._2Tree_PreClass;
@SuppressWarnings("all")

public class _2_BinaryTree_2Medium_3ValidBinaryTree {
	public boolean isValidBST(TreeNode root) {
		if(root == null)
			return true;
		return isValidBST(root, Long.MAX_VALUE, Long.MAX_VALUE);
	}
	private boolean isValidBST(TreeNode root,  long min, long max) {
		if(root == null)
			return true;
		//current level: check root.val
		if(root.val >= max || root.val <= min)
			return false;
		//recurse down 
		return isValidBST(root.left,  min, root.val) &&
				isValidBST(root.right, root.val, max);
	}
}
