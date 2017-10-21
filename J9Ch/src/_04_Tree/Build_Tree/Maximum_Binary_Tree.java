package _04_Tree.Build_Tree;
import lib.*;

/*
654
Maximum Binary Tree
 */
public class Maximum_Binary_Tree {
//Approach #1 Recursive Solution[Accepted]

    public class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return construct(nums, 0, nums.length);
        }
        public TreeNode construct(int[] nums, int l, int r) {
            if (l == r)
                return null;
            int max_i = max(nums, l, r);
            TreeNode root = new TreeNode(nums[max_i]);
            root.left = construct(nums, l, max_i);
            root.right = construct(nums, max_i + 1, r);
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
    }
////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////



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