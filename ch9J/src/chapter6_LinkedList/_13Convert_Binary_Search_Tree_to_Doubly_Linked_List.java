package ch9J.chapter6_LinkedList;

public class _13Convert_Binary_Search_Tree_to_Doubly_Linked_List {
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
     * */
    //Definition for Doubly-ListNode.
    class DoublyListNode {
        int val;
        DoublyListNode next, prev;
        DoublyListNode(int val) {
            this.val = val;
            this.next = this.prev = null;
        }
    }


    class ResultType {
        DoublyListNode first, last;

        public ResultType(DoublyListNode first, DoublyListNode last) {
            this.first = first;
            this.last = last;
        }
    }

    class Solution {
        /**
         * @param root: The root of tree
         * @return: the head of doubly list node
         */
        public DoublyListNode bstToDoublyList(TreeNode root) {
            if (root == null) {
                return null;
            }

            ResultType result = helper(root);
            return result.first;
        }

        public ResultType helper(TreeNode root) {
            if (root == null) {
                return null;
            }

            ResultType left = helper(root.left);
            ResultType right = helper(root.right);
            DoublyListNode node = new DoublyListNode(root.val);

            ResultType result = new ResultType(null, null);

            if (left == null) {
                result.first = node;
            } else {
                result.first = left.first;
                left.last.next = node;
                node.prev = left.last;
            }

            if (right == null) {
                result.last = node;
            } else {
                result.last = right.last;
                right.first.prev = node;
                node.next = right.first;
            }

            return result;
        }
    }
}



/*
 * Convert a binary search tree to doubly linked list with in-order traversal.

Have you met this question in a real interview? Yes
Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

Tags 
Linked List
Related Problems 
Easy Flatten Binary Tree to Linked List 28 %
Medium Convert Sorted List to Balanced BST 28 %
 */