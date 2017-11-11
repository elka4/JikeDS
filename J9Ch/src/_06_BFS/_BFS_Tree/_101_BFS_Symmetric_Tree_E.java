package _06_BFS._BFS_Tree;

import lib.TreeNode;

import java.util.Stack;

public class _101_BFS_Symmetric_Tree_E {

//    Recursive--400ms:

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


//    Non-recursive(use Stack)--460ms:

    public boolean isSymmetric2(TreeNode root) {
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


    class Solution2{
        public boolean isSymmetric(TreeNode root) {
            if (root ==null)
                return true;
            return isSymmetricSubTree(root.left, root.right);
        }

        public boolean isSymmetricSubTree(TreeNode left,  TreeNode right){
            if(left ==null && right ==null) return true;
            if(left==null || right ==null) return false;
            if(left.val == right.val ) {
                return isSymmetricSubTree(left.left, right.right) && isSymmetricSubTree(left.right, right.left);
            }else
                return false;
        }
    }


    class Solution3{
        public boolean isSymmetric(TreeNode root) {
            if(root==null)  return true;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode left, right;
            if(!process(root.left, root.right, stack)) return false;

            while(!stack.empty()){
                if(stack.size()%2!=0)   return false;
                right = stack.pop();
                left = stack.pop();
                if(right.val!=left.val) return false;
                if(!process(left.left, right.right, stack)) return false;
                if(!process(left.right, right.left, stack)) return false;
            }
            return true;
        }

        public boolean process(TreeNode a, TreeNode b, Stack<TreeNode> s) {
            if(a != null) {
                if(b == null) return false;
                s.push(a);
                s.push(b);
            }else if(b != null) return false;
            return true;
        }
    }
}