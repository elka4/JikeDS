package _04_Tree._BST;

import lib.TreeNode;


//
//
//
public class _669_Tree_Trim_a_Binary_Search_Tree_E {

    class Solution {
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) return null;

            if (root.val < L) return trimBST(root.right, L, R);
            if (root.val > R) return trimBST(root.left, L, R);

            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);

            return root;
        }
    }


    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }

        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
/*

 */
/*

 */
