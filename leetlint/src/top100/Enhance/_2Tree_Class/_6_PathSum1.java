package top100.Enhance._2Tree_Class;

import java.util.*;

//root to leaf, 所有node的value和为sum， 返回所有的path
public class _6_PathSum1 {
	private class TreeNode {

		public int val ;
		public TreeNode(int i) {
		}
		public TreeNode left;
		public TreeNode right;

	}

	  public List<List<Integer>> pathSum(TreeNode root, int sum) {

	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        if (root == null) {
	            return res;
	        }

	        List<Integer> list = new ArrayList<Integer>();
	        helper(root, sum, list, res);

	        return res;
	    }

	    private void helper(TreeNode root, int sum, List<Integer> list, 
	    		List<List<Integer>> res) {
	    	

	        if (root == null) {
	            return;
	        }

	        list.add(root.val);
//叶子节点，且叶子节点的value和剩余在sum中的值相等时，将list生成新的list并加入到结果中
	        if (sum == root.val && root.left == null && root.right == null) {
	            res.add(new ArrayList<Integer>(list));

	        } else {
	            helper(root.left, sum - root.val, list, res);
	            helper(root.right, sum - root.val, list, res);
	        }

	        list.remove(list.size() - 1);

	    }
}
