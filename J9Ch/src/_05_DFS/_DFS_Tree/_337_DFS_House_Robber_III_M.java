package _05_DFS._DFS_Tree;
import lib.TreeNode;

import java.util.HashMap;
import java.util.Map;

//  337. House Robber III
//  https://leetcode.com/problems/house-robber-iii/
//  http://www.lintcode.com/zh-cn/problem/house-robber-iii/
public class _337_DFS_House_Robber_III_M {

    //Step by step tackling of the problem
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


//------------------------------------------------------------------------------

    //Step II -- Think one step further
    //And here is the improved solution:
    public int rob2(TreeNode root) {
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

//------------------------------------------------------------------------------

    //Step III -- Think one step back
    public int rob3(TreeNode root) {
        int[] res = robSub3(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub3(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub3(root.left);
        int[] right = robSub3(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }

//-----------------------------------------------------------------------------///

    public int rob4(TreeNode root) {
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

//-------------------------------------------------------------------------///
    // 9Ch
    //dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
    public int houseRobber_J1(TreeNode root) {
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

//-------------------------------------------------------------------------///
    // 9Ch
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
    public int houseRobber_J2(TreeNode root) {
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

//-------------------------------------------------------------------------///
}
/*
打劫房屋 III

 描述
 笔记
 数据
 评测
在上次打劫完一条街道之后和一圈房屋之后，窃贼又发现了一个新的可以打劫的地方，但这次所有的房子组成的区域比较奇怪，聪明的窃贼考察地形之后，发现这次的地形是一颗二叉树。与前两次偷窃相似的是每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且当相邻的两个房子同一天被打劫时，该系统会自动报警。

算一算，如果今晚去打劫，你最多可以得到多少钱，当然在不触动报警装置的情况下。

样例
  3
 / \
2   3
 \   \
  3   1
窃贼最多能偷窃的金钱数是 3 + 3 + 1 = 7.

    3
   / \
  4   5
 / \   \
1   3   1
窃贼最多能偷窃的金钱数是 4 + 5 = 9.


 */

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
Credits:
 */
