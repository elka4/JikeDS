package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _285_Tree_Inorder_Successor_in_BST_M {
//    Successor

    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }
//    Predecessor

    public TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val >= p.val) {
            return predecessor(root.left, p);
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }


    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        while (root != null && root.val <= p.val)
            root = root.right;
        if (root == null)
            return null;
        TreeNode left = inorderSuccessor2(root.left, p);
        return (left != null && left.val > p.val) ? left : root;
    }
//    And another:

    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        while (root != null && root.val <= p.val)
            root = root.right;
        TreeNode left = root == null ? null : inorderSuccessor3(root.left, p);
        return (left != null && left.val > p.val) ? left : root;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return succ;
    }

}
