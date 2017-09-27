package j_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tzh on 3/2/17.
 */
public class _480Binary_Tree_Paths {
    // version 1: Divide Conquer
    public class Solution1 {
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
    }

    // version 2: traverse
    public class Solution2 {
        /**
         * @param root the root of the binary tree
         * @return all root-to-leaf paths
         */
        public List<String> binaryTreePaths(TreeNode root) {
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
                helper(root.left, path + "->" + String.valueOf(root.left.val), result);
            }

            if (root.right != null) {
                helper(root.right, path + "->" + String.valueOf(root.right.val), result);
            }
        }
    }
}
