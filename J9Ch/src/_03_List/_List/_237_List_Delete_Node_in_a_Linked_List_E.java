package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//237. Delete Node in a Linked List

//  https://leetcode.com/problems/delete-node-in-a-linked-list/description/
//
public class _237_List_Delete_Node_in_a_Linked_List_E {
    //  https://leetcode.com/problems/delete-node-in-a-linked-list/solution/
    public void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


//    Release the memory:

    public void deleteNode2(ListNode node) {
        node.val=node.next.val;
        ListNode temp = node.next;
        node.next=node.next.next;
        temp = null;
    }

    public class Solution3 {
        public void deleteNode(ListNode node) {
            if(node != null && node.next != null) {
                node.val = node.next.val;
                node.next = node.next.next;
            }
        }
    }


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------

}
/*

 */

/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.


 */