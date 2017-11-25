package _TwoPointer.List;
import lib.ListNode;

//  234. Palindrome Linked List
//  https://leetcode.com/problems/palindrome-linked-list/discuss/
//  http://www.lintcode.com/zh-cn/problem/palindrome-linked-list/
//
public class _234_TwoPointer_Palindrome_Linked_List_E {
//------------------------------------------------------------------------------
    //1
    //Java, easy to understand

    /*
    This can be solved by reversing the 2nd half and compare the two halves. Let's start with an example [1, 1, 2, 1].

    In the beginning, set two pointers fast and slow starting at the head.

    1 -> 1 -> 2 -> 1 -> null
    sf
    (1) Move: fast pointer goes to the end, and slow goes to the middle.

    1 -> 1 -> 2 -> 1 -> null
              s          f
    (2) Reverse: the right half is reversed, and slow pointer becomes the 2nd head.

    1 -> 1    null <- 2 <- 1
    h                      s
    (3) Compare: run the two pointers head and slow together and compare.

    1 -> 1    null <- 2 <- 1
         h            s
     */
    class Solution1{
        public boolean isPalindrome(ListNode head) {
            ListNode fast = head, slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            if (fast != null) { // odd nodes: let right half smaller
                slow = slow.next;
            }
            slow = reverse(slow);
            fast = head;

            while (slow != null) {
                if (fast.val != slow.val) {
                    return false;
                }
                fast = fast.next;
                slow = slow.next;
            }
            return true;
        }

        public ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
    }

//------------------------------------------------------------------------------
    //2
    class Solution2{
    //    Solution with even String reversal.
        public boolean isPalindrome(ListNode head) {

            return head == null || recurse (head, head) != null;
        }

        private ListNode recurse (ListNode node, ListNode head) {
            if (node == null) return  head;
            ListNode res = recurse (node.next, head);
            if (res == null) return res;
            else if (res.val == node.val) return (res.next == null ? res : res.next);
            else return null;
        }
    }
    /*
    Nice solution. You can also add this if statement to return on palindromic even lists faster.
    while (slow != null) {
        if (fast.val != slow.val) {
            return false;
        }
        if (fast == slow) {  // To return on even lists faster.
            return true;
        }
        fast = fast.next;
        slow = slow.next;
    }
     */

//------------------------------------------------------------------------------
    //3

/*if (fast != null) { // odd nodes: let right half smaller
        slow = slow.next;
    }
    You don`t need to make right half smaller.
    For example:
            1 1 2 1 1
    s f

    after reversion, right half should be
1 1 2 null
    s

    but you didn't change the next pointer from 1 before 2. so for left half, it should be :
            1 1 2 ...
    h

    Then just use while loop to compare from the head and slow pointers. It will stop when slow points to null that irrelevant with node after 2*/

/*    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow);
        while (head != null && slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }*/

    public boolean isPalindrome(ListNode head) {
        ListNode tmp = head;
        int n = 0;
        while(tmp != null){
            tmp = tmp.next;
            n++;
        }
        int i = 0;
        int[] nums = new int[n];
        while(head != null){
            if(i < (n + 1) / 2) nums[i] = head.val;
            else if(nums[n - i - 1] != head.val) return false;
            i++;
            head = head.next;
        }
        return true;
    }
//------------------------------------------------------------------------------
    //4
//    @ruinan My solution is a little different than yours in the initialization of fast. Mine initializes fast to be head as well. This guarantees that the second half pointed by slow is less than or equal to the first half pointed by head. Therefore checking slow != null only will suffice. My reverse() method checks the null and one node conditions as well.

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) { return true; }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        // Compare the two halves.
        while (slow != null) {
            if (slow.val != head.val) { return false; }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    // Reverse a singly linked list headed at head ListNode.
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) { return head; }
        ListNode prev = null;
        while (head != null) {
            ListNode realNext = head.next;
            head.next = prev;
            prev = head;
            head = realNext;
        }
        return prev;
    }
//------------------------------------------------------------------------------
    //5
    // 9Ch
    // This code would destroy the original structure of the linked list.
    // If you do not want to destroy the structure, you can reserve the second part back.
    public class Jiuzhang {
        /**
         * @param head a ListNode
         * @return a boolean
         */
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }

            ListNode middle = findMiddle(head);
            middle.next = reverse(middle.next);

            ListNode p1 = head, p2 = middle.next;
            while (p1 != null && p2 != null && p1.val == p2.val) {
                p1 = p1.next;
                p2 = p2.next;
            }

            return p2 == null;
        }

        private ListNode findMiddle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;

            while (head != null) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }

            return prev;
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */

/*

 */