package ch9J.chapter6_LinkedList;

public class _02Reverse_Linked_List_II {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		//corner case checking
	    if (m >= n || head == null) {
	        return head;
	    }
	    
	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    head = dummy;
	    
	    //find the mth node
	    for (int i = 1; i < m; i++) {
	        if (head == null) {
	            return null;
	        }
	        head = head.next;
	    }
	    
	    //reverse	    
	    ListNode premNode = head;
	    ListNode mNode = head.next;
	    ListNode nNode = mNode, postnNode = mNode.next;
	    for (int i = m; i < n; i++) {
	        if (postnNode == null) {
	            return null;
	        }
	        ListNode temp = postnNode.next;
	        postnNode.next = nNode;
	        nNode = postnNode;
	        postnNode = temp;
	    }
	    
	    //connect
	    mNode.next = postnNode;
	    premNode.next = nNode;
	    
	    return dummy.next;
	}
}

/*
 * Reverse a linked list from position m to n.

 Notice

Given m, n satisfy the following condition: 1 � m � n � length of list.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.

Challenge 
Reverse it in-place and in one-pass

Tags 
Linked List
Related Problems 
Hard Reverse Nodes in k-Group 31 %
Easy Reverse Words in a String 23 %
Easy Reverse Linked List
 */
