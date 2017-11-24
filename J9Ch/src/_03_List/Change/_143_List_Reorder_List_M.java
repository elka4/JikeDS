package _03_List.Change;

import lib.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


//  143. Reorder List

//  https://leetcode.com/problems/reorder-list/description/
//  http://www.lintcode.com/problem/reorder-list/
public class _143_List_Reorder_List_M {
//    Java solution with 3 steps
//    This question is a combination of Reverse a linked list I & II. It should be pretty straight forward to do it in 3 steps :)

    public void reorderList1(ListNode head) {
        if(head==null||head.next==null) return;

        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){
            p1=p1.next;
            p2=p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }


//    Java solution with 3 steps
    public class Solution1 {

        public void reorderList(ListNode head) {
            if (head == null || head.next == null)
                return;

            // step 1. cut the list to two halves
            // prev will be the tail of 1st half
            // slow will be the head of 2nd half
            ListNode prev = null, slow = head, fast = head, l1 = head;

            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }

            prev.next = null;

            // step 2. reverse the 2nd half
            ListNode l2 = reverse(slow);

            // step 3. merge the two halves
            merge(l1, l2);
        }

        ListNode reverse(ListNode head) {
            ListNode prev = null, curr = head, next = null;

            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }

        void merge(ListNode l1, ListNode l2) {
            while (l1 != null) {
                ListNode n1 = l1.next, n2 = l2.next;
                l1.next = l2;

                if (n1 == null)
                    break;

                l2.next = n1;
                l1 = n1;
                l2 = n2;
            }
        }

    }

/*        Use Recursion is simpler and quicker
        This problem could be solved by recursion, we first dig down to the middle node of the list, and recurse back from that point, the time complexity is O(N), and faster than existing algorithm.
                1 -> 2 ->3 ->4, first dig down to 3, then recurse back, concatenate 2->3 (which already together), then 1->4(we return next node of each node starting from middle one)*/
    class Solution3{

        public void reorderList(ListNode head){
            if(head == null || head.next == null) return;
            ListNode h = reorderList(head, head,head);
        }

        public ListNode reorderList(ListNode prev, ListNode slow, ListNode faster){
            if(faster == null || faster.next == null) {
                if(faster != null) {
                    ListNode reverse = slow.next;
                    slow.next = null;
                    return reverse;
                }
                prev.next = null;
                return slow;
            }
            ListNode retNode = reorderList(slow, slow.next, faster.next.next);
            // concanate
            ListNode temp = retNode.next;
            retNode.next = slow.next;
            slow.next = retNode;
            return temp;
        }
    }


//    My java solution in O(n) time
    //1. find the middle node
