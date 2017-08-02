package _4_Tree.dfs_bfs;

/*
LeetCode – Lowest Common Ancestor of a Binary Search Tree (Java)

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

Analysis

This problem can be solved by using BST property, i.e., left < parent < right for each node. There are 3 cases to handle.
 */
public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode m = root;

        if(m.val > p.val && m.val < q.val){
            return m;
        }else if(m.val>p.val && m.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(m.val<p.val && m.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}
