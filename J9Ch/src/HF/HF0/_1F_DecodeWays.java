package HF.HF0;

import org.junit.Test;
import java.util.*;

//• 划分性动态规划

/*
f[i] = f[i-1] | S[i-1]对应一个字母 + f[i-2] | S[i-2]S[i-1]

f[i]
数字串S前i个数字解 密成字母串的方式数

f[i-1] | S[i-1]对应一个字母
数字串S前i-1个数字解 密成字母串的方式数

f[i-2] | S[i-2]S[i-1]对应一个字母
数字串S前i-2个数字解密 成字母串的方式数
 */

/*
• 设数字串S前i个数字解密成字母串有f[i]种方式
• 初始条件:f[0] = 1，即空串有1种方式解密 – 解密成空串
• 边界情况:如果i = 1, 只看最后一个数字
 */

/*
• f[0], f[1], ..., f[N]
• 答案是f[N]
• 时间复杂度O(N), 空间复杂度O(N)
 */

/*
思路:
• 数学表示:f[n]表示从第0层走到第n层一共有多少种方法
    – f[n] = f[n-1] + f[n-2]
 */

//Decode Ways
public class _1F_DecodeWays {
    //jiuzhang
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                nums[i] = nums[i - 1];
            }

            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }

    @Test
    public void test01(){
        System.out.println(numDecodings("2526"));
        System.out.println(numDecodings("12"));
    }

////////////////////////////////////////////////////////////
    // version: 高频题班
    //jiuzhang
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings2(String s) {
        // Write your code here
        int l = s.length();
        if (l == 0) {
            return 0;   // only for this problem, but the ans should be 1
        }
        int[] f = new int[l + 1];
        f[0] = 1;
        char sc[] = s.toCharArray();

        for (int i = 1; i <= l; i++) {
            if (sc[i - 1] != '0') {
                f[i] += f[i - 1];
            }
            if (i >= 2) {
                int val2 = (sc[i - 2] - '0') * 10 + sc[i - 1] - '0';
                if (10 <= val2 && val2 <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[l];
    }

    @Test
    public void test02(){
        System.out.println(numDecodings2("2526"));
        System.out.println(numDecodings2("12"));
        System.out.println(numDecodings2("1212"));
    }

/////////////////////////////////////////////////////////

    public int numDecodings3(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }

///////////////////////////////////////////////////////////////

    public int numDecodings4(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

////////////////////////////////////////////////////////////

    public int numDecodings5(String s) {

        if (s == null || s.length() == 0)
            return 0;

        Set<String> symbols = new HashSet<String>();
        for (int i=1; i<=26; i++){
            symbols.add(String.valueOf(i));
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        return numDec(s, 0,  map, symbols);
    }

    private int numDec(String str, int start,  Map<Integer, Integer> map, Set<String> symbols) {
        Integer count = map.get(start);
        if (count != null){
            return count;
        }

        if (start == str.length()){
            return 1;
        }

        int numWays = 0;
        if ((start + 1 <= str. length()) &&
                symbols.contains(str.substring(start, start + 1)) && symbols.contains(str.substring(start, start + 1)))
            numWays += numDec(str, start + 1, map, symbols);

        if ((start + 2 <= str. length()) &&
                symbols.contains(str.substring(start, start + 2)) && symbols.contains(str.substring(start, start + 2)))
            numWays += numDec(str, start + 2, map, symbols);

        map.put(start, numWays);

        return numWays;
    }
}
/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Example
Given encoded message 12, it could be decoded as AB (1 2) or L (12).
The number of ways decoding 12 is 2.


 */