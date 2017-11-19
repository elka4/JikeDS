package _03_List._List;
import java.util.*;
import org.junit.Test;import lib.*;


//  21. Merge Two Sorted Lists

//  https://leetcode.com/problems/merge-two-sorted-lists/description/
//  http://www.lintcode.com/zh-cn/problem/merge-two-sorted-lists/
public class _021_List_Merge_Two_Sorted_Lists_E {
//    My recursive way to solve this problem(JAVA, easy understanding)
//    Hello every one, here is my code, simple but works well:

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
//-------------------------------------------------------------------------//
//    Java, 1 ms, 4 lines codes, using recursion
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

//-------------------------------------------------------------------------//
/*
Java solution for reference

40
    C chase1991
    Reputation:  540
    Similar to array, the difference is if any of two listnode is not null after first loop, we only need to add it as previous node's next and no need to add them one by one.
*/

    public class Solution3 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode result = new ListNode(0);
            ListNode prev = result;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            }
            if (l1 != null) {
                prev.next = l1;
            }
            if (l2 != null) {
                prev.next = l2;
            }
            return result.next;
        }
    }

//-------------------------------------------------------------------------//

    public ListNode mergeTwoLists4(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode tmp,ans;
        if(l1.val <= l2.val){
            tmp = l1;
            l1 = l1.next;
        }else{
            tmp = l2;
            l2 = l2.next;
        }
        ans = tmp;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tmp.next = l1;
                tmp = tmp.next;
                l1 = l1.next;
            }else{
                tmp.next = l2;
                tmp = tmp.next;
                l2 = l2.next;
            }
        }
        if(l2 == null) tmp.next = l1;
        else tmp.next = l2;
        return ans;
    }
//-------------------------------------------------------------------------//
//jiuzhang
public class Jiuzhang {
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
}


//-------------------------------------------------------------------------//

}
/*
将两个排序链表合并为一个新的排序链表

样例
给出 1->3->8->11->15->null，2->null， 返回 1->2->3->8->11->15->null。
 */

/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.


 */