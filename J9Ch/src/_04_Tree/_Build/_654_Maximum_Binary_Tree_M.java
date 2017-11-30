package _04_Tree._Build;
import lib.TreeNode;

//  654. Maximum Binary Tree
//  https://leetcode.com/problems/maximum-binary-tree/description/
//  Tree
//
public class _654_Maximum_Binary_Tree_M {
//----------------------------------------------------
    //1
    //Approach #1 Recursive Solution[Accepted]
    //非常直觉的recursive建树，每次找到范围内的最大值建root
    public TreeNode constructMaximumBinaryTree1(int[] nums) {

        return construct1(nums, 0, nums.length);
    }

    public TreeNode construct1(int[] nums, int l, int r) {
        if (l == r)
            return null;
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct1(nums, l, max_i);
        root.right = construct1(nums, max_i + 1, r);
        return root;
    }

    public int max(int[] nums, int l, int r) {
        int max_i = l;
        for (int i = l; i < r; i++) {
            if (nums[max_i] < nums[i])
                max_i = i;
        }
        return max_i;
    }


//----------------------------------------------------
    //2
    //一个一个去insert
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            root = insert(root, new TreeNode(nums[i]));
        }
        return root;
    }

    private TreeNode insert(TreeNode root, TreeNode node) {
        if(root == null) {
            return node;
        }
        if(node.val > root.val) {
            node.left = root;
            return node;
        }
        root.right = insert(root.right, node);
        return root;
    }

//----------------------------------------------------
    //3
    //和1是一样的
    public TreeNode constructMaximumBinaryTree3(int[] nums) {
        if (nums == null) return null;
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) return null;

        int idxMax = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[idxMax]) {
                idxMax = i;
            }
        }

        TreeNode root = new TreeNode(nums[idxMax]);

        root.left = build(nums, start, idxMax - 1);
        root.right = build(nums, idxMax + 1, end);

        return root;
    }

//----------------------------------------------------
}
/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].

 */