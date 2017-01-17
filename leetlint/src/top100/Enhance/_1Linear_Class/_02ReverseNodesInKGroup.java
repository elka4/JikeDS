package top100.Enhance._1Linear_Class;


public class _02ReverseNodesInKGroup {

public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k  == 1) {
            return head;
        }
        //Dummy node: unify the first subproblem with all others   
        ListNode dummy = new ListNode(0);
        dummy.next = head;
      //pre.next points to head of sublist to be reversed
        ListNode pre = dummy;
        int count = k;

        while (head != null) {
            count--;
            if (count == 0) {//Reverse every k steps
                pre = reverse(pre, head.next);
                head = pre.next;
                count = k;
            } else {
                head = head.next;//head: find head of next sublist
            }
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while (cur != nextHead) {
            ListNode tmp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = tmp;
        }          
        tail.next = nextHead;  
        return tail;//Return tail reference after each subproblem
    }  
}
