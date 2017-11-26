package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;

//114. Flatten Binary Tree to Linked List

public class _114_DFS_Flatten_Binary_Tree_to_Linked_List_M {
    //My short post order traversal Java solution for share
    class Solution{
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

    class Solutoin2{
        public void flatten(TreeNode root) {
            flatten(root,null);
        }
        private TreeNode flatten(TreeNode root, TreeNode pre) {
            if(root==null) return pre;
            pre=flatten(root.right,pre);
            pre=flatten(root.left,pre);
            root.right=pre;
            root.left=null;
            pre=root;
            return pre;
        }
    }

    public class Solution3 {
        private TreeNode pointer = null;

        public void flatten(TreeNode root) {
            if(root == null)
                return;

            if(pointer != null)
                pointer.right = root;

            pointer = root;

            TreeNode right = root.right;
            flatten(root.left);
            root.left = null;
            flatten(right);
        }
    }
//----------------------------------------------------------------------------
    // 9Ch
// Version 1: Traverse
public class Jiuzhang1 {
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
}

    // version 2: Divide & Conquer
    public class Jiuzhang2 {
        /**
         * @param root: a TreeNode, the root of the binary tree
         * @return: nothing
         */
        public void flatten(TreeNode root) {
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
    }

// version 3: Non-Recursion
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    public class Jiuzhang3 {
        /**
         * @param root: a TreeNode, the root of the binary tree
         * @return: nothing
         */
        public void flatten(TreeNode root) {
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
    }



//----------------------------------------------------------------------------






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
