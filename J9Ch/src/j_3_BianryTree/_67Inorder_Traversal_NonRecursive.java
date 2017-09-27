package j_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
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
