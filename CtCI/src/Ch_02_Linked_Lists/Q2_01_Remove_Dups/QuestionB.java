package Ch_02_Linked_Lists.Q2_01_Remove_Dups;

import CtCILibrary.LinkedListNode;
import org.junit.Test;

public class QuestionB {
	public static void deleteDups(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
			/* Remove all future nodes that have the same value */
			LinkedListNode runner = current;
			while (runner.next != null) { 
				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}	
	
	public static void main(String[] args) {
		//AssortedMethods.randomLinkedList(1000, 0, 2);
		LinkedListNode first = new LinkedListNode(0, null, null);
		LinkedListNode head = first;
		LinkedListNode second = first;
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(i % 2, null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		System.out.println(head.printForward());
		deleteDups(head);
		System.out.println(head.printForward());
	}

	@Test
	public  void test01(){
		LinkedListNode first = new LinkedListNode(0, null, null);
		LinkedListNode head = first;
		LinkedListNode second = first;

		second = new LinkedListNode(1, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		second = new LinkedListNode(1, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		second = new LinkedListNode(2, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		second = new LinkedListNode(2, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		System.out.println(head.printForward());
		deleteDups(head);
		System.out.println(head.printForward());

	}

	@Test
	public  void test02(){
		LinkedListNode first = new LinkedListNode(0, null, null);
		LinkedListNode head = first;
		LinkedListNode second = first;

		second = new LinkedListNode(2, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;


		second = new LinkedListNode(1, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;


		second = new LinkedListNode(1, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		second = new LinkedListNode(2, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		second = new LinkedListNode(1, null, null);
		first.setNext(second);
		second.setPrevious(first);
		first = second;

		System.out.println(head.printForward());
		deleteDups(head);
		System.out.println(head.printForward());

	}
}
