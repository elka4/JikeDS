package _05_DFS._DFS_Tree;

import lib.TreeNode;

import java.util.*;
import java.util.List;

import lib.TreeNode;

import java.util.*;
import java.util.List;

//  199. Binary Tree Right Side View
//  https://leetcode.com/problems/binary-tree-right-side-view/description/
//
public class _199_DFS_Binary_Tree_Right_Side_View_M {

    //https://leetcode.com/articles/binary-tree-right-side-view/
    //    Initial Thoughts
    //    Approach #1 Depth-First Search [Accepted]
    public List<Integer> rightSideView01(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        /* These two stacks are always synchronized, providing an implicit
         * association values with the same offset on each stack. */
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> depthStack = new Stack<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                /* The first node that we encounter at a particular depth contains
                * the correct value. */
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }

        /* Construct the solution based on the values that we end up with at the
         * end. */
        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    //-------------------------------------------------------------------------------
    //    Approach #2 Breadth-First Search [Accepted]
    public List<Integer> rightSideView2(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

            /* These two Queues are always synchronized, providing an implicit
             * association values with the same offset on each Queue. */
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> depthQueue = new LinkedList<Integer>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                    /* The last node that we encounter at a particular depth contains
                    * the correct value, so the correct value is never overwritten. */
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth+1);
                depthQueue.add(depth+1);
            }
        }

            /* Construct the solution based on the values that we end up with at the
             * end. */
        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    //-------------------------------------------------------------------------------
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView3(root, result, 0);
        return result;
    }

    public void rightView3(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView3(curr.right, result, currDepth + 1);
        rightView3(curr.left, result, currDepth + 1);
    }

    //-------------------------------------------------------------------------------
    // 9Ch
    private void dfs(HashMap<Integer, Integer> depthToValue, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        depthToValue.put(depth, node.val);
        dfs(depthToValue, node.left, depth + 1);
        dfs(depthToValue, node.right, depth + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> depthToValue = new HashMap<Integer, Integer>();
        dfs(depthToValue, root, 1);

        int depth = 1;
        List<Integer> result = new ArrayList<Integer>();
        while (depthToValue.containsKey(depth)) {
            result.add(depthToValue.get(depth));
            depth++;
        }
        return result;
    }

//-------------------------------------------------------------------------------
}
/*

 */

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].


 */