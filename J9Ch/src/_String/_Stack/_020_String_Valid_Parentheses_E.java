package _String._Stack;
import java.util.*;
import org.junit.Test;

//  20. Valid Parentheses
//  https://leetcode.com/problems/valid-parentheses/description/
//  http://www.lintcode.com/problem/valid-parentheses/
//
//  给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'， 判定是否是有效的括号序列。
//  String Stack
//  22 Generate Parentheses - String, Backtracking
//  32 Longest Valid Parentheses - String, DP
//  301 Remove Invalid Parentheses - DFS, BFS
//
//  5:  1, 真的是简洁优美啊
//
public class _020_String_Valid_Parentheses_E {
//------------------------------------------------------------------------------
    //1
    //Short java solution
    //逐个看每个char，如果是左括号就将相应的右括号压栈
    //如果是右括号就检查两点：1，stack是否为空。2，stack.pop()是否和当前char相同
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
//------------------------------------------------------------------------------
    //2
    //My easy to understand Java Solution with one stack
    public class Solution2 {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            // Iterate through string until empty
            for(int i = 0; i<s.length(); i++) {
                // Push any open parentheses onto stack
                if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                    stack.push(s.charAt(i));
                    // Check stack for corresponding closing parentheses, false if not valid
                else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                    stack.pop();
                else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                    stack.pop();
                else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                    stack.pop();
                else
                    return false;
            }
            // return true if no open parentheses left in stack
            return stack.empty();
        }
    }

//------------------------------------------------------------------------------
    //3
    //12 lines of Java
    public class Solution3 {
        public boolean isValid(String s) {
            Stack<Integer> p = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                int q = "(){}[]".indexOf(s.substring(i, i + 1));
                if(q % 2 == 1) {
                    if(p.isEmpty() || p.pop() != q - 1) return false;
                } else p.push(q);
            }
            return p.isEmpty();
        }
    }
//------------------------------------------------------------------------------
    //4
    //9CH
    public class Jiuzhang {
        public boolean isValidParentheses(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (Character c : s.toCharArray()) {
                if ("({[".contains(String.valueOf(c))) {
                    stack.push(c);
                } else {
                    if (!stack.isEmpty() && is_valid(stack.peek(), c)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

        private boolean is_valid(char c1, char c2) {
            return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
                    || (c1 == '[' && c2 == ']');
        }
    }
//------------------------------------------------------------------------------
    //5
    // version: 高频题班
    public class Jiuzhang2 {
        public boolean isValidParentheses(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                }
                if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                }
                if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
                if (c == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }


//------------------------------------------------------------------------------
}
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Seen this question in a real interview before?   Yes  No

Companies
Google Facebook Microsoft Amazon Bloomberg Twitter Airbnb Zenefits

Related Topics
String Stack

Similar Questions
22  Generate Parentheses - String, Backtracking
32  Longest Valid Parentheses - String, DP
301 Remove Invalid Parentheses - DFS, BFS
 */