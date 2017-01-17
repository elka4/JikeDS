package ch9J.chapter8_DataStructure;

import java.util.Stack;

public class _04Max_Tree_1 {
/**
   * @param A
   *            : Given an integer array with no duplicates.
   * @return: The root of max tree.
   */
  public static TreeNode maxTree(int[] A) {
    // write your code here
    Stack<TreeNode> stack = new Stack<TreeNode>();
    //TreeNode root = null; //this line not used?
    for (int i = 0; i <= A.length; i++) {
      TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE)
          : new TreeNode(A[i]);
      while (!stack.isEmpty()) {
        if (right.val > stack.peek().val) {
          TreeNode nodeNow = stack.pop();
          if (stack.isEmpty()) {
            right.left = nodeNow;
          } else {
            TreeNode left = stack.peek();
            if (left.val > right.val) {
              right.left = nodeNow;
            } else {
              left.right = nodeNow;
            }
          }
        } else
          break;
      }
      stack.push(right);
    }
    return stack.peek().left;
  }
}

/*Given an integer array with no duplicates. 
 * A max tree building on this array is defined as follow:
 

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Have you met this question in a real interview? Yes
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge 
O(n) time and memory.

Tags 
LintCode Copyright Stack Cartesian Tree
Related Problems 
Hard Largest Rectangle in Histogram 25 %
Medium Min Stack 31 %*/