package J_3_Binary_Tree_Divide_Conquer.Required_10;
import java.util.*;
/** 597. Subtree with Maximum Average
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _597_Subtree_with_Maximum_Average_Easy {

    // version 1: Traverse + Divide Conquer
    public class Solution {
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
    }
}
