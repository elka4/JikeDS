package _String._String;
import java.util.*;
import org.junit.Test;

//  227. Basic Calculator II
//  https://leetcode.com/problems/basic-calculator-ii/description/
//
//  3:
//
public class _227_String_Basic_Calculator_II_M {
//------------------------------------------------------------------------------
    //1
    //Share my java solution
    public class Solution1 {
        public int calculate(String s) {
            int len;
            if(s==null || (len = s.length())==0) return 0;
            Stack<Integer> stack = new Stack<Integer>();
            int num = 0;
            char sign = '+';
            for(int i=0;i<len;i++){
                if(Character.isDigit(s.charAt(i))){
                    num = num*10+s.charAt(i)-'0';
                }
                if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                    if(sign=='-'){
                        stack.push(-num);
                    }
                    if(sign=='+'){
                        stack.push(num);
                    }
                    if(sign=='*'){
                        stack.push(stack.pop()*num);
                    }
                    if(sign=='/'){
                        stack.push(stack.pop()/num);
                    }
                    sign = s.charAt(i);
                    num = 0;
                }
            }

            int re = 0;
            for(int i:stack){
                re += i;
            }
            return re;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Java straight forward iteration Solution with comments, No Stack, O(N) & O(1)
    public int calculate(String s) {
        if (s == null) return 0;
        s = s.trim().replaceAll(" +", "");
        int length = s.length();

        int res = 0;
        long preVal = 0; // initial preVal is 0
        char sign = '+'; // initial sign is +
        int i = 0;
        while (i < length) {
            long curVal = 0;
            while (i < length && (int)s.charAt(i) <= 57 && (int)s.charAt(i) >= 48) { // int
                curVal = curVal*10 + (s.charAt(i) - '0');
                i++;
            }
            if (sign == '+') {
                res += preVal;  // update res
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;  // update res
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
            } else if (sign == '/') {
                preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
            }
            if (i < length) { // getting new sign
                sign = s.charAt(i);
                i++;
            }
        }
        res += preVal;
        return res;
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------



//------------------------------------------------------------------------------
    //3
    //9Ch
    class Jiuzhang {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            List<String> list = new ArrayList<>();
            Deque<String> deque = new ArrayDeque<>();

            int n = 0;
            for (char ch : s.toCharArray()) {
                if (ch == ' ') {
                    continue;
                }
                if (Character.isDigit(ch)) {
                    n = n * 10 + ch - '0';
                } else {
                    list.add("" + n);
                    n = 0;
                    list.add("" + ch);
                }
            }
            list.add("" + n);

            for (int i = 0; i < list.size(); i++) {
                String cur = list.get(i);
                if (cur.equals("*") || cur.equals("/")) {
                    int a = Integer.parseInt(deque.pop());
                    int b = Integer.parseInt(list.get(i+1));
                    i++;
                    int c = cur.equals("*") ? a * b : a / b;
                    deque.push("" + c);
                } else {
                    deque.push(cur);
                }
            }
            // System.out.println(deque.toString());

            while (deque.size() != 1) {
                int a = Integer.parseInt(deque.pollLast());
                String sign = deque.pollLast();
                int b = Integer.parseInt(deque.pollLast());
                int c = sign.equals("+") ? a + b : a - b;
                deque.offerLast("" + c);
            }

            return (int)Integer.parseInt(deque.peek());
        }
    }

//------------------------------------------------------------------------------
}
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Seen this question in a real interview before?   Yes  No
Companies
Airbnb
Related Topics
String
Similar Questions
Basic Calculator
Expression Add Operators
 */