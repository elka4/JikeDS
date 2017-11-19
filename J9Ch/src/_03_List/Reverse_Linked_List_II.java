package _03_List;

/*
LeetCode – Reverse Linked List II (Java)

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example: given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.
 */


public class Reverse_Linked_List_II {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n) return head;
        //track (m-1)th node
        ListNode prev = null;
        //first's next points to mth
        ListNode first = new ListNode(0);
        //second's next points to (n+1)th
        ListNode second = new ListNode(0);

        int i=0;
        ListNode p = head;
        while(p!=null){
            i++;
            if(i==m-1){
                prev = p;
            }

            if(i==m){
                first.next = p;
            }

            if(i==n){
                second.next = p.next;
                p.next = null;
            }

            p= p.next;
        }
        if(first.next == null)
            return head;

        // reverse list [m, n]
        ListNode p1 = first.next;
        ListNode p2 = p1.next;
        p1.next = second.next;

        while(p1!=null && p2!=null){
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        //connect to previous part
        if(prev!=null)
            prev.next = p1;
        else
            return p1;

        return head;
    }


//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///
}
