package j_3_BianryTree.video;

import lib.TreeNode;

import java.util.Stack;

public class _09BST_Iterator {
    //it is the same as the nonrecursive inorder traversal
    class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        private TreeNode curt;

        // @param root: The root of binary tree.
        public BSTIterator(TreeNode root) {
            curt = root;
        }

        //@return: True if there has next node, or false
        public boolean hasNext() {
            return (curt != null || !stack.isEmpty());
        }

        //@return: return next node
        public TreeNode next() {
            while (curt != null) {
                stack.push(curt);
                curt = curt.left;
            }

            curt = stack.pop();
            TreeNode node = curt;
            curt = curt.right;

            return node;
        }
    }
}


/*
 * Design an iterator over a binary search tree with the following rules:

Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.
Have you met this question in a real interview? Yes
Example
For the following binary search tree, in-order traversal by using 
iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
Challenge 
Extra memory usage O(h), h is the height of the tree.

Super Star: Extra memory usage O(1)

Tags 
Binary Tree LintCode Copyright Non Recursion Binary Search Tree 
Google LinkedIn Facebook

Related Problems 
Medium Inorder Successor in Binary Search Tree 30 %
 * */
