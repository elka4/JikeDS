package top100.Enhance._2Tree_PreClass;

public class _2_BianryTree_1Easy_SearchInsertANodeInBST_Iterator {
	public TreeNode search (TreeNode root, int val) {
		if(root == null || root.val == val)
			return root;
		TreeNode cur = root;
		while(cur != null) {
			if(cur.val == val)
				return cur;
			else if (cur.val < val)
				cur = cur.right;
			else 
				cur = cur.left;
		}
		return null;
		
	}
}
