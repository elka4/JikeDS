package _03_List.Math;

import lib.ListNode;


//  369. Plus One Linked List

//  https://leetcode.com/problems/plus-one-linked-list/description/
//
public class _369_List_Plus_One_Linked_List_M {
//    Iterative Two-Pointers with dummy node Java O(n) time, O(1) space

    public class Solution1 {
        public ListNode plusOne(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode i = dummy;
            ListNode j = dummy;

            while (j.next != null) {
                j = j.next;
                if (j.val != 9) {
                    i = j;
                }
            }

            if (j.val != 9) {
                j.val++;
            } else {
                i.val++;
                i = i.next;
                while (i != null) {
                    i.val = 0;
                    i = i.next;
                }
            }

            if (dummy.val == 0) {
                return dummy.next;
            }

            return dummy;
        }
    }


//    Nice solution. Simplified a little bit.

    public class Solution2 {
        public ListNode plusOne(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode lastNotNine = dummy, node = head;

            while (node != null) {
                if (node.val != 9) {
                    lastNotNine = node;
                }
                node = node.next;
            }
            lastNotNine.val++;
            node = lastNotNine.next;
            while (node != null) {
                node.val = 0;
                node = node.next;
            }
            return dummy.val == 1 ? dummy : dummy.next;
        }
    }


//    Very nice solution. Especially the use of dummy head.
//    Note the invariant which help we think and code correctly. Thanks for sharing!

    public ListNode plusOne3(ListNode head) {
        ListNode dmy = new ListNode(0), lastNot9 = dmy;
        dmy.next = head;
        for (ListNode n = head; n != null; n = n.next) {
            if (n.val != 9) lastNot9 = n; /* invariant: [lastNot9.next, tail] are all 9*/
        }
        lastNot9.val++;
        for (ListNode n = lastNot9.next; n != null; n = n.next) {
            n.val = 0;
        }
        return dmy.val == 1 ? dmy : head;
    }



/*    For example: 8->7->9->9
    Add dummy: 0->8->7->9->9
    The lastNotNine is 7.
            7 + 1 = 8
            9 change to 0.
    We got 0->8->8->0->0
            return dummy.next

    For example: 9->9->9
    Add dummy: 0->9->9->9
    The lastNotNine is 0.
            0 + 1 = 1
            9 change to 0.
    We got 1->0->0->0
            return dummy*/

    public class Solution4 {
        public ListNode plusOne(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode lastNotNine = dummy;
            ListNode node = head;
            while(node != null){
                if(node.val != 9){
                    lastNotNine = node;
                }
                node = node.next;
            }

            lastNotNine.val = lastNotNine.val + 1;
            lastNotNine = lastNotNine.next;
            while(lastNotNine != null){
                lastNotNine.val = 0;
                lastNotNine = lastNotNine.next;
            }
            return dummy.val == 1 ? dummy : dummy.next;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////

//    Two-Pointers Java Solution: O(n) time, O(1) space

    public class Solution5 {
        public ListNode plusOne(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode i = dummy;
            ListNode j = dummy;

            while (j.next != null) {
                j = j.next;
                if (j.val != 9) {
                    i = j;
                }
            }
            // i = index of last non-9 digit

            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }

            if (dummy.val == 0) return dummy.next;
            return dummy;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////

}
/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4

 */

/*

 */