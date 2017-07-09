package J_3_Binary_Tree_Divide_Conquer.Required_10;
import java.util.*;
import lib.TreeNode;
import lib.AssortedMethods;
import org.junit.Test;
/**93. Balanced Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _93_Balanced_Binary_Tree_Easy {

// Version 1: with ResultType
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }


    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced_1(TreeNode root) {
        return helper(root).isBalanced;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // subtree not balance
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType(false, -1);
        }

        // root not balance
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }

        return new ResultType(true,
                Math.max(left.maxDepth, right.maxDepth) + 1);
    }


    // Version 2: without ResultType
    public boolean isBalanced_2(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
/*
A)  3            B)    3
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
 */
    @Test
    public void test01(){
        int[] arr = {3,9,20}; // A = {3,9,20,#,#,15,7}, B = {3,#,20,15,7}
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_2(root));
//        TreeNode result = findSubtree2(root);
//        System.out.println("root: ");
//        result.print();
    }

    @Test
    public void test02(){
        int[] arr = {3}; // A = {3,9,20,#,#,15,7}, B = {3,#,20,15,7}
        TreeNode root = new TreeNode(3);
        root.setRightChild(new TreeNode(20));
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));

        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_2(root));
//        TreeNode result = findSubtree2(root);
//        System.out.println("root: ");
//        result.print();
    }
}
