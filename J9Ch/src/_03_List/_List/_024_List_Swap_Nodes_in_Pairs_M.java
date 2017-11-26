package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  24. Swap Nodes in Pairs

//  https://leetcode.com/problems/swap-nodes-in-pairs/description/
//  http://www.lintcode.com/zh-cn/problem/swap-nodes-in-pairs/
public class _024_List_Swap_Nodes_in_Pairs_M {
//    Share my accepted Java solution

    public class Solution1 {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode first = head, last = head.next;
            first.next = swapPairs(last.next);
            last.next = first;
            return last;
        }
    }


/*    My simple recursive solution
    My solution is quite simple. Just find the reverse job is the same for every 2 nodes.*/

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newhd = head.next;
        head.next = swapPairs(newhd.next);
        newhd.next = head;
        return newhd;
    }

/*
    Java simple recursive solution

    Starting to see that recursion is the perfect tool for (many) linked list problems (this one + merging list problem).
*/


    public class Solution3 {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode second = head.next;
            ListNode third = second.next;

            second.next = head;
            head.next = swapPairs(third);

            return second;
        }
    }

    //My simple JAVA solution for share

    public ListNode swapPairs4(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }

/*    I am newbie, so helping other newbies.
    I have explained the solution with inline comments,*/

    public class Solution5 {
        public ListNode swapPairs(ListNode head) {

            if (head == null) {
                return null;
            }
            ListNode newhead = new ListNode(-1);//dummy
            newhead.next = head;
            ListNode temp = newhead;

            ListNode one = null;
            ListNode two = null;

            // {dummy->1->2->3->4->null}
            //explanation for one loop rest are same.


            while(temp.next!= null && temp.next.next!=null) {
                // temp points to dummy in the beginning.
                // one -> 1
                one = temp.next;
                //two -> 2
                two = temp.next.next;
                // 1-> = 2.next = 3;
                one.next=two.next;
                // 2-> = 1
                two.next = one;
                //now dummy should point to 2
                //if the below is not done dummy->1;
                temp.next = two;
                // temp was pointing to dummy
                //temp->1
                temp = one;

                // now { dummy->2->1->3->4 }

            }
            return newhead.next;
        }
    }

//    Same thought.

    public ListNode swapPairs6(ListNode head) {
        ListNode virtual = new ListNode(0);
        virtual.next = head;
        ListNode p = head;
        ListNode pre = virtual;
        while (p != null && p.next != null) {
            pre.next = p.next;
            pre = p;
            p = p.next.next;
            pre.next.next = pre;
        }
        pre.next = p;
        return virtual.next;
    }

//    Great one, mine is messy due to the order...

    public ListNode swapPairs7(ListNode head) {
        if (head == null)  return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while(head != null && head.next != null && head.next.next != null) {
            ListNode first = head.next;
            ListNode third = head.next.next.next;
            head.next = head.next.next;
            head.next.next = first;
            first.next = third;
            head = first;
        }

        return dummy.next;
    }

//    And in fact, I don't need to make change as you said, but add one more line code as below:

    public class Solution8 {
        public ListNode swapPairs(ListNode head) {
            ListNode start = new ListNode(0);
            start.next = head;
            ListNode pre = start;
            while (head != null && head.next != null) {
                ListNode after = head.next.next;
                pre.next = head.next;
                // Time limit exceeded without this line below.
                //will always be changed and meaningless, but when it comes to the last pair, it is usable.
                head.next = head.next.next;
                pre.next.next = head;
                pre = head;
                head = after;
            }
            return start.next;
        }
    }

//    Draw a picture on paper will help a lot for List problem.

    // O(N): prev->first->second => prev->second->first, then move prev to first to maintain invariant
    // invariant: nodes behind prev (inclusive) are swapped already
    public ListNode swapPairs9(ListNode head) {
        if (head == null) return null;
        ListNode dmy = new ListNode(0), prev = dmy;
        dmy.next = head;
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            prev.next = first.next;
            first.next = first.next.next;
            prev.next.next = first;
            prev = prev.next.next;
        }
        return dmy.next;
    }

    public ListNode swapPairs10(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            ListNode curNext = cur.next;
            cur.next = curNext.next;
            curNext.next = cur.next.next;
            cur.next.next = curNext;
            cur = curNext;
        }
        return dummyHead.next;
    }
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    /**
     * @param head a ListNode
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        head = dummy;
        while (head.next != null && head.next.next != null) {
            ListNode n1 = head.next, n2 = head.next.next;
            // head->n1->n2->...
            // => head->n2->n1->...
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            // move to next pair
            head = n1;
        }

        return dummy.next;
    }
}


//--------------------------------------------------------------------------------

}
/*
给一个链表，两两交换其中的节点，然后返回交换后的链表。

样例
给出 1->2->3->4, 你应该返回的链表是 2->1->4->3。


 */

/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */