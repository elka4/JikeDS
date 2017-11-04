package _03_List.Sort;

import lib.ListNode;


//  148. Sort List

//  https://leetcode.com/problems/sort-list/description/
//  http://www.lintcode.com/zh-cn/problem/sort-list/
public class _148_List_Sort_List_M {
//    Java merge sort solution
    public class Solution1 {

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            // step 1. cut the list to two halves
            ListNode prev = null, slow = head, fast = head;

            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }

            prev.next = null;

            // step 2. sort each half
            ListNode l1 = sortList(head);
            ListNode l2 = sortList(slow);

            // step 3. merge l1 and l2
            return merge(l1, l2);
        }

        ListNode merge(ListNode l1, ListNode l2) {
            ListNode l = new ListNode(0), p = l;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }

            if (l1 != null)
                p.next = l1;

            if (l2 != null)
                p.next = l2;

            return l.next;
        }

    }



//    I have a pretty good MergeSort method. Can anyone speed up the run time or reduce the memory usage?

    public class Solution2 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode f = head.next.next;
            ListNode p = head;
            while (f != null && f.next != null) {
                p = p.next;
                f =  f.next.next;
            }
            ListNode h2 = sortList(p.next);
            p.next = null;
            return merge(sortList(head), h2);
        }
        public ListNode merge(ListNode h1, ListNode h2) {
            ListNode hn = new ListNode(Integer.MIN_VALUE);
            ListNode c = hn;
            while (h1 != null && h2 != null) {
                if (h1.val < h2.val) {
                    c.next = h1;
                    h1 = h1.next;
                }
                else {
                    c.next = h2;
                    h2 = h2.next;
                }
                c = c.next;
            }
            if (h1 != null)
                c.next = h1;
            if (h2 != null)
                c.next = h2;
            return hn.next;
        }
    }


/*    Basically, it seems like merge sort problem(Really easy understand)
    Each time divided the given list into two sub list. Merge sub list after bottom case return.
    Here is the code:*/

    public class Solution3 {

        //merge two sorted list, return result head
        public ListNode merge(ListNode h1, ListNode h2){
            if(h1 == null){
                return h2;
            }
            if(h2 == null){
                return h1;
            }

            if(h1.val < h2.val){
                h1.next = merge(h1.next, h2);
                return h1;
            }
            else{
                h2.next = merge(h1, h2.next);
                return h2;
            }

        }

        public ListNode sortList(ListNode head) {
            //bottom case
            if(head == null){
                return head;
            }
            if(head.next == null){
                return head;
            }

            //p1 move 1 step every time, p2 move 2 step every time, pre record node before p1
            ListNode p1 = head;
            ListNode p2 = head;
            ListNode pre = head;

            while(p2 != null && p2.next != null){
                pre = p1;
                p1 = p1.next;
                p2 = p2.next.next;
            }
            //change pre next to null, make two sub list(head to pre, p1 to p2)
            pre.next = null;

            //handle those two sub list
            ListNode h1 = sortList(head);
            ListNode h2 = sortList(p1);

            return merge(h1, h2);

        }

    }

    //Java solution with strict O(1) auxiliary space complexity
    //  https://discuss.leetcode.com/topic/4860/java-solution-with-strict-o-1-auxiliary-space-complexity/2
    public class Solution4 {
        private class MergeHelper {
            public ListNode newHead;
            public ListNode newTail;
        }
        public ListNode sortList(ListNode head) {
            if ( head == null || head.next == null) {
                return head;
            }

            ListNode dummyHeadOne = new ListNode(0);
            ListNode dummyHeadTwo = new ListNode(0);
            ListNode dummySortedHead = new ListNode(0);
            ListNode dummySortedLast = dummySortedHead;
            ListNode unvisitedNode = head;
            MergeHelper mergeRst = new MergeHelper();

            int listLength = 0;
            int level = 0;
            while ( unvisitedNode != null && unvisitedNode.next != null ) {
                unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
                unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
                merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
                dummySortedLast.next = mergeRst.newHead;
                dummySortedLast = mergeRst.newTail;
                listLength += 2;
            }
            if (unvisitedNode != null) {
                dummySortedLast.next = unvisitedNode;
                listLength ++;
            }
            level ++;

            while ( listLength > 1 << level) {
                dummySortedLast = dummySortedHead;
                unvisitedNode = dummySortedHead.next;
                while (unvisitedNode != null) {
                    unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
                    unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
                    merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
                    dummySortedLast.next = mergeRst.newHead;
                    dummySortedLast = mergeRst.newTail;
                }
                level ++;
            }

            return dummySortedHead.next;
        }

        /* merge listOne and listTwo.
        Save the sorted list head into rst.newHead
        Save the last node of the sorted list into rst.newTail
        */
        private void merge (ListNode listOne, ListNode listTwo, MergeHelper rst) {
            ListNode dummyHead = new ListNode (0);
            ListNode lastNode = dummyHead;
            while (listOne != null && listTwo != null) {
                if ( listOne.val < listTwo.val ) {
                    lastNode.next = listOne;
                    listOne = listOne.next;
                } else {
                    lastNode.next = listTwo;
                    listTwo = listTwo.next;
                }
                lastNode = lastNode.next;
            }

            while (listOne != null) {
                lastNode.next = listOne;
                listOne = listOne.next;
                lastNode = lastNode.next;
            }
            while ( listTwo != null ) {
                lastNode.next = listTwo;
                listTwo = listTwo.next;
                lastNode = lastNode.next;
            }
            rst.newHead = dummyHead.next;
            rst.newTail = lastNode;
        }

        /*
         add at max #"count" nodes into "head" from "source"
         return the new position of source after adding.
        */
        private ListNode addNode ( ListNode head, ListNode source, int count ) {
            while (count > 0 && source != null) {
                head.next = source;
                head = head.next;
                source = source.next;
                count --;
            }
            head.next = null;
            return source;
        }
    }

