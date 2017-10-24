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

//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */
