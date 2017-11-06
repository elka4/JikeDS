package _04_Tree._Build;
import lib.*;

//  109. Convert Sorted List to Binary Search Tree
//  https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
//  http://www.lintcode.com/zh-cn/problem/convert-sorted-list-to-balanced-bst/
public class _109_Convert_Sorted_List_to_Binary_Search_Tree {
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
///////////////////////////////////////////////////////////////////////////
    static ListNode h;

    public TreeNode sortedListToBST11(ListNode head) {
        if (head == null)
            return null;

        h = head;
        int len = getLength(head);
        return sortedListToBST(0, len - 1);
    }

    // get list length
    public int getLength(ListNode head) {
        int len = 0;
        ListNode p = head;

        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    // build tree bottom-up
    public TreeNode sortedListToBST(int start, int end) {
        if (start > end)
            return null;

        // mid
        int mid = (start + end) / 2;

        TreeNode left = sortedListToBST(start, mid - 1);
        TreeNode root = new TreeNode(h.val);
        h = h.next;
        TreeNode right = sortedListToBST(mid + 1, end);

        root.left = left;
        root.right = right;

        return root;
    }


//////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////



}
/*
排序列表转换为二分查找树

 描述
 笔记
 数据
 评测
给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树

样例
               2
1->2->3  =>   / \
             1   3
标签
链表 递归
 */
/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


 */