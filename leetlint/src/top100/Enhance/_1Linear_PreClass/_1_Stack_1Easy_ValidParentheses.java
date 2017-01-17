package top100.Enhance._1Linear_PreClass;

import java.util.Deque;
import java.util.LinkedList;


public class _1_Stack_1Easy_ValidParentheses {
	public boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		if ((s.length() & 1) == 1) {
			return false;
		}
		char[] str = s.toCharArray();
		Deque<Character> stack = new LinkedList<Character>();
		for (char ch : str) {
			//Case 1: left -->put into stack
			if (ch == '(' || ch == '[' || ch =='{') {
				stack.offerLast(ch);
			} else {//Case 2: rigth parenthese --> check stack.peek()
				if (stack.isEmpty()) {
					return false;
				}
				char left = stack.pollLast();
				if ((ch == ')' && left == '(') ||
					(ch == ']' && left  == '[') ||
					(ch == '}' && left == '{')
					) {
					continue;
				} else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	

}
