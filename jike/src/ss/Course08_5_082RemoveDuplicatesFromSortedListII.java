package ss;

import org.junit.Test;


/**
 * 删除排序链表的所有重复节点
 */
public class Course08_5_082RemoveDuplicatesFromSortedListII {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		} else {
			ListNode newHead = new ListNode(0);
			newHead.next = head;
			ListNode pre = newHead;
			ListNode p = head;
			ListNode next = null;
			while (p != null && p.next != null) {
				next = p.next;
				if (p.val == next.val) {
					while (next != null && next.val == p.val) {
						next = next.next;
					}
					pre.next = next;
					p = next;
				} else {
					pre = p;
					p = p.next;
				}
			}
			return newHead.next;
		}
	}
	@Test
	public void test() {
		int[] array1 = {1, 2, 3, 3, 3, 4, 4, 5};
		ListNode head = ListNode.arrayToList(array1);
		head = deleteDuplicates(head);
		ListNode.printList(head);
		int[] array2 = {1, 1, 1, 2, 3};
		head = ListNode.arrayToList(array2);
		head = deleteDuplicates(head);
		ListNode.printList(head);
	}
}
