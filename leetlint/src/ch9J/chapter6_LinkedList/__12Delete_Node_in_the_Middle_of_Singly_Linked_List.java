package ch9J.chapter6_LinkedList;


public class __12Delete_Node_in_the_Middle_of_Singly_Linked_List {
	public void deleteNode(ListNode node) {
        // write your code here
        if (node == null || node.next == null)
            return;
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
        return;
    }
}

/*
 * mplement an algorithm to delete a node in the middle of a singly
 *  linked list, given only access to that node.

Example
Given 1->2->3->4, and node 3. return 1->2->4

Tags 
Cracking The Coding Interview Linked List
Related Problems 
Easy Remove Nth Node From End of List 29 %
 */