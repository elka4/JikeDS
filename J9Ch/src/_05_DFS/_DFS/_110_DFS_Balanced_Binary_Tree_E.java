package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;

//110. Balanced Binary Tree

public class _110_DFS_Balanced_Binary_Tree_E {
    //Java solution based on height, check left and right node in every recursion to avoid further useless search
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        return height(root)!=-1;

    }
    public int height(TreeNode node){
        if(node==null){
            return 0;
        }
        int lH=height(node.left);
        if(lH==-1){
            return -1;
        }
        int rH=height(node.right);
        if(rH==-1){
            return -1;
        }
        if(lH-rH<-1 || lH-rH>1){
            return -1;
        }
        return Math.max(lH,rH)+1;
    }
//-------------------------------------------------------------------------///

    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public class Jiuzhang1 {
        /**
         * @param root: The root of binary tree.
         * @return: True if this Binary tree is Balanced, or false.
         */
        public boolean isBalanced(TreeNode root) {
            return helper(root).isBalanced;
        }

        private ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(true, 0);
            }

            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            // subtree not balance
            if (!left.isBalanced || !right.isBalanced) {
                return new ResultType(false, -1);
            }

            // root not balance
            if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
                return new ResultType(false, -1);
            }

            return new ResultType(true, Math.max(left.maxDepth, right.maxDepth) + 1);
        }
    }

    // Version 2: without ResultType
    public class Jiuzhang2 {
        public boolean isBalanced(TreeNode root) {
            return maxDepth(root) != -1;
        }

        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }


//-------------------------------------------------------------------------///






}
/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the
 depth of the two subtrees of every node never differ by more than 1.


 */