package top100.Enhance._2Tree_PreClass;

public class _2_BianryTree_1Easy_SearchInsertANodeInBST_Recursion {
	public TreeNode search (TreeNode root, int val) {
		if(root == null || root.val == val)
			return root;
		else if (root.val < val)
			return search (root.right, val);
		else
			return search (root.left, val);
	}
	
	public TreeNode insert(TreeNode root, int val) {
		//Base Case
		if(root == null) return new TreeNode(val);
		if(root.val > val) {
			//Assume all nodes are handled in left subtree
			root.left = insert(root.left, val);
		} else if (root.val < val) {
			//assume all nodes are handled in right subtree
			root.right = insert(root.right, val);
		}
		return root;
	}
	
}
