package top100.Enhance._2Tree_Class;

public class _8_LargestBSTSubTree {
	 public int largestBSTSubtree(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }

	    if (isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
	        return count(root);
	    }
	    return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
	}

	public boolean isValid(TreeNode root, Integer min, Integer max) {
	    if (root == null) {
	        return true;
	    }
	    if (root.val >= max || root.val <= min) {
	        return false;
	    }

	    return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
	}

	public int count(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }

	    return count(root.left) + count(root.right) + 1;
	}
	private class TreeNode {

		public int val ;
		public TreeNode(int i) {
			// TODO Auto-generated constructor stub
		}
		public TreeNode left;
		public TreeNode right;

	}
}
