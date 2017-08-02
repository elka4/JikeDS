package _4_Tree.traveral;
import java.util.*;
/*
LeetCode – Binary Tree Level Order Traversal II (Java)

Given a binary tree, return the bottom-up level order traversal of its nodes' values.

For example, given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as [[15,7], [9,20],[3]]
 */
public class Level_Order_II {
    public List<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
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
}
