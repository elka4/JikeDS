package _4_Tree;

import lib.TreeNode;

public class BinaryTreeMaximumNode {
    int max = Integer.MIN_VALUE;
    public TreeNode maxNode(TreeNode root) {
        // write your code here
        // Not AC
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left == null) {
            if (root.val > root.right.val) {
                return root;
            } else {
                return root.right;
            }
        }

        if (root.right == null) {
            if (root.val > root.left.val) {
                return root;
            } else {
                return root.left;
            }
        }



        TreeNode left = maxNode(root.left);
        TreeNode right = maxNode(root.right);

        int curMax = Integer.MIN_VALUE;

        if (left != null && right != null) {
            int tmp = Math.max(root.val, Math.max(left.val, right.val));
            curMax = Math.max(tmp, curMax);
        }
        if (left == null) {
            int tmp = Math.max(root.val, right.val);
            curMax = Math.max(tmp, curMax);

        }
        if (right == null) {
            int tmp  = Math.max(curMax, left.val);
            curMax = Math.max(tmp, curMax);
        }

        if (curMax > max) {
            max = curMax;
        }

        if (curMax == left.val) {
            return left;
        } else if (curMax == right.val) {
            return right;
        } else  {
            return root;
        }


    }
////////////////////////////////////////////////////////???
    // jiuzhang

    public TreeNode maxNode2(TreeNode root) {
        // Write your code here
        if (root == null)
            return root;

        TreeNode left = maxNode2(root.left);
        TreeNode right = maxNode2(root.right);
        return max(root, max(left, right));
    }

    TreeNode max(TreeNode a, TreeNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.val > b.val) {
            return a;
        }
        return b;
    }
}
