package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  160. Intersection of Two Linked Lists

//  https://leetcode.com/problems/intersection-of-two-linked-lists/description/
//  http://www.lintcode.com/zh-cn/problem/intersection-of-two-linked-lists/
public class _160_List_Intersection_of_Two_Linked_Lists_E {
//  https://leetcode.com/articles/intersection-two-linked-lists/

    //Approach #1 (Brute Force) [Time Limit Exceeded]
    //Approach #2 (Hash Table) [Accepted]
    //Approach #3 (Two Pointers) [Accepted]



/*    Java solution without knowing the difference in len!

    I found most solutions here preprocess linkedlists to get the difference in len.
    Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the intersection node at the same time.

    We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference. So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null

    Below is my commented Java code:*/

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }

/*
    Concise JAVA solution, O(1) memory O(n) time

138
    Z zkfairytale
    Reputation:  468
            1, Get the length of the two lists.

2, Align them to the same start point.

3, Move them together until finding the intersection point, or the end null
*/

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }


/*
    A askyfeng7
    Reputation:  65
    Scan both lists
    For each list once it reaches the end, continue scanning the other list
    Once the two runner equal to each other, return the position
    Time O(n+m), space O(1)*/

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if( null==headA || null==headB )
            return null;

        ListNode curA = headA, curB = headB;
        while( curA!=curB){
            curA = curA==null?headB:curA.next;
            curB = curB==null?headA:curB.next;
        }
        return curA;
    }

/*    Clean Java code, easy to understand, explanation, O(m+n) time, O(1) space.

17
    S SmartFingers
    Reputation:  57
    If we add list A to the end of B, and add B to the end of A, then if they have intersection, the intersection would located at the end of the combined list, and plus! they have the same index. Awesome!
    To return the right ListNode, we cannot actually combine them, just "next" the end to another list. The first equal element is what we want to find.*/

    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        if(headA==null || headB==null)return null;

        ListNode a=headA;
        ListNode b=headB;
        while(a!=b){
            a=a==null?headB:a.next;
            b=b==null?headA:b.next;

        }return b;
    }

/*    [Java] Beats 99.56%

            16
    A always_my_fault163.com
    Reputation:  25*/
    public ListNode getIntersectionNode5(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        int len1 = 0, len2 = 0;
        while (p1 != null) {
            p1 = p1.next;
            len1++;
        }
        while (p2 != null) {
            p2 = p2.next;
            len2++;
        }
        p1 = headA;
        p2 = headB;
        if (len1 > len2) {
            for (int i = 0;i < len1 - len2; i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 0;i < len2 - len1; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

//--------------------------------------------------------------------------------

/*    Java O(n) time O(1) space solution by using "assume there are no cycles"

            15
    T TWiStErRob
    Reputation:  268
    As a reminder:

    A:         a1 → a2
                   ↘
    c1 → c2 → c3
                   ↗
    B:    b1 → b2 → b3
    Based on this I construct a structure that has a cycle in it:

    A:         a1 → a2
                   ↘
    c1 → c2 → c3 \
            ↗              |
    B:    b1 → b2 → b3                /
            ↖------------------------
    Then starting from a1 I try to find a cycle (fast iterator would wrap around to itself [slow]).
    If that's found the take some steps until the starting point is found.
            (A good explanation of this can be found here)*/

    public ListNode getIntersectionNode6(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        // find last node of list A (c3)
        ListNode endA = headA;
        while (endA.next != null) {
            endA = endA.next;
        }
        // join c3 to b1 making a c1...c3-b1...b3-c1 loop (if b3 indeed points to c1)
        endA.next = headB;

        ListNode start = null; // if there's no cycle this will stay null
        // Floyd's cycle finder
        ListNode slow = headA, fast = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // found a cycle
                // reset to beginning to find cycle start point (c1)
                start = headA;
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                break;
            }
        }
        // unjoin c3-b1
        endA.next = null;
        return start;
    }
//    If there's no cycle it simply concats B after A and traverese to the end via the fast pointer.
//--------------------------------------------------------------------------------
//Java O(1) space solution

    public ListNode getIntersectionNode7(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode currA = headA, currB = headB;
        while(currA != null) {
            lenA++;
            currA = currA.next;
        }
        while(currB != null) {
            lenB++;
            currB = currB.next;
        }
        currA = headA;
        currB = headB;
        if(lenA > lenB) {
            for(int i=0; i<lenA-lenB; i++)
                currA = currA.next;
        } else {
            for(int i=0; i<lenB-lenA; i++)
                currB = currB.next;
        }
        while(currA != currB) {
            currA = currA.next;
            currB = currB.next;
        }
        return currA;
    }
//    First get the two length for each list, then put them at the same starting line by moving the longer one forward. Then move them at the same time and check the first match.

//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // get the tail of list A.
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = listCycleII(headA);
        node.next = null;
        return result;
    }

    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}


//--------------------------------------------------------------------------------

}
/*
请写一个程序，找到两个单链表最开始的交叉节点。

 注意事项

如果两个链表没有交叉，返回null。
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
样例
下列两个链表：

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
在节点 c1 开始交叉。

挑战
需满足 O(n) 时间复杂度，且仅用 O(1) 内存。


 */

/*

 */