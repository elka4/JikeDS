package HF.HF5_Math_Graphic_Bit_2Calc;
import lib.*;
import org.junit.Test;

//  2. Add Two Numbers
//  https://leetcode.com/problems/add-two-numbers/description/
//  3:
public class _3AddTwoNumbers {
//------------------------------------------------------------------------------
    //https://leetcode.com/problems/add-two-numbers/solution/
//------------------------------------------------------------------------------
    //1
    class Solution1{
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode p = l1, q = l2, curr = dummyHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }
    }
//------------------------------------------------------------------------------
    //2
    class Solution2{
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if(l1 == null && l2 == null) {
                return null;
            }

            ListNode head = new ListNode(0);
            ListNode point = head;
            int carry = 0;
            while(l1 != null && l2!=null){
                int sum = carry + l1.val + l2.val;
                point.next = new ListNode(sum % 10);
                carry = sum / 10;
                l1 = l1.next;
                l2 = l2.next;
                point = point.next;
            }

            while(l1 != null) {
                int sum =  carry + l1.val;
                point.next = new ListNode(sum % 10);
                carry = sum /10;
                l1 = l1.next;
                point = point.next;
            }

            while(l2 != null) {
                int sum =  carry + l2.val;
                point.next = new ListNode(sum % 10);
                carry = sum /10;
                l2 = l2.next;
                point = point.next;
            }

            if (carry != 0) {
                point.next = new ListNode(carry);
            }
            return head.next;
        }
    }


    @Test
    public void test01(){
        //给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
        ListNode l1 = ListNode.create(new int[]{3,1,5});
        l1.print();
        ListNode l2 = ListNode.create(new int[]{5,9,2});
        l2.print();
        new Solution2().addTwoNumbers(l1, l2).print();
    }

//------------------------------------------------------------------------------
    //3
    // version: 高频题班
    public ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        int carry = 0;
        for (ListNode i = l1, j = l2; i != null || j != null; ) {
            int sum = carry;
            sum += (i != null) ? i.val : 0;
            sum += (j != null) ? j.val : 0;

            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            carry = sum / 10;
            i = (i == null) ? i : i.next;
            j = (j == null) ? j : j.next;
        }

        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return dummy.next;
    }

    @Test
    public void test02(){
        //给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null
        ListNode l1 = ListNode.create(new int[]{3,1,5});
        l1.print();

        ListNode l2 = ListNode.create(new int[]{5,9,2});
        l2.print();
        addLists(l1, l2).print();
    }

//------------------------------------------------------------------------------
}

/*
Add Two Numbers

 Description
 Notes
 Testcase
 Judge
You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 7->1->6 + 5->9->2. That is, 617 + 295.

Return 2->1->9. That is 912.

Given 3->1->5 and 5->9->2, return 8->0->8.


 */
/*
你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。写出一个函数将两个整数相加，用链表形式返回和。

样例
给出两个链表 3->1->5->null 和 5->9->2->null，返回 8->0->8->null


 */