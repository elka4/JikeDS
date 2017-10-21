package _03_List.Ch_02_Linked_Lists.Q2_04_Partition;

import lib.LinkedListNode;
import org.junit.Test;

public class QuestionC {

	public static LinkedListNode partition(LinkedListNode node, int x) {
		LinkedListNode head = node;
		LinkedListNode tail = node;
		
		/* Partition list */
		while (node != null) {
			LinkedListNode next = node.next;
			if (node.data < x) {
				/* Insert node at head. */
				node.next = head;
				head = node;
				//System.out.println(head.printForward());
			} else {
				/* Insert node at tail. */
				tail.next = node;
				tail = node;
				//System.out.println(tail.printForward());
			}	
			node = next;
		}
		tail.next = null;
		
		return head;
	}
	
	public static void main(String[] args) {
		int length = 20;
		LinkedListNode[] nodes = new LinkedListNode[length];
		for (int i = 0; i < length; i++) {
			nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
		}
		
		for (int i = 0; i < length; i++) {
			if (i < length - 1) {
				nodes[i].setNext(nodes[i + 1]);
			}
			if (i > 0) {
				nodes[i].setPrevious(nodes[i - 1]);
			}
		}
		
		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		
		LinkedListNode h = partition(head, 8);
		System.out.println(h.printForward());
	}

	@Test
	public void test01(){
		int[] vals = {3,5,8,5,10,2,1};
		LinkedListNode head = new LinkedListNode(vals[0], null, null);
		LinkedListNode current = head;
		for (int i = 1; i < vals.length; i++) {
			current = new LinkedListNode(vals[i], null, current);
		}
		System.out.println(head.printForward());

		/* Partition */
		LinkedListNode h = partition(head, 5);

		/* Print Result */
		System.out.println(h.printForward());
	}

}
