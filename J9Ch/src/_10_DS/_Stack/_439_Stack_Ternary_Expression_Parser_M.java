package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _439_Stack_Ternary_Expression_Parser_M {

/*    Very easy 1 pass Stack Solution in JAVA (NO STRING CONCAT)
    Iterate the expression from tail, whenever encounter a character before '?', calculate the right value and push back to stack.

            P.S. this code is guaranteed only if "the given expression is valid" base on the requirement.*/

    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) return "";
        Deque<Character> stack = new LinkedList<>();

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '?') {

                stack.pop(); //pop '?'
                char first = stack.pop();
                stack.pop(); //pop ':'
                char second = stack.pop();

                if (c == 'T') stack.push(first);
                else stack.push(second);
            } else {
                stack.push(c);
            }
        }

        return String.valueOf(stack.peek());
    }



//5ms JAVA DFS Solution
    public class Solution2 {
        public String parseTernary(String expression) {
            if(expression == null || expression.length() == 0){
                return expression;
            }
            char[] exp = expression.toCharArray();

            return DFS(exp, 0, exp.length - 1) + "";

        }
        public char DFS(char[] c, int start, int end){
            if(start == end){
                return c[start];
            }
            int count = 0, i =start;
            for(; i <= end; i++){
                if(c[i] == '?'){
                    count ++;
                }else if (c[i] == ':'){
                    count --;
                    if(count == 0){
                        break;
                    }
                }
            }
            return c[start] == 'T'? DFS(c, start + 2, i - 1) : DFS(c, i+1,end);
        }
    }

//    Java (It costs 7-lines):

    public class Solution3 {
        public String parseTernary(String expression) {
            while (expression.length() != 1) {
                int i = expression.lastIndexOf("?");    // get the last shown '?'
                char tmp;
                if (expression.charAt(i-1) == 'T') { tmp = expression.charAt(i+1); }
                else { tmp = expression.charAt(i+3); }
                expression = expression.substring(0, i-1) + tmp + expression.substring(i+4);
            }
            return expression;
        }
    }



}
/*
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
 */