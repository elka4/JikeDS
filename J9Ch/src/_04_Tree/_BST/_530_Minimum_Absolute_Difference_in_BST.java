package _04_Tree._BST;
import lib.*;
import java.util.*;

//  530. Minimum Absolute Difference in BST
//  https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
//
public class _530_Minimum_Absolute_Difference_in_BST {
    /*
    Two Solutions, in-order traversal and a more general way using TreeSet
    The most common idea is to first inOrder traverse the tree and compare
    the delta between each of the adjacent values. It's guaranteed to have
    the correct answer because it is a BST thus inOrder traversal values are sorted.

    Solution 1 - In-Order traverse, time complexity O(N), space complexity O(1).*/
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference1(TreeNode root) {
        if (root == null) return min;

        getMinimumDifference1(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        getMinimumDifference1(root.right);

        return min;
    }

//-------------------------------------------------------------------------///

    /*
    What if it is not a BST? (Follow up of the problem) The idea is to put values in
    a TreeSet and then every time we can use O(lgN) time to lookup for the nearest values.
    Solution 2 - Pre-Order traverse, time complexity O(NlgN), space complexity O(N).
    */
    TreeSet<Integer> set = new TreeSet<>();
    int min2 = Integer.MAX_VALUE;

    public int getMinimumDifference2(TreeNode root) {
        if (root == null) return min2;

        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min2 = Math.min(min2, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min2 = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }

        set.add(root.val);

        getMinimumDifference2(root.left);
        getMinimumDifference2(root.right);

        return min2;
    }

//-------------------------------------------------------------------------///
    /*
    Java O(n) Time Inorder Traversal Solution
    Since this is a BST, the inorder traversal of its nodes results in a sorted list of values.
    Thus, the minimum absolute difference must occur in any adjacently traversed nodes.
    I use the global variable "prev" to keep track of each node's inorder predecessor.
    */
    int minDiff3 = Integer.MAX_VALUE;
    TreeNode prev3;

    public int getMinimumDifference3(TreeNode root) {
        inorder(root);
        return minDiff3;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev3 != null)
            minDiff3 = Math.min(minDiff3, root.val - prev3.val);
        prev3 = root;
        inorder(root.right);
    }

//-------------------------------------------------------------------------///

    /*
    Java No In-order Traverse Solution, just pass upper bound and lower bound
    Make use of the property of BST that value of nodes is bounded by their "previous" and "next" node.
    Edit: Thanks to Stefan pointing out a small bug.
    Previous code would fail when testing [2147483647, 2147483646].
    Now Long.MAX_VALUE/MIN_VALUE is used to mark the INF.
    */
    long minDiff = Long.MAX_VALUE;
    public int getMinimumDifference4(TreeNode root) {
        helper(root,Long.MIN_VALUE,Long.MAX_VALUE);
        return (int)minDiff;
    }
    private void helper(TreeNode curr, long lb, long rb){
        if(curr==null) return;
        if(lb!=Long.MIN_VALUE){
            minDiff = Math.min(minDiff,curr.val - lb);
        }
        if(rb!=Long.MAX_VALUE){
            minDiff = Math.min(minDiff,rb - curr.val);
        }
        helper(curr.left,lb,curr.val);
        helper(curr.right,curr.val,rb);
    }


////////////////////////////////////////////////////////////
}
/*
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.


 */
