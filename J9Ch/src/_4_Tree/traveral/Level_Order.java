package _4_Tree.traveral;
import java.util.*;
/*
LeetCode – Binary Tree Level Order Traversal (Java)

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as [[3], [9,20], [15,7]]

Analysis

It is obvious that this problem can be solve by using a queue. However, if we use one queue we can not track when each level starts. So we use two queues to track the current level and the next level.
 */
public class Level_Order {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();
        if(root == null)
            return al;

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.add(root);

        while(!current.isEmpty()){
            TreeNode node = current.remove();

            if(node.left != null)
                next.add(node.left);
            if(node.right != null)
                next.add(node.right);

            nodeValues.add(node.val);
            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                al.add(nodeValues);
                nodeValues = new ArrayList();
            }

        }
        return al;
    }
}
