package jike;

import java.util.Stack;

public class Course13_3_TestSqStack {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		while (!stack.isEmpty()) {
			System.out.println(stack.peek() + " " + stack.pop());
		}
	}
}
