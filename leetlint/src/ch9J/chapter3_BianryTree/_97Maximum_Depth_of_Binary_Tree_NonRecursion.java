package ch9J.chapter3_BianryTree;

import java.util.Deque;
import java.util.LinkedList;

//non-recursion
@SuppressWarnings("all")

public class _97Maximum_Depth_of_Binary_Tree_NonRecursion {
	public int maxDepth(TreeNode root) {
		if (root == null){
			return 0;
		}
		
		Deque<TreeNode> stack = new LinkedList<>();
		
		stack.push(root);
		int count = 0;
		
		while (!stack.isEmpty()) {
			int size = stack.size();
			while (size-- > 0) {
				TreeNode cur = stack.pop();
				if (cur.left != null){
					stack.addLast(cur.left);
				}
				if (cur.right != null){
					stack.addLast(cur.right);
				}
			}
			count++;

		}

		return count;
	}
}
