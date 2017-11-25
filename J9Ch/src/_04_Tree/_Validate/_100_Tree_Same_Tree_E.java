package _04_Tree._Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  100. Same Tree
//  https://leetcode.com/problems/same-tree/discuss/
public class _100_Tree_Same_Tree_E {
	/**
     * @param a, b, the root of binary trees.
     * @return true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        if (a == null && b == null)
            return true;

        if (a != null && b != null) {
            return a.val == b.val && isIdentical(a.left, b.left)
                    && isIdentical(a.right, b.right);
        }
        return false;
    }


    @Test
    public void test01(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode root2 = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isIdentical(root, root2));
    }

//---------------------------------/////////////////////

    public boolean isIdentical2(TreeNode a, TreeNode b) {
        // Write your code here
        if (a == null && b == null)
            return true;

        if (a == null || b == null)
            return false;

        return a.val == b.val && isIdentical(a.left, b.left)
                && isIdentical(a.right, b.right);
    }

    @Test
    public void test02(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode root2 = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isIdentical2(root, root2));
    }


//------------------------------------------------------------------------------

    //Five line Java solution with recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }else if(p==null || q==null){
            return false;
        }

        if(p.val==q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else{
            return false;
        }
    }

//------------------------------------------------------------------------------

//    My non-recursive method
//    the idea is to use stack for preorder traverse

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack_p = new Stack <> ();
        Stack<TreeNode> stack_q = new Stack <> ();
        if (p != null) stack_p.push( p ) ;
        if (q != null) stack_q.push( q ) ;
        while (!stack_p.isEmpty() && !stack_q.isEmpty()) {
            TreeNode pn = stack_p.pop() ;
            TreeNode qn = stack_q.pop() ;
            if (pn.val != qn.val) return false ;
            if (pn.right != null) stack_p.push(pn.right) ;
            if (qn.right != null) stack_q.push(qn.right) ;
            if (stack_p.size() != stack_q.size()) return false ;
            if (pn.left != null) stack_p.push(pn.left) ;
            if (qn.left != null) stack_q.push(qn.left) ;
            if (stack_p.size() != stack_q.size()) return false ;
        }
        return stack_p.size() == stack_q.size() ;
    }
//------------------------------------------------------------------------------

    class ResultType {
        boolean is_bst;
        int maxValue, minValue;
        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        ResultType r = validateHelper(root);
        return r.is_bst;
    }

    private ResultType validateHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = validateHelper(root.left);
        ResultType right = validateHelper(root.right);

        if (!left.is_bst || !right.is_bst) {
            // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && left.maxValue >= root.val ||
                root.right != null && right.minValue <= root.val) {
            return new ResultType(false, 0, 0);
        }

        //前面都是不正常，这里时正常
        return new ResultType(true,
                Math.max(root.val, right.maxValue),
                Math.min(root.val, left.minValue));
    }

//---------------------------------//////////////////////

//version 1 Traverse， 缺点：经常要用到全局变量
    class _95Validate_Binary_Search_Tree_Traverse {
        private int lastVal = Integer.MIN_VALUE;
        private boolean firstNode = true;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
            if (!firstNode && lastVal >= root.val) {
                return false;
            }
            firstNode = false;
            lastVal = root.val;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }


        ///////
        //this is a better solution
        //就是中序遍历一遍，看看是不是每个左边的比右边的小。
        private TreeNode lastNode = null;

        public boolean isValidBST2(TreeNode root) {
            //先判断不正常情况
            if (root == null) {
                return true;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
            if (lastNode != null && lastNode.val >= root.val) {
                return false;
            }
            lastNode = root;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }
    }
//------------------------------------------------------------------------------
}
/*
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.


Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
 */

/*
Leetcode – Same Tree

Two binary trees are considered the same if they have identical structure and nodes have the same value.

This problem can be solved by using a simple recursive function.
 */

/*  lintcode

Check if two binary trees are identical. Identical means the two binary
 trees have the same structure and every identical position has the same value.


Have you met this question in a real interview? Yes
Example
    1             1
   / \           / \
  2   2   and   2   2
 /             /
4             4
are identical.

    1             1
   / \           / \
  2   3   and   2   3
 /               \
4                 4
are not identical.

Tags 
Binary Tree
Related Problems 
Easy Tweaked Identical Binary Tree 38 %
Easy Symmetric Binary Tree 38 %
Easy Complete Binary Tree 25 %*/

/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than
the node's key.The right subtree of a node contains only nodes with
 keys greater than the node's key.

Both the left and right subtrees must also be binary search trees.
A single node tree is a BST

Example An example:

  2
 / \
1   4
   / \
  3   5
The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).

Tags: Divide and Conquer Recursion Binary Search Tree Binary Tree
Related Problems:Medium Inorder Successor in Binary Search Tree 30 %
				 Easy Balanced Binary Tree 38 %
 * */
