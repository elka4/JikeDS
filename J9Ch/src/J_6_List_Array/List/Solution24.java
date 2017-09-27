package J_6_List_Array.List;

import org.junit.Test;

/**
 * Created by Hao on 9/18/15.
 */
public class Solution24 {

    public static void main(String[] args) {
        Solution24 sol24 = new Solution24();
        int[] values = {2, 1, 2, 2, 5, 3, 5, 15, 1, 8};
        ListNode head = sol24.createList(values);
        sol24.printList(head);
        head = sol24.partitionList(head, 5);
        sol24.printList(head);
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

    public ListNode partition2(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
    
    // move nodes with values greater or equal than k to the tail
    public ListNode partitionList(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode fakeHeader = new ListNode(0);
        fakeHeader.next = head;
        ListNode pre = fakeHeader;
        ListNode cur = head;
        ListNode newTail = tail;
        while (cur != tail) {
            if (cur.val < k) {
                pre = cur;
                cur = cur.next;
            } else {
                // move to tail
                pre.next = cur.next;
                newTail.next = cur;
                newTail = newTail.next;
                newTail.next = null;
                cur = pre.next;
            }
        }
        return fakeHeader.next;
    }

    private void printList(ListNode head) {
        for (ListNode pt = head; pt != null; pt = pt.next) {
            System.out.print(pt.val + " ");
        }
        System.out.println();
    }

    // create a linkedlist from an array of values.
    private ListNode createList(int[] values) {
        ListNode header = new ListNode(0);
        ListNode cur = header;
        for (int val : values) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return header.next;
    }

    private class ListNode {
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        int val;
        ListNode next;
                public String toString(){
            String str = "" + val;
            while(next != null){
                str += "->" + next.val;
                next = next.next;

            }
            return str;
        }
    }


    @Test
    public void test01(){
        //Solution24 sol24 = new Solution24();
        int[] values = {2, 1, 2, 2, 5, 3, 5, 15, 1, 8};
        ListNode head = createList(values);
        //System.out.println(head);
        printList(head);
        printList(head);
        head = partitionList(head, 5);
        printList(head);




    }
}
