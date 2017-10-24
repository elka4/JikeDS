package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _098_DFS_Validate_Binary_Search_Tree_M {
    public class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
            if (root == null) return true;
            if (root.val >= maxVal || root.val <= minVal) return false;
            return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
        }
    }

    public class Solution2 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Optional.empty(), Optional.empty());
        }

        private boolean isValidBST(TreeNode node, Optional<Integer> min, Optional<Integer> max) {
            if (node == null) {
                return true;
            }
            if ((min.isPresent() && node.val <= min.get()) || (max.isPresent() && node.val >= max.get())) {
                return false;
            }
            Optional val = Optional.of(node.val);
            return isValidBST(node.left, min, val) && isValidBST(node.right, val, max);
        }
    }

    class SOlution3{
        Integer min = null;
        public boolean isValidBST(TreeNode root) {
            if(root == null){
                return true;
            }
            if(isValidBST(root.left) && (min == null || root.val > min)){
                min = root.val;
                return isValidBST(root.right);
            }
            return false;
        }
    }

    class Solution4{
        private boolean help(TreeNode p, Integer low, Integer high) {
            if (p == null) return true;
            return (low == null || p.val > low) && (high == null || p.val < high) && help(p.left, low, p.val) && help(p.right, p.val, high);
        }

        public boolean isValidBST(TreeNode root) {
            return help(root, null, null);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */