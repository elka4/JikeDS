package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _098_Tree_Validate_Binary_Search_Tree_M {


    public class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); }
        public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
            if (root == null) return true;
            if (root.val >= maxVal || root.val <= minVal) return false;
            return isValidBST(root.left, minVal, root.val) && isValidBST(root.right,
                    root.val, maxVal);
        }
    }
}
