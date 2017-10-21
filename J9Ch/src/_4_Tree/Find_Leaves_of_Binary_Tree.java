package _4_Tree;

import lib.*;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Find Leaves of Binary Tree (Java)

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree

          1
         / \
        2   3
       / \
      4   5
Returns [4, 5, 3], [2], [1].

Java Solution

The key to solve this problem is converting the problem to be finding the index of the element in the result list. Then this is a typical DFS problem on trees.
 */

public class Find_Leaves_of_Binary_Tree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, root);
        return result;
    }


    // traverse the tree bottom-up recursively
    private int helper(List<List<Integer>> list, TreeNode root){
        if(root==null)
            return -1;

        int left = helper(list, root.left);
        int right = helper(list, root.right);

        int curr = Math.max(left, right)+1;

        // the first time this code is reached is when curr==0,
        //since the tree is bottom-up processed.
        if(list.size()<=curr){
            list.add(new ArrayList<Integer>());
        }

        list.get(curr).add(root.val);

        return curr;
    }


//////////////////////////////////////////////////////////////////

    //10 lines simple Java solution using recursion with explanation
    public class Solution2 {
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
    /*For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
    The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees

    I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
    The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.

            UPDATE:
    Thanks @adrianliu0729 for pointing out that my previous code does not actually remove leaves. I added one line node.left = node.right = null; to remove visited nodes

    UPDATE:
    There seems to be some debate over whether we need to actually "remove" leaves from the input tree. Anyway, it is just a matter of one line code. In the actual interview, just confirm with the interviewer whether removal is required.*/


//////////////////////////////////////////////////////////////

/*    Java backtracking O(n) time O(n) space No hashing!
    The essential of problem is not to find the leaves, but group leaves of same level together and also to cut the tree. This is the exact role backtracking plays. The helper function returns the level which is the distance from its furthest subtree leaf to root, which helps to identify which group the root belongs to*/

    public class Solution3 {
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


//////////////////////////////////////////////////////////////

    //  1 ms Easy understand Java Solution
    public class Solution4 {
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


//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////


}
