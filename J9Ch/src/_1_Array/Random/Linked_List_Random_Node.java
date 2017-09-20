package _1_Array.Random;

import java.util.Random;
/*
LeetCode – Linked List Random Node (Java)

Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Java Solution

This problem is trivial, so I focus on the follow-up problem.

We want the probability of being chosen is 1/count.

Given a list 1 -> 2 -> 3 -> 4 -> 5 -> ... -> n, the only time we can possibly select the third node is when the pointer points to 3. The probability of selecting the third node is 1/3 * 3/4 * 4/5 * ... * (n-1)/n = 1/n.
 */
public class Linked_List_Random_Node {
    class ListNode{
        ListNode next;
        int val;

    }

    public class Solution {

        /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
        Random r=null;
        ListNode h=null;
        public Solution(ListNode head) {
            r = new Random();
            h = head;
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int count=1;
            ListNode p = h;
            int result = 0;
            while(p!=null){
                if(r.nextInt(count)==0)
                    result= p.val;
                count++;
                p = p.next;
            }
            return result;
        }
    }

///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////



}
