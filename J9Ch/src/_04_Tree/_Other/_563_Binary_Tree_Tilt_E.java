package _04_Tree._Other;
import lib.TreeNode;

//  563. Binary Tree Tilt
//  https://leetcode.com/problems/binary-tree-tilt/
//
public class _563_Binary_Tree_Tilt_E {

    //https://leetcode.com/problems/binary-tree-tilt/solution/
    //Approach #1 Using Recursion [Accepted]
    //post-order traversal
    //helper will return sum of a subtree.
    int result = 0;

    public int findTilt(TreeNode root) {
        postOrder(root);
        return result;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }


//------------------------------------------------------------------------------
    //    Simple Java Solution -- without Global Variable
    //    To avoid using global variable, you can take use of size-1 array or any other objects.
    public int findTilt2(TreeNode root) {
        int[] ret = new int[1];
        helper(root, ret);
        return ret[0];
    }

    private int helper(TreeNode node, int[] ret){
        if(node == null){
            return 0;
        }
        int l_sum = helper(node.left, ret);
        int r_sum = helper(node.right, ret);
        ret[0] += Math.abs(l_sum - r_sum);
        return l_sum + r_sum + node.val;
    }

//------------------------------------------------------------------------------
}
/*

Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.

 */
