package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _156_Tree_Binary_Tree_Upside_Down_M {

    public class Solution {
        public TreeNode UpsideDownBinaryTree(TreeNode root) {
            TreeNode curr = root;
            TreeNode prev = null;
            TreeNode next = null;
            TreeNode temp = null;

            while (curr != null) {
                next = curr.left;
                curr.left = temp;
                temp = curr.right;
                curr.right = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }
    }
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;

        return newRoot;
    }


}
