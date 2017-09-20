package J_3_BianryTree;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Version 0: Non-Recursion (Recommend)
public class _66Preorder_Traversal_Iteration {
	public List<Integer> preorderTraversal(TreeNode root) {
	    List<Integer> preorder = new ArrayList<>();
	    if (root == null) {
	    		return preorder;
	    }
	    
	    Stack<TreeNode> stack = new Stack<>();
	    stack.push(root);
	    
	    while (!stack.empty()) {
	        TreeNode node = stack.pop();
	        preorder.add(node.val);
	        if (node.right != null) {
	            stack.push(node.right);
	        }
	        if (node.left != null) {
	            stack.push(node.left);
	        }
	    }
	    
	    return preorder;
	}
}


/*
 * Given a binary tree, return the preorder traversal of its nodes' values.

Have you met this question in a real interview? Yes
Example
Given:

    1
   / \
  2   3
 / \
4   5
return [1,2,4,5,3].

Challenge 
Can you do it without recursion?

Tags 
Recursion Binary Tree Binary Tree Traversal Non Recursion
Related Problems 
Easy Binary Tree Postorder Traversal 39 %
Easy Binary Tree Inorder Traversal 39 %
 * */

