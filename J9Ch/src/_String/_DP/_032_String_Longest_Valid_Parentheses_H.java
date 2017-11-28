package _String._DP;
import java.util.*;
import org.junit.Test;

//  32. Longest Valid Parentheses
//  https://leetcode.com/problems/longest-valid-parentheses/description/
//  DP, String
//  _020_String_Valid_Parentheses_E - Stack
//  5:
public class _032_String_Longest_Valid_Parentheses_H {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/longest-valid-parentheses/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution1 {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push('(');
                } else if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            return stack.empty();
        }
        public int longestValidParentheses(String s) {
            int maxlen = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 2; j <= s.length(); j+=2) {
                    if (isValid(s.substring(i, j))) {
                        maxlen = Math.max(maxlen, j - i);
                    }
                }
            }
            return maxlen;
        }
    }

//------------------------------------------------------------------------------
    //2
    //Approach #2 Using Dynamic Programming [Accepted]
    public class Solution2 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }


//------------------------------------------------------------------------------
    //3
    //Approach #3 Using Stack [Accepted]
    public class Solution3 {
        public int longestValidParentheses(String s) {
            int maxans = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(i);
                    } else {
                        maxans = Math.max(maxans, i - stack.peek());
                    }
                }
            }
            return maxans;
        }
    }

//------------------------------------------------------------------------------
    //4
    //Approach #4 Without extra space [Accepted]
    public class Solution4{
        public int longestValidParentheses(String s) {
            int left = 0, right = 0, maxlength = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * right);
                } else if (right >= left) {
                    left = right = 0;
                }
            }
            left = right = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    maxlength = Math.max(maxlength, 2 * left);
                } else if (left >= right) {
                    left = right = 0;
                }
            }
            return maxlength;
        }
    }


//------------------------------------------------------------------------------
    //5
    //https://discuss.leetcode.com/topic/35776/two-java-solutions-with-explanation-stack-dp-short-easy-to-understand
    /*
    // Stack solution 10ms
The idea is simple, we only update the result (max) when we find a "pair".
If we find a pair. We throw this pair away and see how big the gap is between current and previous invalid.
EX: "( )( )"
stack: -1, 0,
when we get to index 1 ")", the peek is "(" so we pop it out and see what's before "(".
In this example it's -1. So the gap is "current_index" - (-1) = 2.

The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases.


     */
    public class Solution5 {
        public int longestValidParentheses(String s) {
            LinkedList<Integer> stack = new LinkedList<>();
            int result = 0;
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                    result = Math.max(result, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
            return result;
        }
    }

//------------------------------------------------------------------------------
    //6
/*
//DP solution 4ms
The idea is go through the string and use DP to store the longest valid parentheses at that point.
take example "()(())"
i : [0,1,2,3,4,5]
s : [( ,) ,( ,( ,) ,) ]
DP:[0,2,0,0,2,6]

1, We count all the ‘(‘.
2, If we find a ‘)’ and ‘(‘ counter is not 0, we have at least a valid result size of 2. “()"
3, Check the the one before (i - 1). If DP[i - 1] is not 0 means we have something like this “(())” . This will have DP “0024"
4, We might have something before "(())”. Take "()(())” example, Check the i = 1 because this might be a consecutive valid string.
 */
    public class Solution6 {
        public int longestValidParentheses(String s) {
            int[] dp = new int[s.length()];
            int result = 0;
            int leftCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    leftCount++;
                } else if (leftCount > 0){
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                    result = Math.max(result, dp[i]);
                    leftCount--;
                }
            }
            return result;
        }
    }
//------------------------------------------------------------------------------
    //7
    //My DP, O(n) solution without using stack
    //JAVA version. Store the result to the last element of array. Make it one line shorter
    /*
    My solution uses DP. The main idea is as follows: I construct a array longest[], for any longest[i], it stores the longest length of valid parentheses which is end at i.

And the DP idea is :

If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.

Else if s[i] is ')'

     If s[i-1] is '(', longest[i] = longest[i-2] + 2

     Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]

For example, input "()(())", at i = 5, longest array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.

     */
    public int longestValidParentheses(String s) {
        s = ")" + s;
        int[] longest = new int[s.length() + 1];

        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')' && s.charAt(i - longest[i - 1] - 1) == '(') {
                longest[i] = longest[i - 1] + 2 + longest[i - longest[i - 1] - 2];
                longest[s.length()] = Math.max(longest[i], longest[s.length()]);
            }
        }

        return longest[s.length()];
    }
//------------------------------------------------------------------------------
    //8
    //My DP, O(n) solution without using stack
//    My java solution with both stack and an array....It is very easy to understand. Maybe it is the only good part compared to the other space-saving solution.

    public class Solution8 {
        public int longestValidParentheses(String s){
            if (s==null||s.length()==0) return 0;

            Stack<Integer> stack= new Stack<Integer>();	//Store indices of '('
            int[] result=new int[s.length()];//Store the length of the current longest valid sequence.
            Arrays.fill(result, 0);

            int max=0;

            for (int i=0;i<s.length();i++)
                if (s.charAt(i)=='(')
                    stack.push(i);

                else if (s.charAt(i)==')'){
                    if (stack.isEmpty())
                        continue;
                    else if (stack.peek()>0)
                        result[i]=2+result[stack.pop()-1]+result[i-1];//May connect two valid sequences, or increase the length of current valid sequence.
                    else {
                        result[i]=2+result[i-1];//Handle the special case that the leftmost char is a '('
                        stack.pop();
                    }

                    max=Math.max(result[i],max);
                }
            return max;
        }
    }
//------------------------------------------------------------------------------
    //
    //9Ch
    public class Jiuzhang {
        public int longestValidParentheses(String s) {

            if (s == null) {
                return 0;
            }

            Stack<Integer> stack = new Stack<Integer>();
            int maxLen = 0;
            int accumulatedLen = 0;

            for(int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    if (stack.isEmpty()) {
                        accumulatedLen = 0;
                    } else {
                        int matchedPos = stack.pop();
                        int matchedLen = i - matchedPos + 1;

                        if (stack.isEmpty()) {
                            accumulatedLen += matchedLen;
                            matchedLen = accumulatedLen;
                        } else {
                            matchedLen = i - stack.peek();
                        }

                        maxLen = Math.max(maxLen, matchedLen);
                    }
                }
            }

            return maxLen;
        }
    }

//------------------------------------------------------------------------------
}
/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

Seen this question in a real interview before?   Yes  No
Related Topics
String Dynamic Programming
Similar Questions
Valid Parentheses
 */