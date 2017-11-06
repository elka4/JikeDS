package _04_Tree._List;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

/*
leet  114. Flatten Binary Tree to Linked List


453. Flatten Binary Tree to Linked List
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class Flatten_Binary_Tree_to_Linked_List {

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
    private TreeNode prev = null;

    public void flatten4(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
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



}
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

 */