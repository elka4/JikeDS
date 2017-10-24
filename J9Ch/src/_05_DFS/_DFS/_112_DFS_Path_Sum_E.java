package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _112_DFS_Path_Sum_E {
    public class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if(root == null) return false;

            if(root.left == null && root.right == null && sum - root.val == 0) return true;

            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */