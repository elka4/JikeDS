package _03_List.Other;
import lib.*;

//  234. Palindrome Linked List
//  https://leetcode.com/problems/palindrome-linked-list/
//  http://www.lintcode.com/zh-cn/problem/palindrome-linked-list/
public class _234_List_Palindrome_Linked_List_E {
/*    Java, easy to understand
    This can be solved by reversing the 2nd half and compare the two halves. Let's start with an example [1, 1, 2, 1].

    In the beginning, set two pointers fast and slow starting at the head.

            1 -> 1 -> 2 -> 1 -> null
    sf
            (1) Move: fast pointer goes to the end, and slow goes to the middle.

1 -> 1 -> 2 -> 1 -> null
    s          f
            (2) Reverse: the right half is reversed, and slow pointer becomes the 2nd head.

            1 -> 1    null <- 2 <- 1
    h                      s
            (3) Compare: run the two pointers head and slow together and compare.

            1 -> 1    null <- 2 <- 1
    h            s*/
    public boolean isPalindrome1(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
//------------------------------------------------------------------------------
    /*Java Solution 1 - Creat a new reversed list

    We can create a new list in reversed order and then compare each node.
    The time and space are O(n).*/

    public boolean isPalindrome2(ListNode head) {
        if(head == null)
            return true;

        ListNode p = head;
        ListNode prev = new ListNode(head.val);

        while(p.next != null){
            ListNode temp = new ListNode(p.next.val);
            temp.next = prev;
            prev = temp;
            p = p.next;
        }

        ListNode p1 = head;
        ListNode p2 = prev;

        while(p1!=null){
            if(p1.val != p2.val)
                return false;

            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }






    /*Java Solution 2 - Break and reverse second half

    We can use a fast and slow pointer to get the center of the list,
    then reverse the second list and compare two sublists.
    The time is O(n) and space is O(1).*/

    public boolean isPalindrome3(ListNode head) {

        if(head == null || head.next==null)
            return true;

        //find list center
        ListNode fast = head;
        ListNode slow = head;

        while(fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHead = slow.next;
        slow.next = null;

        //reverse second part of the list
        ListNode p1 = secondHead;
        ListNode p2 = p1.next;

        while(p1!=null && p2!=null){
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }

        secondHead.next = null;

        //compare two sublists now
        ListNode p = (p2==null?p1:p2);
        ListNode q = head;
        while(p!=null){
            if(p.val != q.val)
                return false;

            p = p.next;
            q = q.next;

        }

        return true;
    }




    //Java Solution 3 - Recursive

    public class Solution4 {
        ListNode left;

        public boolean isPalindrome(ListNode head) {
            left = head;

            boolean result = helper(head);
            return result;
        }

        public boolean helper(ListNode right){

            //stop recursion
            if (right == null)
                return true;

            //if sub-list is not palindrome,  return false
            boolean x = helper(right.next);
            if (!x)
                return false;

            //current left and right
            boolean y = (left.val == right.val);

            //move left to next
            left = left.next;

            return y;
        }
    }
    //Time is O(n) and space is O(1).
//-------------------------------------------------------------------------//
// 9Ch
    // This code would destroy the original structure of the linked list.
// If you do not want to destroy the structure, you can reserve the second part back.
    public class Jiuzhang {
        /**
         * @param head a ListNode
         * @return a boolean
         */
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }

            ListNode middle = findMiddle(head);
            middle.next = reverse(middle.next);

            ListNode p1 = head, p2 = middle.next;
            while (p1 != null && p2 != null && p1.val == p2.val) {
                p1 = p1.next;
                p2 = p2.next;
            }

            return p2 == null;
        }

        private ListNode findMiddle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;

            while (head != null) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }

            return prev;
        }
    }


//-------------------------------------------------------------------------//

}
/*
设计一种方式检查一个链表是否为回文链表。

样例
1->2->1 就是一个回文链表。
 */

/*

 */