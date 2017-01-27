package top100.Enhance._2Tree_PreClass;

import java.util.Deque;
import java.util.LinkedList;
@SuppressWarnings("all")

public class _2_Recursion_1Easy_SymmetricTree {
	public boolean isSymetric_1 (TreeNode root) {
		if (root == null) return true;
		return isSymetric(root.left, root.right);
	}
	public boolean isSymetric (TreeNode one, TreeNode two) {
		//base case
		if (one == null || two == null) {
			if (one == two) return true;
			else return false;
		}

		if (one.val != two.val) {//curretn level
			return false;
		}else {//return left && right
			return isSymetric(one.left, two.right) &&
					isSymetric(one.right, two.left);
		}
		
	}

	//

	public boolean isSymetric_2(TreeNode root){
		if(root == null){
			return true;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		deque.offer(root.left);
		deque.offer(root.right);

		while(!deque.isEmpty()){
			//left polled from first, right polled from last
			TreeNode left = deque.pollFirst();
			TreeNode right = deque.peekLast();

			//Tricky here: if list.add(null), list.isEmpty() == false
			if(left == null && right == null){
				continue;
			}
			if(left == null || right == null || left.val != right.val){
				return false;
			}

			//left offered from first, right offered from last
			deque.offerFirst(left.right);
			deque.offerFirst(left.left);
			deque.offerLast(left.left);
			deque.offerLast(left.right);
		}
		return true;
	}
}
