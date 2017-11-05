package _03_List.Merge;

import lib.ListNode;

import java.util.*;


//  23. Merge k Sorted Lists

//  https://leetcode.com/problems/merge-k-sorted-lists/description/
//
public class _023_List_Merge_k_Sorted_Lists_H {
//  https://leetcode.com/problems/merge-k-sorted-lists/solution/

/*    A java solution based on Priority Queue
    If someone understand how priority queue works, then it would be trivial to walk through the codes.

    My question: is that possible to solve this question under the same time complexity without implementing the priority queue?*/

    public class Solution1 {
        public ListNode mergeKLists(List<ListNode> lists) {
            if (lists==null||lists.size()==0) return null;

            PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
                @Override
                public int compare(ListNode o1,ListNode o2){
                    if (o1.val<o2.val)
                        return -1;
                    else if (o1.val==o2.val)
                        return 0;
                    else
                        return 1;
                }
            });

            ListNode dummy = new ListNode(0);
            ListNode tail=dummy;

            for (ListNode node:lists)
                if (node!=null)
                    queue.add(node);

            while (!queue.isEmpty()){
                tail.next=queue.poll();
                tail=tail.next;

                if (tail.next!=null)
                    queue.add(tail.next);
            }
            return dummy.next;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////
    //    My simple java Solution use recursion
    class Solution2{
        public ListNode mergeKLists(ListNode[] lists){
            return partion(lists,0,lists.length-1);
        }

        public ListNode partion(ListNode[] lists,int s,int e){
            if(s==e)  return lists[s];
            if(s<e){
                int q=(s+e)/2;
                ListNode l1=partion(lists,s,q);
                ListNode l2=partion(lists,q+1,e);
                return merge(l1,l2);
            }else
                return null;
        }

        //This function is from Merge Two Sorted Lists.
        public  ListNode merge(ListNode l1,ListNode l2){
            if(l1==null) return l2;
            if(l2==null) return l1;
            if(l1.val<l2.val){
                l1.next=merge(l1.next,l2);
                return l1;
            }else{
                l2.next=merge(l1,l2.next);
                return l2;
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////
    //Simple Java Merge Sort


public class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}
/////////////////////////////////////////////////////////////////////////////////////
    //My Accepted Java Solution Using PriorityQueue
    public class Solution4 {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode dummy = new ListNode(0), cur = dummy;
            if (lists == null || lists.length < 1) {
                return null;
            }
            PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>
                    (lists.length, new Comparator<ListNode>() {
                public int compare(ListNode l1, ListNode l2) {
                    return l1.val - l2.val;
                }
            });
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    minHeap.offer(lists[i]);
                }
            }
            while (!minHeap.isEmpty()) {
                ListNode temp = minHeap.poll();
                cur.next = temp;
                if (temp.next != null) {
                    minHeap.offer(temp.next);
                }
                cur = temp;
            }
            return dummy.next;
        }
    }

/////////////////////////////////////////////////////////////////////////////////////
//jiuzhang
    // version 1: Divide & Conquer
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
    public class Jiuzhang1 {
        /**
         * @param lists: a list of ListNode
         * @return: The head of one sorted list.
         */
        public ListNode mergeKLists(List<ListNode> lists) {
            if (lists.size() == 0) {
                return null;
            }
            return mergeHelper(lists, 0, lists.size() - 1);
        }

        private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
            if (start == end) {
                return lists.get(start);
            }

            int mid = start + (end - start) / 2;
            ListNode left = mergeHelper(lists, start, mid);
            ListNode right = mergeHelper(lists, mid + 1, end);
            return mergeTwoLists(left, right);
        }

        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    tail.next = list1;
                    tail = list1;
                    list1 = list1.next;
                } else {
                    tail.next = list2;
                    tail = list2;
                    list2 = list2.next;
                }
            }
            if (list1 != null) {
                tail.next = list1;
            } else {
                tail.next = list2;
            }

            return dummy.next;
        }
    }

    // version 2: Heap
    public class Jiuzhang2 {
        public ListNode mergeKLists(List<ListNode> lists) {
            if (lists == null || lists.size() == 0) {
                return null;
            }

            Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i) != null) {
                    heap.add(lists.get(i));
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (!heap.isEmpty()) {
                ListNode head = heap.poll();
                tail.next = head;
                tail = head;
                if (head.next != null) {
                    heap.add(head.next);
                }
            }
            return dummy.next;
        }
        private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
            public int compare(ListNode left, ListNode right) {

                return left.val - right.val;
            }
        };

    }

// Version 3: merge two by two
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
    public class Jiuzhang3 {
        /**
         * @param lists: a list of ListNode
         * @return: The head of one sorted list.
         */
        public ListNode mergeKLists(List<ListNode> lists) {
            if (lists == null || lists.size() == 0) {
                return null;
            }

            while (lists.size() > 1) {
                List<ListNode> new_lists = new ArrayList<ListNode>();
                for (int i = 0; i + 1 < lists.size(); i += 2) {
                    ListNode merged_list = merge(lists.get(i), lists.get(i+1));
                    new_lists.add(merged_list);
                }
                if (lists.size() % 2 == 1) {
                    new_lists.add(lists.get(lists.size() - 1));
                }
                lists = new_lists;
            }

            return lists.get(0);
        }

        private ListNode merge(ListNode a, ListNode b) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            while (a != null && b != null) {
                if (a.val < b.val) {
                    tail.next = a;
                    a = a.next;
                } else {
                    tail.next = b;
                    b = b.next;
                }
                tail = tail.next;
            }

            if (a != null) {
                tail.next = a;
            } else {
                tail.next = b;
            }

            return dummy.next;
        }
    }




/////////////////////////////////////////////////////////////////////////////////////

}
/*

 */

/*

 */