package _03_List.Ch_02_Linked_Lists.Q2_01_Remove_Dups;

import lib.LinkedListNode;
import org.junit.Test;

import java.util.HashSet;

public class QuestionA {
	public static void deleteDups(LinkedListNode n) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while (n != null) {
			if (set.contains(n.data)) {
				previous.next = n.next;
			} else {
				set.add(n.data);
				previous = n;
			}
			n = n.next;
		}
	}

////////////////////////////////////////////////////////////////////

    public static void deleteDups2(LinkedListNode head) {
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

////////////////////////////////////////////////////////////////////

    public static void deleteDups3(LinkedListNode head) {
        if (head == null) return;
        LinkedListNode previous = head;
        LinkedListNode current = previous.next;
        while (current != null) {
            // Look backwards for dups, and remove any that you see.
            LinkedListNode runner = head;
            while (runner != current) {
                if (runner.data == current.data) {
                    LinkedListNode tmp = current.next;
                    previous.next = tmp;
                    current = tmp;
					/* We know we can't have more than one dup preceding
					 * our element since it would have been removed
					 * earlier. */
                    break;
                }
                runner = runner.next;
            }

			/* If runner == current, then we didn't find any duplicate
			 * elements in the previous for loop.  We then need to
			 * increment current.
			 * If runner != current, then we must have hit the �break�
			 * condition, in which case we found a dup and current has
			 * already been incremented.*/
            if (runner == current) {
                previous = current;
                current = current.next;
            }
        }
    }

////////////////////////////////////////////////////////////////////


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
