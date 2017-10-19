package j_3_BianryTree;

import lib.TreeNode;

// Subtree with Maximum Average
/**
 * Created by tzh on 3/2/17.
 */
public class Subtree_with_Maximum_Average {
    private class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    private TreeNode subtree = null;
    private ResultType subtreeResult = null;

    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        helper(root);
        return subtree;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );

        if (subtree == null ||
                subtreeResult.sum * result.size < result.sum * subtreeResult.size
                ) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }


    ///////////////////////////////////////////////////////

    public class Solution {
        /**
         * @param root the root of binary tree
         * @return the root of the maximum average of subtree
         */

        class ResultType {
            TreeNode node;
            int sum;
            int size;
            public ResultType(TreeNode node, int sum, int size) {
                this.node = node;
                this.sum = sum;
                this.size = size;
            }
        }

        private ResultType result = null;

        public TreeNode findSubtree2(TreeNode root) {
            // Write your code here
            if (root == null) {
                return null;
            }

            ResultType rootResult = helper(root);
            return result.node;
        }

        public ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(null, 0, 0);
            }

            ResultType leftResult = helper(root.left);
            ResultType rightResult = helper(root.right);

            ResultType currResult = new ResultType(
                    root,
                    leftResult.sum + rightResult.sum + root.val,
                    leftResult.size + rightResult.size + 1);

            if (result == null
                    || currResult.sum * result.size > result.sum * currResult.size) {
                result = currResult;
            }

            return currResult;
        }

    }
}
