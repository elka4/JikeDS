package _TwoPointer;
import java.util.*;
import org.junit.Test;
import lib.*;

//  141. Linked List Cycle
//  https://leetcode.com/problems/linked-list-cycle/description/
//  http://www.lintcode.com/zh-cn/problem/linked-list-cycle/
public class _141_TwoPointer_Linked_List_Cycle_M {
    //https://leetcode.com/articles/linked-list-cycle/
    //Approach #1 (Hash Table) [Accepted]
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

    //Approach #2 (Two Pointers) [Accepted]
    public boolean hasCycle(ListNode head) {
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


    ////////////////////////////////////////////////
////////////////////////////////////////////////
    //jiuzhang
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
}
/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 */

/*
给定一个链表，判断它是否有环。

样例
给出 -21->10->4->5, tail connects to node index 1，返回 true
 */