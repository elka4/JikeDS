package top100.Enhance._1Linear_PreClass;

import java.util.*;

import java.util.Deque;

public class _1_Stack_2Medium_EvaluateReversePolishRotaion_1 {
	public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0) {
			return 0;
		}
		//Store all numbers to be calculate
		Deque<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < tokens.length; i++) {
			switch (tokens[i]) {
			case "+":
				stack.offerLast(stack.pollLast() + stack.pollLast());
				break;
			case "-":
				int a1 = stack.pollLast();
				int b1 = stack.pollLast();
				stack.offerLast(b1 - a1);
				break;
			case "*":
				stack.offerLast(stack.pollLast() * stack.pollLast());
				break;
			case "/":
				int a2 = stack.pollLast();
				int b2 = stack.pollLast();
				stack.offerLast(b2 / a2);
				break;
			default:
				stack.offerLast(Integer.parseInt(tokens[i]));
				break;
			}
		}
		return stack.pollLast();
	}
}
