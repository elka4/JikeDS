package top100.HighFreq._0Linear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Recursion {
	Node reverse (Node head){ 
		//Ending process
		if (head == null || head.next == null) return head;
		/*Node newTail = head.next;

		//Divide&Conquer
		Node newHead = reverse(head.next);
		
		//Combination
		newTail.next = head;
		head.next = null;*/
		
		Node newHead = reverse(head.next);

		head.next.next = head;
		head.next = null;
		
		return newHead;
	}
	public void display(Node head) {
		while(head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}
}

public class _3ReverseLinkedList_Recursion {
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
			Recursion rec = new Recursion();
			rec.display(head);
			
			Node newHead = rec.reverse(head);
			rec.display(newHead);
		}
		in.close();
	}
}
