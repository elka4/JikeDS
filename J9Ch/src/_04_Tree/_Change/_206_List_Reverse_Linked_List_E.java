package _04_Tree._Change;
import lib.ListNode;

//  206. Reverse Linked List
//  https://leetcode.com/problems/reverse-linked-list/description/
//  http://www.lintcode.com/zh-cn/problem/reverse-linked-list/
//  _156_Tree_Binary_Tree_Upside_Down_M 这两题应该一起看
public class _206_List_Reverse_Linked_List_E {
    //  https://leetcode.com/problems/reverse-linked-list/solution/
//-------------------------------------------------------------------------
    //1
    //  Approach #1 (Iterative) [Accepted]
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //    In-place iterative and recursive Java solution
    public ListNode reverseList3(ListNode head) {
    /* iterative solution */
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
//-------------------------------------------------------------------------
    //4
    // 9Ch
    public class Jiuzhang {
        /**
         * @param head: The head of linked list.
         * @return: The new head of reversed linked list.
         */
        public ListNode reverse(ListNode head) {
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

//-------------------------------------------------------------------------
    //2
    //  Approach #2 (Recursive) [Accepted]
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

//-------------------------------------------------------------------------
    //3
    public ListNode reverseList4(ListNode head) {
    /* recursive solution */
        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }


//-------------------------------------------------------------------------
}
/*
翻转一个链表

样例
给出一个链表1->2->3->null，这个翻转后的链表为3->2->1->null

挑战
在原地一次翻转完成
 */

/*

 */