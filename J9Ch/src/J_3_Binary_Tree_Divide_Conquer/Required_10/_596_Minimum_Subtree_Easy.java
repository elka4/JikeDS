package J_3_Binary_Tree_Divide_Conquer.Required_10;
import java.util.*;
/** 596. Minimum Subtree
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _596_Minimum_Subtree_Easy {

    // version 1 : traverse + divide conquer
    public class Solution1 {
        private TreeNode subtree = null;
        private int subtreeSum = Integer.MAX_VALUE;
        /**
         * @param root the root of binary tree
         * @return the root of the minimum subtree
         */
        public TreeNode findSubtree(TreeNode root) {
            helper(root);
            return subtree;
        }

        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int sum = helper(root.left) + helper(root.right) + root.val;
            if (sum <= subtreeSum) {
                subtreeSum = sum;
                subtree = root;
            }
            return sum;
        }
    }

    // version 2: Pure divide conquer
    class ResultType {
        public TreeNode minSubtree;
        public int sum, minSum;
        public ResultType(TreeNode minSubtree, int minSum, int sum) {
            this.minSubtree = minSubtree;
            this.minSum = minSum;
            this.sum = sum;
        }
    }

    public class Solution2 {
        /**
         * @param root the root of binary tree
         * @return the root of the minimum subtree
         */
        public TreeNode findSubtree(TreeNode root) {
            ResultType result = helper(root);
            return result.minSubtree;
        }

        public ResultType helper(TreeNode node) {
            if (node == null) {
                return new ResultType(null, Integer.MAX_VALUE, 0);
            }

            ResultType leftResult = helper(node.left);
            ResultType rightResult = helper(node.right);

            ResultType result = new ResultType(
                    node,
                    leftResult.sum + rightResult.sum + node.val,
                    leftResult.sum + rightResult.sum + node.val
            );

            if (leftResult.minSum <= result.minSum) {
                result.minSum = leftResult.minSum;
                result.minSubtree = leftResult.minSubtree;
            }

            if (rightResult.minSum <= result.minSum) {
                result.minSum = rightResult.minSum;
                result.minSubtree = rightResult.minSubtree;
            }

            return result;
        }
    }
}
