package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  92. Reverse Linked List II

//  https://leetcode.com/problems/reverse-linked-list-ii/description/
//  http://www.lintcode.com/zh-cn/problem/reverse-linked-list-ii/
public class _092_List_Reverse_Linked_List_II_M {
//    Simple Java solution with clear explanation

    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for(int i = 0; i<m-1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for(int i=0; i<n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }



//    Easy understanding java solution
    public class Solution2 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            //first part
            ListNode cur1 = dummy;
            ListNode pre1 = null;
            for(int i=0;i<m;i++){
                pre1 = cur1;
                cur1 = cur1.next;
            }

            //reverse
            ListNode cur2 = cur1;
            ListNode pre2 = pre1;
            ListNode q2;
            for(int i=m;i<=n;i++){
                q2 = cur2.next;
                cur2.next = pre2;
                pre2 = cur2;
                cur2 = q2;
            }

            //connect
            pre1.next = pre2;
            cur1.next = cur2;

            return dummy.next;
        }
    }

//    Short Java Solution for Reverse Linked List II
    public class Solution3 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode curr = new ListNode(0);
            curr.next = head;
            int k = n - m;
            int j = m;
            while(j > 1){
                curr = curr.next;
                j--;
            }
            ListNode reve = curr.next;
            while(k > 0){
                ListNode temp = reve.next;
                reve.next = reve.next.next;
                temp.next = curr.next;
                curr.next = temp;
                k--;
            }
            if(m == 1){
                head = curr.next;
            }
            return head;
        }
    }

//    Java solution with less pointers and detailed comments
    public ListNode reverseBetween4(ListNode head, int m, int n) {
        if(head == null) return head;
        ListNode fake_head = new ListNode(0);
        fake_head.next = head;

        //move to the start point
        ListNode pre = fake_head;
        for(int i = 0; i < m - 1; i ++){
            pre = pre.next;
        }

        //do the reverse
        ListNode cur = pre.next;
        ListNode new_head = null;
        for(int i = 0; i <= n - m; i ++){
            ListNode next = cur.next;
            cur.next = new_head;
            new_head = cur;
            cur = next;
        }

        //reconnect
        pre.next.next = cur;
        pre.next = new_head;

        return fake_head.next;
    }


//    In fact we don't need fake head

    public ListNode reverseBetween5(ListNode head, int m, int n) {
        ListNode current = head, pre = null, breakNode = null;
        int i = 0;
        while(++i<m){ // go to breakNode, which is node m-1
            breakNode = current;
            pre = current;
            current = current.next;
        }
        while(i++<=n){// Reverse nodes from m to n
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        if(breakNode==null) { // incase of m = 1, we connect head with node n+1 and return node n;
            head.next = current; return pre;
        }
        breakNode.next.next = current;  // breakNode.next is the node m, we connect it with node n+1;
        breakNode.next = pre;  // connect breakNode with node n;
        return head;
    }

/*    Share my Java code

    The basic idea is to build a sub-list when we hit Node m by adding the subsequent nodes to the head of the sub-list one by one until we hit Node n. Then connect the nodes before Node m, the sub-list and the nodes following Node n.*/

    public ListNode reverseBetween6(ListNode head, int m, int n) {
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode sublisthead = new ListNode(0);
        ListNode sublisttail = new ListNode(0);
        int count = 1;
        ListNode pre_cur = dummyhead, cur = head;
        while(count <=n){
            ListNode temp = cur.next;
            if (count < m)
                pre_cur = cur;
            else if (count == m){
                sublisttail = cur;
                sublisthead.next = cur;
            }else if (count > m){
                cur.next = sublisthead.next;
                sublisthead.next = cur;
            }
            cur = temp;
            ++count;
        }
        pre_cur.next = sublisthead.next;
        sublisttail.next = cur;
        return dummyhead.next;
    }

//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------
// 9Ch
public class Jiuzhang {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }

        ListNode premNode = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode, postnNode = mNode.next;
        for (int i = m; i < n; i++) {
            if (postnNode == null) {
                return null;
            }
            ListNode temp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = temp;
        }
        mNode.next = postnNode;
        premNode.next = nNode;

        return dummy.next;
    }
}

//--------------------------------------------------------------------------------

}
/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */

/*
翻转链表中第m个节点到第n个节点的部分

 注意事项

m，n满足1 ≤ m ≤ n ≤ 链表长度

样例
给出链表1->2->3->4->5->null， m = 2 和n = 4，返回1->4->3->2->5->null


 */