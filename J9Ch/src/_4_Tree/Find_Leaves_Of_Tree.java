package _4_Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import lib.*;

// Find Leaves Of Tree
public class Find_Leaves_Of_Tree {

    //366. Find Leaves of Binary Tree
    //10 lines simple Java solution using recursion with explanation
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

    @Test
    public void test01(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.print();
        System.out.println(findLeaves(root));

    }

////////////////////////////////////////////////////////////////////////////////////
    //Java backtracking O(n) time O(n) space No hashing!
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper2(list, root);
        return list;
    }

    // return the level of root
    private int findLeavesHelper2(List<List<Integer>> list, TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftLevel = findLeavesHelper2(list, root.left);
        int rightLevel = findLeavesHelper2(list, root.right);
        int level = Math.max(leftLevel, rightLevel) + 1;
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        root.left = root.right = null;
        return level;
    }

////////////////////////////////////////////////////////////////////////////////////
    //1 ms Easy understand Java Solution
    public List<List<Integer>> findLeaves3(TreeNode root) {

        List<List<Integer>> leavesList = new ArrayList< List<Integer>>();
        List<Integer> leaves = new ArrayList<Integer>();

        while(root != null) {
            if(isLeave3(root, leaves)) root = null;
            leavesList.add(leaves);
            leaves = new ArrayList<Integer>();
        }
        return leavesList;
    }

    public boolean isLeave3(TreeNode node, List<Integer> leaves) {

        if (node.left == null && node.right == null) {
            leaves.add(node.val);
            return true;
        }

        if (node.left != null) {
            if(isLeave3(node.left, leaves))  node.left = null;
        }

        if (node.right != null) {
            if(isLeave3(node.right, leaves)) node.right = null;
        }

        return false;
    }
////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////



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