package J_6_List_Array.List;

/** 165 Merge Two Sorted Lists
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */
public class _165_Merge_Two_Sorted_Lists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }

        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }

        return dummy.next;
    }


}
