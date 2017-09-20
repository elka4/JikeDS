package J_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//version 1: BFS
public class _60Binary_Tree_Level_Order_Traversal_BFS {
 public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
     ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

     if (root == null) {
         return result;
     }

     Queue<TreeNode> queue = new LinkedList<>();
     queue.offer(root);

     while (!queue.isEmpty()) {
         ArrayList<Integer> level = new ArrayList<>();
         int size = queue.size();
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

     return result;
 }
}

/*Given a binary tree, return the level order traversal 
 * of its nodes' values. (ie, from left to right, level by level).

Have you met this question in a real interview? Yes
Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
Challenge 
Challenge 1: Using only 1 queue to implement it.

Challenge 2: Use DFS algorithm to do it.

Tags 
Queue Binary Tree Breadth First Search Binary Tree Traversal 
LinkedIn Uber Facebook*/
