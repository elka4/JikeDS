package _03_List.List;

/*
LeetCode – Remove Nth Node From End of List (Java)

Given a linked list, remove the nth node from the end of list and return its head.

For example, given linked list 1->2->3->4->5 and n = 2, the result is 1->2->3->5.
 */


public class Remove_Nth_Node_From_End_of_List {


    /*    Java Solution 1 - Naive Two Passes

    Calculate the length first, and then remove the nth from the beginning.*/

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;

        //get length of list
        ListNode p = head;
        int len = 0;
        while(p != null){
            len++;
            p = p.next;
        }

        //if remove first node
        int fromStart = len-n+1;
        if(fromStart==1)
            return head.next;

        //remove non-first node
        p = head;
        int i=0;
        while(p!=null){
            i++;
            if(i==fromStart-1){
                p.next = p.next.next;
            }
            p=p.next;
        }

        return head;
    }



    /*Java Solution 2 - One Pass

    Use fast and slow pointers.
    The fast pointer is n steps ahead of the slow pointer.
    When the fast reaches the end, the slow pointer points at the
    previous element of the target element.*/

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if(head == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        for(int i=0; i<n; i++){
            fast = fast.next;
        }

        //if remove the first node
        if(fast == null){
            head = head.next;
            return head;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }


//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------





//----------------------------------------------------------------------------

}
