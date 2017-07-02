package S_7_Follow_Up.Required_14;

import J_3_Binary_Tree_Divide_Conquer.Required_10.TreeNode;

import java.util.Stack;

/** 453. Flatten Binary Tree to Linked List
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _453_Flatten_Binary_Tree_to_Linked_List_Easy {
    public class TreeNode {
        int val;
        S_7_Follow_Up.Required_14.TreeNode left;
        S_7_Follow_Up.Required_14.TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Version 1: Traverse
    public class Solution1 {

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
    public class Solution2 {
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
    public class Solution3 {
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
}
