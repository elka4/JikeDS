package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
// 104. Maximum Depth of Binary Tree

public class _104_DFS_Maximum_Depth_of_Binary_Tree_E {

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
//////////////////////////////////////////////////////////////////////////////////////

    // Version 1: Divide Conquer
    public class Jiuzhang1 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }

// version 2: Traverse
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    public class Jiuzhang2 {
        /**
         * @param root: The root of binary tree.
         * @return: An integer.
         */
        private int depth;

        public int maxDepth(TreeNode root) {
            depth = 0;
            helper(root, 1);

            return depth;
        }

        private void helper(TreeNode node, int curtDepth) {
            if (node == null) {
                return;
            }

            if (curtDepth > depth) {
                depth = curtDepth;
            }

            helper(node.left, curtDepth + 1);
            helper(node.right, curtDepth + 1);
        }
    }


//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */