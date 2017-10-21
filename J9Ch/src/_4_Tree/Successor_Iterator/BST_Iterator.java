package _4_Tree.Successor_Iterator;

import _4_Tree.HF3_Algo_DS_II_2BST._4BinarySearchTreeIterator;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

public class BST_Iterator {
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
    @Test
    public void test01(){
        int[] arr = {1,10,11};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setRightChild(new TreeNode(6));
        root.right.setRightChild(new TreeNode(12));
        root.print();

        BSTIterator itr = new BSTIterator(root);
//        itr.AddNodeToStack(root);

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
/////////////////////////////////////////////////////////////////////
    public class BSTIterator2 {
        Stack<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            stack = new Stack<TreeNode>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            int result = node.val;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.print();

        BSTIterator itr = new BSTIterator(root);

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
/////////////////////////////////////////////////////////////////////

    public class BSTIterator3 {
        private Stack<TreeNode> stack = new Stack<>();
        TreeNode next = null;

        void AddNodeToStack(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        // @param root: The root of binary tree.
        public BSTIterator3(TreeNode root) {
            next = root;
        }

        //@return: True if there has next node, or false
        public boolean hasNext() {
            if (next != null) {
                AddNodeToStack(next);
                next = null;
            }
            return !stack.isEmpty();
        }

        //@return: return next node
        public TreeNode next() {
            if (!hasNext()) {
                return null;
            }
            TreeNode cur = stack.pop();
            next = cur.right;
            return cur;
        }
    }

    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

        BSTIterator itr = new BSTIterator(root);

        while(itr.hasNext()){
            TreeNode node = itr.next();
//            node.print();
            System.out.println(node);
        }
    }
/////////////////////////////////////////////////////////////////////
    public class BSTIterator4 {
        private Stack<TreeNode> stack = new Stack<>();
        TreeNode next = null;
        void AddNodeToStack(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        // @param root: The root of binary tree.
        public BSTIterator4(TreeNode root) {
            next = root;
        }

        //@return: True if there has next node, or false
        public boolean hasNext() {
            if (next != null) {
                AddNodeToStack(next);
                next = null;
            }
            return !stack.isEmpty();
        }

        //@return: return next node
        public TreeNode next() {
            if (!hasNext()) {
                return null;
            }
            TreeNode cur = stack.pop();
            next = cur.right;
            return cur;
        }
    }

    @Test
    public void test04(){
        int[] arr = {1,10,11};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setRightChild(new TreeNode(6));
        root.right.setRightChild(new TreeNode(12));
        root.print();

        BSTIterator itr = new BSTIterator(root);
//        itr.AddNodeToStack(root);

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
/////////////////////////////////////////////////////////////////////
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
