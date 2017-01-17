package top100.Enhance._2Tree_PreClass;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _2_BinaryTree_2Medium_2LevelOrderTraversal {
	public List<List<Integer>> levelOrder (TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) return res;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new ArrayList<Integer>();
			//1. Pop and print all nodes in curernt level
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				list.add(cur.val);
				//2.add left and right children into queue
				if(cur.left != null) queue.add(cur.left);
				if(cur.right != null) queue.add(cur.right);	
				
			}
			res.add(list);
		}
		return res;
	}
	
}
