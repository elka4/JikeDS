package _04_Tree._LCA;
import lib.TreeNode;

//  235. Lowest Common Ancestor of a Binary Search Tree
//  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
public class _235_Tree_Lowest_Common_Ancestor_of_a_Binary_Search_Tree_E {

    //3 lines with O(1) space, 1-Liners, Alternatives
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right; return root;
    }

//-----------------------------------------------------------------------------
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return (root.val - p.val) * (root.val - q.val) < 1 ? root :
    lowestCommonAncestor2(p.val < root.val ? root.left : root.right, p, q);

    }
//-----------------------------------------------------------------------------
    //    My Java Solution
    public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
        //root比p，q都大，那就是root太大了，那么把比较小的root.left代入method
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor5(root.left, p, q);
            //root比p，q都小，那就是root太小了，那么把比较大的root.right代入method
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor5(root.right, p, q);
        }else{
            return root;
        }
    }

//-----------------------------------------------------------------------------
    //    11ms java solution, 3 lines
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val<Math.min(p.val,q.val))
            return lowestCommonAncestor4(root.right,p,q);
        if(root.val>Math.max(p.val,q.val))
            return lowestCommonAncestor4(root.left,p,q);
        return root;
    }

//------------------------------------------------------------------------------
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
