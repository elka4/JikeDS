package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import org.junit.Test;

public class _6_PathSum3_ReturntheMaxPathSumOnTheSubsectionOfCompletePathFromRootToLeaf {

    public int maxPathSum(TreeNode root) {
        if (root == null)
          return Integer.MIN_VALUE;

        int[] max = {Integer.MIN_VALUE};

        helper(root, max);
        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null)
          return 0;

        int left = helper(root.left, max);
        int right = helper(root.right, max);

        left = Math.max(left, 0);
        right = Math.max(right, 0);

        max[0] = Math.max(max[0], Math.max(left, right) + root.val);

        return Math.max(left, right) + root.val;
    }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum(root));
    }

}
