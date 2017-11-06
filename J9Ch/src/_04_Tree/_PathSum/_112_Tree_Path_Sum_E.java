package _04_Tree._PathSum;
import java.util.*;
import lib.TreeNode;


//  111. Minimum Depth of Binary Tree
//  https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
//  http://www.lintcode.com/zh-cn/problem/minimum-depth-of-binary-tree/
public class _112_Tree_Path_Sum_E {
//    My 4 Line java solution
    public class Solution {
        public int minDepth(TreeNode root) {
            if(root == null) return 0;
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

        }
    }
////////////////////////////////////////////////////////////////////////////
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null && sum - root.val == 0)
            return true;

        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }

////////////////////////////////////////////////////////////////////////////
/*My solution used level-order traversal
    level-order traversal and record current level depth, when meet a node which both child is null then return, no need to go farther*/

    public class Solution3 {
        public int minDepth(TreeNode root) {
            if (root == null)
                return 0;
            int depth = 1;
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            TreeNode temp,magic = new TreeNode(0);
            queue.add(root);
            queue.add(magic);
            while(!queue.isEmpty()){
                temp = queue.poll();
                if(temp.equals(magic)){
                    if(!queue.isEmpty()){
                        depth++;
                        queue.add(magic);
                    }
                    continue;
                }
                if(temp.left == null && temp.right == null)
                    return depth;
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
            }
            return depth;
        }
    }
////////////////////////////////////////////////////////////////////////////
    //jiuzhang
public class iuzhang {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}
////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}
/*
二叉树的最小深度

 描述
 笔记
 数据
 评测
给定一个二叉树，找出其最小深度。

二叉树的最小深度为根节点到最近叶子节点的距离。
样例
给出一棵如下的二叉树:

        1

     /     \

   2       3

          /    \

        4      5

这个二叉树的最小深度为 2
 */

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.


 */
