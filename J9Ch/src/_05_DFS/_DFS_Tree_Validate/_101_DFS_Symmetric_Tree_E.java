package _05_DFS._DFS_Tree_Validate;

import lib.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _101_DFS_Symmetric_Tree_E {

//    Recursive--400ms:
    class Solution{
        public boolean isSymmetric(TreeNode root) {
            return root==null || isSymmetricHelp(root.left, root.right);
        }

        private boolean isSymmetricHelp(TreeNode left, TreeNode right){
            if(left==null || right==null)
                return left==right;
            if(left.val!=right.val)
                return false;
            return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
        }
    }

//    Non-recursive(use Stack)--460ms:
    class Solution2{
        public boolean isSymmetric(TreeNode root) {
            if(root==null)  return true;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode left, right;
            if(root.left!=null){
                if(root.right==null) return false;
                stack.push(root.left);
                stack.push(root.right);
            }
            else if(root.right!=null){
                return false;
            }

            while(!stack.empty()){
                if(stack.size()%2!=0)   return false;
                right = stack.pop();
                left = stack.pop();
                if(right.val!=left.val) return false;

                if(left.left!=null){
                    if(right.right==null)   return false;
                    stack.push(left.left);
                    stack.push(right.right);
                }
                else if(right.right!=null){
                    return false;
                }

                if(left.right!=null){
                    if(right.left==null)   return false;
                    stack.push(left.right);
                    stack.push(right.left);
                }
                else if(right.left!=null){
                    return false;
                }
            }

            return true;
        }
    }


    //Approach #1 (Recursive) [Accepted]
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return isMirror(root.left,root.right);
    }
    public boolean isMirror(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        return (p.val==q.val) && isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
    //Approach #2 (Iterative) [Accepted]
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


//-------------------------------------------------------------------------///

// jiuzhang
public class Jiuzhang {
    /**
     * @param root, the root of binary tree.
     * @return true if it is a mirror of itself, or false.
     */
    public boolean isSymmetric(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return check(root1.left, root2.right) && check(root1.right, root2.left);
    }
}



//-------------------------------------------------------------------------///






}
/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */