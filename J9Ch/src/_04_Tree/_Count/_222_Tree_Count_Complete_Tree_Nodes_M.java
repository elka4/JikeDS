package _04_Tree._Count;
import lib.TreeNode;

//  222. Count Complete Tree Nodes
//  https://leetcode.com/problems/count-complete-tree-nodes/description/
public class _222_Tree_Count_Complete_Tree_Nodes_M {
    //Concise Java solutions O(log(n)^2)
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes1(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h-1 ? (1 << h) + countNodes1(root.right)
                        : (1 << h-1) + countNodes1(root.left);
    }

/////////////////////////////////////////////////////////////////////////////////////
    //    Iterative Version - 508 ms
    //    Here's an iterative version as well, with the benefit that I don't recompute h in every step.
    int height2(TreeNode root) {
        return root == null ? -1 : 1 + height2(root.left);
    }
    public int countNodes2(TreeNode root) {
        int nodes = 0, h = height2(root);
        while (root != null) {
        if (height2(root.right) == h - 1) {
            nodes += 1 << h;
            root = root.right;
        } else {
            nodes += 1 << h-1;
            root = root.left;
        }
        h--; }
        return nodes;
    }


/////////////////////////////////////////////////////////////////////////////////////
    //    A Different Solution - 544 ms
    //    Here's one based on victorlee's C++ solution.
    public int countNodes3(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;
        return 1 + countNodes3(root.left) + countNodes3(root.right);
    }



/////////////////////////////////////////////////////////////////////////////////////
    public int countNodes4(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes3(root.left) + countNodes3(root.right);
    }

/////////////////////////////////////////////////////////////////////////////////////
}
/*
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


 */
/*

 */
