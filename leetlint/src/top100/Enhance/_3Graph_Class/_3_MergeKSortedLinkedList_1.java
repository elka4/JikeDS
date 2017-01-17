package top100.Enhance._3Graph_Class;
import java.util.*;


public class _3_MergeKSortedLinkedList_1 {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		if (lists.length == 1) {
			return lists[0];
		}
	
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>
				(lists.length, new MyComparator());
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy;
		//1.Put all first nodes into minHeap
		for (ListNode n : lists) {
			if (n != null) {
				minHeap.add(n);
			}
		}
		//2.find min elem in minHeap
		while (!minHeap.isEmpty()) {
			ListNode next = minHeap.poll();
			if (next.next != null) {
				minHeap.add(next.next);
			}
			prev.next = next;
			prev = prev.next;
		}
		return dummy.next;
	}
}

class MyComparator implements Comparator<ListNode> {
	@Override
	public int compare(ListNode o1, ListNode o2) {
		if (o1.val == o2.val) {
			return 0;
		} else {
			return o1.val < o2.val ? -1 : 1;
		}
	}
}