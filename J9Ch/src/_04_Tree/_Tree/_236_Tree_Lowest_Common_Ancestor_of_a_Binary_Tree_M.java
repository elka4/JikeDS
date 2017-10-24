package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _236_Tree_Lowest_Common_Ancestor_of_a_Binary_Tree_M {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
