package J_6_List_Array.Required_10;

/** 102 Linked List Cycle
 * Medium

 * Created by tianhuizhu on 6/28/17.
 */
public class _102_Linked_List_Cycle {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class Solution {
        public Boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }

            ListNode fast, slow;
            fast = head.next;
            slow = head;
            while (fast != slow) {
                if(fast==null || fast.next==null)
                    return false;
                fast = fast.next.next;
                slow = slow.next;
            }
            return true;
        }
    }
}
