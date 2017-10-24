package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _100_DFS_Same_Tree_E {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        return p.val == q.val && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }

    public boolean isSameTree3(TreeNode p, TreeNode q) {
        return p == null && q == null || !(p == null || q == null)
                && p.val == q.val && isSameTree3(p.left, q.left) && isSameTree3(p.right, q.right);
    }

    public boolean isSameTree4(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree4(p.left, q.left) && isSameTree4(p.right, q.right);
    }

    public boolean isSameTree5(TreeNode p, TreeNode q) {

        // Equal nullity denotes that this branch is the same (local equality)
        // This is a base case, but also handles being given two empty trees
        if (p == null && q == null) return true;

            // Unequal nullity denotes that the trees aren't the same
            // Note that we've already ruled out equal nullity above
        else if (p == null || q == null) return false;

        // Both nodes have values; descend iff those values are equal
        // "&&" here allows for any difference to overrule a local equality
        if (p.val == q.val) return isSameTree5(p.left, q.left) && isSameTree5(p.right, q.right);

        // If we're here, both nodes have values, and they're unequal, so the trees aren't the same
        return false;
    }

    public boolean isSameTree6(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        else if(p != null && q != null) {
            boolean b = p.val == q.val;
            b &= isSameTree6(p.left, q.left);
            b &= isSameTree6(p.right, q.right);
            return b;
        } else return false;
    }
    public class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p!=null && q!=null){
                if(p.val==q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right)){
                    return true;
                }
            }
            return (p==null && q==null);
        }
    }

    public boolean isSameTree7(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack_p = new Stack <> ();
        Stack<TreeNode> stack_q = new Stack <> ();
        if (p != null) stack_p.push( p ) ;
        if (q != null) stack_q.push( q ) ;
        while (!stack_p.isEmpty() && !stack_q.isEmpty()) {
            TreeNode pn = stack_p.pop() ;
            TreeNode qn = stack_q.pop() ;
            if (pn.val != qn.val) return false ;
            if (pn.right != null) stack_p.push(pn.right) ;
            if (qn.right != null) stack_q.push(qn.right) ;
            if (stack_p.size() != stack_q.size()) return false ;
            if (pn.left != null) stack_p.push(pn.left) ;
            if (qn.left != null) stack_q.push(qn.left) ;
            if (stack_p.size() != stack_q.size()) return false ;
        }
        return stack_p.size() == stack_q.size() ;
    }
}
