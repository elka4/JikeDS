package _01_Array.Other_dbf_bfs;

import java.util.ArrayList;
import java.util.List;
/*
LeetCode – Generate Parentheses (Java)

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
 */
public class Generate_Parentheses {
    /*Java Solution 1 - DFS

    This solution is simple and clear. In the dfs() method, left stands for the remaining number of (, right stands for the remaining number of ).*/

    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }
    /*
    left and right represents the remaining number of ( and ) that need to be added.
    When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops.
    */
    public void dfs(ArrayList<String> result, String s, int left, int right){
        if(left > right)
            return;

        if(left==0&&right==0){
            result.add(s);
            return;
        }

        if(left>0){
            dfs(result, s+"(", left-1, right);
        }

        if(right>0){
            dfs(result, s+")", left, right-1);
        }
    }


    /*Java Solution 2

    This solution looks more complicated. ,You can use n=2 to walk though the code.*/

    public List<String> generateParenthesis2(int n) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Integer> diff = new ArrayList<Integer>();

        result.add("");
        diff.add(0);

        for (int i = 0; i < 2 * n; i++) {
            ArrayList<String> temp1 = new ArrayList<String>();
            ArrayList<Integer> temp2 = new ArrayList<Integer>();

            for (int j = 0; j < result.size(); j++) {
                String s = result.get(j);
                int k = diff.get(j);

                if (i < 2 * n - 1) {
                    temp1.add(s + "(");
                    temp2.add(k + 1);
                }

                if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
                    temp1.add(s + ")");
                    temp2.add(k - 1);
                }
            }

            result = new ArrayList<String>(temp1);
            diff = new ArrayList<Integer>(temp2);
        }

        return result;
    }


//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






}
