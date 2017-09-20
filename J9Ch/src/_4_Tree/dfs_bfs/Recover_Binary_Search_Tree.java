package _4_Tree.dfs_bfs;

import lib.*;
/*
LeetCode – Recover Binary Search Tree (Java)

Two elements of a binary search tree (BST) are swapped by mistake. Recover the tree without changing its structure.

Java Solution

Inorder traveral will return values in an increasing order. So if an element is less than its previous element,the previous element is a swapped node.
 */


public class Recover_Binary_Search_Tree {

    TreeNode first;
    TreeNode second;
    TreeNode pre;

    public void inorder(TreeNode root){
        if(root==null)
            return;

        inorder(root.left);

        if(pre==null){
            pre=root;
        }else{
            if(root.val<pre.val){
                if(first==null){
                    first=pre;
                }

                second=root;
            }
            pre=root;
        }

        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        if(root==null)
            return;

        inorder(root);
        if(second!=null && first !=null){
            int val = second.val;
            second.val = first.val;
            first.val = val;
        }

    }



////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////







////////////////////////////////////////////////////////////////////////////

}
