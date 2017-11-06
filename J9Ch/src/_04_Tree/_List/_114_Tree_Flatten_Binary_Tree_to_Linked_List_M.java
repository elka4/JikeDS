package _04_Tree._List;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

//  114. Flatten Binary Tree to Linked List
//  https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
//  http://www.lintcode.com/zh-cn/problem/flatten-binary-tree-to-linked-list/
public class _114_Tree_Flatten_Binary_Tree_to_Linked_List_M {
    // Version 1: Traverse
    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }
//        root.print();

        lastNode = root;
        TreeNode right = root.right; //切记要把root.right 先存下来。

        flatten(root.left);
        flatten(right);
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 5,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        flatten(root);
        root.print();
    }
    /*
                    root:
                   1
                  / \
                 /   \
                 2   5
                / \   \
                3 4   6
     */

///////////////////////////////////////////////////////////////

    // version 2: Divide & Conquer
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten_2(TreeNode root) {
        helper(root);
    }

    // flatten root and return the last node
    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        // connect leftLast to root.right
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLast != null) {
            return rightLast;
        }

        if (leftLast != null) {
            return leftLast;
        }

        return root;
    }

    @Test
    public void test02() {
        int[] arr = {1, 2, 5,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        flatten_2(root);
        root.print();
    }

///////////////////////////////////////////////////////////////

    // version 3: Non-Recursion
    public void flatten_3(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

            // connect
            node.left = null;
            if (stack.empty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }

    @Test
    public void test03() {
        int[] arr = {1, 2, 5,3,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        flatten(root);
        root.print();
    }


    /*
              1
               \
     1          2
    / \          \
   2   5    =>    3
  / \   \          \
 3   4   6          4
                     \
                      5
                       \
                        6
     */
///////////////////////////////////////////////////////////////
//    My short post order traversal Java solution for share
    private TreeNode prev4 = null;

    public void flatten4(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev4;
        root.left = null;
        prev4 = root;
    }

///////////////////////////////////////////////////////////////

    //    Straightforward Java Solution
    public void flatten5(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
///////////////////////////////////////////////////////////////
//Accepted simple Java solution , iterative
//    it is DFS so u need a stack. Dont forget to set the left child to null, or u'll get TLE. (tricky!)

    public void flatten6(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode curr = stk.pop();
            if (curr.right!=null)
                stk.push(curr.right);
            if (curr.left!=null)
                stk.push(curr.left);
            if (!stk.isEmpty())
                curr.right = stk.peek();
            curr.left = null;  // dont forget this!!
        }
    }

///////////////////////////////////////////////////////////////
//The idea is very simple:
//
//    flatten left subtree
//
//    flatten right subtree
//
//    concatenate root -> left flatten subtree -> right flatten subtree

    public void flatten7(TreeNode root) {
        if(root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        // save current right for concatination
        TreeNode right = root.right;

        if(root.left != null) {

            // step 1: concatinate root with left flatten subtree
            root.right = root.left;
            root.left = null; // set left to null

            // step 2: move to the end of new added flatten subtree
            while(root.right != null)
                root = root.right;

            // step 3: contatinate left flatten subtree with flatten right subtree
            root.right = right;
        }
    }
///////////////////////////////////////////////////////////////

    // Java Solution Recursive & Non-Recursive
    /**
     * Move from root down,
     * for each node,
     *  attach original right as the right child of the rigthmost node of left subtree,
     *  set original left as new right child.
     * repeat with next right child.
     */
    /// SOLUTION II: non-recursive ///
    public void flatten8(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                TreeNode temp = left;
                while (temp.right != null)
                    temp = temp.right;
                temp.right = right;
                node.right = left;
                node.left = null;
            }
            node = node.right;
        }
    }

    /// SOLUTION I: accepted, recursion ///
    public void flatten9(TreeNode root) {
        if (root == null)
            return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            TreeNode rightmost = getRightmost(left);
            rightmost.right = right;
            root.left = null; // CATCH: must set left to null explicitly
            root.right = left;
        }
        flatten(root.right);
    }

    // return the rightmost node of a subtree;
    // node must not be null.
    private TreeNode getRightmost(TreeNode node) {
        while (node.right != null)
            node = node.right;
        return node;
    }
    ///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
    private TreeNode prev = null;
    public void flatten10(TreeNode root) { if (root == null)
        return; flatten10(root.right); flatten10(root.left); root.right = prev; root.left = null; prev = root;
    }




    public void flatten2(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten2(left);
        flatten2(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

    public void flatten444(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while(p != null || !stack.empty()){

            if(p.right != null){
                stack.push(p.right);
            }

            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                TreeNode temp = stack.pop();
                p.right=temp;
            }

            p = p.right;
        }
    }



//////////////////////////////////////////////////////////////

    //My short post order traversal Java solution for share
    class Solution2{
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }

    }


//////////////////////////////////////////////////////////////

    //Straightforward Java Solution

    class Solution3{
        public void flatten(TreeNode root) {
            if (root == null) return;

            TreeNode left = root.left;
            TreeNode right = root.right;

            root.left = null;

            flatten(left);
            flatten(right);

            root.right = left;
            TreeNode cur = root;
            while (cur.right != null) cur = cur.right;
            cur.right = right;
        }

    }



//////////////////////////////////////////////////////////////

    //Accepted simple Java solution , iterative
    class Solution4{
/*
        it is DFS so u need a stack. Dont forget to set the left child to null, or u'll get TLE. (tricky!)*/

        public void flatten(TreeNode root) {
            if (root == null) return;
            Stack<TreeNode> stk = new Stack<TreeNode>();
            stk.push(root);
            while (!stk.isEmpty()){
                TreeNode curr = stk.pop();
                if (curr.right!=null)
                    stk.push(curr.right);
                if (curr.left!=null)
                    stk.push(curr.left);
                if (!stk.isEmpty())
                    curr.right = stk.peek();
                curr.left = null;  // dont forget this!!
            }
        }
    }

//////////////////////////////////////////////////////////////

    //Easy 1ms Java DFS solution

    class Solution55{
        public void flatten(TreeNode root) {
            if(root==null)
                return;

            flatten(root.left);
            flatten(root.right);

            TreeNode left  = root.left;
            TreeNode right = root.right;
            root.left  = null;
            root.right = left;
            while(root.right!=null)
                root = root.right;
            root.right = right;
        }

    }



//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////

    //Java solution refer to Mirris traversal using constant place

    class Solutoin66{
        public void flatten(TreeNode root) {
            TreeNode cur = root;
            TreeNode prev = null;
            while(cur != null){
                if(cur.left == null) cur = cur.right;
                else {
                    prev = cur.left;
                    while(prev.right != null) prev = prev.right;
                    prev.right = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                }
            }
        }
    }



//////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
}
/*

 */
/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

 */