package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

/** 453. Flatten Binary Tree to Linked List
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _453_Flatten_Binary_Tree_to_Linked_List {

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

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }

//-------------------------------------------------------------------------/

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

//-------------------------------------------------------------------------/

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

//-------------------------------------------------------------------------/

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
}
