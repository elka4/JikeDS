package HF.HF4_Search.DFS;

import java.util.ArrayList;
import java.util.List;

//Add Operators
public class _4AddOperators {
    /**
     * @param num a string contains only digits 0-9
     * @param target an integer
     * @return return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        // Write your code here
        List<String> results = new ArrayList<String>();
        if (num == null || num.length() == 0) {
            return results;
        }
        helper(results, "", num, target, 0, 0, 0);
        return results;
    }
    public void helper(List<String> results, String path, String num,
                       int target, int pos, long eval, long multed){

        if (pos == num.length()){
            if(target == eval)
                results.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(results, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(results, path + "+" + cur, num, target,
                        i + 1, eval + cur , cur);

                helper(results, path + "-" + cur, num, target,
                        i + 1, eval -cur, -cur);

                helper(results, path + "*" + cur, num, target,
                        i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }

///////////////////////////////////////////////////////////

    // version: 高频题班
    /**
     * @param num a string contains only digits 0-9
     * @param target an integer
     * @return return all possibilities
     */
    String num;
    int target;
    List<String> ans = new ArrayList<>();

    void dfs(int pos, String str, long sum, long lastF) {
        if (pos == num.length()) {
            if (sum == target) {
                ans.add(str);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            long cur = Long.parseLong(num.substring(pos, i + 1));

            if (pos == 0) {
                dfs(i + 1, "" + cur, cur, cur);
            } else {
                dfs(i + 1, str + "*" + cur, sum - lastF + lastF * cur, lastF * cur);
                dfs(i + 1, str + "+" + cur, sum + cur, cur);
                dfs(i + 1, str + "-" + cur, sum - cur, -cur);
            }
            if (num.charAt(pos) == '0') {
                break;
            }
        }
    }

    public List<String> addOperators2(String num, int target) {
        // Write your code here
        this.num = num;
        this.target = target;
        dfs(0, "", 0, 0);
        return ans;
    }



///////////////////////////////////////////////////////////

}
/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Have you met this question in a real interview? Yes
Example
"123", 6 -> ["1+2+3", "1*2*3"]
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
 */