package _03_List.Math;

import lib.ListNode;

//  2. Add Two Numbers

//  https://leetcode.com/problems/add-two-numbers/description/
//  http://www.lintcode.com/zh-cn/problem/add-two-numbers/
public class _002_List_Add_Two_Numbers_M {
//https://leetcode.com/articles/add-two-numbers/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//
//jiuzhang
public class Jiuzhang1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode point = head;
        int carry = 0;
        while(l1 != null && l2!=null){
            int sum = carry + l1.val + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            point = point.next;
        }

        while(l1 != null) {
            int sum =  carry + l1.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l1 = l1.next;
            point = point.next;
        }

        while(l2 != null) {
            int sum =  carry + l2.val;
            point.next = new ListNode(sum % 10);
            carry = sum /10;
            l2 = l2.next;
            point = point.next;
        }

        if (carry != 0) {
            point.next = new ListNode(carry);
        }
        return head.next;
    }
}


    // version: 高频题班
    public class Jiuzhang2 {
        /**
         * @param l1: the first list
         * @param l2: the second list
         * @return: the sum list of l1 and l2
         */
        public ListNode addLists(ListNode l1, ListNode l2) {
            // write your code here
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            int carry = 0;
            for (ListNode i = l1, j = l2; i != null || j != null; ) {
                int sum = carry;
                sum += (i != null) ? i.val : 0;
                sum += (j != null) ? j.val : 0;

                tail.next = new ListNode(sum % 10);
                tail = tail.next;

                carry = sum / 10;
                i = (i == null) ? i : i.next;
                j = (j == null) ? j : j.next;
            }

            if (carry != 0) {
                tail.next = new ListNode(carry);
            }
            return dummy.next;
        }
    }


//-------------------------------------------------------------------------//
}
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */

/*
你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和。

样例
给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null


 */