package _4_Tree._2Tree_Adv;

import CtCILibrary.TreeNode;

// leetcode 333

// Largest BST Subtree
public class _9LargestBSTSubtree {

    class Result {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
        int size;
        int lower;
        int upper;

        Result(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }

    int max = 0;

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) { return 0; }
        traverse(root);
        return max;
    }

    private Result traverse(TreeNode root) {
        if (root == null) { return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
        Result left = traverse(root.left);
        Result right = traverse(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
            return new Result(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
////////////////////////////////////////////////////////////////////////

    //Clean and easy to understand Java Solution
    public int largestBSTSubtree2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid(root, null, null)) return countNode(root);
        return Math.max(largestBSTSubtree2(root.left), largestBSTSubtree2(root.right));
    }

    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public int countNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode(root.left) + countNode(root.right);
    }


///////////////////////////////////////////////////////////////////////////

    //Java 1ms solution, by passing a three-element array up to parent

    private int largestBSTSubtreeSize = 0;
    public int largestBSTSubtree3(TreeNode root) {
        helper(root);
        return largestBSTSubtreeSize;
    }

    private int[] helper(TreeNode root) {
        // return 3-element array:
        // # of nodes in the subtree, leftmost value, rightmost value
        // # of nodes in the subtree will be -1 if it is not a BST
        int[] result = new int[3];
        if (root == null) {
            return result;
        }
        int[] leftResult = helper(root.left);
        int[] rightResult = helper(root.right);
        if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] <= root.val) &&
                (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] >= root.val)) {
            int size = 1 + leftResult[0] + rightResult[0];
            largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
            int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
            int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
            result[0] = size;
            result[1] = leftBoundary;
            result[2] = rightBoundary;
        } else {
            result[0] = -1;
        }
        return result;
    }
}
/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \
 1   8   7
The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */