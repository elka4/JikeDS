package _03_List.Remove_Insert;

import lib.ListNode;


//  82. Remove Duplicates from Sorted List II

//  https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
//  http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-list-ii/
public class _082_List_Remove_Duplicates_from_Sorted_List_II_M {
//    My accepted Java code

    public ListNode deleteDuplicates1(ListNode head) {
        if(head==null) return null;
        ListNode FakeHead=new ListNode(0);
        FakeHead.next=head;
        ListNode pre=FakeHead;
        ListNode cur=head;
        while(cur!=null){
            while(cur.next!=null&&cur.val==cur.next.val){
                cur=cur.next;
            }
            if(pre.next==cur){
                pre=pre.next;
            }
            else{
                pre.next=cur.next;
            }
            cur=cur.next;
        }
        return FakeHead.next;
    }


//    My Recursive Java Solution

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;

        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;
    }
//if current node is not unique, return deleteDuplicates with head.next.
//    If current node is unique, link it to the result of next list made by recursive call. Any improvement?


//    Another java version

    public ListNode deleteDuplicates3(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        int val = head.val;
        ListNode next = head.next;
        if(next.val != val){
            head.next = deleteDuplicates3(next);
            return head;
        }else{
            while(next != null && next.val == val){
                next = next.next;
            }
            return deleteDuplicates3(next);
        }
    }
//    recursive without while loop:

    public class Solution4 {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null || head.next == null) return head;
            boolean noHead = (head.next.val == head.val);
            ListNode n = deleteDuplicates(head.next);
            if(n != null && n.val == head.val){
                return n.next;
            }else if(noHead){
                return n;
            }else{
                head.next = n;
                return head;
            }
        }
    }

//    Java simple and clean code with comment

    public class Solution5 {
        public ListNode deleteDuplicates(ListNode head) {
            //use two pointers, slow - track the node before the dup nodes,
            // fast - to find the last node of dups.
            ListNode dummy = new ListNode(0), fast = head, slow = dummy;
            slow.next = fast;
            while(fast != null) {
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;    //while loop to find the last node of the dups.
                }
                if (slow.next != fast) { //duplicates detected.
                    slow.next = fast.next; //remove the dups.
                    fast = slow.next;     //reposition the fast pointer.
                } else { //no dup, move down both pointer.
                    slow = slow.next;
                    fast = fast.next;
                }

            }
            return dummy.next;
        }
    }
    //can be further simplified as:


    class Solution6 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(0), fast = head, slow = dummy;
            if (slow.next != fast) { //duplicates detected.
                slow.next = fast.next; //remove the dups.
            } else { //no dup, move down both pointer.
                slow = slow.next;
            }
            fast = fast.next;
            return dummy.next;

        }
    }

    class Solution7 {
        public ListNode deleteDuplicates(ListNode head) {
//            It's a simple one, well done :)
//
//            below is my solution without dummy node, almost the same:

            ListNode pre = null;
            ListNode cur = head;
            while(cur != null && cur.next != null){
                if(cur.val != cur.next.val){
                    pre = cur;
                    cur = cur.next;
                }else{
                    while(cur.next != null && cur.next.val == cur.val){
                        cur = cur.next;
                    }
                    if(pre != null){
                        pre.next = cur.next;
                    }else{
                        head = cur.next;
                    }
                    cur = cur.next;
                }
            }
            return head;
        }
    }


//    A short and simple Java solution

    public ListNode deleteDuplicates8(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
            } else {
                d.next = head;
                d = d.next;
            }
            head = head.next;
        }
        d.next = null;
        return dummy.next;
    }
/*1->1->1->2->2->3

    we skip all the 1's and start the loop from 2

    and also skip all the 2's, and now head.val == 3;

    ponit d.next to the tail, end the loop*/
/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
//jiuzhang
public class Juizhang {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }
}


/////////////////////////////////////////////////////////////////////////////////////

}
/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.


 */

/*
给定一个排序链表，删除所有重复的元素只留下原链表中没有重复的元素。

样例
给出 1->2->3->3->4->4->5->null，返回 1->2->5->null

给出 1->1->1->2->3->null，返回 2->3->null


 */