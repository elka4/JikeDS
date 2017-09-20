package J_3_BianryTree;

import lib.TreeNode;

public class _448Inorder_Successor_in_Binary_Search_Tree {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root != p) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        if (root == null) {
            return null;
        }
        
        if (root.right == null) {
            return successor;
        }
        
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        
        return root;
    }
}

/*
 * Given a binary search tree (See Definition) and a node in it,
 *  find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

 Notice

It's guaranteed p is one node in the given tree. 
(You can directly compare the memory address to find p)

Have you met this question in a real interview? Yes
Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.

Challenge: O(h), where h is the height of the BST.

Tags: Binary Search Tree Binary Tree
Related Problems: 
Medium Validate Binary Search Tree 21 %
Hard Binary Search Tree Iterator 33 %*/

