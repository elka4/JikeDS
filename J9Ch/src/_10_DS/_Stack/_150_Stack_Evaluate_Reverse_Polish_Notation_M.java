package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _150_Stack_Evaluate_Reverse_Polish_Notation_M {

/*    Hi everyone.
    The Reverse Polish Notation is a stack of operations, thus, I decided to use java.util.Stack to solve this problem. As you can see, I add every token as an integer in the stack, unless it's an operation. In that case, I pop two elements from the stack and then save the result back to it. After all operations are done through, the remaining element in the stack will be the result.
    Any comments or improvements are welcome.

    Cheers.*/


    public class Solution {
        public int evalRPN(String[] tokens) {
            int a,b;
            Stack<Integer> S = new Stack<Integer>();
            for (String s : tokens) {
                if(s.equals("+")) {
                    S.add(S.pop()+S.pop());
                }
                else if(s.equals("/")) {
                    b = S.pop();
                    a = S.pop();
                    S.add(a / b);
                }
                else if(s.equals("*")) {
                    S.add(S.pop() * S.pop());
                }
                else if(s.equals("-")) {
                    b = S.pop();
                    a = S.pop();
                    S.add(a - b);
                }
                else {
                    S.add(Integer.parseInt(s));
                }
            }
            return S.pop();
        }
    }

//    Accepted clean Java solution
    public int evalRPN(String[] a) {
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;

                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;

                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;

                case "/":
                    int n1 = stack.pop(), n2 = stack.pop();
                    stack.push(n2 / n1);
                    break;

                default:
                    stack.push(Integer.parseInt(a[i]));
            }
        }

        return stack.pop();
    }

//-------------------------------------------------------------------------///
    // 9Ch

    public class Jiuzhang {
        public int evalRPN(String[] tokens) {
            Stack<Integer> s = new Stack<Integer>();
            String operators = "+-*/";
            for(String token : tokens){
                if(!operators.contains(token)){
                    s.push(Integer.valueOf(token));
                    continue;
                }

                int a = s.pop();
                int b = s.pop();
                if(token.equals("+")) {
                    s.push(b + a);
                } else if(token.equals("-")) {
                    s.push(b - a);
                } else if(token.equals("*")) {
                    s.push(b * a);
                } else {
                    s.push(b / a);
                }
            }
            return s.pop();
        }
    }
}
/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */