package _05_DFS._Recursion;
import lib.*;

//687. Longest Univalue Path
//https://leetcode.com/problems/longest-univalue-path/description/
public class _687_Longest_Univalue_Path_E {
    //https://leetcode.com/problems/longest-univalue-path/solution/
//Approach #1: Recursion [Accepted]

    class Solution {
        int ans;
        public int longestUnivaluePath(TreeNode root) {
            ans = 0;
            arrowLength(root);
            return ans;
        }
        public int arrowLength(TreeNode node) {
            if (node == null) return 0;
            int left = arrowLength(node.left);
            int right = arrowLength(node.right);
            int arrowLeft = 0, arrowRight = 0;
            if (node.left != null && node.left.val == node.val) {
                arrowLeft += left + 1;
            }
            if (node.right != null && node.right.val == node.val) {
                arrowRight += right + 1;
            }
            ans = Math.max(ans, arrowLeft + arrowRight);
            return Math.max(arrowLeft, arrowRight);
        }
    }

//----------------------------------------------------------------------------
//Same idea.

    class Solution2 {
        int max = 0;
        public int longestUnivaluePath(TreeNode root) {
            max = 0;
            if(root == null) return 0;
            return Math.max(longestUnivaluePathHelper(root.left,root.val) + longestUnivaluePathHelper(root.right,root.val),max);
        }

        public int longestUnivaluePathHelper(TreeNode root,int parent_val){
            if(root == null) return 0;
            int left  = longestUnivaluePathHelper(root.left,root.val);
            int right = longestUnivaluePathHelper(root.right,root.val);
            max = Math.max(max,left + right);
            return root.val == parent_val ? 1 + Math.max(left,right) : 0;
        }
    }

//----------------------------------------------------------------------------
}
/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */