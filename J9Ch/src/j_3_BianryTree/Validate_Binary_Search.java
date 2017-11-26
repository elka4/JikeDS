package j_3_BianryTree;

import lib.TreeNode;

//version 2  Divide and Conquer
public class Validate_Binary_Search {
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

//--------------------------------------------------------------------------------/
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

//--------------------------------------------------------------------------------/

//--------------------------------------------------------------------------------/


}
