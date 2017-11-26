package _05_DFS._DFS_Tree_Validate;

import lib.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

//
public class _098_DFS_Validate_Binary_Search_Tree_M {
    //My simple Java solution in 3 lines
    /*
    Basically what I am doing is recursively iterating over the tree while defining
    interval <minVal, maxVal> for each node which value must fit in.
     */
    public class Solution {
        public boolean isValidBST(TreeNode root) {

             return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
            if (root == null) return true;
            if (root.val >= maxVal || root.val <= minVal) return false;
            return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
        }
    }

    public class Solution2 {
        public boolean isValidBST(TreeNode root) {

            return isValidBST(root, Optional.empty(), Optional.empty());
        }

        private boolean isValidBST(TreeNode node, Optional<Integer> min, Optional<Integer> max) {
            if (node == null) {
                return true;
            }
            if ((min.isPresent() && node.val <= min.get()) || (max.isPresent() && node.val >= max.get())) {
                return false;
            }
            Optional val = Optional.of(node.val);
            return isValidBST(node.left, min, val) && isValidBST(node.right, val, max);
        }
    }

    class Solution3{
        Integer min = null;
        public boolean isValidBST(TreeNode root) {
            if(root == null){
                return true;
            }
            if(isValidBST(root.left) && (min == null || root.val > min)){
                min = root.val;
                return isValidBST(root.right);
            }
            return false;
        }
    }

    class Solution4{
        private boolean help(TreeNode p, Integer low, Integer high) {
            if (p == null) return true;
            return (low == null || p.val > low) && (high == null || p.val < high) &&
                    help(p.left, low, p.val) && help(p.right, p.val, high);
        }

        public boolean isValidBST(TreeNode root) {
            return help(root, null, null);
        }
    }


    //Learn one iterative inorder traversal, apply it to multiple tree questions (Java Solution)
    class SolutionX{

/*        I will show you all how to tackle various tree questions using iterative inorder traversal.
First one is the standard iterative inorder traversal using stack. Hope everyone agrees with this solution.


        Question : Binary Tree Inorder Traversal
*/
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null) return list;
            Stack<TreeNode> stack = new Stack<>();
            while(root != null || !stack.empty()){
                while(root != null){
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                list.add(root.val);
                root = root.right;

            }
            return list;
        }
/*        Now, we can use this structure to find the Kth smallest element in BST.

                Question : Kth Smallest Element in a BST
*/
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            while(root != null || !stack.isEmpty()) {
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if(--k == 0) break;
                root = root.right;
            }
            return root.val;
        }

/*        We can also use this structure to solve BST validation question.

        Question : Validate Binary Search Tree
*/
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if(pre != null && root.val <= pre.val) return false;
                pre = root;
                root = root.right;
            }
            return true;
        }
    }
//----------------------------------------------------------------------------

// 9Ch
// version 1 Traverse
public class Jiuzhang1 {
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
}


    // version 2  Divide and Conquer
    class ResultType {
        boolean is_bst;
        int maxValue, minValue;

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public class Jiuzhang2 {
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

            return new ResultType(true,
                    Math.max(root.val, right.maxValue),
                    Math.min(root.val, left.minValue));
        }
    }

    // version 3  Divide and Conquer
    public class Jiuzhang3 {
        /**
         * @param root: The root of binary tree.
         * @return: True if the binary tree is BST, or false
         */
        public boolean isValidBST(TreeNode root) {
            // write your code here
            return divConq(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean divConq(TreeNode root, long min, long max){
            if (root == null){
                return true;
            }
            if (root.val <= min || root.val >= max){
                return false;
            }
            return divConq(root.left, min, Math.min(max, root.val)) &&
                    divConq(root.right, Math.max(min, root.val), max);
        }
    }


//----------------------------------------------------------------------------






}
/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

 */