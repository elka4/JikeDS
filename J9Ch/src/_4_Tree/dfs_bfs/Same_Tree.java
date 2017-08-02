package _4_Tree.dfs_bfs;

/*
Leetcode – Same Tree

Two binary trees are considered the same if they have identical structure and nodes have the same value.

This problem can be solved by using a simple recursive function.
 */
public class Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }else if(p==null || q==null){
            return false;
        }

        if(p.val==q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else{
            return false;
        }
    }
}
