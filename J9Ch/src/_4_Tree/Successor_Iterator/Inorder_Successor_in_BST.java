package _4_Tree.Successor_Iterator;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

public class Inorder_Successor_in_BST {
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

    @Test
    public void test01(){
        int[] arr = {7,5,10};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(8));
        root.right.setRightChild(new TreeNode(12));
        System.out.println("root: ");
        root.print();
        inorderSuccessor(root, root).print();
        //System.out.println(inorderSuccessor(root, root).val);
        //System.out.println(inorderSuccessor(root, root.right).val);
        inorderSuccessor(root, root.left).print();
        inorderSuccessor(root, root.right.left).print();
    }
////////////////////////////////////////////////////////////////////////////////
    //time O(logn) spaceO(1)

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p){
        if(root == null) {
            return null;
        }
        //Case 1: has right child
        if (p.right != null) {
            return searchLeftMost(p.right);
        } else {
            //Case 2: no right -> find the first left turing on the searching path
            return searchPar(root, p);
        }
    }

    private TreeNode searchPar(TreeNode root, TreeNode p) {
        TreeNode par = null;
        while(root != p) {
            if(p.val < root.val) {
                par = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return par;
    }

    private TreeNode searchLeftMost(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

//////////////////////////////////////////////////

    //mehtod 2
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        TreeNode par = null;
        while (root != null){
            if (p.val < root.val) {
                par = root;
                root = root.left;

            } else {
                root = root.right;
            }
        }
        return par;

    }

    @Test
    public void test03() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        inorderSuccessor(root,root.left).print();
        inorderSuccessor(root,root.right.left).print();

    }

////////////////////////////////////////////////////////////////////////////////

    // version: 高频题班
    public TreeNode inorderSuccessorX(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessorX(root.right, p);
        } else {
            TreeNode left = inorderSuccessorX(root.left, p);
            return (left != null) ? left : root;
        }
    }

    @Test
    public void test02(){
        int[] input = {1,2};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessorX(root, new TreeNode(1)).print();

    }

    @Test
    public void test02_2(){
        int[] input = {1,2,3};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessorX(root, new TreeNode(2)).print();

    }
////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////



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

