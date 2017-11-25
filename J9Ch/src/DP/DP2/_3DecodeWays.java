package DP.DP2;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//• 划分性动态规划

/*
f[i] = f[i-1] | S[i-1]       + f[i-2] | S[i-2]S[i-1]

f[i]:                   数字串S前i个数字解密成字母串的方式数
f[i-1] | S[i-1]:        数字串S前i-1个数字解密成字母串的方式数
f[i-2] | S[i-2]S[i-1]:  数字串S前i-2个数字解密成字母串的方式数

设数字串S前i个数字解密成字母串有f[i]种方式

初始条件： f[0] = 1, 即空串有1种方式解密

边界条件：如果i = 1， 只看最后哦一个数字

f[0], f[1], .... f[N]
所以初始化设为f[N + 1]
答案是 f[N]
时间复杂度 O(N), 空间复杂度O(N)

-----------------------------------------------------------------------------------------------
 */


//  91. Decode Ways
//  https://leetcode.com/problems/decode-ways/description/
public class _3DecodeWays {

    //这个算法好地方在于从2开始算，所以中间不用那么多if
    public int numDecodings(String s) {
        char[] sc  = s.toCharArray();  //这样之后操作简单，直接变成对array的操作
        int n = sc.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n + 1];
        //初始化两位字母
        f[0] = 1;                        //空串有1种方式解密
        f[1] = sc[0] != '0' ? 1 : 0;    //最后哦一个数字

        for(int i = 2; i <= n; i++) {
            int first = sc[i - 1] - '0';
            int second = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
            if(first >= 1 && first <= 9) {
                f[i] += f[i-1];
            }
            if(second >= 10 && second <= 26) {
                f[i] += f[i-2];
            }
        }
        return f[n];
    }

    //上面算法的String版本
    public int numDecodings7(String s) {
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

//------------------------------------------------------------------------------
    // 9Ch
    public int numDecodings2(String s) {
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
        System.out.println(numDecodings("2526")); //4
        System.out.println(numDecodings("12"));   //2
    }

//---------------------------------///////////////////////

    // version: 高频题班
    // 9Ch
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings3(String s) {
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
        System.out.println(numDecodings2("2526")); //4
        System.out.println(numDecodings2("12"));   //2
    }

    // 9Ch DP
    public int numDecodings4(String ss){
        char[] s  = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }

        // n + 1
        int[] f = new int[n + 1];
        f[0] = 1;

        // first i digits
        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            // last digit
            int t = s[i - 1] - '0';
            if (t >= 1 && t <= 9) {
                f[i] += f[i - 1];
            }

            // length must be greater than 1
            if (i >= 2) {
                // last two digits
                t = (s[i - 2] - '0') * 10 + (s[i - 1] - '0');
                if (t >= 10 && t <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }

    public int numDecodings5(String ss){
        char[] s  = ss.toCharArray();
        int n = s.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[n + 1];
        f[0] = 1;

        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            int t = s[i - 1] - '0';
            if (t >= 1 && t <= 9) {
                f[i] += f[i - 1];
            }

            if (i >= 2) {
                t = (s[i - 2] - '0') * 10 + (s[i - 1] - '0');
                if (t >= 10 && t <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }




    @Test
    public void test022(){
        System.out.println(numDecodings5("2526")); //4
        System.out.println(numDecodings5("12"));   //2
    }


//---------------------------------////////////////////
    //从长向短计算， 可以把状态memo[i]理解成不要i个字符可以decode的方法数
    public int numDecodings6(String s) {
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

//-------------------------------------------------------------------------/


//---------------------------------///////////////////////

    public int numDecodings8(String s) {

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
                symbols.contains(str.substring(start, start + 1))
                && symbols.contains(str.substring(start, start + 1)))
            numWays += numDec(str, start + 1, map, symbols);

        if ((start + 2 <= str. length()) &&
                symbols.contains(str.substring(start, start + 2))
                && symbols.contains(str.substring(start, start + 2)))
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

/*
有一个消息包含A-Z通过以下规则编码

'A' -> 1
'B' -> 2
...
'Z' -> 26
现在给你一个加密过后的消息，问有几种解码的方式

您在真实的面试中是否遇到过这个题？ Yes
样例
给你的消息为12，有两种方式解码 AB(12) 或者 L(12). 所以返回 2
 */