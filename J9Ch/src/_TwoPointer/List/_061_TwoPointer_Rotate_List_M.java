package _TwoPointer.List;

import lib.ListNode;

//61. Rotate List
//https://leetcode.com/problems/rotate-list/description/

//http://www.lintcode.com/zh-cn/problem/rotate-list/
public class _061_TwoPointer_Rotate_List_M {
/*
Share my java solution with explanation
Since n may be a large number compared to the length of list. So we need to know the length of linked list.After that, move the list after the (l-n%l )th node to the front to finish the rotation.

Ex: {1,2,3} k=2 Move the list after the 1st node to the front

Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.

So the code has three parts.

Get the length

Move to the (l-n%l)th node

3)Do the rotation


 */

    public ListNode rotateRight(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int i;
        for (i=0;fast.next!=null;i++)//Get the total length
            fast=fast.next;

        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
            slow=slow.next;

        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;

        return dummy.next;
    }

/*
Clean Java Solution with Brief Explanation
The basic idea is to link the tail of the list with the head, make it a cycle. Then count to the rotate point and cut it.
 */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null)
            return head;

        ListNode copyHead = head;

        int len = 1;
        while (copyHead.next != null) {
            copyHead = copyHead.next;
            len++;
        }

        copyHead.next = head;

        for (int i = len - k % len; i > 1; i--)
            head = head.next;

        copyHead = head.next;
        head.next = null;

        return copyHead;
    }


/*
    Java clean solution, only one pointer used

12
    Y yfcheng
    Reputation:  1,563
    I first used a ListNode p, and point it to the head, then move it to the end of the list, and at the same time get the length of the list. Then p.next = head; gives me a circle. At this time, by moving p for len-k times, it will be pointing to the node before the break point. Then all we need to do is record the next node as head, and break the circle with p.next = null.
*/

    public ListNode rotateRight3(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        ListNode p = head;
        int len = 1;
        while(p.next != null) {
            p = p.next;
            len++;
        }
        p.next = head;
        k %= len;
        for(int i = 0; i < len - k; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }

//---------------------------------///////////////////
    // 9Ch

    public class Jiuzhang {
        private int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                length ++;
                head = head.next;
            }
            return length;
        }

        public ListNode rotateRight(ListNode head, int n) {
            if (head == null) {
                return null;
            }

            int length = getLength(head);
            n = n % length;

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;

            ListNode tail = dummy;
            for (int i = 0; i < n; i++) {
                head = head.next;
            }

            while (head.next != null) {
                tail = tail.next;
                head = head.next;
            }

            head.next = dummy.next;
            dummy.next = tail.next;
            tail.next = null;
            return dummy.next;
        }
    }

}
/*
给定一个链表，旋转链表，使得每个节点向右移动k个位置，其中k是一个非负数

样例
给出链表1->2->3->4->5->null和k=2

返回4->5->1->2->3->null


 */
