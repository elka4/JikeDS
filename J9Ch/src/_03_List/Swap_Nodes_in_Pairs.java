package _03_List;

/*
LeetCode – Swap Nodes in Pairs (Java)

Given a linked list, swap every two adjacent nodes and return its head.

For example, given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space.

You may not modify the values in the list, only nodes itself can be changed.
 */



public class Swap_Nodes_in_Pairs {

    /*Java Solution 1

    Use two template variable to track the previous and next node of each pair.*/

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode h = new ListNode(0);
        h.next = head;
        ListNode p = h;

        while(p.next != null && p.next.next != null){
            //use t1 to track first node
            ListNode t1 = p;
            p = p.next;
            t1.next = p.next;

            //use t2 to track next node of the pair
            ListNode t2 = p.next.next;
            p.next.next = p;
            p.next = t2;
        }

        return h.next;
    }





    /*Java Solution 2

    Each time I do the same problem I often get the different solutions.
    Here is another way of writing this solution.*/

    public ListNode swapPairs2(ListNode head) {
        if(head==null || head.next==null)
            return head;

        //a fake head
        ListNode h = new ListNode(0);
        h.next = head;

        ListNode p1 = head;
        ListNode p2 = head.next;

        ListNode pre = h;
        while(p1!=null && p2!=null){
            pre.next = p2;

            ListNode t = p2.next;
            p2.next = p1;
            pre = p1;
            p1.next = t;

            p1 = p1.next;

            if(t!=null)
                p2 = t.next;
        }

        return h.next;
    }



//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





//-------------------------------------------------------------------------///





}
