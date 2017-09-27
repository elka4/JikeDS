package J_3_BTree_Divide.Optional_11;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
/**
 475
 Binary Tree Maximum Path Sum II

 * Created by tianhuizhu on 6/28/17.
 */
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

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

    }

}
