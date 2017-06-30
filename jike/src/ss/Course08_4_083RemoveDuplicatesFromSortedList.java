package ss;

import org.junit.Test;


/**
 * 删除排序链表的重复节点
 */
public class Course08_4_083RemoveDuplicatesFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		} else {
			ListNode pre = head;
			ListNode p = head.next;
			while (p != null) {
				if (pre.val == p.val) {
					while (p != null && pre.val == p.val) {
						p = p.next;
					}
					pre.next = p;
				} else {
					pre = p;
					p = p.next;
				}
			}
			return head;
		}
	}
	@Test
	public void test() {
		int[] array1 = {1, 1, 2, 2, 2, 3, 4};
		ListNode head = ListNode.arrayToList(array1);
		head = deleteDuplicates(head);
		ListNode.printList(head);
		int[] array2 = {1, 1, 2, 3, 3};
		head = ListNode.arrayToList(array2);
		head = deleteDuplicates(head);
		ListNode.printList(head);
	}
}
