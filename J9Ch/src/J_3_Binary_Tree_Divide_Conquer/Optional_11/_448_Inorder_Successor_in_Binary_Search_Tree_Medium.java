package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
 448
 Inorder Successor in Binary Search Tree


 * Created by tianhuizhu on 6/28/17.
 */
public class _448_Inorder_Successor_in_Binary_Search_Tree_Medium {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode successor = null;
            while (root != null && root != p) {
                if (root.val > p.val) {
                    successor = root;
                    root = root.left;
                } else {
                    root = root.right;
                }
            }

            if (root == null) {
                return null;
            }

            if (root.right == null) {
                return successor;
            }

            root = root.right;
            while (root.left != null) {
                root = root.left;
            }

            return root;
        }
    }
}
