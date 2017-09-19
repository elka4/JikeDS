package chapter3_BianryTree;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
import java.util.ArrayList;

public class _68Postorder_Recursive {
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
