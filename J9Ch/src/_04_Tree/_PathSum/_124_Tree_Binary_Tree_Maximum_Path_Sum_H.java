package _04_Tree._PathSum;

import lib.TreeNode;


//
//
//
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


}
/*

 */
/*

 */
