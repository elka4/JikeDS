package ss;

import org.junit.Test;

import java.util.Stack;

public class Course12_2_020ValidParentheses {
	public boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		if (s.length() % 2 != 0) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '(' :
				case '{' :
				case '[' :
					stack.push(c);
					break;
				case ')' :
					if (stack.isEmpty() || stack.peek() != '(') {
						return false;
					} else {
						stack.pop();
					}
					break;
				case '}' :
					if (stack.isEmpty() || stack.peek() != '{') {
						return false;
					} else {
						stack.pop();
					}
					break;
				case ']' :
					if (stack.isEmpty() || stack.peek() != '[') {
						return false;
					} else {
						stack.pop();
					}
					break;
				default :
					break;
			}
		}
		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	@Test
	public void test() {
		String s1 = "{([]})";
		String s2 = "{([])}()[[";
		String s3 = "{()}[]";
		System.out.println(isValid(s1) + " " + isValid(s2) + " " + isValid(s3));
	}
}
