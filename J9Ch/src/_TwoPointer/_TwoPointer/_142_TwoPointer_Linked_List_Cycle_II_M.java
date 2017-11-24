package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;
import lib.*;


//  142. Linked List Cycle II
//  https://leetcode.com/problems/linked-list-cycle-ii/description/

//  http://www.lintcode.com/zh-cn/problem/linked-list-cycle-ii/
public class _142_TwoPointer_Linked_List_Cycle_II_M {
    //Java O(1) space solution with detailed explanation.
    //  https://discuss.leetcode.com/topic/19367/java-o-1-space-solution-with-detailed-explanation/13
/*    Define two pointers slow and fast. Both start at head node, fast is twice as fast as slow. If it reaches the end it means there is no cycle, otherwise eventually it will eventually catch up to slow pointer somewhere in the cycle.

    Let the distance from the first node to the the node where cycle begins be A, and let say the slow pointer travels travels A+B. The fast pointer must travel 2A+2B to catch up. The cycle size is N. Full cycle is also how much more fast pointer has traveled than slow pointer at meeting point.

            A+B+N = 2A+2B
            N=A+B
    From our calculation slow pointer traveled exactly full cycle when it meets fast pointer, and since originally it travled A before starting on a cycle, it must travel A to reach the point where cycle begins! We can start another slow pointer at head node, and move both pointers until they meet at the beginning of a cycle.*/

/*
When fast and slow meet at point p, the length they have run are 'a+2b+c' and 'a+b'.
Since the fast is 2 times faster than the slow. So a+2b+c == 2(a+b), then we get 'a==c'.
So when another slow2 pointer run from head to 'q', at the same time, previous slow pointer will run from 'p' to 'q', so they meet at the pointer 'q' together.
 */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;

                if (fast == slow){
                    ListNode slow2 = head;
                    while (slow2 != slow){
                        slow = slow.next;
                        slow2 = slow2.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }




/*    Concise JAVA solution based on slow fast pointers
    Explanations

    Definitions:
    Cycle = length of the cycle, if exists.
    C is the beginning of Cycle, S is the distance of slow pointer from C when slow pointer meets fast pointer.

    Distance(slow) = C + S, Distance(fast) = 2 * Distance(slow) = 2 * (C + S). To let slow poiner meets fast pointer, only if fast pointer run 1 cycle more than slow pointer. Distance(fast) - Distance(slow) = Cycle
=> 2 * (C + S) - (C + S)	= Cycle
=>	C + S = Cycle
=>	C = Cycle - S
=> This means if slow pointer runs (Cycle - S) more, it will reaches C. So at this time, if there's another point2 running from head
            => After C distance, point2 will meet slow pointer at C, where is the beginning of the cycle.*/

    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
    //---------------------------------///////
//---------------------------------///////
    // 9Ch
public class Jiuzhang {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
}
/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
 */