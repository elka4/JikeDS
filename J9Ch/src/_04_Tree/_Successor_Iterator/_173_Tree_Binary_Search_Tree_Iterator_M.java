package _04_Tree._Successor_Iterator;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

//  173. Binary Search Tree Iterator
//  https://leetcode.com/problems/binary-search-tree-iterator/
//  http://wiki.ruihan.org/index.php/All_algorithm_problems/linked_list
//  Stack, Tree, Design

//  Binary Tree Inorder Traversal
//  Flatten 2D Vector
//  Zigzag Iterator
//  Peeking Iterator
//  Inorder Successor in BST

//  设计题关键就是要选对数据结构，Tree的Iterator关键就是要会用Stack
//  6:
public class _173_Tree_Binary_Search_Tree_Iterator_M {
//-------------------------------------------------------------------------------
    //1
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
        // itr.AddNodeToStack(root);

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

//-------------------------------------------------------------------------------
    //2
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

//-------------------------------------------------------------------------------
    //3
    //jiuzhang
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

//-------------------------------------------------------------------------------
    //4
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
//-------------------------------------------------------------------------------
    //5
    public class BSTIterator6 {
        private Stack<TreeNode> stack = new Stack<TreeNode>();
        public BSTIterator6(TreeNode root) { pushAll(root);
        }
        /** @return whether we have a next smallest number */
        public boolean hasNext() { return !stack.isEmpty();
        }
        /** @return the next smallest number */
        public int next() {
            TreeNode tmpNode = stack.pop(); pushAll(tmpNode.right);
            return tmpNode.val;
        }
        private void pushAll(TreeNode node) {

            for (; node != null; stack.push(node), node = node.left);
        }
    }

//-------------------------------------------------------------------------------
    //6
    public class BSTIterator22 {

        private Stack<TreeNode> stack;
        public BSTIterator22(TreeNode root) {
            stack = new Stack<>();
            TreeNode cur = root;
            while(cur != null){
                stack.push(cur);
                if(cur.left != null)
                    cur = cur.left;
                else
                    break;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            TreeNode cur = node;
            // traversal right branch
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    if(cur.left != null)
                        cur = cur.left;
                    else
                        break;
                }
            }
            return node.val;
        }
    }
//-------------------------------------------------------------------------------

}
/* leetcode
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */


/* lintcode
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

/*  lintcode
二叉查找树迭代器

 描述
 笔记
 数据
 评测
设计实现一个带有下列属性的二叉查找树的迭代器：

元素按照递增的顺序被访问（比如中序遍历）
next()和hasNext()的询问操作要求均摊时间复杂度是O(1)
样例
对于下列二叉查找树，使用迭代器进行中序遍历的结果为 [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
挑战
额外空间复杂度是O(h)，其中h是这棵树的高度

Super Star：使用O(1)的额外空间复杂度


 */