package J_6_LinkedList;

public class _06Linked_List_Cycle {
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

/*
 * Given a linked list, determine if it has a cycle in it.



Have you met this question in a real interview? Yes
Example
Given -21->10->4->5, tail connects to node index 1, return true

Challenge 
Follow up:
Can you solve it without using extra space?

Tags 
Two Pointers Linked List
Related Problems 
Medium Intersection of Two Linked Lists 39 %
 */