package _04_Tree._List;

import lib.TreeNode;


//
//
//
public class _114_Tree_Flatten_Binary_Tree_to_Linked_List_M {


    private TreeNode prev = null;
    public void flatten(TreeNode root) { if (root == null)
        return; flatten(root.right); flatten(root.left); root.right = prev; root.left = null; prev = root;
    }




    public void flatten2(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten2(left);
        flatten2(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
}
/*

 */
/*

 */
