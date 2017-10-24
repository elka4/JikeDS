package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _129_DFS_Sum_Root_to_Leaf_Numbers_M {

    public class Solution {
        public int sumNumbers(TreeNode root) {
            if (root == null)
                return 0;
            return sumR(root, 0);
        }
        public int sumR(TreeNode root, int x) {
            if (root.right == null && root.left == null)
                return 10 * x + root.val;
            int val = 0;
            if (root.left != null)
                val += sumR(root.left, 10 * x + root.val);
            if (root.right != null)
                val += sumR(root.right, 10 * x + root.val);
            return val;
        }
    }

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }
}
