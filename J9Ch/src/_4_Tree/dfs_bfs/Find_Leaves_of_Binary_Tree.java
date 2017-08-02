package _4_Tree.dfs_bfs;
import java.util.*;
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
}
