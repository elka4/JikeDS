package _04_Tree._BST;

import lib.TreeNode;


//  669. Trim a Binary Search Tree
//  https://leetcode.com/problems/trim-a-binary-search-tree/description/
//
public class _669_Trim_a_Binary_Search_Tree_E {
    //https://leetcode.com/articles/trim-a-binary-search-tree/


//--------------------------------------------------------------------------------
    //Java solution, 6 liner
    public TreeNode trimBST2(TreeNode root, int L, int R) {
        if (root == null) return null;

        if (root.val < L) return trimBST2(root.right, L, R);
        if (root.val > R) return trimBST2(root.left, L, R);

        root.left = trimBST2(root.left, L, R);
        root.right = trimBST2(root.right, L, R);

        return root;
    }


//--------------------------------------------------------------------------------
    //Simple Java recursive solution
    public TreeNode trimBST3(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }

        if (root.val > R) {
            return trimBST3(root.left, L, R);
        }

        if (root.val < L) {
            return trimBST3(root.right, L, R);
        }

        root.left = trimBST3(root.left, L, R);
        root.right = trimBST3(root.right, L, R);
        return root;
    }
//--------------------------------------------------------------------------------
    //    easy java
    public TreeNode trimBST4(TreeNode root, int L, int R) {
        if (root == null) return null;
        TreeNode left = trimBST4(root.left, L, R);
        TreeNode right = trimBST4(root.right, L, R);
        root.left = left;
        root.right = right;
        return root.val >= L && root.val <= R? root : root.val < L? right : left;
    }
//--------------------------------------------------------------------------------
}
/*
Given a binary search tree and the lowest and highest boundaries as L and R,
trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree,
so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1

 */
/*

 */
