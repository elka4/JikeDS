package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**97. Maximum Depth of Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _97_Maximum_Depth_of_Binary_Tree {

    // Version 1: Divide Conquer. With no global depth.

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(4));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth(root));
    }


/////////////////////////////////////////////////////////////////////////////////
public int minDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return getMin(root);
}

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
/////////////////////////////////////////////////////////////////////////////////

// version 2: Traverse. With global depth.

    private int depth;

    public int maxDepth_2(TreeNode root) {
        depth = 0;
        helper(root, 1);

        return depth;
    }

    private void helper(TreeNode node, int curtDepth) {
        if (node == null) {
            return;
        }

        if (curtDepth > depth) {
            depth = curtDepth;
        }

        helper(node.left, curtDepth + 1);
        helper(node.right, curtDepth + 1);
    }

    @Test
    public void test02() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(4));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth_2(root));
    }

/////////////////////////////////////////////////////////////////////////////////

/*
          1
         / \
        2   3
           / \
          4   5
        The maximum depth is 3.
 */


}
