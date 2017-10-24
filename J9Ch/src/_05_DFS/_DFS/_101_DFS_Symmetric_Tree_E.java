package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
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



    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return isMirror(root.left,root.right);
    }
    public boolean isMirror(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        return (p.val==q.val) && isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */