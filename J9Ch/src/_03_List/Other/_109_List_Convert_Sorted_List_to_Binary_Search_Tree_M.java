package _03_List.Other;

import lib.ListNode;
import lib.TreeNode;


//  109. Convert Sorted List to Binary Search Tree

//  https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
//  http://www.lintcode.com/zh-cn/problem/convert-sorted-list-to-balanced-bst/
public class _109_List_Convert_Sorted_List_to_Binary_Search_Tree_M {
//    Share my JAVA solution, 1ms, very short and concise.
    public class Solution1 {
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


//        Share my O(1) space and O(n) time Java code
    class Solultion2{
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


//    Recursive BST construction using slow-fast traversal on linked list
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
//    Traverse the list to get the middle element and make that the root.
// left side of the list forms left sub-tree and right side of the middle element forms the right sub-tree.

//    making this solution slightly easier to understand. here

    public TreeNode sortedListToBST4(ListNode head) {
        if(null == head){
            return null;
        }

        if(null == head.next){
            return new TreeNode(head.val);
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while(null != fast && null != fast.next){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST4(head);
        root.right = sortedListToBST4(slow.next);

        return root;
    }

//    Share My Easy Understatnd Java Solution.

    public class Solution5 {
        public TreeNode sortedListToBST(ListNode head) {
            if(head==null)
                return null;
            ListNode slow = head;
            ListNode fast = head;
            ListNode temp=null;

            //find the mid node
            while(fast.next!=null && fast.next.next!=null){
                fast = fast.next.next;
                temp = slow;
                slow = slow.next;
            }

            if(temp!=null)
                temp.next = null; //break the link
            else
                head = null;

            TreeNode root = new TreeNode(slow.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(slow.next);
            return root;
        }
    }

//    My simple Java solution

    public class Solution6 {

        public TreeNode sortedListToBST(ListNode head) {

            if (head == null) { return null; }
            if (head.next == null) { return new TreeNode(head.val); }

            ListNode mid = head;
            ListNode pre_mid = null;
            ListNode fast = head;

            while (true) {
                if (fast != null && fast.next != null) {
                    fast = fast.next.next;
                } else {
                    break;
                }
                pre_mid = mid;
                mid = mid.next;
            }
            if (pre_mid != null)
                pre_mid.next = null;


            TreeNode root = new TreeNode(mid.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(mid.next);

            return root;
        }
    }

//    Java 1ms solution. Easy understood. The main idea of the solution is similar to merge sort.

    //The main idea of the solution is similar to merge sort.(#148 Sort List https://leetcode.com/problems/sort-list/)
    //Divide the sorted list into halves.
    //The middle of the list is root.
    //The left half of the list is the left child of root.
    //The right half of the list is the right child of root.
    //Then do the same to the left child and right child recursively.
    //Pay attention to the type: ListNode TreeNode
    public class Solution7 {
        public TreeNode sortedListToBST(ListNode head) {
            if(head==null){
                return null;
            }
            if(head.next==null){
                TreeNode treeNode=new TreeNode(head.val);
                return treeNode;
            }
            ListNode slow=head;
            ListNode fast=head.next.next;
            while(fast!=null&&fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }
            TreeNode root=new TreeNode(slow.next.val);
            ListNode temp=slow.next.next;
            slow.next=null;
            root.left=sortedListToBST(head);
            root.right=sortedListToBST(temp);
            return root;
        }
    }

//    Java fast-slow pointer recursive solution.

    public TreeNode sortedListToBST8(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode fast = head, slow = head;
        if (fast != null && fast.next != null) {
            fast = fast.next.next;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode node = new TreeNode(slow.next.val);
        node.right = sortedListToBST8(slow.next.next);
        slow.next = null;
        node.left = sortedListToBST8(head);
        return node;
    }

//    Divide&Conquer Java solution Complexity?


    public class Solution9 {
        public TreeNode sortedListToBST(ListNode head) {
            if(head==null) return null;
            if(head.next==null) return new TreeNode(head.val);

            ListNode slow = head, fast = head.next;
            while(fast!=null&&fast.next!=null&&fast.next.next!=null){
                fast=fast.next.next;
                slow=slow.next;
            }

            TreeNode root = new TreeNode(slow.next.val);


            ListNode second=slow.next.next;
            slow.next=null;

            root.left = sortedListToBST(head);
            root.right = sortedListToBST(second);

            return root;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
//jiuzhang
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

/////////////////////////////////////////////////////////////////////////////////////

}
/*
给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树

样例
               2
1->2->3  =>   / \
             1   3
 */

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.


 */