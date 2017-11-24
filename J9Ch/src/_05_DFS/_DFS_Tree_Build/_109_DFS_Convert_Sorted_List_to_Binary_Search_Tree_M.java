package _05_DFS._DFS_Tree_Build;
import java.util.*;
import lib.*;

//  109. Convert Sorted List to Binary Search Tree
//  https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
//  http://www.lintcode.com/zh-cn/problem/convert-sorted-list-to-balanced-bst/
public class _109_DFS_Convert_Sorted_List_to_Binary_Search_Tree_M {
    class Solution{
        private ListNode node;

        public TreeNode sortedListToBST(ListNode head) {
            if(head == null){
                return null;
            }

            int size = 0;
            ListNode runner = head;
            node = head;

            while(runner != null){
                runner = runner.next;
                size ++;
            }

            return inorderHelper(0, size - 1);
        }

        public TreeNode inorderHelper(int start, int end){
            if(start > end){
                return null;
            }

            int mid = start + (end - start) / 2;
            TreeNode left = inorderHelper(start, mid - 1);

            TreeNode treenode = new TreeNode(node.val);
            treenode.left = left;
            node = node.next;

            TreeNode right = inorderHelper(mid + 1, end);
            treenode.right = right;

            return treenode;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        // base case
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        // find the median node in the linked list, after executing this loop
        // fast will pointing to the last node, while slow is the median node.
        while (fast.next != null) {
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (prev != null) prev.next = null;
        else head = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }


    public class Solution3 {
        private class State {
            ListNode head;
            State(ListNode head) {
                this.head = head;
            }
        }
        public TreeNode sortedListToBST(ListNode head) {
            return sortedListToBST(size(head), new State(head));
        }
        private TreeNode sortedListToBST(int size, State state) {
            if (size<=0) return null;
            int mid = size/2;
            TreeNode root = new TreeNode(0);
            root.left = sortedListToBST(mid, state);
            root.val = state.head.val;
            state.head = state.head.next;
            root.right = sortedListToBST(size - mid - 1, state);
            return root;
        }
        private int size(ListNode head) {
            int size = 0;
            while (head != null) {
                head = head.next;
                size++;
            }
            return size;
        }
    }

//    Share my JAVA solution, 1ms, very short and concise.
    public class Solution4 {
        public TreeNode sortedListToBST(ListNode head) {
            if(head==null) return null;
            return toBST(head,null);
        }
        public TreeNode toBST(ListNode head, ListNode tail){
            ListNode slow = head;
            ListNode fast = head;
            if(head==tail) return null;

            while(fast!=tail&&fast.next!=tail){
                fast = fast.next.next;
                slow = slow.next;
            }
            TreeNode thead = new TreeNode(slow.val);
            thead.left = toBST(head,slow);
            thead.right = toBST(slow.next,tail);
            return thead;
        }
    }

//-------------------------------------------------------------------------///
    // 9Ch
    public class Jiuzhang {
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


//-------------------------------------------------------------------------///






}
/*

 */
