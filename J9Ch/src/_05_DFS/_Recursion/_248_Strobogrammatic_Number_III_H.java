package _05_DFS._Recursion;
import java.util.*;

//248. Strobogrammatic Number III
//https://leetcode.com/problems/strobogrammatic-number-iii/description/
public class _248_Strobogrammatic_Number_III_H {
//-------------------------------------------------------------------------//
//    Concise Java Solution
//    Construct char arrays from low.length() to high.length()
//    Add stro pairs from outside
//    When left > right, add eligible count
    private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
        int[] count = {0};
        for (int len = low.length(); len <= high.length(); len++) {
            char[] c = new char[len];
            dfs(low, high, c, 0, len - 1, count);
        }
        return count[0];
    }

    public void dfs(String low, String high , char[] c, int left, int right, int[] count) {
        if (left > right) {
            String s = new String(c);
            if ((s.length() == low.length() && s.compareTo(low) < 0) ||
                    (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count[0]++;
            return;
        }
        for (char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];
            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (left == right && p[0] != p[1]) {
                continue;
            }
            dfs(low, high, c, left + 1, right - 1, count);
        }
    }
//-------------------------------------------------------------------------//

    //2
/*
    Clear Java AC solution using Strobogrammatic Number II method
    The basic idea is to find generate a list of strobogrammatic number with the length between the length of lower bound and the length of upper bound. Then we pass the list and ignore the numbers with the same length of lower bound or upper bound but not in the range.

    I think it is not a a very optimized method and can any one provide a better one?
*/

    public class Solution2{


        public int strobogrammaticInRange(String low, String high){
            int count = 0;
            List<String> rst = new ArrayList<String>();
            for(int n = low.length(); n <= high.length(); n++){
                rst.addAll(helper(n, n));
            }
            for(String num : rst){

                if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||(num.length() == high.length() && num.compareTo(high) > 0)) continue;
                count++;
            }
            return count;
        }

        private List<String> helper(int cur, int max){
            if(cur == 0) return new ArrayList<String>(Arrays.asList(""));
            if(cur == 1) return new ArrayList<String>(Arrays.asList("1", "8", "0"));

            List<String> rst = new ArrayList<String>();
            List<String> center = helper(cur - 2, max);

            for(int i = 0; i < center.size(); i++){
                String tmp = center.get(i);
                if(cur != max) rst.add("0" + tmp + "0");
                rst.add("1" + tmp + "1");
                rst.add("6" + tmp + "9");
                rst.add("8" + tmp + "8");
                rst.add("9" + tmp + "6");
            }
            return rst;
        }
    }
//-------------------------------------------------------------------------//
//-------------------------------------------------------------------------//
}
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */