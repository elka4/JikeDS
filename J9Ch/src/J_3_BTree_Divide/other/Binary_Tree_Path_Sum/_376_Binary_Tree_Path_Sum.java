package J_3_BTree_Divide.other.Binary_Tree_Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 376. Binary Tree Path Sum
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */

// root to leaf
public class _376_Binary_Tree_Path_Sum {
    /**A valid path is from root node to any of the leaf nodes.
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */

    // Algorithm: Traverse
    // Use recursion to traverse the tree in preorder, pass with a parameter
    // `sum` as the sum of each node from root to current node.
    // Put the whole path into result if the leaf has a sum equal to target.

    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target, result);
        return result;
    }

    private void helper(TreeNode root,
                        ArrayList<Integer> path,
                        int sum,
                        int target,
                        List<List<Integer>> result) {

        // meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }

        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target, result);
            path.remove(path.size() - 1);
        }

        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target, result);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum(root, 5));
    }

    @Test
    public void test02() {
        int[] arr = {1, 2, 4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum(root, 6));
    }
}

/*Given a binary tree, and target = 5:

     1
    / \
   2   4
  / \
 2   3
return

[
  [1, 2, 2],
  [1, 4]
]
 */



