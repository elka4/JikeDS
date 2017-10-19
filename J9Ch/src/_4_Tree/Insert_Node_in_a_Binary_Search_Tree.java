package _4_Tree;

import lib.TreeNode;

public class Insert_Node_in_a_Binary_Search_Tree {
	 /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            root = node;
            return root;
        }
        TreeNode tmp = root;
        TreeNode last = null;
        while (tmp != null) {
            last = tmp;
            if (tmp.val > node.val) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        if (last != null) {
            if (last.val > node.val) {
                last.left = node;
            } else {
                last.right = node;
            }
        }
        return root;
    }
////////////////////////////////////////////////////////////////////////

    class _85Insert_Node_in_a_Binary_Search_Tree_Recursion {
        /**
         * @param root: The root of the binary search tree.
         * @param node: insert this node into the binary search tree
         * @return: The root of the new binary search tree.
         */
        public TreeNode insertNode(TreeNode root, TreeNode node) {
            if (root == null) {
                return node;
            }
            if (root.val > node.val) {
                root.left = insertNode(root.left, node);
            } else {
                root.right = insertNode(root.right, node);
            }
            return root;
        }
    }
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////

}
/*
 * Given a binary search tree and a new tree node,
 * insert the node into the tree. You should keep the
 * tree still be a valid binary search tree.

 Notice:
You can assume there is no duplicate values in this tree + node.

Example
Given binary search tree as follow,
after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \
  3             3   6
Challenge: Can you do it without recursion?

Tags: LintCode Copyright Binary Search Tree
Related Problems: Hard Remove Node in Binary Search Tree 25 %
 * */
