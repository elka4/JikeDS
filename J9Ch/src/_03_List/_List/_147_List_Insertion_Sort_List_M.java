package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  147. Insertion Sort List

//  https://leetcode.com/problems/insertion-sort-list/description/
//  http://www.lintcode.com/zh-cn/problem/insertion-sort-list/
public class _147_List_Insertion_Sort_List_M {
//    An easy and clear way to sort ( O(1) space )
    public ListNode insertionSortList1(ListNode head) {
        if( head == null ){
            return head;
        }

        ListNode helper = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = helper; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        while( cur != null ){
            next = cur.next;
            //find the right place to insert
            while( pre.next != null && pre.next.val < cur.val ){
                pre = pre.next;
            }
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }

        return helper.next;
    }


//    Accepted Solution using JAVA
    public class Solution2 {
        public ListNode insertionSortList(ListNode head) {
            ListNode helper=new ListNode(0);
            ListNode pre=helper;
            ListNode current=head;
            while(current!=null) {
                pre=helper;
                while(pre.next!=null&&pre.next.val<current.val) {
                    pre=pre.next;
                }
                ListNode next=current.next;
                current.next=pre.next;
                pre.next=current;
                current=next;
            }
            return helper.next;
        }
    }


//    Clean Java solution using a fake head
    public ListNode insertionSortList3(ListNode head) {
        ListNode curr = head, next = null;
        // l is a fake head
        ListNode l = new ListNode(0);

        while (curr != null) {
            next = curr.next;

            ListNode p = l;
            while (p.next != null && p.next.val < curr.val)
                p = p.next;

            // insert curr between p and p.next
            curr.next = p.next;
            p.next = curr;
            curr = next;
        }

        return l.next;
    }

    /*7ms Java solution with explanation

    The only real modification here is to take advantage of the ability to add to both the front and end of a linked list in constant time. A typical insertion sort would have to go through the entire array to find the new location to insert the element. If the element should be placed first in the array then we have to shift everything over. Thankfully, with a linked list we don't need to do this. The slight modification of keeping a pointer to the last node as well as the first dramatically increased the runtime of the algorithm. That being said, the speedup still has a lot to do with the ordering if the items in the array.*/

    public ListNode insertionSortList4(ListNode head) {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode sortedHead = head, sortedTail = head;
        head = head.next;
        sortedHead.next = null;

        while (head != null)
        {
            ListNode temp = head;
            head = head.next;
            temp.next = null;

            // new val is less than the head, just insert in the front
            if (temp.val <= sortedHead.val)
            {
                temp.next = sortedHead;
                sortedTail = sortedHead.next == null ? sortedHead : sortedTail;
                sortedHead = temp;
            }
            // new val is greater than the tail, just insert at the back
            else if (temp.val >= sortedTail.val)
            {
                sortedTail.next = temp;
                sortedTail = sortedTail.next;
            }
            // new val is somewhere in the middle, we will have to find its proper
            // location.
            else
            {
                ListNode current = sortedHead;
                while (current.next != null && current.next.val < temp.val)
                {
                    current = current.next;
                }

                temp.next = current.next;
                current.next = temp;
            }
        }

        return sortedHead;
    }


//    Java 34ms solution, clear logic with separate insert method
//    using insertion sort logic. Insert each new node into a sorted linked list with dummy head.

    public class Solution5 {
        public ListNode insertionSortList(ListNode head) {
            ListNode sortedHeadDummy = new ListNode(0);
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                insert(sortedHeadDummy, curr);
                curr = next;
            }
            return sortedHeadDummy.next;
        }

        private void insert(ListNode dummyHead, ListNode target) {
            // left to right scan to insert the target node
            ListNode curr = dummyHead;
            while (curr.next != null && curr.next.val < target.val) {
                curr = curr.next;
            }
            target.next = curr.next;
            curr.next = target;
        }
    }

