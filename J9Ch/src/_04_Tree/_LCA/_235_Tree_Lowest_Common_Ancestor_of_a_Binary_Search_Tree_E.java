package _04_Tree._LCA;

import lib.TreeNode;


//
//  leetcode    235. Lowest Common Ancestor of a Binary Search Tree
//
public class _235_Tree_Lowest_Common_Ancestor_of_a_Binary_Search_Tree_E {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right; return root;
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return (root.val - p.val) * (root.val - q.val) < 1 ? root :
    lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);

    }


    public class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root.val > p.val && root.val > q.val){
                return lowestCommonAncestor(root.left, p, q);
            }else if(root.val < p.val && root.val < q.val){ return lowestCommonAncestor(root.right, p, q);
            }else{
                return root;
            } }
    }
    //////////////////////////////////////////////////////////////////////////////
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){        //root比p，q都大，那就是root太大了，那么把比较小的root.left代入method
            return lowestCommonAncestor1(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){  //root比p，q都小，那就是root太小了，那么把比较大的root.right代入method
            return lowestCommonAncestor1(root.right, p, q);
        }else{
            return root;
        }
    }

    class solution2{
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root.val<Math.min(p.val,q.val)) return lowestCommonAncestor(root.right,p,q);
            if(root.val>Math.max(p.val,q.val)) return lowestCommonAncestor(root.left,p,q);
            return root;
        }
    }
    class Solution3{
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


//////////////////////////////////////////////////////////////////////////

}
/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */

/*

 */
