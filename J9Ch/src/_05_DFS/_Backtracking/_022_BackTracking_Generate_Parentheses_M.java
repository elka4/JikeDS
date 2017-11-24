package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;

//  22. Generate Parentheses

public class _022_BackTracking_Generate_Parentheses_M {
    /*
    My method is DP. First consider how to get the result f(n) from previous result f(0)...f(n-1).
Actually, the result f(n) will be put an extra () pair to f(n-1). Let the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.

Let us consider an example to get clear view:

f(0): ""

f(1): "("f(0)")"

f(2): "("f(0)")"f(1), "("f(1)")"

f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"

So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"

Below is my code:
     */
    public class Solution
    {
        public List<String> generateParenthesis(int n)
        {
            List<List<String>> lists = new ArrayList<>();
            lists.add(Collections.singletonList(""));

            for (int i = 1; i <= n; ++i)
            {
                final List<String> list = new ArrayList<>();

                for (int j = 0; j < i; ++j)
                {
                    for (final String first : lists.get(j))
                    {
                        for (final String second : lists.get(i - 1 - j))
                        {
                            list.add("(" + first + ")" + second);
                        }
                    }
                }

                lists.add(list);
            }

            return lists.get(lists.size() - 1);
        }
    }


    //Easy to understand Java backtracking solution
    class Solution2{
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<String>();
            backtrack(list, "", 0, 0, n);
            return list;
        }

        public void backtrack(List<String> list, String str, int open, int close, int max){

            if(str.length() == max*2){
                list.add(str);
                return;
            }

            if(open < max)
                backtrack(list, str+"(", open+1, close, max);
            if(close < open)
                backtrack(list, str+")", open, close+1, max);
        }
    }
//-------------------------------------------------------------------------//
    // 9Ch
    public class Juzhang {
        public ArrayList<String> generateParenthesis(int n) {
            ArrayList<String> result = new ArrayList<String>();
            if (n <= 0) {
                return result;
            }
            helper(result, "", n, n);
            return result;
        }

        public void helper(ArrayList<String> result,
                           String paren, // current paren
                           int left,     // how many left paren we need to add
                           int right) {  // how many right paren we need to add
            if (left == 0 && right == 0) {
                result.add(paren);
                return;
            }

            if (left > 0) {
                helper(result, paren + "(", left - 1, right);
            }

            if (right > 0 && left < right) {
                helper(result, paren + ")", left, right - 1);
            }
        }
    }

//-------------------------------------------------------------------------//
    class SolutionZhu {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();

            if(n <= 0){
                return result;
            }
            dfs("", n, n, result);
            return result;

        }
        void dfs(String paren, int left, int right, List<String> result){
            if (left > right) {
                return;
            }
            if (left == 0 && right == 0) {
                result.add(paren);
            }
            if (left > 0) {
                dfs(paren + "(", left - 1, right, result);
            }
            if(right > 0 ){
                dfs(paren + ")", left, right - 1, result);
            }
        }
    }
//-------------------------------------------------------------------------//
}
/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


 */