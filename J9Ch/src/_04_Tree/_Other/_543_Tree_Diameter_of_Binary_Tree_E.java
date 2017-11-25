package _04_Tree._Other;

import lib.TreeNode;


//  543. Diameter of Binary Tree
//  https://leetcode.com/problems/diameter-of-binary-tree/
public class _543_Tree_Diameter_of_Binary_Tree_E {

    //    Java Solution, MaxDepth
    //    For every node, length of longest path which pass it = MaxDepth of
    // its left subtree + MaxDepth of its right subtree.
    int max = 0;

    public int diameterOfBinaryTree1(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }

//-----------------------------------------------------------------------------//
    public int DiameterOfBinaryTree2(TreeNode root) {
        return DFS(root)[0];
    }

    // int[2] = [best, height]
    private int[] DFS(TreeNode node)
    {
        if (node == null) return new int[] { 0, 0 };
        int[] left = DFS(node.left);
        int[] right = DFS(node.right);

        int best = Math.max(left[1] + right[1], Math.max(left[0], right[0]));
        int height = 1 + Math.max(left[1], right[1]);
        return new int[] { best, height };
    }

//-----------------------------------------------------------------------------//
    int max3;
    public int diameterOfBinaryTree3(TreeNode root) {
        max3 = 0;
        height(root);
        return max3;
    }
    int height(TreeNode root){
        if(root==null)return -1;
        int leftH = height(root.left);
        int rightH = height(root.right);
        int height = Math.max(leftH,rightH)+1;
        max3 = Math.max(max3,leftH+rightH+2);
        return height;
    }

//-----------------------------------------------------------------------------//
    //    Java easy to understand solution
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        int dia = depth(root.left) + depth(root.right);
        int ldia = diameterOfBinaryTree(root.left);
        int rdia = diameterOfBinaryTree(root.right);
        return Math.max(dia,Math.max(ldia,rdia));

    }
    public int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1+Math.max(depth(root.left), depth(root.right));
    }

//-----------------------------------------------------------------------------//
}
/*

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

 */