//2. reverse the right side of the list
//3. merger the left side list and right side list


    public class Solution4 {
        public void reorderList(ListNode head) {
            if(head==null) return;
            ListNode slow = head, fast = head;
            while(fast!=null && fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode mid = slow, cur = slow.next;
            if(cur!=null){
                ListNode tmp = cur.next;
                cur.next = null;
                cur = tmp;
            }
            while(cur!=null){
                ListNode tmp = cur.next;
                cur.next = mid.next;
                mid.next = cur;
                cur = tmp;
            }
            ListNode left = head, right = mid.next;
            while(right!=null){
                mid.next = right.next;
                right.next = left.next;
                left.next = right;
                left = right.next;
                right = mid.next;
            }

        }
    }

    //Accepted Answer in Java

    public class Solution5 {

        private ListNode start;

        public void reorderList(ListNode head) {

            // 1. find the middle point
            if(head == null || head.next == null || head.next.next == null)return;

            ListNode a1 = head, a2 = head;

            while(a2.next!=null){
                // a1 step = 1
                a1 = a1.next;
                // a2 step = 2
                a2 = a2.next;
                if(a2.next==null)break;
                else a2 = a2.next;
            }
            // a1 now points to middle, a2 points to last elem

            // 2. reverse the second half of the list
            this.reverseList(a1);

            // 3. merge two lists
            ListNode p = head, t1 = head, t2 = head;
            while(a2!=a1){ // start from both side of the list. when a1, a2 meet, the merge finishes.
                t1 = p;
                t2 = a2;
                p = p.next;
                a2 = a2.next;

                t2.next = t1.next;
                t1.next = t2;
            }
        }

        // use recursion to reverse the right part of the list
        private ListNode reverseList(ListNode n){

            if(n.next == null){
                // mark the last node
                // this.start = n;
                return n;
            }

            reverseList(n.next).next = n;
            n.next = null;
            return n;
        }

//        Instead of using recursion for reversing the list, iteration code like below can be used. Recursion creates new stack as such the space increases.

        public ListNode reverseList2(ListNode head) {
            ListNode reverse = null;
            ListNode next = null;
            while(head != null) {
                next = head.next;
                head.next = reverse;
                reverse = head;
                head = next;
            }
            return reverse;
        }
    }


/*
    Java solution with stack

    I see no one use stack with java as the same idea as me, so I share my code here.

    It is a bit straightforward, so need not explaination.
*/

    public class Solution6 {
        public void reorderList(ListNode head) {
            if (head==null||head.next==null) return;
            Deque<ListNode> stack = new ArrayDeque<ListNode>();
            ListNode ptr=head;
            while (ptr!=null) {
                stack.push(ptr); ptr=ptr.next;
            }
            int cnt=(stack.size()-1)/2;
            ptr=head;
            while (cnt-->0) {
                ListNode top = stack.pop();
                ListNode tmp = ptr.next;
                ptr.next=top;
                top.next=tmp;
                ptr=tmp;
            }
            stack.pop().next=null;
        }
    }


//    Clear and Simple JAVA O(n) solution with O(1) extra space
    public class Solution7 {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) return;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode n2 = reverse(slow.next);
            slow.next = null;
            combine(head, n2);
        }
        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode preHead = new ListNode(0);
            ListNode runner = head;
            while (runner != null) {
                ListNode tmp = preHead.next;
                preHead.next = runner;
                runner = runner.next;
                preHead.next.next = tmp;
            }
            return preHead.next;
        }
        private void combine(ListNode n1, ListNode n2) {
            if (n1 == null || n2 == null) return;
            ListNode preHead = new ListNode(0);
            ListNode runner = preHead;
            while (n1 != null && n2 != null) {
                runner.next = n1;
                n1 = n1.next;
                runner.next.next = n2;
                n2 = n2.next;
                runner = runner.next.next;
            }
            runner.next = n1 == null ? n2 : n1;
        }
    }


 //   My java solution with a hashmap

    public void reorderList8(ListNode head) {

        HashMap<Integer, ListNode> nodeMap = new HashMap<Integer, ListNode>();
        int len = 0;
        ListNode p = head;

        while (p != null) {
            nodeMap.put(len++, p);
            p = p.next;
        }


        int i = 0;
        int j = len - 1;
        for (; i < j - 1; ++i, --j) {
            ListNode tmp = nodeMap.get(i).next;
            nodeMap.get(i).next = nodeMap.get(j);
            nodeMap.get(j).next = tmp;
            nodeMap.get(j - 1).next = null;

        }
    }
//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//
// 9Ch
public class Jiuzhang {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;

        merge(head, tail);
    }
    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    private void merge(ListNode head1, ListNode head2) {
        int index = 0;
        ListNode dummy = new ListNode(0);
        while (head1 != null && head2 != null) {
            if (index % 2 == 0) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            index ++;
        }
        if (head1 != null) {
            dummy.next = head1;
        } else {
            dummy.next = head2;
        }
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}

//-------------------------------------------------------------------------//

}
/*
给定一个单链表L: L0→L1→…→Ln-1→Ln,

重新排列后为：L0→Ln→L1→Ln-1→L2→Ln-2→…

必须在不改变节点值的情况下进行原地操作。

样例
给出链表 1->2->3->4->null，重新排列后为1->4->2->3->null。
 */

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */