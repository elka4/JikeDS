package s_5_DP1;
import lib.*;

// House Robber III
public class _3House_Robber_III {
    //dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
    public int houseRobber3_1(TreeNode root) {
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

//------------------------------------------------------------------------------////////

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
/*
在上次打劫完一条街道之后和一圈房屋之后，窃贼又发现了一个新的可以打劫的地方，但这次所有的房子组成的区域比较奇怪，聪明的窃贼考察地形之后，发现这次的地形是一颗二叉树。与前两次偷窃相似的是每个房子都存放着特定金额的钱。

你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且当相邻的两个房子同一天被打劫时，该系统会自动报警。

算一算，如果今晚去打劫，你最多可以得到多少钱，当然在不触动报警装置的情况下。

您在真实的面试中是否遇到过这个题？ Yes
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