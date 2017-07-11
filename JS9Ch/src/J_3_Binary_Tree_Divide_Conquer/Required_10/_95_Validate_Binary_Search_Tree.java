package J_3_Binary_Tree_Divide_Conquer.Required_10;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
import lib.TreeNode;
import lib.AssortedMethods;
import org.junit.Test;
/** 95. Validate Binary Search Tree
 * Medium

 * Created by tianhuizhu on 6/27/17.
 */
public class _95_Validate_Binary_Search_Tree {

    // version 1 Traverse
    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }



    // version 2  Divide and Conquer
    class ResultType {
        boolean is_bst;
        int maxValue, minValue;

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }


    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST2(TreeNode root) {
        ResultType r = validateHelper(root);
        return r.is_bst;
    }

    private ResultType validateHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = validateHelper(root.left);
        ResultType right = validateHelper(root.right);

        if (!left.is_bst || !right.is_bst) {
            // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && left.maxValue >= root.val ||
                root.right != null && right.minValue <= root.val) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true,
                Math.max(root.val, right.maxValue),
                Math.min(root.val, left.minValue));
    }


    // version 3  Divide and Conquer
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST_3(TreeNode root) {
        // write your code here
        return divConq(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean divConq(TreeNode root, long min, long max){
        if (root == null){
            return true;
        }
        if (root.val <= min || root.val >= max){
            return false;
        }
        return divConq(root.left, min, Math.min(max, root.val)) &&
                divConq(root.right, Math.max(min, root.val), max);
    }

/*
  2
 / \
1   4
   / \
  3   5
 */
    @Test
    public void test01(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.print();
        root.right.setLeftChild(new TreeNode(3));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));
//        TreeNode result = findSubtree2(root);
//        System.out.println("root: ");
//        result.print();
    }

}
