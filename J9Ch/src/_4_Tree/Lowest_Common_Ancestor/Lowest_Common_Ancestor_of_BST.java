package _4_Tree.Lowest_Common_Ancestor;

import lib.*;

/*
LeetCode – Lowest Common Ancestor of a Binary Search Tree (Java)

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

Analysis

This problem can be solved by using BST property, i.e., left < parent < right for each node. There are 3 cases to handle.
 */

public class Lowest_Common_Ancestor_of_BST {

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


//////////////////////////////////////////////////////////////////////////


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor2(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor2(root.right, p, q);
        }else{
            return root;
        }
    }
    ///////////////////////////////////////////////////
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val<Math.min(p.val,q.val)) return lowestCommonAncestor3(root.right,p,q);
        if(root.val>Math.max(p.val,q.val)) return lowestCommonAncestor3(root.left,p,q);
        return root;
    }




//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////////////////

}
