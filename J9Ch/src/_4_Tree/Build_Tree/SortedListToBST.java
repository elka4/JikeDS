package _4_Tree.Build_Tree;

import lib.*;


public class SortedListToBST {
    // jiuzhang
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
///////////////////////////////////////////////////////////////////////////

//Share my JAVA solution, 1ms, very short and concise.
    public TreeNode sortedListToBST1(ListNode head) {
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
///////////////////////////////////////////////////////////////////////////
    // Share my O(1) space and O(n) time Java code
    private ListNode node;

    public TreeNode sortedListToBST2(ListNode head) {
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

///////////////////////////////////////////////////////////////////////////
    // Recursive BST construction using slow-fast traversal on linked list
    public TreeNode sortedListToBST3(ListNode head) {
        if(head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev =null;
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            prev =slow;
            slow=slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if(prev != null)
            prev.next = null;
        else
            head  = null;

        root.left = sortedListToBST3(head);
        root.right = sortedListToBST3(slow.next);
        return root;
    }

///////////////////////////////////////////////////////////////////////////



}
/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


 */