package _04_Tree._Kth;

import lib.TreeNode;

import java.util.*;


//  671. Second Minimum Node In a Binary Tree
//  https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
public class _671_Tree_Second_Minimum_Node_In_a_Binary_Tree_E {

    //  https://leetcode.com/articles/second-minimum-node-in-a-binary-tree/

    //    Approach #1: Brute Force [Accepted]

    //    Approach #2: Ad-Hoc [Accepted]

    // need to OCR
///////////////////////////////////////////////////////////////////////////
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Set<Integer> set = new TreeSet<>();
        dfs(root, set);
        Iterator<Integer> iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            int result = iterator.next();
            if (count == 2) {
                return result;
            }
        }
        return -1;
    }

    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
        return;
    }

    //BFS
    public int findSecondMinimumValue2(TreeNode root) {
        int rootVal = root.val;
        int secondSmall =Integer.MAX_VALUE;
        boolean diffFound = false;
        Queue<TreeNode> Q= new LinkedList<TreeNode>();
        Q.add(root);

        while(!Q.isEmpty()) {
            TreeNode curr=Q.poll();
            if(curr.val!=rootVal && curr.val < secondSmall) {
                secondSmall=curr.val;
                diffFound=true;
            }
            if(curr.left!=null) {
                Q.add(curr.left);
                Q.add(curr.right);
            }
        }

        return (secondSmall == Integer.MAX_VALUE && !diffFound) ? -1 : secondSmall;
    }
///////////////////////////////////////////////////////////////////////////
}
/*

 */

/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
