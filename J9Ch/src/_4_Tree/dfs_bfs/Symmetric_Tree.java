package _4_Tree.dfs_bfs;

import lib.*;

/*
LeetCode – Symmetric Tree (Java)

Problem

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:

    1
   / \
  2   2
   \   \
   3    3
 */
public class Symmetric_Tree {

    /*Java Solution - Recursion

    This problem can be solve by using a simple recursion. The key is finding the conditions that return false, such as value is not equal, only one node(left or right) has value.*/

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }


    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (r == null || l == null) {
            return false;
        }

        if (l.val != r.val)
            return false;

        if (!isSymmetric(l.left, r.right))
            return false;
        if (!isSymmetric(l.right, r.left))
            return false;

        return true;
    }




////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////

}
