package _04_Tree._LCA;

import lib.TreeNode;


//
//
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
}
/*

 */
/*

 */
