package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _663_Tree_Equal_Tree_Partition_M {

    public boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = getsum(root, map);
        if(sum == 0)return map.getOrDefault(sum, 0) > 1;
        return sum%2 == 0 && map.containsKey(sum/2);
    }

    public int getsum(TreeNode root, Map<Integer, Integer> map ){
        if(root == null)return 0;
        int cur = root.val + getsum(root.left, map) + getsum(root.right, map);
        map.put(cur, map.getOrDefault(cur,0) + 1);
        return cur;
    }


    class Solution {
        boolean equal = false;
        long total = 0;

        public boolean checkEqualTree(TreeNode root) {
            if (root.left == null && root.right == null) return false;
            total = getTotal(root);
            checkEqual(root);
            return equal;
        }

        private long getTotal(TreeNode root) {
            if (root == null) return 0;
            return getTotal(root.left) + getTotal(root.right) + root.val;
        }

        private long checkEqual(TreeNode root) {
            if (root == null || equal) return 0;

            long curSum = checkEqual(root.left) + checkEqual(root.right) + root.val;
            if (total - curSum == curSum) {
                equal = true;
                return 0;
            }
            return curSum;
        }
    }
}
