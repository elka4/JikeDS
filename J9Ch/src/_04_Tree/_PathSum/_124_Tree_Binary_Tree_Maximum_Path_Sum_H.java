package _04_Tree._PathSum;
import java.util.*;
import lib.TreeNode;

//  124. Binary Tree Maximum Path Sum
//  https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-maximum-path-sum/
public class _124_Tree_Binary_Tree_Maximum_Path_Sum_H {
    public class Solution {
        int maxValue;

        public int maxPathSum(TreeNode root) {
            maxValue = Integer.MIN_VALUE;
            maxPathDown(root);
            return maxValue;
        }

        private int maxPathDown(TreeNode node) {
            if (node == null) return 0;
            int left = Math.max(0, maxPathDown(node.left));
            int right = Math.max(0, maxPathDown(node.right));
            maxValue = Math.max(maxValue, left + right + node.val);
            return Math.max(left, right) + node.val;
        }
    }



    public class Solution2 {
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        // helper returns the max branch
        // plus current node's value
        int helper(TreeNode root) {
            if (root == null) return 0;

            int left = Math.max(helper(root.left), 0);
            int right = Math.max(helper(root.right), 0);

            max = Math.max(max, root.val + left + right);

            return root.val + Math.max(left, right);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang1 {
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}


    // Version 2:
// SinglePath也定义为，至少包含一个点。
    public class Jiuzhang2 {

        private class ResultType {
            int singlePath, maxPath;
            ResultType(int singlePath, int maxPath) {
                this.singlePath = singlePath;
                this.maxPath = maxPath;
            }
        }

        private ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
            // Divide
            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            // Conquer
            int singlePath =
                    Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

            int maxPath = Math.max(left.maxPath, right.maxPath);
            maxPath = Math.max(maxPath,
                    Math.max(left.singlePath, 0) +
                            Math.max(right.singlePath, 0) + root.val);

            return new ResultType(singlePath, maxPath);
        }

        public int maxPathSum(TreeNode root) {
            ResultType result = helper(root);
            return result.maxPath;
        }

    }
//////////////////////////////////////////////////////////////////////////////////////

}
/*
二叉树中的最大路径和

 描述
 笔记
 数据
 评测
给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和）

样例
给出一棵二叉树：

       1
      / \
     2   3
返回 6
 */
/*
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 */
