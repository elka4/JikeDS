package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**480. Binary Tree Paths
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _480_Binary_Tree_Paths {

    // version 1: Divide Conquer
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }

        // root is a leaf
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }

        return paths;
    }

//-------------------------------------------------------------------------/////////////

    // version 2: traverse
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths_2(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null) {
            return result;
        }
        helper(root, String.valueOf(root.val), result);
        return result;
    }

    private void helper(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        if (root.left != null) {
            helper(root.left, path + "->"
                    + String.valueOf(root.left.val), result);
        }

        if (root.right != null) {
            helper(root.right, path + "->"
                    + String.valueOf(root.right.val), result);
        }
    }

//-------------------------------------------------------------------------/////////////

    /*
   1
 /   \
2     3
 \
  5
     */
    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.setRightChild(new TreeNode(5));
        root.left.setLeftChild(new TreeNode(4));
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePaths(root));

    }
}
