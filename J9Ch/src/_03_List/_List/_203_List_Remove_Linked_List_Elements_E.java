package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  203. Remove Linked List Elements

//  https://leetcode.com/problems/remove-linked-list-elements/description/
//  http://www.lintcode.com/zh-cn/problem/remove-linked-list-elements/
public class _203_List_Remove_Linked_List_Elements_E {
    //3 line recursive solution

    public ListNode removeElements1(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements1(head.next, val);
        return head.val == val ? head.next : head;
    }

//    I think this recursion is more intuitive:

    public ListNode removeElements2(ListNode head, int val) {
        if(head == null) return null;
        ListNode next = removeElements2(head.next, val);
        if(head.val == val) return next;
        head.next = next;
        return head;
    }

//    AC Java solution
    public class Solution3 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode fakeHead = new ListNode(-1);
            fakeHead.next = head;
            ListNode curr = head, prev = fakeHead;
            while (curr != null) {
                if (curr.val == val) {
                    prev.next = curr.next;
                } else {
                    prev = prev.next;
                }
                curr = curr.next;
            }
            return fakeHead.next;
        }
    }

/*
    Iterative short Java solution

    Here's an iterative solution without dummy head.
    First, we shift a head of a list while its' value equals to val.
    Then, we iterate through the nodes of the list checking if the next node's value equals to val and removing it if needed.*/

    public class Solution4 {
        public ListNode removeElements(ListNode head, int val) {
            while (head != null && head.val == val) head = head.next;
            ListNode curr = head;
            while (curr != null && curr.next != null)
                if (curr.next.val == val) curr.next = curr.next.next;
                else curr = curr.next;
            return head;
        }
    }


    public ListNode removeElements5(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(head != null){
            if(head.val != val){
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        cur.next = null;
        return dummy.next;
    }

//    Accepted 7 line clean java solution
    public ListNode removeElements6(ListNode head, int val) {
        if (head == null) return null;
        ListNode pointer = head;
        while (pointer.next != null) {
            if (pointer.next.val == val) pointer.next = pointer.next.next;
            else pointer = pointer.next;
        }
        return head.val == val ? head.next : head;
    }

//    Java remove linked list elements solution
    public class Solution7 {

        public ListNode removeElements(ListNode head, int val) {

            ListNode dummy = new ListNode(1);
            ListNode i = dummy;
            ListNode j = head;
            dummy.next = head;

            while(j != null){
                if(j.val == val){
                    i.next = i.next.next;
                    j = j.next;
                }else{
                    i = i.next;
                    j = j.next;
                }
            }
            return dummy.next;
        }
    }


//    IMHO, it can be easier:
    public class Solution8 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode p = dummy;
            while (p.next != null) {
                if (p.next.val == val) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return dummy.next;
        }
    }

//    An easy-understand solution with pretty fast speed
    public class Solution9 {
        public ListNode removeElements(ListNode head, int val) {
            while(head != null && head.val == val) {
                head = head.next;
            }
            if(head == null) {
                return head;
            }
            ListNode p = head;
            while(p.next != null) {
                if(p.next.val == val) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return head;
        }
    }
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    /**
     * @param head a ListNode
     * @param val an integer
     * @return a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }
}


//--------------------------------------------------------------------------------

}
/*
删除链表中等于给定值val的所有节点。

样例
给出链表 1->2->3->3->4->5->3, 和 val = 3, 你需要返回删除3之后的链表：1->2->4->5。
 */

/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5


 */