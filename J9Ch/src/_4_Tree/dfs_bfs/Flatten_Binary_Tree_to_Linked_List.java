package _4_Tree.dfs_bfs;
import java.util.*;
import lib.*;
/*
LeetCode – Flatten Binary Tree to Linked List

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */


public class Flatten_Binary_Tree_to_Linked_List {

    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while(p != null || !stack.empty()){

            if(p.right != null){
                stack.push(p.right);
            }

            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                TreeNode temp = stack.pop();
                p.right=temp;
            }

            p = p.right;
        }
    }



//////////////////////////////////////////////////////////////

    //My short post order traversal Java solution for share
    class Solution2{
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }

    }


//////////////////////////////////////////////////////////////

    //Straightforward Java Solution

    class Solution3{
        public void flatten(TreeNode root) {
            if (root == null) return;

            TreeNode left = root.left;
            TreeNode right = root.right;

            root.left = null;

            flatten(left);
            flatten(right);

            root.right = left;
            TreeNode cur = root;
            while (cur.right != null) cur = cur.right;
            cur.right = right;
        }

    }



//////////////////////////////////////////////////////////////

    //Accepted simple Java solution , iterative
    class Solution4{
/*
        it is DFS so u need a stack. Dont forget to set the left child to null, or u'll get TLE. (tricky!)*/

        public void flatten(TreeNode root) {
            if (root == null) return;
            Stack<TreeNode> stk = new Stack<TreeNode>();
            stk.push(root);
            while (!stk.isEmpty()){
                TreeNode curr = stk.pop();
                if (curr.right!=null)
                    stk.push(curr.right);
                if (curr.left!=null)
                    stk.push(curr.left);
                if (!stk.isEmpty())
                    curr.right = stk.peek();
                curr.left = null;  // dont forget this!!
            }
        }
    }

//////////////////////////////////////////////////////////////

    //Easy 1ms Java DFS solution

    class Solution5{
        public void flatten(TreeNode root) {
            if(root==null)
                return;
            flatten(root.left);
            flatten(root.right);
            TreeNode left  = root.left;
            TreeNode right = root.right;
            root.left  = null;
            root.right = left;
            while(root.right!=null)
                root = root.right;
            root.right = right;
        }

    }



//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////

    //Java solution refer to Mirris traversal using constant place

    class Solutoin6{
        public void flatten(TreeNode root) {
            TreeNode cur = root;
            TreeNode prev = null;
            while(cur != null){
                if(cur.left == null) cur = cur.right;
                else {
                    prev = cur.left;
                    while(prev.right != null) prev = prev.right;
                    prev.right = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                }
            }
        }
    }



//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////



}
