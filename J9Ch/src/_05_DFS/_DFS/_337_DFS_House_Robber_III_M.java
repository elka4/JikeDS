package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _337_DFS_House_Robber_III_M {

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
}
