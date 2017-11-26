package _03_List.Cycle;

import lib.ListNode;

import java.util.HashSet;
import java.util.Set;


//  141. Linked List Cycle

//  https://leetcode.com/problems/linked-list-cycle/description/
//  http://www.lintcode.com/zh-cn/problem/linked-list-cycle/
public class _142_List_Linked_List_Cycle_II_M {
    //  https://leetcode.com/problems/linked-list-cycle/solution/
    //  Approach #1 (Hash Table) [Accepted]
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //  Approach #2 (Two Pointers) [Accepted]
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

//--------------------------------------------------------------------------------
//O(1) Space Solution
    public boolean hasCycle3(ListNode head) {
        if(head==null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
/*    Use two pointers, walker and runner.
    walker moves step by step. runner moves two steps at time.
    if the Linked List has a cycle walker and runner will meet at some point.
*/


//Simple and easy understanding java solution, Time o(n) ,Space O(1)
    public class Solution4 {
        public boolean hasCycle(ListNode head) {
            ListNode p = head,pre = head;
            while(p != null && p.next != null){
                if (p.next == head) return true;
                p = p.next;
                pre.next = head;
                pre = p;
            }
            return false;
        }
    }
//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    public Boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}

//--------------------------------------------------------------------------------

}
/*
给定一个链表，判断它是否有环。

样例
给出 -21->10->4->5, tail connects to node index 1，返回 true
 */

/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 */