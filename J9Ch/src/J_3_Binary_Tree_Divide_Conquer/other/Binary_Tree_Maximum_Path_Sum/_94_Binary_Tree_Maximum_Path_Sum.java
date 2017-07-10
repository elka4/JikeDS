package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Maximum_Path_Sum;
import lib.TreeNode;
import lib.AssortedMethods;
import org.junit.Test;
/** 94 Binary Tree Maximum Path Sum
 * Created by tianhuizhu on 7/9/17.
 */
public class _94_Binary_Tree_Maximum_Path_Sum {
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;

        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }

/*
  1
 / \
2   3
return 6.
 */

    // Version 2:
// SinglePath也定义为，至少包含一个点。
    public class Solution {
        private class ResultType {
            int singlePath, maxPath;

            ResultType(int singlePath, int maxPath) {
                this.singlePath = singlePath;
                this.maxPath = maxPath;
            }
        }

        private ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
            // Divide
            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            // Conquer
            int singlePath =
                    Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

            int maxPath = Math.max(left.maxPath, right.maxPath);
            maxPath = Math.max(maxPath,
                    Math.max(left.singlePath, 0) +
                            Math.max(right.singlePath, 0) + root.val);

            return new ResultType(singlePath, maxPath);
        }

        public int maxPathSum(TreeNode root) {
            ResultType result = helper(root);
            return result.maxPath;
        }


    }
}