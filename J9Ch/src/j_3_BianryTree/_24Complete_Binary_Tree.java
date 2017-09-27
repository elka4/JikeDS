package j_3_BianryTree;

import lib.TreeNode;

public class _24Complete_Binary_Tree {
	class ResultType {
	    public int depth;
	    public boolean isFull, isComplete;
	    ResultType(int depth, boolean isFull, boolean isComplete) {
	        this.depth = depth;
	        this.isFull = isFull;
	        this.isComplete = isComplete;
	    }
	}

    /**
     * @param root, the root of binary tree.
     * @return true if it is a complete binary tree, or false.
     */
    public boolean isComplete(TreeNode root) {
        ResultType result = helper(root);
        return result.isComplete;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true, true);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        if (!left.isComplete) {
            return new ResultType(-1, false, false);
        }
        
// depth is the same, left should be full and right should be complete
        if (left.depth == right.depth) {
            if (!left.isFull || !right.isComplete) {
                return new ResultType(-1, false, false);
            }
            return new ResultType(left.depth + 1, right.isFull, true);
        }
        
// left.depth = right.depth + 1, left should be complete and right should be full
        if (left.depth == right.depth + 1) {
            if (!left.isComplete || !right.isFull) {
                return new ResultType(-1, false, false);
            }
            return new ResultType(left.depth + 1, false, true);
        }
        
        return new ResultType(-1, false, false);
    }
}

/*Check a binary tree is completed or not. A complete binary tree is a binary 
 * tree that every level is completed filled except the deepest level. 
 * In the deepest level, all nodes must be as left as possible. See more definition


Have you met this question in a real interview? Yes
Example
    1
   / \
  2   3
 /
4
is a complete binary.

    1
   / \
  2   3
   \
    4
is not a complete binary.

Challenge 
Do it in O(n) time

Tags 
Binary Tree
Related Problems 
Easy Identical Binary Tree 45 %
Easy Symmetric Binary Tree 38 %
Easy Balanced Binary Tree 38 %*/