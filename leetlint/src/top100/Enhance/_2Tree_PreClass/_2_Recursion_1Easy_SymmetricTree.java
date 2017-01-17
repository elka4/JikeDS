package top100.Enhance._2Tree_PreClass;

public class _2_Recursion_1Easy_SymmetricTree {
	public boolean isSymetric (TreeNode root) {
		if (root == null) return true;
		return isSymetric(root.left, root.right);
	}
	public boolean isSymetric (TreeNode one, TreeNode two) {
		if (one == null || two == null) {
			if (one == two) return true;
			else return false;
		}
		if (one.val != two.val) return false;
		else return isSymetric(one.left, two.right) &&
					isSymetric(one.right, two.left);
		
	}
}
