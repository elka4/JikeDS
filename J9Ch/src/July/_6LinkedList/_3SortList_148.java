package July._6LinkedList;

import lib.*;

public class _3SortList_148 {
    //Java merge sort solution
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }

//-------------------------------------------------------------------------/////

    //I have a pretty good MergeSort method. Can anyone speed up the run time or reduce the memory usage?
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode f = head.next.next;
        ListNode p = head;
        while (f != null && f.next != null) {
            p = p.next;
            f =  f.next.next;
        }
        ListNode h2 = sortList(p.next);
        p.next = null;
        return merge(sortList(head), h2);
    }
    public ListNode merge2(ListNode h1, ListNode h2) {
        ListNode hn = new ListNode(Integer.MIN_VALUE);
        ListNode c = hn;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                c.next = h1;
                h1 = h1.next;
            }
            else {
                c.next = h2;
                h2 = h2.next;
            }
            c = c.next;
        }
        if (h1 != null)
            c.next = h1;
        if (h2 != null)
            c.next = h2;
        return hn.next;
    }

//-------------------------------------------------------------------------/////



//-------------------------------------------------------------------------/////




}
