package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Maximum_Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**
 475
 Binary Tree Maximum Path Sum II

 * Created by tianhuizhu on 6/28/17.
 */
// root to any
public class _475_Binary_Tree_Maximum_Path_Sum_II {

    /**
     * @param root the root of binary tree.
     * @return an integer
     */
    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int left = maxPathSum2(root.left);
        int right = maxPathSum2(root.right);
        return root.val + Math.max(0, Math.max(left, right));
    }

/*
  1
 / \
2   3
return 6.
 */

    @Test
    public void test01(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }

    @Test
    public void test02(){
        int[] arr = {-1,-5,11,1,2,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.left.setLeftChild(new TreeNode(-8));
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }

}
