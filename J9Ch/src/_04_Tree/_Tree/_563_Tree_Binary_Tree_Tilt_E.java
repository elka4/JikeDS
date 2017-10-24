package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _563_Tree_Binary_Tree_Tilt_E {

    public class Solution {
        int result = 0;

        public int findTilt(TreeNode root) {
            postOrder(root);
            return result;
        }

        private int postOrder(TreeNode root) {
            if (root == null) return 0;

            int left = postOrder(root.left);
            int right = postOrder(root.right);

            result += Math.abs(left - right);

            return left + right + root.val;
        }
    }

    class Solution2{
//        helper will return sum of a subtree.

        int res = 0;
        public int findTilt(TreeNode root) {
            helper(root);
            return res;
        }

        int helper(TreeNode root){
            if(root == null) return 0;

            int l = helper(root.left);
            int r = helper(root.right);
            res += Math.abs(l - r);
            return l + r + root.val;
        }
    }


    public class Solution3 {

        public int sumOfTilt;
        public int findTilt(TreeNode root) {
            SumOfTree(root);
            return sumOfTilt;
        }
        public int SumOfTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftSum = SumOfTree(root.left);
            int rightSum = SumOfTree(root.right);

            sumOfTilt += Math.abs(leftSum - rightSum);
            return leftSum + rightSum + root.val;

        }
    }
}
