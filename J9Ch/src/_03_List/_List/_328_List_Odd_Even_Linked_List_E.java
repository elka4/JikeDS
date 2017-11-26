package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  328. Odd Even Linked List

//  https://leetcode.com/problems/odd-even-linked-list/description/
//
public class _328_List_Odd_Even_Linked_List_E {
    //  https://leetcode.com/articles/odd-even-linked-list/
    public class Solution1 {
        public ListNode oddEvenList(ListNode head) {
            if (head == null) return null;
            ListNode odd = head, even = head.next, evenHead = even;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }
    }

    //1ms Java Solution
    public class Solution11 {
        public ListNode oddEvenList(ListNode head) {
            if(head == null || head.next == null)
                return head;
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;
            while(odd.next != null && even.next != null){
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }
    }

    public ListNode OddEvenList2(ListNode head)
    {
        if(head == null || head.next == null)
            return head;

        ListNode currNode = head;
        ListNode oddList  = currNode;
        ListNode evenList = currNode.next;
        ListNode evenHead = evenList;

        currNode = currNode.next.next;

        int lpCnt = 3;

        while(currNode != null)
        {
            if(lpCnt % 2 == 0)
            {
                evenList.next = currNode;
                evenList = evenList.next;
            }
            else
            {
                oddList.next = currNode;
                oddList = oddList.next;
            }

            currNode = currNode.next;
            ++lpCnt;
        }

        if (evenList != null)
            evenList.next = null;

        if(oddList != null)
            oddList.next = evenHead;

        return head;
    }
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------

}
/*

 */

/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...


 */