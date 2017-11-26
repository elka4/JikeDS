package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _337_DFS_House_Robber_III_M {
    //https://leetcode.com/problems/house-robber-iii/discuss/
    class Solution{
        public int rob(TreeNode root) {
            if (root == null) return 0;

            int val = 0;

            if (root.left != null) {
                val += rob(root.left.left) + rob(root.left.right);
            }

            if (root.right != null) {
                val += rob(root.right.left) + rob(root.right.right);
            }

            return Math.max(val + root.val, rob(root.left) + rob(root.right));
        }
    }

    class Solution2{
        public int rob(TreeNode root) {
            return robSub(root, new HashMap<>());
        }

        private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
            if (root == null) return 0;
            if (map.containsKey(root)) return map.get(root);

            int val = 0;

            if (root.left != null) {
                val += robSub(root.left.left, map) + robSub(root.left.right, map);
            }

            if (root.right != null) {
                val += robSub(root.right.left, map) + robSub(root.right.right, map);
            }

            val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
            map.put(root, val);

            return val;
        }
    }

    class Solution3{
        public int rob(TreeNode root) {
            int[] res = robSub(root);
            return Math.max(res[0], res[1]);
        }

        private int[] robSub(TreeNode root) {
            if (root == null) return new int[2];

            int[] left = robSub(root.left);
            int[] right = robSub(root.right);
            int[] res = new int[2];

            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = root.val + left[0] + right[0];

            return res;
        }
    }

    public class Solution4 {
        public int rob(TreeNode root) {
            int[] num = dfs(root);
            return Math.max(num[0], num[1]);
        }
        private int[] dfs(TreeNode x) {
            if (x == null) return new int[2];
            int[] left = dfs(x.left);
            int[] right = dfs(x.right);
            int[] res = new int[2];
            res[0] = left[1] + right[1] + x.val;
            res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            return res;
        }
    }
//----------------------------------------------------------------------------

// 9Ch
//dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
public class Jiuzhang1 {
    public int houseRobber3(TreeNode root) {
        int[] ans = dp(root);
        return Math.max(ans[0], ans[1]);
    }
    public int[] dp(TreeNode root) {
        if (root == null) {
            int[] now = new int[]{0, 0};
            return now;
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int[] now = new int[2];
        now[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        now[1] = left[0] + right[0] + root.val;
        return now;
    }
}

// version 2
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int x) { val = x; }
     * }
     */
    class ResultType {
        public int rob, not_rob;
        public ResultType() { rob = not_rob = 0; }
    }

    public class Jiuzhang2 {
        /**
         * @param root: The root of binary tree.
         * @return: The maximum amount of money you can rob tonight
         */
        public int houseRobber3(TreeNode root) {
            // write your code here
            ResultType result = visit(root);
            return Math.max(result.rob, result.not_rob);
        }

        public ResultType visit(TreeNode root) {
            ResultType result = new ResultType();
            if (root == null)
                return result;

            ResultType left_result = visit(root.left);
            ResultType right_result = visit(root.right);

            result.rob = root.val + left_result.not_rob + right_result.not_rob;
            result.not_rob = Math.max(left_result.rob, left_result.not_rob) +
                    Math.max(right_result.rob, right_result.not_rob);
            return result;
        }
    }


//----------------------------------------------------------------------------






}
/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
 */