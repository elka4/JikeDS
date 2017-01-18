package jike;

import org.junit.Test;


/**
 *删除链表的倒数第N个节点
 */
public class Course08_2_19RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return head;
		} else {
			n++;
			ListNode pre = head;
			ListNode p2 = head;
			for (int i = 1; i <= n; i++) {
				if (p2 == null) {
					return head.next;
				}
				p2 = p2.next;
			}
			while (p2 != null) {
				p2 = p2.next;
				pre = pre.next;
			}
			pre.next = pre.next.next;
			return head;
		}
	}
	@Test
	public void test() {
		int[] array1 = {1, 2, 3, 4, 5, 6};
		ListNode head = ListNode.arrayToList(array1);
		head = removeNthFromEnd(head, 4);
		ListNode.printList(head);
		int[] array2 = {1, 2, 3, 4, 5};
		head = ListNode.arrayToList(array2);
		head = removeNthFromEnd(head, 2);
		ListNode.printList(head);
	}
}
