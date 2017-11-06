package _04_Tree._Successor_Iterator;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

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
        /*
        退出条件就是root == null, 也就是在上一次循环中root.left或者root.rigth为null，
        如果是"p.val < root.val", 说明当前root太大了，要变小，所以 root = root.left;
        这时候记录当前root为parent。

        如果是"else" 说明当前root太小了，要变大，所以 root = root.right;
         */
        while (root != null){
            if (p.val < root.val) {
                par = root;
                System.out.println("p.val < root.val: ");
                root.print();
                root = root.left;

            } else {
                System.out.println("else: ");
                root.print();
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
        /*
           5
          / \
         /   \
         3   7
        / \ / \
        1 2 6 8
         */
        inorderSuccessor3(root,root.left).print();
        /*     root.left is 3

                p.val < root.val:
                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8

                else:
                 3
                / \
                1 2

                else:
                2

                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8
         */
        inorderSuccessor3(root,root.right.left).print();
        /*  root.right.left is 6
            else:
               5
              / \
             /   \
             3   7
            / \ / \
            1 2 6 8

            p.val < root.val:
             7
            / \
            6 8

            else:
            6

             7
            / \
            6 8
         */
        inorderSuccessor3(root,root.right).print();
        /*   root.right is 7
                else:
                   5
                  / \
                 /   \
                 3   7
                / \ / \
                1 2 6 8

                else:
                 7
                / \
                6 8

                p.val < root.val:  5 < 8
                8

                8
         */
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
    //Java Solution 1

    public TreeNode inorderSuccessor11(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null || p==null)
            return null;

        stack.push(root);
        boolean isNext = false;
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();

            if(top.right==null&&top.left==null){
                if(isNext){
                    return top;
                }

                if(p.val==top.val){
                    isNext = true;
                }
                continue;
            }

            if(top.right!=null){
                stack.push(top.right);
                top.right=null;
            }

            stack.push(top);

            if(top.left!=null){
                stack.push(top.left);
                top.left=null;
            }
        }

        return null;
    }
    //Time is O(n), Space is O(n).

//////////////////////////////////////////////////////////////////////////

    //Java Solution 2

    public TreeNode inorderSuccessor22(TreeNode root, TreeNode p) {
        if(root==null)
            return null;

        TreeNode next = null;
        TreeNode c = root;
        while(c!=null && c.val!=p.val){
            if(c.val > p.val){
                next = c;
                c = c.left;
            }else{
                c= c.right;
            }
        }

        if(c==null)
            return null;

        if(c.right==null)
            return next;

        c = c.right;
        while(c.left!=null)
            c = c.left;

        return c;
    }
    //Time is O(log(n)) and space is O(1).


//////////////////////////////////////////////////////////////////////////




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
