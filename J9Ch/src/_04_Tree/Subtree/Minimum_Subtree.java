package _04_Tree.Subtree;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/** 596. Minimum Subtree
 *
 * Given a binary tree, find the subtree with minimum sum.
 * Return the root of the subtree.
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */

// subtree with minimum sum.

public class Minimum_Subtree {

    // version 1 : traverse + divide conquer
    private TreeNode subtree = null;
    private int subtreeSum = Integer.MAX_VALUE;

    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return subtree;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = helper(root.left) + helper(root.right) + root.val;
        if (sum <= subtreeSum) {
            subtreeSum = sum;
            subtree = root;
        }
        return sum;
    }

    @Test
    public void test01(){
        int[] arr = {1,-5,2,0,2,-4,-5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree(root);
        System.out.println("root: ");
        result.print();

    }

    @Test
    public void test02(){
        int[] arr = {1,-5,2,3,-2,7,-5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree(root);
        System.out.println("root: ");
        result.print();
    }

///////////////////////////////////////////////////////////////////////////

    // version 2: Pure divide conquer

    class ResultType {
        public TreeNode minSubtree;
        public int sum, minSum;
        public ResultType(TreeNode minSubtree, int minSum, int sum) {
            this.minSubtree = minSubtree;
            this.minSum = minSum;
            this.sum = sum;
        }
    }

    public TreeNode findSubtree_2(TreeNode root) {
        ResultType result = helper_2(root);
        return result.minSubtree;
    }

    public ResultType helper_2(TreeNode node) {
        if (node == null) {
            return new ResultType(null, Integer.MAX_VALUE, 0);
        }

        ResultType leftResult = helper_2(node.left);
        ResultType rightResult = helper_2(node.right);

        ResultType result = new ResultType(
                node,
                leftResult.sum + rightResult.sum + node.val,
                leftResult.sum + rightResult.sum + node.val
        );

        if (leftResult.minSum <= result.minSum) {
            result.minSum = leftResult.minSum;
            result.minSubtree = leftResult.minSubtree;
        }

        if (rightResult.minSum <= result.minSum) {
            result.minSum = rightResult.minSum;
            result.minSubtree = rightResult.minSubtree;
        }

        return result;
    }

    @Test
    public void test03(){
        int[] arr = {1,-5,2,0,2,-4,-5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree_2(root);
        System.out.println("root: ");
        result.print();

    }
    @Test
    public void test04(){
        int[] arr = {1,-5,2,3,-2,7,-5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree_2(root);
        System.out.println("root: ");
        result.print();

    }

    /*
Given a binary tree:

     1
   /   \
 -5     2
 / \   /  \
0   2 -4  -5
return the node 1.
 */


/////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode result = null;
    public int maximum_weight = Integer.MIN_VALUE;

    public TreeNode findSubtree3(TreeNode root) {
        // Write your code here
        helper3(root);

        return result;
    }

    public int helper3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_weight = helper3(root.left);
        int right_weight = helper3(root.right);

        if (result == null || left_weight + right_weight + root.val > maximum_weight) {
            maximum_weight = left_weight + right_weight + root.val;
            result = root;
        }

        return left_weight + right_weight + root.val;
    }

    @Test
    public void test3(){
        int[] arr = {0,-5,3,1,-4,2,-5};
        TreeNode root = TreeNode.createMinimalBST(arr);
//        root.right.setLeftChild(new TreeNode(15));
//        root.right.setRightChild(new TreeNode(7));
        root.print();
        findSubtree3(root).print();

//        List<List<Integer>> result = verticalOrder2(root);

//        System.out.println(result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////


}
