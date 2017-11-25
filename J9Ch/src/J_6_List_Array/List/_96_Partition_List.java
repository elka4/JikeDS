package J_6_List_Array.List;

import org.junit.Test;

/** 96 Partition List
 * Created by tianhuizhu on 6/28/17.
 */
public class _96_Partition_List {

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
        /*
        Given 1->4->3->2->5->2 and x = 3,
        return 1->2->2->4->3->5.
         */

    @Test
    public void test00(){
        //ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(2);
        //node0.next = node1;
        node1.next = node4;
        node4.next = node3;
        node3.next = node2;
        node2.next = node5;
        node5.next = node6;

        printList(node1);
        ListNode result = partition(node1, 3);

        //printList(partitionList(node1, 2));
        printList(result);

    }
//-----------------------------------------------------------------------------/

    public ListNode partition2(ListNode head, int x) {
        //dummy heads of the 1st and 2nd queues
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        //current tails of the two queues;
        ListNode curr1 = dummy1, curr2 = dummy2;
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
        //important! avoid cycle in linked list. otherwise u will get TLE.
        curr2.next = null;
        curr1.next = dummy2.next;
        return dummy1.next;
    }

    @Test
    public void test05(){
        //ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(2);
        //node0.next = node1;
        node1.next = node4;
        node4.next = node3;
        node3.next = node2;
        node2.next = node5;
        node5.next = node6;

        printList(node1);
        ListNode result = partition2(node1, 3);

        //printList(partitionList(node1, 2));
        printList(result);

    }
//-------------------------------------------------------------------------///

    public ListNode partition3 (ListNode head, int x) {
        if (head == null || head.next ==null) {
            return head;
        }
        ListNode smallPre = new ListNode(0);
        ListNode largePre = new ListNode(0);
        ListNode largeDummy = largePre;
        ListNode smallDummy = smallPre;
        while (head != null) {
            if (head.val < x) {
                smallPre.next = head;
                smallPre = smallPre.next;
            } else {
                largePre.next = head;
                largePre = largePre.next;
            }

            head = head.next;
        }
        smallPre.next = largeDummy.next;
        largePre.next = null;
        return smallDummy.next;
    }


//-----------------------------------------------------------------------------/


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

    private class ListNode {
        public ListNode(int i) {
            this.val = i;
        }
        public ListNode next;
        public int val;
    }

//-----------------------------------------------------------------------------/
    /*
    Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
     */

    @Test
    public void test01(){
        //ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(2);
        //node0.next = node1;
        node1.next = node4;
        node4.next = node3;
        node3.next = node2;
        node2.next = node5;
        node5.next = node6;

        printList(node1);
        //ListNode result = partitionList(node1, 2);

        //printList(partitionList(node1, 2));
        printList(partitionList(node1, 3));

    }

    @Test
    public void test02(){
        //ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(2);
        //node0.next = node1;
        // 1,4,3,2,5,2
        node1.next = node4;
        node4.next = node3;
        node3.next = node2;
        node2.next = node5;
        node5.next = node6;

        printList(node1);
        ListNode result = partition(node1, 3);
        printList(result);
        printList(node1);
        //printList(partitionList(node1, 2));
        printList(result);
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
    @Test
    public void test03(){
        //Solution24 sol24 = new Solution24();
        int[] values = {2, 1, 2, 2, 5, 3, 5, 15, 1, 8};
        ListNode head = createList(values);
        //System.out.println(head);
        printList(head);
        printList(head);
        ListNode result = partition2(head, 5);
        printList(result);
        printList(head);
    }
    @Test
    public void test04(){
        //Solution24 sol24 = new Solution24();
        int[] values = {1,4,3,2,5,2};
        ListNode head = createList(values);
        //System.out.println(head);
        printList(head);

        ListNode result = partition3(head, 3);
        printList(result);
        printList(head);
    }

}
