package Classification.Tree_63;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
/**
 * Created by tianhuizhu on 6/19/17.
 */
public class a_101_Symmetric_Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root.left);
        deque.offerLast(root.right);
        while(!deque.isEmpty()){
            TreeNode left = deque.pollFirst();
            TreeNode right = deque.pollLast();
            if (left == null && right == null){
                continue;
            }
            if(left == null || right == null || left.val != right.val){
                return false;
            }
            deque.offerFirst(left.right);
            deque.offerFirst(left.left);
            deque.offerLast(right.left);
            deque.offerLast(right.right);
        }
        return true;
    }

    //recursion
    public class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            if(root == null){
                return true;
            }
            return helper(root.left, root.right);
        }

        public boolean helper(TreeNode one, TreeNode two){
            if (one == null || two == null){
                return one == two;
            }
            if (one.val != two.val){
                return false;
            }
            return helper(one.left, two.right) && helper(one.right, two.left);

        }
    }

    //////////////////////////////////////////////////
   // Recursive--400ms:
    public class solution2 {
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

    //////////////////////////////////////////////////
    //Non-recursive(use Stack)--460ms:

    public class solution3 {
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





}
