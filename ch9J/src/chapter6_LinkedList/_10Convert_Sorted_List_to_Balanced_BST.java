package chapter6_LinkedList;

public class _10Convert_Sorted_List_to_Balanced_BST {
    private ListNode current;

    private int getListLength(ListNode head) {
        int size = 0;

        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int size;

        current = head;
        size = getListLength(head);

        return sortedListToBSTHelper(size);
    }

    public TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) {
            return null;
        }

        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;

        return root;
    }
}


/*
 * Given a singly linked list where elements are sorted in ascending
 *  order, convert it to a height balanced BST.

Example
               2
1->2->3  =>   / \
             1   3
Tags 
Recursion Linked List
Related Problems 
Easy Flatten Binary Tree to Linked List 28 %
Easy Convert Sorted Array to Binary Search Tree With Minimal Height 31 %
 */