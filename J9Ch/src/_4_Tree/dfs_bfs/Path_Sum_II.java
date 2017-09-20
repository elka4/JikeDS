package _4_Tree.dfs_bfs;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Path Sum II (Java)

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example, given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
the method returns the following:

[
   [5,4,11,2],
   [5,8,4,5]
]

Analysis

This problem can be converted to be a typical depth-first search problem. A recursive depth-first search algorithm usually requires a recursive method call, a reference to the final result, a temporary result, etc.
 */


public class Path_Sum_II {

    public List<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return result;

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(root.val);
        dfs(root, sum-root.val, result, l);
        return result;
    }


    public void dfs(TreeNode t, int sum, ArrayList<ArrayList<Integer>> result,
                    ArrayList<Integer> l){

        if(t.left==null && t.right==null && sum==0){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(l);
            result.add(temp);
        }

        //search path of left node
        if(t.left != null){
            l.add(t.left.val);
            dfs(t.left, sum-t.left.val, result, l);
            l.remove(l.size()-1);
        }

        //search path of right node
        if(t.right!=null){
            l.add(t.right.val);
            dfs(t.right, sum-t.right.val, result, l);
            l.remove(l.size()-1);
        }
    }


////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////



}
