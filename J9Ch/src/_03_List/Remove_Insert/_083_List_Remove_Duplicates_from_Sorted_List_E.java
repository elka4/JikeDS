package _03_List.Remove_Insert;
import lib.ListNode;


//  83. Remove Duplicates from Sorted List
//  https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
//  http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-list/
//  11:
//
public class _083_List_Remove_Duplicates_from_Sorted_List_E {

    //  https://leetcode.com/problems/remove-duplicates-from-sorted-list/solution/
//---------------------------------------------------------------------------
    //1
    //Straight-Forward Approach [Accepted]
    /*
    Algorithm

This is a simple problem that merely tests your ability to manipulate list node pointers. Because the input list is sorted, we can determine if a node is a duplicate by comparing its value to the node after it in the list. If it is a duplicate, we change the next pointer of the current node so that it skips the next node and points directly to the one after the next node.
     */

    /*
    Correctness

We can prove the correctness of this code by defining a loop invariant. A loop invariant is condition that is true before and after every iteration of the loop. In this case, a loop invariant that helps us prove correctness is this:

All nodes in the list up to the pointer current do not contain duplicate elements.
We can prove that this condition is indeed a loop invariant by induction. Before going into the loop, current points to the head of the list. Therefore, the part of the list up to current contains only the head. And so it can not contain any duplicate elements. Now suppose current is now pointing to some node in the list (but not the last element), and the part of the list up to current contains no duplicate elements. After another loop iteration, one of two things happen.

current.next was a duplicate of current. In this case, the duplicate node at current.next is deleted, and current stays pointing to the same node as before. Therefore, the condition still holds; there are still no duplicates up to current.

current.next was not a duplicate of current (and, because the list is sorted, current.next is also not a duplicate of any other element appearing before current). In this case, current moves forward one step to point to current.next. Therefore, the condition still holds; there are no duplicates up to current.

At the last iteration of the loop, current must point to the last element, because afterwards, current.next = null. Therefore, after the loop ends, all elements up to the last element do not contain duplicates.
     */
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
//---------------------------------------------------------------------------
    //2
    //    Easy understand Java solution
    public ListNode deleteDuplicates2(ListNode head) {

        ListNode temp = head;

        while(temp!=null&&temp.next!=null){

            if(temp.val == temp.next.val){

                temp.next = temp.next.next;
            }
            else
                temp = temp.next;
        }
        return head;
    }
//---------------------------------------------------------------------------
    //3
    /*    3 Line JAVA recursive solution
        This solution is inspired by renzid https://leetcode.com/discuss/33043/3-line-recursive-solution*/

    public ListNode deleteDuplicates3(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates3(head.next);
        return head.val == head.next.val ? head.next : head;
    }
//---------------------------------------------------------------------------
    //4
    public ListNode removeElements4(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements4(head.next, val);
        return head.val == val ? head.next : head;
    }
//---------------------------------------------------------------------------
    //5
    //    My pretty solution. Java.
    public class Solution5 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode list = head;

            while(list != null) {
                if (list.next == null) {
                    break;
                }
                if (list.val == list.next.val) {
                    list.next = list.next.next;
                } else {
                    list = list.next;
                }
            }

            return head;
        }
    }
//---------------------------------------------------------------------------
    //6
    //    recursive version
    public class Solution6 {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null)
                return null;
            if(head.next != null && head.next.val == head.val) {
                head.next = head.next.next;
                head = deleteDuplicates(head);
            }
            else
                head.next = deleteDuplicates(head.next);

            return head;
        }
    }
//---------------------------------------------------------------------------
    //7
    //    I did some garbage collection thing, not sure if this is good or not.
    public static ListNode deleteDuplicates7(ListNode head){
        if( head == null ) return null;
        ListNode prev = head, cur = head.next;
        while( cur != null ){
            if( cur.val == prev.val ){
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            } else {
                prev = prev.next;
                cur = cur.next;
            }
        }
        return head;
    }

//---------------------------------------------------------------------------
    //8
    // I agree, this solution is really pretty. One pointer.
    // No nonsense approach. You can remove some of the redundant code in your
    // solution like this. It becomes more efficient.
    public ListNode deleteDuplicates8(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode list = head;
        while(list.next != null)
        {
            if (list.val == list.next.val)
                list.next = list.next.next;
            else
                list = list.next;
        }

        return head;
    }
//---------------------------------------------------------------------------
    //9
    public ListNode deleteDuplicates9(ListNode head) {
        ListNode tmp = head;
        while (tmp != null
                &&tmp.next!=null) {
            if (tmp.val == tmp.next.val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }
//---------------------------------------------------------------------------
    //10
    //    Clean Java solution
    public ListNode deleteDuplicates10(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode dummy=head;
        while(dummy.next!=null){
            if(dummy.next.val==dummy.val){
                dummy.next=dummy.next.next;
            }else dummy=dummy.next;
        }
        return head;
    }

//-------------------------------------------------------------------------
    //11
    // 9Ch
    public class Jiuzhang {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode node = head;
            while (node.next != null) {
                if (node.val == node.next.val) {
                    node.next = node.next.next;
                } else {
                    node = node.next;
                }
            }
            return head;
        }
    }

//-------------------------------------------------------------------------
}
/*
//---------------------------------------------------------------------------
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
//---------------------------------------------------------------------------
 */

/*
//---------------------------------------------------------------------------
给定一个排序链表，删除所有重复的元素每个元素只留下一个。

样例
给出 1->1->2->null，返回 1->2->null

给出 1->1->2->3->3->null，返回 1->2->3->null
//---------------------------------------------------------------------------
 */