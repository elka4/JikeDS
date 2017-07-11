package J_6_Linked_List_Array.all;

/** 96 Partition List
 * Created by tianhuizhu on 6/28/17.
 */
public class _96_Partition_List {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return null;
            }

            ListNode leftDummy = new ListNode(0);
            ListNode rightDummy = new ListNode(0);
            ListNode left = leftDummy, right = rightDummy;

            while (head != null) {
                if (head.val < x) {
                    left.next = head;
                    left = head;
                } else {
                    right.next = head;
                    right = head;
                }
                head = head.next;
            }

            right.next = null;
            left.next = rightDummy.next;
            return leftDummy.next;
        }
    }
}
