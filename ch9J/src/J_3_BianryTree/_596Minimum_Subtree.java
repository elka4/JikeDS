package chapter3_BianryTree;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;

/** Given a binary tree, find the subtree with minimum sum.
 * Return the root of the subtree.
 * Created by tzh on 3/2/17.
 */
public class _596Minimum_Subtree {
    // version 1 : traverse + divide conquer
    private TreeNode subtree = null;
    private int subtreeSum = Integer.MAX_VALUE;
    /**
     * @param root the root of binary tree
     * @return the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return subtree;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = helper(root.left) + helper(root.right) + root.val;
        if (sum < subtreeSum) {
            subtreeSum = sum;
            subtree = root;
        }
        return sum;
    }
}
