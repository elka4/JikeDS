package J_3_Binary_Tree_Divide_Conquer.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**97. Maximum Depth of Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class _97_Maximum_Depth_of_Binary_Tree {

    // Version 1: Divide Conquer
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


// version 2: Traverse
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

    /*
  1
 / \
2   3
   / \
  4   5
The maximum depth is 3.
     */

    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(4));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth(root));
        System.out.println(maxDepth_2(root));
    }

}
