package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _394_DFS_Decode_String_M {
    //Simple Java Solution using Stack
    public class Solution {
        public String decodeString(String s) {
            String res = "";
            Stack<Integer> countStack = new Stack<>();
            Stack<String> resStack = new Stack<>();
            int idx = 0;
            while (idx < s.length()) {
                if (Character.isDigit(s.charAt(idx))) {
                    int count = 0;
                    while (Character.isDigit(s.charAt(idx))) {
                        count = 10 * count + (s.charAt(idx) - '0');
                        idx++;
                    }
                    countStack.push(count);
                }
                else if (s.charAt(idx) == '[') {
                    resStack.push(res);
                    res = "";
                    idx++;
                }
                else if (s.charAt(idx) == ']') {
                    StringBuilder temp = new StringBuilder (resStack.pop());
                    int repeatTimes = countStack.pop();
                    for (int i = 0; i < repeatTimes; i++) {
                        temp.append(res);
                    }
                    res = temp.toString();
                    idx++;
                }
                else {
                    res += s.charAt(idx++);
                }
            }
            return res;
        }
    }

    //There will be many temporary String object there. So I used a Stack with StringBuilder.

    class StrItem {
        int num = 0;
        StringBuilder sb = new StringBuilder();

        StrItem(int n) {this.num = n;}
    }

    public class Solution2 {
        public String decodeString(String s) {
            int num = 0;
            Stack<StrItem> stack = new Stack<>();
            stack.push(new StrItem(1));
            for (char c: s.toCharArray())
                switch (c) {
                    case '0': case '1': case '2': case '3': case '4':
                    case '5': case '6': case '7': case '8': case '9':
                        num = num * 10 + c - '0';
                        break;
                    case '[':
                        stack.push(new StrItem(num));
                        num = 0;
                        break;
                    case ']':
                        String curStr = stack.peek().sb.toString();
                        int n = stack.pop().num;
                        for (int i = 0; i < n; i++)
                            stack.peek().sb.append(curStr);
                        break;
                    default:
                        stack.peek().sb.append(c);
                }
            return stack.pop().sb.toString();
        }
    }


//    Java short and easy-understanding solution using stack
    public class Solution3 {
        public String decodeString(String s) {
            Stack<Integer> count = new Stack<>();
            Stack<String> result = new Stack<>();
            int i = 0;
            result.push("");
            while (i < s.length()) {
                char ch = s.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    int start = i;
                    while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                    count.push(Integer.parseInt(s.substring(start, i + 1)));
                } else if (ch == '[') {
                    result.push("");
                } else if (ch == ']') {
                    String str = result.pop();
                    StringBuilder sb = new StringBuilder();
                    int times = count.pop();
                    for (int j = 0; j < times; j += 1) {
                        sb.append(str);
                    }
                    result.push(result.pop() + sb.toString());
                } else {
                    result.push(result.pop() + ch);
                }
                i += 1;
            }
            return result.pop();
        }
    }

//-------------------------------------------------------------------------///

// 9Ch



//-------------------------------------------------------------------------///






}
/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

/*

 */