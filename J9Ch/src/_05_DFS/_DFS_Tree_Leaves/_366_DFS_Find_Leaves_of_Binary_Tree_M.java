package _05_DFS._DFS_Tree_Leaves;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _366_DFS_Find_Leaves_of_Binary_Tree_M {
    //10 lines simple Java solution using recursion with explanation
    public class Solution {
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            height(root, res);
            return res;
        }
        private int height(TreeNode node, List<List<Integer>> res){
            if(null==node)  return -1;
            int level = 1 + Math.max(height(node.left, res), height(node.right, res));
            if(res.size()<level+1)  res.add(new ArrayList<>());
            res.get(level).add(node.val);
            return level;
        }
    }


    //Java backtracking O(n) time O(n) space No hashing!
    public class Solution2 {
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            findLeavesHelper(list, root);
            return list;
        }

        // return the level of root
        private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftLevel = findLeavesHelper(list, root.left);
            int rightLevel = findLeavesHelper(list, root.right);
            int level = Math.max(leftLevel, rightLevel) + 1;
            if (list.size() == level) {
                list.add(new ArrayList<>());
            }
            list.get(level).add(root.val);
            root.left = root.right = null;
            return level;
        }
    }

//    1 ms Easy understand Java Solution
    public class Solution3 {
        public List<List<Integer>> findLeaves(TreeNode root) {

            List<List<Integer>> leavesList = new ArrayList< List<Integer>>();
            List<Integer> leaves = new ArrayList<Integer>();

            while(root != null) {
                if(isLeave(root, leaves)) root = null;
                leavesList.add(leaves);
                leaves = new ArrayList<Integer>();
            }
            return leavesList;
        }

        public boolean isLeave(TreeNode node, List<Integer> leaves) {

            if (node.left == null && node.right == null) {
                leaves.add(node.val);
                return true;
            }

            if (node.left != null) {
                if(isLeave(node.left, leaves))  node.left = null;
            }

            if (node.right != null) {
                if(isLeave(node.right, leaves)) node.right = null;
            }

            return false;
        }
    }
//-------------------------------------------------------------------------///




//-------------------------------------------------------------------------///






}
/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree
          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         /
        2
2. Now removing the leaf [2] would result in this tree:

          1
3. Now removing the leaf [1] would result in the empty tree:

          []
Returns [4, 5, 3], [2], [1].


 */