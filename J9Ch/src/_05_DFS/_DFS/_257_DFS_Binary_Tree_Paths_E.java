package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _257_DFS_Binary_Tree_Paths_E {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }
    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }




    public List<String> binaryTreePaths2(TreeNode root) {

        List<String> paths = new LinkedList<>();

        if(root == null) return paths;

        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

        for (String path : binaryTreePaths2(root.left)) {
            paths.add(root.val + "->" + path);
        }

        for (String path : binaryTreePaths2(root.right)) {
            paths.add(root.val + "->" + path);
        }

        return paths;

    }
//-------------------------------------------------------------------------///

    // version 1: Divide Conquer
    public class Jiuzhang1 {
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


    public class Jiuzhang2 {
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

//-------------------------------------------------------------------------///






}
/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
Credits:
 */