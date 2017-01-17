package ch9J.chapter3_BianryTree;

import java.util.*;

public class _03Postorder_Recursive {
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
