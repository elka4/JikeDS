package _TwoPointer.TwoPointer_S;
import lib.*;

//  21. Merge Two Sorted Lists
//  https://leetcode.com/problems/merge-two-sorted-lists/description/
//  http://www.lintcode.com/zh-cn/problem/merge-two-sorted-lists/
public class _12_Merge_Two_Sorted_Lists {
    //My recursive way to solve this problem(JAVA, easy understanding)
    public class Solution1 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null){
                return l2;
            }
            if(l2 == null){
                return l1;
            }

            ListNode mergeHead;
            if(l1.val < l2.val){
                mergeHead = l1;
                mergeHead.next = mergeTwoLists(l1.next, l2);
            }
            else{
                mergeHead = l2;
                mergeHead.next = mergeTwoLists(l1, l2.next);
            }
            return mergeHead;
        }
    }

    public class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2 == null) return l1;

            if(l1.val < l2.val){
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            else{
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    //JAVA Easy to understand solution
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode prev = ans;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                prev.next = l2;
                l2 = l2.next;
            } else{
                prev.next = l1;
                l1 = l1.next;
            }
            prev = prev.next;
        }
        if(l2 == null){
            prev.next = l1;
        } else {
            prev.next = l2;
        }
        return ans.next;
    }

    //1ms java clean code
    public class Solution4 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1!=null && l2==null) return l1;
            if(l2!=null && l1==null) return l2;
            ListNode head=new ListNode(0);
            ListNode node=head;
            while(l1!=null && l2!=null){
                if(l1.val>=l2.val) {
                    node.next=l2;
                    l2=l2.next;
                }
                else{
                    node.next=l1;
                    l1=l1.next;
                }
                node=node.next;
            }
            node.next = (l1 != null) ? l1 : l2;// Append the remaining elements in the longer list
            return head.next;
        /*head如果开始赋值null，则返回最后都是空，
        为了返回变化了的链表需要返回head.next,所以需要改变的是node.next
        ListNode head=null;
        ListNode node=head;
        while(l1!=null && l2!=null){
            if(l1.val>=l2.val) {
                node=l2;
                l2=l2.next;
            }
            else{
                node=l1;
                l1=l1.next;
            }
            node=node.next;
        }
        node = (l1 != null) ? l1 : l2;// Append the remaining elements in the longer list
        return head;
        */
        }
    }

    //Non Recursive, No Extra Space (Java)
    public class Solution5 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            ListNode t1 = l1;
            ListNode t2 = l2;

            //whichever list is starting with least, make it t1;
            if (t1.val > t2.val) {
                ListNode temp = t1;
                t1 = t2;
                t2 = temp;
            }
            l1 = t1; //this is what you return eventually

            while (t1.next != null && t2 != null) {
                if (t1.val <= t2.val && t1.next.val <= t2.val) {
                    t1 = t1.next; // proceed if both are less.
                } else if (t1.val <= t2.val) {
                    // if t2.val comes in b/w, connect t2,
                    // and make t1.next as t2.
                    ListNode temp = t2;
                    t2 = t1.next;
                    t1.next = temp;
                }
            }
            //If the first list reaches end, just connect to second list.
            if (t1.next == null) {
                t1.next = t2;
            }
            return l1;
        }
    }

//-------------------------------------------------------------------------////
    //jiuzhang
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        
        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }
        
        return dummy.next;
    }

//-------------------------------------------------------------------------////
}
/*
合并两个排序链表

 描述
 笔记
 数据
 评测
将两个排序链表合并为一个新的排序链表

您在真实的面试中是否遇到过这个题？ Yes
样例
给出 1->3->8->11->15->null，2->null， 返回 1->2->3->8->11->15->null。
 */