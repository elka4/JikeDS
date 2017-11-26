package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;

//301. Remove Invalid Parentheses

public class _301_DFS_Remove_Invalid_Parentheses_H {
    public class Solution {
        public List<String> removeInvalidParentheses(String s) {
            List<String> res = new ArrayList<>();

            // sanity check
            if (s == null) return res;

            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            // initialize
            queue.add(s);
            visited.add(s);

            boolean found = false;

            while (!queue.isEmpty()) {
                s = queue.poll();

                if (isValid(s)) {
                    // found an answer, add to the result
                    res.add(s);
                    found = true;
                }

                if (found) continue;

                // generate all possible states
                for (int i = 0; i < s.length(); i++) {
                    // we only try to remove left or right paren
                    if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                    String t = s.substring(0, i) + s.substring(i + 1);

                    if (!visited.contains(t)) {
                        // for each state, if it's not visited, add it to the queue
                        queue.add(t);
                        visited.add(t);
                    }
                }
            }

            return res;
        }

        // helper function checks if string s contains valid parantheses
        boolean isValid(String s) {
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') count++;
                if (c == ')' && count-- == 0) return false;
            }

            return count == 0;
        }
    }

    class Solution2{
        public List<String> removeInvalidParentheses(String s) {
            int rmL = 0, rmR = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    rmL++;
                } else if (s.charAt(i) == ')') {
                    if (rmL != 0) {
                        rmL--;
                    } else {
                        rmR++;
                    }
                }
            }
            Set<String> res = new HashSet<>();
            dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
            return new ArrayList<String>(res);
        }

        public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
            if (rmL < 0 || rmR < 0 || open < 0) {
                return;
            }
            if (i == s.length()) {
                if (rmL == 0 && rmR == 0 && open == 0) {
                    res.add(sb.toString());
                }
                return;
            }

            char c = s.charAt(i);
            int len = sb.length();

            if (c == '(') {
                dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
                dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

            } else if (c == ')') {
                dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
                dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

            } else {
                dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);
            }

            sb.setLength(len);
        }
    }


    /*
    Easy, Short, Concise and Fast Java DFS 3 ms solution
For a better view see here

Key Points:

Generate unique answer once and only once, do not rely on Set.
Do not need preprocess.
Runtime 3 ms.
Explanation:
We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
For this, we keep tracking the last removal position and only remove ‘)’ after that.

Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
The answer is: do the same from right to left.
However a cleverer idea is: reverse the string and reuse the code!
Here is the final implement in Java.
     */

    class Solution3{
        public List<String> removeInvalidParentheses(String s) {
            List<String> ans = new ArrayList<>();
            remove(s, ans, 0, 0, new char[]{'(', ')'});
            return ans;
        }

        public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
            for (int stack = 0, i = last_i; i < s.length(); ++i) {
                if (s.charAt(i) == par[0]) stack++;
                if (s.charAt(i) == par[1]) stack--;
                if (stack >= 0) continue;
                for (int j = last_j; j <= i; ++j)
                    if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                        remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
                return;
            }
            String reversed = new StringBuilder(s).reverse().toString();
            if (par[0] == '(') // finished left to right
                remove(reversed, ans, 0, 0, new char[]{')', '('});
            else // finished right to left
                ans.add(reversed);
        }
    }

//----------------------------------------------------------------------------




//----------------------------------------------------------------------------






}
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
 */
