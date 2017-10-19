package j_3_BianryTree;

import lib.TreeNode;

import java.util.*;
import java.util.Stack;

public class _67Inorder_Traversal_NonRecursive {
	/**
	 * @param root: The root of binary tree.
	 * @return: Inorder in ArrayList which contains node values.
	 */
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    ArrayList<Integer> result = new ArrayList<>();
	    TreeNode curt = root;
	    while (curt != null || !stack.empty()) {
	        while (curt != null) {
	            stack.add(curt);
	            curt = curt.left;
	        }
	        curt = stack.peek();
	        stack.pop();
	        result.add(curt.val);
	        curt = curt.right;
	    }
	    return result;
	}
////////////////////////////////////////////////////////

    public List<Integer> inorderTraversal2(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

////////////////////////////////////////////////////////

    public List<Integer> inorderTraversal3(TreeNode root) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        // if (root == null) {
        //     return list;
        // }
        helper(root, list);
        return list;
    }

    private void helper (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

////////////////////////////////////////////////////////
}

/*
 * Given a binary tree, return the inorder traversal of its nodes' values.

Have you met this question in a real interview? Yes
Example
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3
 

return [1,3,2].

Challenge 
Can you do it without recursion?

Tags 
Recursion Binary Tree Binary Tree Traversal
Related Problems 
Easy Binary Tree Preorder Traversal 40 %
 * */
