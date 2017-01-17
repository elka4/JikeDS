package ch9J.chapter6_LinkedList;

public class __11Reverse_Nodes_in_k_Group {
	/**
     * @param head a ListNode
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        head = dummy;
        while (head.next != null) {
            head = reverseNextK(head, k);
        }
        
        return dummy.next;
    }
    
    // reverse head->n1->..->nk->next..
    // to head->nk->..->n1->next..
    // return n1
    private ListNode reverseNextK(ListNode head, int k) {
        // check there is enought nodes to reverse
        ListNode next = head; // next is not null
        for (int i = 0; i < k; i++) {
            if (next.next == null) {
                return next;
            }
            next = next.next;
        }
        
        // reverse
        ListNode n1 = head.next;
        ListNode prev = head, curt = n1;
        for (int i = 0; i < k; i++) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        
        n1.next = curt;
        head.next = prev;
        return n1;
    }
}

/*
 * Given a linked list, reverse the nodes of a linked list k at 
 * a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes
 in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may
 be changed. Only constant memory is allowed.

Example
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Tags 
Linked List Facebook
Related Problems 
Easy Swap Nodes in Pairs 33 %
Medium Rotate List 25 %
Medium Reverse Linked List II 29 %
Easy Reverse Linked List 41 %
 */
