package _4_Tree.Lowest_Common_Ancestor;

import lib.*;

public class LCA_BST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
///////////////////////////////////////////////////
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val<Math.min(p.val,q.val)) return lowestCommonAncestor2(root.right,p,q);
        if(root.val>Math.max(p.val,q.val)) return lowestCommonAncestor2(root.left,p,q);
        return root;
    }
///////////////////////////////////////////////////

///////////////////////////////////////////////////


}
