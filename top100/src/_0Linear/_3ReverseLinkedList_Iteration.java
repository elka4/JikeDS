package _0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node {
	int value;
	Node next;
	Node() {
		next = null;
	}
}

class Iteration {
	public Node reverse (Node head) {
		Node pre = null;
		Node current = head;
		Node next = null;
		while (current != null) {
			next = current.next;// store the current.next
			current.next = pre;//mid.next point to front
			pre = current;//front move one step forward, to mid
			current = next;//mid move one step forward, to end (saved 'next')
		}
		return pre;
	}
	public void display(Node head) {
		while(head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
}

public class _3ReverseLinkedList_Iteration {
	public static void main (String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("_3ReverseLinkedList_Iteration.txt"));
		int n = in.nextInt();
		while (n != -1) {
			Node head = null;
			Node pre = null;
			while (n-- != 0) {
				Node temp = new Node();
				temp.value = in.nextInt();
				
				if (head == null) {
					head = temp;
				} else {
					pre.next = temp;
				}
				pre = temp;
			}
			Iteration itr = new Iteration();
			itr.display(head);
			
			head = itr.reverse(head);
			itr.display(head);
			n = in.nextInt();
		}
		in.close();
	}
}