//    Java quicksort 4ms

    public class Solution6 {
        public ListNode sortList(ListNode head) {
            if (head == null) return null;

            ListNode pivot = head;
            head = head.next;
            pivot.next = null;
            if (head == null) return pivot;
            ListNode small = new ListNode(0);
            ListNode large = new ListNode(0);
            ListNode p = pivot;
            ListNode s = small;
            ListNode l = large;
            while (head!=null) {
                if (head.val < pivot.val) {
                    s.next = head;
                    s = s.next;
                } else if (head.val == pivot.val) {
                    p.next = head;
                    p = p.next;
                } else {
                    l.next = head;
                    l = l.next;
                }
                head = head.next;
            }
            l.next = null;
            s.next = null;
            p.next = null;
            ListNode ss = sortList(small.next);
            if (ss == null) {
                ss = pivot;
            } else {
                ListNode sss = ss;
                while (sss.next!=null) {
                    sss = sss.next;
                }
                sss.next = pivot;
            }
            p.next = sortList(large.next);
            return ss;
        }
    }

//        Simple java merge sort, commented.
    class Solution7{

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            //Use two runners to break list into two halfs.
            ListNode fast = head, slow = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode second = prev.next;
            prev.next = null;
            //sort each half
            ListNode first = sortList(head);
            second = sortList(second);
            //merge them.
            head = mergeList(first, second);
            return head;
        }

        private ListNode mergeList(ListNode first, ListNode second) {
            ListNode dummy = new ListNode(0), merged = dummy;
            while (first != null && second != null) {
                if (first.val <= second.val) {
                    merged.next = first;
                    merged = merged.next;
                    first = first.next;
                } else {
                    merged.next = second;
                    merged = merged.next;
                    second = second.next;
                }
            }
            if (first != null || second != null)
                merged.next = (first != null)? first :second;

            return dummy.next;
        }
    }

