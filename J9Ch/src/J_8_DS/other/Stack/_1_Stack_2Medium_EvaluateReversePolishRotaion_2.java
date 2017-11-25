package J_8_DS.other.Stack;

import java.util.Deque;
import java.util.LinkedList;


public class _1_Stack_2Medium_EvaluateReversePolishRotaion_2 {
	public int evalInFix(char[] tokens) {
		//Corner Case Checked
		Deque<Integer> valStack = new LinkedList<Integer>();//Stack to store numbers
		Deque<Character> opStack = new LinkedList<Character>();//Stack to store operators
		for (int i = 0; i < tokens.length; i++) {
			//Case 1:'('
			if (tokens[i] == '(') {
				opStack.offerLast(tokens[i]);
			}
			//Case 2; ')'
			else if (tokens[i] == ')') {
				 while(opStack.peekLast() != '(') {
					 valStack.offerLast(cal(opStack.pollLast(), valStack.peekLast(), valStack.pollLast()));
				 }
				//Poll '(';
				opStack.pollLast();
			}
			//Case 3: Operators other than parentheses
			else if (tokens[i] == '+' || tokens[i] == '-'
					|| tokens[i] == '*' || tokens[i] == '/' ){
				while  (!opStack.isEmpty() &&  isLowerThan(tokens[i], opStack.peekLast())) {
					valStack.offerLast(cal(opStack.peekLast(),valStack.pollLast(),valStack.pollLast()));
				}
				opStack.offerLast(tokens[i]); //offer current operator

			} else {//Case 4: number
	//			valStack.offerLast(Integer.parseInt(tokens[i]));
	//这行出错
				
			}
		}
		//Calculate all numbers rest in stack
		while (!opStack.isEmpty()) {
			valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
		}
		return valStack.pollLast();
	}

	private Integer cal(Character pollLast, Integer peekLast, Integer pollLast2) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isLowerThan(char cur, char toPeek) {
		return (toPeek == '*' || toPeek == '/') && (cur == '+' || cur == '-');
	}


//------------------------------------------------------------------------------




//------------------------------------------------------------------------------





//------------------------------------------------------------------------------




//------------------------------------------------------------------------------





	
}
