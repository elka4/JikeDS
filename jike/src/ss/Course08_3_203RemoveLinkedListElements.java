package ss;

import org.junit.Test;


/**
 * 查询节点并删除
 */
public class Course08_3_203RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return head;
		} else {
			ListNode newHead = new ListNode(0);
			newHead.next = head;
			ListNode pre = newHead;
			ListNode p = head;
			while (p != null) {
				if (p.val == val) {
					pre.next = p.next;
					p = p.next;
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
		int[] array1 = {1, 6, 6, 4, 6, 3, 5};
		ListNode head = ListNode.arrayToList(array1);
		head = removeElements(head, 6);
		ListNode.printList(head);
		int[] array2 = {1, 2, 3, 4, 1};
		head = ListNode.arrayToList(array2);
		head = removeElements(head, 1);
		ListNode.printList(head);
	}
}
