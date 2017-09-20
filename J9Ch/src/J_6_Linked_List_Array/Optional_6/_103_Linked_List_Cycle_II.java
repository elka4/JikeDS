package J_6_Linked_List_Array.Optional_6;

/**103 Linked List Cycle II

 * Created by tianhuizhu on 6/28/17.
 */
public class _103_Linked_List_Cycle_II {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next==null) {
                return null;
            }

            ListNode fast, slow;
            fast = head.next;
            slow = head;
            while (fast != slow) {
                if(fast==null || fast.next==null)
                    return null;
                fast = fast.next.next;
                slow = slow.next;
            }

            while (head != slow.next) {
                head = head.next;
                slow = slow.next;
            }
            return head;
        }
    }
}
