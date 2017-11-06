package _04_Tree._Level;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

public class Binary_Tree_Level_Order_Traversal_II {
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

//////////////////////////////////////////////////////////////

    public List<ArrayList<Integer>> levelOrderBottom2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new  ArrayList<ArrayList<Integer>>();

        if(root == null){
            return result;
        }

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.offer(root);

        ArrayList<Integer> numberList = new ArrayList<Integer>();

        // need to track when each level starts
        while(!current.isEmpty()){
            TreeNode head = current.poll();

            numberList.add(head.val);

            if(head.left != null){
                next.offer(head.left);
            }
            if(head.right!= null){
                next.offer(head.right);
            }

            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                result.add(numberList);
                numberList = new ArrayList<Integer>();
            }
        }

        //return Collections.reverse(result);
        ArrayList<ArrayList<Integer>> reversedResult = new  ArrayList<ArrayList<Integer>>();
        for(int i=result.size()-1; i>=0; i--){
            reversedResult.add(result.get(i));
        }

        return reversedResult;
    }

//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////


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