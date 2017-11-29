package DP.DP2;

//  639. Decode Ways II
//  https://leetcode.com/problems/decode-ways-ii/
//  http://www.lintcode.com/problem/decode-ways-ii/
//
//  Dynamic Programming
/*
使用以下映射方式将 A-Z 的消息编码为数字:

'A' -> 1
'B' -> 2
...
'Z' -> 26
除此之外, 编码的字符串也可以包含字符 *, 它代表了 1 到 9 的数字中的其中一个.给出包含数字和字符 * 的编码消息, 返回所有解码方式的数量. 因为结果可能很大, 所以结果需要对 10^9 + 7 取模
 */
//  6:2
//
//
public class _639_Decode_Ways_II {
//------------------------------------------------------------------------
//https://leetcode.com/problems/decode-ways-ii/solution/
//------------------------------------------------------------------------
    //2
    public class Solution22 {
        int M = 1000000007;
        public int numDecodings(String s) {
            int n = s.length();
            char[] sc = s.toCharArray();
            long[] dp = new long[s.length() + 1];
            dp[0] = 1;
            dp[1] = sc[0] == '*' ? 9 : sc[0] == '0' ? 0 : 1;

            for (int i = 1; i < n; i++) {
                if (sc[i] == '*') {
                    dp[i + 1] = 9 * dp[i];
                    if (sc[i - 1] == '1')
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                    else if (sc[i - 1] == '2')
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                    else if (sc[i - 1] == '*')
                        dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                } else {
                    dp[i + 1] = sc[i] != '0' ? dp[i] : 0;
                    if (sc[i - 1] == '1')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (sc[i - 1] == '2' && sc[i] <= '6')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (sc[i - 1] == '*')
                        dp[i + 1] = (dp[i + 1] + (sc[i] <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
            return (int) dp[n];
        }
    }
//------------------------------------------------------------------------
    //1
    //Approach #1 Using Recursion with memoization [Stack Overflow]
    public class Solution1 {
        int M = 1000000007;
        public int numDecodings(String s) {
            Integer[] memo=new Integer[s.length()];
            return ways(s, s.length() - 1,memo);
        }
        public int ways(String s, int i,Integer[] memo) {
            if (i < 0)
                return 1;
            if(memo[i]!=null)
                return memo[i];
            if (s.charAt(i) == '*') {
                long res = 9 * ways(s, i - 1,memo);
                if (i > 0 && s.charAt(i - 1) == '1')
                    res = (res + 9 * ways(s, i - 2,memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '2')
                    res = (res + 6 * ways(s, i - 2,memo)) % M;
                else if (i > 0 && s.charAt(i - 1) == '*')
                    res = (res + 15 * ways(s, i - 2,memo)) % M;
                memo[i]=(int)res;
                return memo[i];
            }
            long res = s.charAt(i) != '0' ? ways(s, i - 1,memo) : 0;
            if (i > 0 && s.charAt(i - 1) == '1')
                res = (res + ways(s, i - 2,memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                res = (res + ways(s, i - 2,memo)) % M;
            else if (i > 0 && s.charAt(i - 1) == '*')
                res = (res + (s.charAt(i)<='6'?2:1) * ways(s, i - 2,memo)) % M;
            memo[i]= (int)res;
            return memo[i];
        }
    }



//------------------------------------------------------------------------
    //2
    //Approach #2 Dynamic Programming [Accepted]
    public class Solution2 {
        int M = 1000000007;
        public int numDecodings(String s) {
            long[] dp = new long[s.length() + 1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;

            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '*') {
                    dp[i + 1] = 9 * dp[i];
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2')
                        dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                } else {
                    dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                    if (s.charAt(i - 1) == '1')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    else if (s.charAt(i - 1) == '*')
                        dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
                }
            }
            return (int) dp[s.length()];
        }
    }



//------------------------------------------------------------------------
    //3
    //Approach #3 Constant Space Dynamic Programming [Accepted]:
    public class Solution3 {
        int M = 1000000007;
        public int numDecodings(String s) {
            long first = 1, second = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
            for (int i = 1; i < s.length(); i++) {
                long temp = second;
                if (s.charAt(i) == '*') {
                    second = 9 * second;
                    if (s.charAt(i - 1) == '1')
                        second = (second + 9 * first) % M;
                    else if (s.charAt(i - 1) == '2')
                        second = (second + 6 * first) % M;
                    else if (s.charAt(i - 1) == '*')
                        second = (second + 15 * first) % M;
                } else {
                    second = s.charAt(i) != '0' ? second : 0;
                    if (s.charAt(i - 1) == '1')
                        second = (second + first) % M;
                    else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                        second = (second + first) % M;
                    else if (s.charAt(i - 1) == '*')
                        second = (second + (s.charAt(i) <= '6' ? 2 : 1) * first) % M;
                }
                first = temp;
            }
            return (int) second;
        }

    }




//------------------------------------------------------------------------
    //4
    //Java DP, O(n) time and O(1) space
    /*
    The idea for DP is simple when using two helper functions
ways(i) -> that gives the number of ways of decoding a single character
and
ways(i, j) -> that gives the number of ways of decoding the two character string formed by i and j.
The actual recursion then boils down to :

f(i) = (ways(i) * f(i+1)) + (ways(i, i+1) * f(i+2))
The solution to a string f(i), where i represents the starting index,

f(i) = no.of ways to decode the character at i, which is ways(i) + solve for remainder of the string using recursion f(i+1)
and
no.of ways to decode the characters at i and i+1, which is ways(i, i+1) + solve for remainder of the string using recursion f(i+2)

The base case is ,

return ways(s.charAt(i)) if(i == j)
The above recursion when implemented with a cache, is a viable DP solution, but it leads to stack overflow error, due to the depth of the recursion. So its better to convert to memoized version.

For the memoized version, the equation changes to

f(i) = ( f(i-1) * ways(i) ) + ( f(i-2) *ways(i-1, i) )
This is exactly the same as the previous recursive version in reverse,

The solution to a string f(i), where i represents the ending index of the string,

f(i) = solution to the prefix of the string f(i-1) + no.of ways to decode the character at i, which is ways(i)
and
solution to the prefix f(i-2) + no.of ways to decode the characters at i - 1 and i, which is ways(i-1, i)
     */
    public class Solution4 {
        public int numDecodings(String s) {
            long[] res = new long[2];
            res[0] = ways(s.charAt(0));
            if(s.length() < 2) return (int)res[0];

            res[1] = res[0] * ways(s.charAt(1)) + ways(s.charAt(0), s.charAt(1));
            for(int j = 2; j < s.length(); j++) {
                long temp = res[1];
                res[1] = (res[1] * ways(s.charAt(j)) + res[0] * ways(s.charAt(j-1), s.charAt(j))) % 1000000007;
                res[0] = temp;
            }
            return  (int)res[1];
        }

        private int ways(int ch) {
            if(ch == '*') return 9;
            if(ch == '0') return 0;
            return 1;
        }

        private int ways(char ch1, char ch2) {
            String str = "" + ch1 + "" + ch2;
            if(ch1 != '*' && ch2 != '*') {
                if(Integer.parseInt(str) >= 10 && Integer.parseInt(str) <= 26)
                    return 1;
            } else if(ch1 == '*' && ch2 == '*') {
                return 15;
            } else if(ch1 == '*') {
                if(Integer.parseInt(""+ch2) >= 0 && Integer.parseInt(""+ch2) <= 6)
                    return 2;
                else
                    return 1;
            } else {
                if(Integer.parseInt(""+ch1) == 1 ) {
                    return 9;
                } else if(Integer.parseInt(""+ch1) == 2 ) {
                    return 6;
                }
            }
            return 0;
        }
    }
//------------------------------------------------------------------------
    //5
    //Java O(N) by General Solution for all DP problems
    //https://discuss.leetcode.com/topic/95518/java-o-n-by-general-solution-for-all-dp-problems
    class Solution5{
        public int numDecodings(String s) {
            /* initial conditions */
            long[] dp = new long[s.length()+1];
            dp[0] = 1;
            if(s.charAt(0) == '0'){
                return 0;
            }
            dp[1] = (s.charAt(0) == '*') ? 9 : 1;

            /* bottom up method */
            for(int i = 2; i <= s.length(); i++){
                char first = s.charAt(i-2);
                char second = s.charAt(i-1);

                // For dp[i-1]
                if(second == '*'){
                    dp[i] += 9*dp[i-1];
                }else if(second > '0'){
                    dp[i] += dp[i-1];
                }

                // For dp[i-2]
                if(first == '*'){
                    if(second == '*'){
                        dp[i] += 15*dp[i-2];
                    }else if(second <= '6'){
                        dp[i] += 2*dp[i-2];
                    }else{
                        dp[i] += dp[i-2];
                    }
                }else if(first == '1' || first == '2'){
                    if(second == '*'){
                        if(first == '1'){
                            dp[i] += 9*dp[i-2];
                        }else{ // first == '2'
                            dp[i] += 6*dp[i-2];
                        }
                    }else if( ((first-'0')*10 + (second-'0')) <= 26 ){
                        dp[i] += dp[i-2];
                    }
                }

                dp[i] %= 1000000007;
            }
            /* Return */
            return (int)dp[s.length()];
        }
    }
//------------------------------------------------------------------------

    //6
    // 9Ch
    public class Jiuzhang {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            final int mod = 1000000007;
            int n = s.length();
            int[] f = new int[n + 1];
            f[0] = 1;
            for (int i = 1; i <= n; i++) {
                f[i] = 0;
                if (s.charAt(i - 1) == '*') {
                    f[i] = (int)((f[i] + 9L * f[i - 1]) % mod);
                    if (i >= 2) {
                        if (s.charAt(i - 2) == '*') {
                            f[i] = (int)((f[i] + 15L * f[i - 2]) % mod);
                        }
                        else if (s.charAt(i - 2) == '1') {
                            f[i] = (int)((f[i] + 9L * f[i - 2]) % mod);
                        }
                        else if (s.charAt(i - 2) == '2') {
                            f[i] = (int)((f[i] + 6L * f[i - 2]) % mod);
                        }
                    }
                }
                else {
                    if (s.charAt(i - 1) != '0') {
                        f[i] = (f[i] + f[i - 1]) % mod;
                    }
                    if (i >= 2) {
                        if (s.charAt(i - 2) == '*'){
                            if (s.charAt(i - 1) <= '6') {
                                f[i] = (int)((f[i] + 2L * f[i - 2]) % mod);
                            }
                            else {
                                f[i] = (f[i] + f[i - 2]) % mod;
                            }
                        }
                        else {
                            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                            if (twoDigits >= 10 && twoDigits <= 26) {
                                f[i] = (f[i] + f[i - 2]) % mod;
                            }
                        }
                    }
                }
            }

            return f[n];
        }
    }


//------------------------------------------------------------------------



//------------------------------------------------------------------------
}
/*
A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.
------------------------------------------------------------------------
Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
------------------------------------------------------------------------
Example 2:
Input: "1*"
Output: 9 + 9 = 18
------------------------------------------------------------------------
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
Seen this question in a real interview before?   Yes  No
------------------------------------------------------------------------
Companies
Facebook

Related Topics
Dynamic Programming

Similar Questions
Decode Ways
------------------------------------------------------------------------
 */