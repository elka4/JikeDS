package jike;

import java.util.LinkedList;

public class Course13_3_TestLinkedStack {
	public static void main(String[] args) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		while (!stack.isEmpty()) {
			System.out.println(stack.peek() + " " + stack.pop());
		}
	}
}