//    Java solution using merge sort
    class Solution8{

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode slow = head, fast = head, pre = head;
            while(fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            pre.next = null;
            return merge(sortList(head), sortList(slow));
        }

        public ListNode merge(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2 == null) return l1;
            if(l1.val <= l2.val) {
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                l2.next = merge(l1, l2.next);
                return l2;
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    // version 1: Merge Sort
    public class Jiuzhang1 {
        private ListNode findMiddle(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private ListNode merge(ListNode head1, ListNode head2) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (head1 != null && head2 != null) {
                if (head1.val < head2.val) {
                    tail.next = head1;
                    head1 = head1.next;
                } else {
                    tail.next = head2;
                    head2 = head2.next;
                }
                tail = tail.next;
            }
            if (head1 != null) {
                tail.next = head1;
            } else {
                tail.next = head2;
            }

            return dummy.next;
        }

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode mid = findMiddle(head);

            ListNode right = sortList(mid.next);
            mid.next = null;
            ListNode left = sortList(head);

            return merge(left, right);
        }
    }

    // version 2: Quick Sort 1
    public class Jiuzhang2 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode mid = findMedian(head); // O(n)

            ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
            ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
            ListNode middleDummy = new ListNode(0), middleTail = middleDummy;
            while (head != null) {
                if (head.val < mid.val) {
                    leftTail.next = head;
                    leftTail = head;
                } else if (head.val > mid.val) {
                    rightTail.next = head;
                    rightTail = head;
                } else {
                    middleTail.next = head;
                    middleTail = head;
                }
                head = head.next;
            }

            leftTail.next = null;
            middleTail.next = null;
            rightTail.next = null;

            ListNode left = sortList(leftDummy.next);
            ListNode right = sortList(rightDummy.next);

            return concat(left, middleDummy.next, right);
        }

        private ListNode findMedian(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private ListNode concat(ListNode left, ListNode middle, ListNode right) {
            ListNode dummy = new ListNode(0), tail = dummy;

            tail.next = left; tail = getTail(tail);
            tail.next = middle; tail = getTail(tail);
            tail.next = right; tail = getTail(tail);
            return dummy.next;
        }

        private ListNode getTail(ListNode head) {
            if (head == null) {
                return null;
            }

            while (head.next != null) {
                head = head.next;
            }
            return head;
        }
    }

// version 3: Quick Sort 2
    /**
     * Definition for ListNode.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int val) {
     *         this.val = val;
     *         this.next = null;
     *     }
     * }
     */
    class Pair {
        public ListNode first, second;
        public Pair(ListNode first, ListNode second) {
            this.first = first;
            this.second = second;
        }
    }

    public class Jiuzhang3 {
        /**
         * @param head: The head of linked list.
         * @return: You should return the head of the sorted linked list,
        using constant space complexity.
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode mid = findMedian(head); // O(n)
            Pair pair = partition(head, mid.val); // O(n)

            ListNode left = sortList(pair.first);
            ListNode right = sortList(pair.second);

            getTail(left).next = right; // O(n)

            return left;
        }

        // 1->2->3 return 2
        // 1->2 return 1
        private ListNode findMedian(ListNode head) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        // < value in the left, > value in the right
        private Pair partition(ListNode head, int value) {
            ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
            ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
            ListNode equalDummy = new ListNode(0), equalTail = equalDummy;
            while (head != null) {
                if (head.val < value) {
                    leftTail.next = head;
                    leftTail = head;
                } else if (head.val > value) {
                    rightTail.next = head;
                    rightTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
                head = head.next;
            }

            leftTail.next = null;
            rightTail.next = null;
            equalTail.next = null;

            if (leftDummy.next == null && rightDummy.next == null) {
                ListNode mid = findMedian(equalDummy.next);
                leftDummy.next = equalDummy.next;
                rightDummy.next = mid.next;
                mid.next = null;
            } else if (leftDummy.next == null) {
                leftTail.next = equalDummy.next;
            } else {
                rightTail.next = equalDummy.next;
            }

            return new Pair(leftDummy.next, rightDummy.next);
        }

        private ListNode getTail(ListNode head) {
            if (head == null) {
                return null;
            }

            while (head.next != null) {
                head = head.next;
            }
            return head;
        }
    }



/////////////////////////////////////////////////////////////////////////////////////

}
/*
Sort a linked list in O(n log n) time using constant space complexity.


 */

/*
在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。

样例
给出 1->3->2->null，给它排序变成 1->2->3->null.


 */