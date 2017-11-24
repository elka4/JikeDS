package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;
import lib.*;

//  86. Partition List

//  https://leetcode.com/problems/partition-list/description/

//http://www.lintcode.com/zh-cn/problem/partition-list/
public class _086_TwoPointer_Partition_List_M {
    //Concise java code with explanation, one pass
//    the basic idea is to maintain two queues, the first one stores all nodes with val less than x , and the second queue stores all the rest nodes. Then concat these two queues. Remember to set the tail of second queue a null next, or u will get TLE.

    public ListNode partition(ListNode head, int x) {
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


    //Java solution -- pick out larger nodes and append to the end

    public ListNode partition2(ListNode head, int x) {
        if(head==null || head.next==null) return head;

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode p1=l1, p2=l2;

        p1.next = head;
        while(p1.next!=null) {
            // keep moving larger node to list 2;

            if(p1.next.val>=x) {
                ListNode tmp = p1.next;
                p1.next = tmp.next;

                p2.next = tmp;
                p2 = p2.next;
            }
            else {
                p1 = p1.next;
            }
        }

        // conbine lists 1 and 2;
        p2.next = null;
        p1.next = l2.next;
        return l1.next;
    }

    //Simple Java solution
    public class Solution3 {
        public ListNode partition(ListNode head, int x) {
            ListNode fake1 = new ListNode(0);
            ListNode fake2 = new ListNode(0);
            ListNode t1 = fake1;
            ListNode t2 = fake2;

            ListNode n = head;
            while (n!=null){
                if (n.val<x){
                    t1.next = n;
                    t1 = t1.next;
                }
                else{
                    t2.next = n;
                    t2 = t2.next;
                }
                n = n.next;
            }
            t2.next = null;
            t1.next = fake2.next;
            return fake1.next;
        }
    }

//My ac java code
public ListNode partition4(ListNode head, int x) {

    ListNode firstHead = new ListNode(0);
    firstHead.next = head;
    ListNode secondHead = new ListNode(x);


    ListNode first = firstHead;
    ListNode second = secondHead;
    ListNode curNode = head;
    while(curNode!=null){
        ListNode tmp = curNode.next;
        if(curNode.val<x){

            first.next = curNode;
            first = curNode;
        }else{
            second.next = curNode;
            second = curNode;
            second.next = null;// important
        }
        curNode = tmp;
    }
    first.next = secondHead.next;
    return firstHead.next;
}


//-------------------------------------------------------------------------//////
    // 9Ch
public class Jiuzhang {
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
/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */

/*
给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。

你应该保留两部分内链表节点原有的相对顺序。



样例
给定链表 1->4->3->2->5->2->null，并且 x=3

返回 1->2->2->4->3->5->null


 */