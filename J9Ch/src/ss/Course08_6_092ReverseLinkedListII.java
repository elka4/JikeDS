package ss;

import org.junit.Test;


/**
 * 反转链表的指定部分
 */
public class Course08_6_092ReverseLinkedListII {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null) {
			return head;
		} else if (m == n) {
			return head;
		} else {
			ListNode newHead = new ListNode(0);
			newHead.next = head;
			ListNode first = newHead;
			int k = 1;
			while (k < m) {
				first = first.next;
				k++;
			}
			ListNode pre = first.next;
			ListNode p = pre.next;
			ListNode next = null;
			final ListNode top = pre;
			while (k < n) {
				next = p.next;
				p.next = pre;
				pre = p;
				p = next;
				k++;
			}
			top.next = p;
			first.next = pre;
			return newHead.next;
		}
	}

	@Test
	public void test() {
		int[] array = {1, 2, 3, 4, 5, 6, 7};
		ListNode head = ListNode.arrayToList(array);
		head = reverseBetween(head, 3, 6);
		ListNode.printList(head);
		head = ListNode.arrayToList(array);
		head = reverseBetween(head, 1, 3);
		ListNode.printList(head);
	}
}
