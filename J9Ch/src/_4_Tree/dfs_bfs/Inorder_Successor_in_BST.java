package _4_Tree.dfs_bfs;
import java.util.*;
import lib.*;

//Given a binary search tree and a node in it, find the in-order successor of that node in the BST.


public class Inorder_Successor_in_BST {
    //Java Solution 1

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null || p==null)
            return null;

        stack.push(root);
        boolean isNext = false;
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();

            if(top.right==null&&top.left==null){
                if(isNext){
                    return top;
                }

                if(p.val==top.val){
                    isNext = true;
                }
                continue;
            }

            if(top.right!=null){
                stack.push(top.right);
                top.right=null;
            }

            stack.push(top);

            if(top.left!=null){
                stack.push(top.left);
                top.left=null;
            }
        }

        return null;
    }
    //Time is O(n), Space is O(n).

//////////////////////////////////////////////////////////////////////////

    //Java Solution 2

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if(root==null)
            return null;

        TreeNode next = null;
        TreeNode c = root;
        while(c!=null && c.val!=p.val){
            if(c.val > p.val){
                next = c;
                c = c.left;
            }else{
                c= c.right;
            }
        }

        if(c==null)
            return null;

        if(c.right==null)
            return next;

        c = c.right;
        while(c.left!=null)
            c = c.left;

        return c;
    }
    //Time is O(log(n)) and space is O(1).


//////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////







//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////

}
