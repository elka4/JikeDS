package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _022_BackTracking_Generate_Parentheses_M {
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
}
