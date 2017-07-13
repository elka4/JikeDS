package J_3_Binary_Tree_Divide_Conquer.other.Level_Order;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class _70Binary_Tree_Level_Order_Traversal_II {
	  /**
	 * @param root: The root of binary tree.
	 * @return: buttom-up level order a list of lists of integer
	 */
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
	    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	    if (root == null) {
	        return result;
	    }
	    Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.offer(root);
	    
	    while (!queue.isEmpty()) {
	        int size = queue.size();
	        ArrayList<Integer> level = new ArrayList<Integer>();
	        for (int i = 0; i < size; i++) {
	            TreeNode head = queue.poll();
	            level.add(head.val);
	            if (head.left != null) {
	                queue.offer(head.left);
	            }
	            if (head.right != null) {
	                queue.offer(head.right);
	            }
	        }
	        result.add(level);
	    }
	    
	    Collections.reverse(result);
	    return result;
	}


    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(levelOrderBottom(root));
    }
}


/*
 * Given a binary tree, return the bottom-up level order
 *  traversal of its nodes' values. (ie, from left to right, 
 *  level by level from leaf to root).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]

Tags 
Queue Binary Tree Binary Tree Traversal Breadth First Search
 * */