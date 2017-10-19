package _4_Tree.Build_Tree;

import lib.TreeNode;
import org.junit.Test;

public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
	private int findPosition(int[] arr, int start, int end, int key) {
	    int i;
	    for (i = start; i <= end; i++) {
	        if (arr[i] == key) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	private TreeNode myBuildTree(int[] inorder, int instart, int inend,
	        int[] preorder, int prestart, int preend) {
	    if (instart > inend) {
	        return null;
	    }
	
	    TreeNode root = new TreeNode(preorder[prestart]);
	    int position = findPosition(inorder, instart, inend, preorder[prestart]);
	
	    root.left = myBuildTree(inorder, instart, position - 1,
	            preorder, prestart + 1, prestart + position - instart);
	    root.right = myBuildTree(inorder, position + 1, inend,
	            preorder, position - inend + preend + 1, preend);
	    return root;
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
	    if (inorder.length != preorder.length) {
	        return null;
	    }
	    return myBuildTree(inorder, 0, inorder.length - 1, 
	    					   preorder, 0, preorder.length - 1);
	}

	@Test
    public void test01(){
        int[] preorder = {2,1,3};
        int[] inorder = {1,2,3};
        buildTree(preorder, inorder).print();
    }

}

/*Given preorder and inorder traversal of a tree, construct the binary tree.

 Notice

You may assume that duplicates do not exist in the tree.

Have you met this question in a real interview? Yes
Example
Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3
Tags 
Binary Tree*/