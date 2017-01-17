package top100.Enhance._2Tree_PreClass;

public class _2_BinaryTree_2Medium_1BSTInorderSuccessor {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p){
		if(root == null) {
			return null;
		}
		//Case 1: has right child
		if (p.right != null) {
			return searchLeftMost(p.right);
		} else {
			//Case 2: no right -> find the first left turing on the searching path
			return searchPar(root, p);
		}
	}
	private TreeNode searchPar(TreeNode root, TreeNode p) {
		TreeNode par = null;
		while(root != p) {
			if(p.val < root.val) {
				par = root;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return par;
	}
	private TreeNode searchLeftMost(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	//////////////////////////////////////////////////
	//mehtod 2
	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		TreeNode par = null;
		while (root != null){
			if (p.val < root.val) {
				par = root;
				root = root.left;
				
			} else {
				root = root.right;
			}
		}
		return par;
				
	}
}
