package top100.Enhance._2Tree_Class;

import java.util.*;


public class _6_PathSum1 {
	private class TreeNode {

		public int val ;
		public TreeNode(int i) {
			// TODO Auto-generated constructor stub
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

	        if (sum == root.val && root.left == null && root.right == null) {
	            res.add(new ArrayList<Integer>(list));

	        } else {
	            helper(root.left, sum - root.val, list, res);
	            helper(root.right, sum - root.val, list, res);
	        }

	        list.remove(list.size() - 1);

	    }
}
