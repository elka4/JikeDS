package j_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class Postorder {
    //Iterative
    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null; // previously traversed node
        TreeNode curr = root;

        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.empty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                // traverse down the tree
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                // traverse up the tree from the left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else { // traverse up the tree from the right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return result;
    }
//------------------------------------------------------------------------------
//Recursive
public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<>();
    if (root == null) {
        return result;
    }
    result.addAll(postorderTraversal(root.left));
    result.addAll(postorderTraversal(root.right));
    result.add(root.val);
    return result;
}
//--------------------------------------------------------------------------------

    public class Solution_mine {
        /**
         * @param root: The root of binary tree.
         * @return: Postorder in ArrayList which contains node values.
         */
        public ArrayList<Integer> postorderTraversal(TreeNode root) {
            // write your code here
            ArrayList<Integer> result = new ArrayList<>();
            if(root == null) {
                return result;
            }
            helper(result, root);
            return result;

        }

        private void helper(ArrayList<Integer> result, TreeNode root){
            if(root == null){
                return;
            }
            helper(result, root.left);
            helper(result, root.right);
            result.add(root.val);
        }
    }
}

/*Given a binary tree, return the postorder traversal of its nodes' values.

Have you met this question in a real interview? Yes
Example
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3


return [3,2,1].

Challenge
Can you do it without recursion?

Tags
Recursion Binary Tree Binary Tree Traversal
Related Problems
Easy Binary Tree Preorder Traversal 40 %
*/
