package _01_Array.Heap;

import org.junit.Test;

import java.util.Comparator;
import java.util.*;
/*
LeetCode – Merge k Sorted Lists (Java)

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Analysis

The simplest solution is using PriorityQueue. The elements of the priority queue are ordered according to their natural ordering, or by a comparator provided at the construction time (in this case).


 */

// Merge k Sorted Lists
public class Merge_k_Sorted_Lists {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }


        StringBuilder sb = new StringBuilder();;
        @Override
        public String toString(){
/*            sb.append(val);
            while(next!= null){

//                str = next.val + "";
                sb.append(" -> " + next.val);
                if(next.next == null){
                    sb.append(" -> null");
                }
                next = next.next;
            }
            return sb.toString();*/
            if (next != null) {
                return val + "->" + next.toString();
            } else {
                return ((Integer) val).toString();
            }
        }
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists==null||lists.length==0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2){
                return l1.val - l2.val;
            }
        });

        ListNode head = new ListNode(0);
        ListNode p = head;

        for(ListNode list: lists){
            if(list!=null)
                queue.offer(list);
        }

        while(!queue.isEmpty()){
            ListNode n = queue.poll();
            p.next = n;
            p=p.next;

            if(n.next!=null)
                queue.offer(n.next);
        }

        return head.next;

    }
    //Time: log(k) * n.
    //k is number of list and n is number of total elements.


    @Test
    public void test0(){
        ListNode[] lists = new ListNode[3] ;
//        lists.add(new ListNode(2)); lists.get(0).next = new ListNode(4);
        lists[0] = new ListNode(2); lists[0].next = new ListNode(4);
//        lists.add(null);

//        lists.add(new ListNode(-1));
        lists[2] = new ListNode(-1);
        System.out.println(lists);

        System.out.println( mergeKLists1(lists));

    }
//------------------------------------------------------------------------------

    // 9Ch
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists2(List<ListNode> lists) {
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
    /*
    [
  2->4->null,
  null,
  -1->null
],
return -1->2->4->null.
     */

    @Test
    public void test02(){
        List<ListNode> lists = new ArrayList<>();
        lists.add(new ListNode(2)); lists.get(0).next = new ListNode(4);
        lists.add(null);
        lists.add(new ListNode(-1));
        System.out.println(lists);

        System.out.println( mergeKLists2(lists));

    }

    // version 2: Heap
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };

    public ListNode mergeKLists3(List<ListNode> lists) {
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
    @Test
    public void test03(){
        List<ListNode> lists = new ArrayList<>();
        lists.add(new ListNode(2)); lists.get(0).next = new ListNode(4);
        lists.add(null);
        lists.add(new ListNode(-1));
        System.out.println(lists);

        System.out.println( mergeKLists3(lists));

    }

    // Version 3: merge two by two
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists4(List<ListNode> lists) {
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


    @Test
    public void test04(){
        List<ListNode> lists = new ArrayList<>();
        lists.add(new ListNode(2)); lists.get(0).next = new ListNode(4);
        lists.add(null);
        lists.add(new ListNode(-1));
        System.out.println(lists);

        System.out.println( mergeKLists4(lists));

    }


//------------------------------------------------------------------------------

    //leetcode
    public ListNode mergeKLists5(List<ListNode> lists) {
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

    @Test
    public void test05(){
        List<ListNode> lists = new ArrayList<>();
        lists.add(new ListNode(2)); lists.get(0).next = new ListNode(4);
        lists.add(null);
        lists.add(new ListNode(-1));
        System.out.println(lists);

        System.out.println( mergeKLists5(lists));

    }


//------------------------------------------------------------------------------




//------------------------------------------------------------------------------





}
