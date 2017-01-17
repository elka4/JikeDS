package top100.Enhance._1Linear_Class;


public class _01ReorderList {
	public void reorderList(ListNode head) {  
        if (head == null || head.next == null ||
        		head.next.next == null) {
            return ;
        }
        //1. Find middle and cut
        ListNode mid = findMid(head);
        ListNode secondHead = mid.next;
        mid.next = null;

        //2. Reverse the second
        secondHead = reverse(secondHead);
        //3. Reconnect
        head = merge(head, secondHead);
    }

    private ListNode reverse (ListNode head) {
        if (head == null || head.next == null)
        return head;
        ListNode newHead = null;
        while (head != null) {
          ListNode tmp = head.next;
          head.next = newHead;
          newHead = head;
          head = tmp;
        }
        return newHead;
    }
//Key Point: finally slow is pointing to the last node of first half 
    private ListNode findMid (ListNode head) { 

        //fast is one step ahead at the beginning
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {

            //fast move two steps a time
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode merge (ListNode head, ListNode secHead) {
    ListNode cur = head;
    while (secHead != null) {
        ListNode tmp = secHead.next;
        secHead.next = cur.next;
        cur.next =secHead;
        cur = cur.next.next;
        secHead = tmp;
    }
    return head;
    }
}
