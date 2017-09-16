package HF.HF0;

import java.util.*;

public class _1F_DecodeWays {
    public int numDecodings(String s) {
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

    public int numDecodings2(String s) {
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

    public int numDecodings3(String s) {

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