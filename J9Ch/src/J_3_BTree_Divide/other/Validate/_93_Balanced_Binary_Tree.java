package J_3_BTree_Divide.other.Validate;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**93. Balanced Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _93_Balanced_Binary_Tree {

// Version 1: with ResultType
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

        root.print();// I added

        // subtree not balance
        if (!left.isBalanced || !right.isBalanced) {
            return new ResultType(false, -1);
        }

        // root not balance  能走到这一步说明subtree都是balanced
        if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
            return new ResultType(false, -1);
        }

        return new ResultType(true,
                Math.max(left.maxDepth, right.maxDepth) + 1);
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_1(root));
    }

    @Test
    public void test02(){
        TreeNode root = new TreeNode(3);
        root.setRightChild(new TreeNode(20));
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_1(root));
    }

//-------------------------------------------------------------------------------

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
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_2(root));
    }
    @Test
    public void test04(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        //root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_2(root));
    }
    @Test
    public void test05(){
        TreeNode root = new TreeNode(3);
        root.setRightChild(new TreeNode(20));
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(isBalanced_2(root));
    }
}
