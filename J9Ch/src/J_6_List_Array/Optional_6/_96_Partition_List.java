package J_6_List_Array.Optional_6;

import org.junit.Test;

/** 96 Partition List
 * Created by tianhuizhu on 6/28/17.
 */

// Partition List
public class _96_Partition_List {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        @Override
        public String toString(){
            if (next != null) {
                return val + "->" + next.toString();
            } else {
                return ((Integer) val).toString();
            }
        }
    }

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
    //1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.

    @Test
    public void test01(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        System.out.println(node1);

        System.out.println(partition(node1, 3));
    }
////////////////////////////////////////////////////////


////////////////////////////////////////////////////////


////////////////////////////////////////////////////////



}
