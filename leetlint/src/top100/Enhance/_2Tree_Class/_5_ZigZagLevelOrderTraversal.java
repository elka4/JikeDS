package top100.Enhance._2Tree_Class;

import java.util.*;

public class _5_ZigZagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>>  res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        //Method: Using a deque to maintain the current nodes in the same level with the same order from left to right
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        boolean lefttoRight = true;//The order to put into result
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                if(lefttoRight) {//Case 1
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null)
                        deque.addLast(cur.left);
                    if (cur.right != null)
                        deque.addLast(cur.right);
                } else {//Case 2
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null)
                        deque.addFirst(cur.right);
                    if (cur.left != null)
                        deque.addFirst(cur.left);
                }
            }
            lefttoRight = lefttoRight ? false: true;
            res.add(list);
        }
        return res;
    }
	
	private class TreeNode {

		public int val ;
		public TreeNode(int i) {
			// TODO Auto-generated constructor stub
		}
		public TreeNode left;
		public TreeNode right;

	}

}