//    Simple and clean java code

    public ListNode insertionSortList6(ListNode head) {
        ListNode cur = head;
        ListNode dummy = new ListNode(0), p;
        while (cur != null) {
            //locate the insertion position.
            p = dummy;
            while (p.next != null &&  cur.val > p.next.val) {
                p = p.next;
            }
            //insert between p and p.next.
            ListNode pNext = p.next;
            p.next = cur;
            ListNode next = cur.next;
            cur.next = pNext;
            cur = next;
        }
        return dummy.next;
    }
//-------------------------------------------------------------------------//

//    Maybe the best JAVA solution with code comments
    public class Solution7 {

        public ListNode insertionSortList(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            //record node before insertNode
            ListNode preNode = head;
            //rocord node need to be inserted
            ListNode insertNode = head.next;

            while(insertNode != null){
                //store next insert node
                ListNode nextInsert = insertNode.next;
                //insert before head
                if(insertNode.val <= head.val){
                    preNode.next = insertNode.next;
                    insertNode.next = head;
                    head = insertNode;
                }
                else if(insertNode.val >= preNode.val){    //insert after tail
                    preNode = preNode.next;
                }
                else{                                      //insert between head and tail
                    ListNode compareNode = head;
                    //start from the node after head, find insert position
                    while(compareNode.next.val < insertNode.val)   compareNode = compareNode.next;
                    //insert
                    preNode.next = insertNode.next;
                    insertNode.next = compareNode.next;
                    compareNode.next = insertNode;
                }
                //get next insert node
                insertNode = nextInsert;
            }
            return head;
        }
    }
/*    Hope it will helpful. The thinking is very straightforward:

    Insert before head.
    Insert after tail.(no need change the list).
    Insert between head and tail.*/


/*    similar solution:

    before head
    after rear
    between head and rear*/
    public ListNode insertionSortList8(ListNode head)
    {
        if (head == null)
            return null;

        ListNode currentCheck = head.next;
        ListNode currentHead = head;
        ListNode currentRear = head;
        ListNode currentInside = head;

        while (currentCheck != null)
        {
            currentRear.next = currentCheck.next;

            if (currentCheck.val < currentHead.val)
            {
                currentCheck.next = currentHead;
                currentHead = currentCheck;
                currentInside=currentHead;
            }
            else if (currentCheck.val >= currentRear.val)
            {
                currentCheck.next = currentRear.next;
                currentRear.next = currentCheck;
                currentRear = currentCheck;
            }
            else
            {
                while (true)
                {
                    if (currentCheck.val >= currentInside.val && currentCheck.val < currentInside.next.val)
                    {
                        currentCheck.next = currentInside.next;
                        currentInside.next = currentCheck;
                        break;
                    }
                    else
                    {
                        currentInside = currentInside.next;
                    }
                }
                currentInside=currentHead;
            }

            currentCheck=currentRear.next;
        }

        return currentHead;
    }


//    Too clumsy. How about mine below:

    public ListNode insertionSortList9(ListNode head) {
        if(head==null) return head;
        ListNode current = head.next, prev = head;
        while(current!=null){
            if(prev.val<=current.val) //no need to insert current node in between sortedWalker and sortedRunner
                prev = current;
            else{//need to insert current node in between sortedWalker and sortedRunner
                ListNode sortedRunner = head, sortedWalker = null;
                while(sortedRunner.val<current.val){
                    sortedWalker = sortedRunner; sortedRunner = sortedRunner.next;
                }
                prev.next = current.next;//let prev connect to current.next
                if(sortedWalker==null) head = current;//In case of current node is inserted to be the new head
                else sortedWalker.next = current; //Then insert current node in between sortedWalker and sortedRunner
                current.next = sortedRunner;
            }
            current = prev.next;
        }
        return head;
    }
//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//


//-------------------------------------------------------------------------//
//jiuzhang
public class Jiuzhang {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        // 这个dummy的作用是，把head开头的链表一个个的插入到dummy开头的链表里
        // 所以这里不需要dummy.next = head;

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                node = node.next;
            }
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }

        return dummy.next;
    }
}

//-------------------------------------------------------------------------//

}
/*
用插入排序对链表排序

样例
Given 1->3->2->0->null, return 0->1->2->3->null
 */

/*

 */