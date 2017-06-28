package J_3_Binary_Tree_Divide_Conquer.Required_10;
import J_3_Binary_Tree_Divide_Conquer.Related_2._155_Minimum_Depth_of_Binary_Tree_Easy;

import java.util.*;
/**93. Balanced Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _93_Balanced_Binary_Tree_Easy {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

// Version 1: with ResultType
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
    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public class Solution1 {
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
    public class Solution2 {
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
}
