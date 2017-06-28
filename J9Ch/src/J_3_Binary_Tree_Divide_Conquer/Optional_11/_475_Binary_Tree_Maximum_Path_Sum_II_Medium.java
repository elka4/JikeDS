package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
 475
 Binary Tree Maximum Path Sum II

 * Created by tianhuizhu on 6/28/17.
 */
public class _475_Binary_Tree_Maximum_Path_Sum_II_Medium {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
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
    public class Solution {
        /**
         * @param root the root of binary tree.
         * @return an integer
         */
        public int maxPathSum2(TreeNode root) {
            if (root == null) {
                return Integer.MIN_VALUE;
            }

            int left = maxPathSum2(root.left);
            int right = maxPathSum2(root.right);
            return root.val + Math.max(0, Math.max(left, right));
        }
    }
}