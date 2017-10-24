package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _501_Tree_Find_Mode_in_Binary_Search_Tree_E {
    public class Solution {

        public int[] findMode(TreeNode root) {
            inorder(root);
            modes = new int[modeCount];
            modeCount = 0;
            currCount = 0;
            inorder(root);
            return modes;
        }

        private int currVal;
        private int currCount = 0;
        private int maxCount = 0;
        private int modeCount = 0;

        private int[] modes;

        private void handleValue(int val) {
            if (val != currVal) {
                currVal = val;
                currCount = 0;
            }
            currCount++;
            if (currCount > maxCount) {
                maxCount = currCount;
                modeCount = 1;
            } else if (currCount == maxCount) {
                if (modes != null)
                    modes[modeCount] = currVal;
                modeCount++;
            }
        }

        private void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            handleValue(root.val);
            inorder(root.right);
        }
    }



/*    private void inorder(TreeNode root) { TreeNode node = root;
        while (node != null) {
            if (node.left == null) { handleValue(node.val); node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left; } else {
                    prev.right = null; handleValue(node.val); node = node.right;
                } }
        } }*/
}
