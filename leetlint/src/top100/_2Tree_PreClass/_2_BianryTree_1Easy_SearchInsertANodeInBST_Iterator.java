package top100._2Tree_PreClass;
@SuppressWarnings("all")

public class _2_BianryTree_1Easy_SearchInsertANodeInBST_Iterator {
	//Iteration
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

	//Recursion
	public TreeNode insert(TreeNode root, int val) {
		if(root == null){
			return new TreeNode(val);
		}
		TreeNode cur = root;
		TreeNode prev = null;
		//1.Search the position to insert
		while(cur != null && cur.val != val){
			prev = cur;
			if(cur.val < val){
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		//2.Insert the large node
		if(prev.val < val){
			prev.right = new TreeNode(val);
		} else {
			prev.left = new TreeNode(val);
		}
		return root;

	}
}
