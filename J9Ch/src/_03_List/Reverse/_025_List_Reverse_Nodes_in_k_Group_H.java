package _03_List.Reverse;

import lib.ListNode;


//  25. Reverse Nodes in k-Group

//  https://leetcode.com/problems/reverse-nodes-in-k-group/description/
//  http://www.lintcode.com/zh-cn/problem/reverse-nodes-in-k-group/
public class _025_List_Reverse_Nodes_in_k_Group_H {
//    Short but recursive Java code with comments
//    Despite the fact that the approach is recursive, the code is less than 20 lines. :)

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup1(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }


/*    Java O(n) elegant code solution
    why use while(true) loop? We don't know if there is k nodes left out in advance. So we advance tail for k steps to check for that. If there is no k nodes, the program exits from there.*/

    public class Solution2 {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k <= 1 || head == null || head.next == null)
                return head;
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            ListNode prev, start, then, tail;
            tail = prev = newHead;
            start = prev.next;
            while (true) {
                // check if there's k nodes left-out
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null)
                        return newHead.next;
                }
                // reverse k nodes
                for (int i = 0; i < k - 1; i++) {
                    then = start.next;
                    start.next = then.next;
                    then.next = prev.next;
                    prev.next = then;
                }
                tail = prev = start;
                start = prev.next;
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////
/*    Non-recursive Java solution and idea
    Reference:
    http://www.cnblogs.com/lichen782/p/leetcode_Reverse_Nodes_in_kGroup.html

    First, build a function reverse() to reverse the ListNode between begin and end. See the explanation below:*/

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
//    Then walk thru the linked list and apply reverse() iteratively. See the code below.
    class Solution3{
        public ListNode reverseKGroup3(ListNode head, int k) {
            ListNode begin;
            if (head==null || head.next ==null || k==1)
                return head;
            ListNode dummyhead = new ListNode(-1);
            dummyhead.next = head;
            begin = dummyhead;
            int i=0;
            while (head != null){
                i++;
                if (i%k == 0){
                    begin = reverse(begin, head.next);
                    head = begin.next;
                } else {
                    head = head.next;
                }
            }
            return dummyhead.next;

        }

        public ListNode reverse(ListNode begin, ListNode end){
            ListNode curr = begin.next;
            ListNode next, first;
            ListNode prev = begin;
            first = curr;
            while (curr!=end){
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            begin.next = prev;
            first.next = curr;
            return first;
        }
    }

/////////////////////////////////////////////////////////////////////////////////////
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution4 {
        public ListNode reverseKGroup(ListNode head, int k) {
            // Save the previous reversed pointer in prev and in wach iteration
            // use prev.next to set the previous ptr to the current reversed.
            ListNode tempNode = new ListNode(0);
            tempNode.next = head;
            ListNode tempHead = head;
            ListNode prev = tempNode;
            while(tempHead!=null){
                // Starting of each reversed list, it will become the last after reversing
                ListNode klast = tempHead;
                int num = k;
                // Jump k
                while(num>0 && tempHead!=null){
                    tempHead = tempHead.next;
                    num--;
                }
                // If cannot reverse
                if(num!=0) {
                    prev.next = klast;
                    break;
                }
                // start of each reversed group
                ListNode kstart = reverse(klast,k);

                // Use previous's next to point to curr reversed
                prev.next = kstart;
                // Set prev to current rev end.
                prev = klast;
            }
            return tempNode.next;

        }

        // Standard reverse code
        public ListNode reverse(ListNode head, int k){
            ListNode prev = null;
            while(head!=null && k>0){
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
                k--;
            }
            return prev;
        }

    }

/////////////////////////////////////////////////////////////////////////////////////
//Share my Java Solution with comments in line
public class Solution5 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null||head.next==null||k<2) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode tail = dummy, prev = dummy,temp;
        int count;
        while(true){
            count =k;
            while(count>0&&tail!=null){
                count--;
                tail=tail.next;
            }
            if (tail==null) break;//Has reached the end


            head=prev.next;//for next cycle
            // prev-->temp-->...--->....--->tail-->....
            // Delete @temp and insert to the next position of @tail
            // prev-->...-->...-->tail-->head-->...
            // Assign @temp to the next node of @prev
            // prev-->temp-->...-->tail-->...-->...
            // Keep doing until @tail is the next node of @prev
            while(prev.next!=tail){
                temp=prev.next;//Assign
                prev.next=temp.next;//Delete

                temp.next=tail.next;
                tail.next=temp;//Insert

            }

            tail=head;
            prev=head;

        }
        return dummy.next;

    }
}

    public ListNode reverseKGroup6(ListNode head, int k) {
        int n = 0;
        ListNode cur = head;
        while(cur != null){
            cur = cur.next;
            n++;
        }
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        ListNode s = dmy, e = dmy.next; //s: start, e: end
        for(int i = n; i >= k; i -= k){
            for(int j = 1; j < k; j++){ // reverse group
                ListNode next = e.next;
                e.next = next.next;
                next.next = s.next;
                s.next = next;
            }
            s = e;
            e = s.next;
        }
        return dmy.next;
    }
/////////////////////////////////////////////////////////////////////////////////////
//jiuzhang
    // version 1:
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public class Jiuzhang1 {
        /**
         * @param head a ListNode
         * @param k an integer
         * @return a ListNode
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            head = dummy;
            while (true) {
                head = reverseK(head, k);
                if (head == null) {
                    break;
                }
            }

            return dummy.next;
        }

        // head -> n1 -> n2 ... nk -> nk+1
        // =>
        // head -> nk -> nk-1 .. n1 -> nk+1
        // return n1
        public ListNode reverseK(ListNode head, int k) {
            ListNode nk = head;
            for (int i = 0; i < k; i++) {
                if (nk == null) {
                    return null;
                }
                nk = nk.next;
            }

            if (nk == null) {
                return null;
            }

            // reverse
            ListNode n1 = head.next;
            ListNode nkplus = nk.next;

            ListNode prev = null;
            ListNode curt = n1;
            while (curt != nkplus) {
                ListNode temp = curt.next;
                curt.next = prev;
                prev = curt;
                curt = temp;
            }

            // connect
            head.next = nk;
            n1.next = nkplus;
            return n1;
        }
    }

// version 2:
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public class Jiuzhang2 {
        /**
         * @param head a ListNode
         * @param k an integer
         * @return a ListNode
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            head = dummy;
            while (head.next != null) {
                head = reverseNextK(head, k);
            }

            return dummy.next;
        }

        // reverse head->n1->..->nk->next..
        // to head->nk->..->n1->next..
        // return n1
        private ListNode reverseNextK(ListNode head, int k) {
            // check there is enought nodes to reverse
            ListNode next = head; // next is not null
            for (int i = 0; i < k; i++) {
                if (next.next == null) {
                    return next;
                }
                next = next.next;
            }

            // reverse
            ListNode n1 = head.next;
            ListNode prev = head, curt = n1;
            for (int i = 0; i < k; i++) {
                ListNode temp = curt.next;
                curt.next = prev;
                prev = curt;
                curt = temp;
            }

            n1.next = curt;
            head.next = prev;
            return n1;
        }
    }

/////////////////////////////////////////////////////////////////////////////////////

}
/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 */

/*
给你一个链表以及一个k,将这个链表从头指针开始每k个翻转一下。
链表元素个数不是k的倍数，最后剩余的不用翻转。

样例
给出链表 1->2->3->4->5

k = 2, 返回 2->1->4->3->5

k = 3, 返回 3->2->1->4->5


 */