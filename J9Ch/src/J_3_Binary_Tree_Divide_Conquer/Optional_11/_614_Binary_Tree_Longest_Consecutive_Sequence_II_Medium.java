package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
 * 614
 * Binary Tree Longest Consecutive Sequence II


 * Created by tianhuizhu on 6/28/17.
 */
public class _614_Binary_Tree_Longest_Consecutive_Sequence_II_Medium {


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class ResultType {
        public int max_length, max_down, max_up;
        ResultType(int len, int down, int up) {
            max_length = len;
            max_down = down;
            max_up = up;
        }
    }

    public class Solution {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }
        /**
         * @param root the root of binary tree
         * @return the length of the longest consecutive sequence path
         */
        public int longestConsecutive2(TreeNode root) {
            // Write your code here
            return helper(root).max_length;
        }

        ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(0, 0, 0);
            }

            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            int down = 0, up = 0;
            if (root.left != null && root.left.val + 1 == root.val)
                down = Math.max(down, left.max_down + 1);

            if (root.left != null && root.left.val - 1 == root.val)
                up = Math.max(up, left.max_up + 1);

            if (root.right != null && root.right.val + 1 == root.val)
                down = Math.max(down, right.max_down + 1);

            if (root.right != null && root.right.val - 1 == root.val)
                up = Math.max(up, right.max_up + 1);

            int len = down + 1 + up;
            len = Math.max(len, Math.max(left.max_length, right.max_length));

            return new ResultType(len, down, up);
        }
    }
}
