package _01_Array.DP;

/*
LeetCode – Decode Ways (Java)

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given an encoded message containing digits, determine the total number of ways to decode it.
 */
public class Decode_Ways {
/*    Java Solution

    This problem can be solve by using dynamic programming. It is similar to the problem of counting ways of climbing stairs. The relation is dp[n]=dp[n-1]+dp[n-2].*/

    public int numDecodings(String s) {
        if(s==null || s.length()==0 || s.charAt(0)=='0')
            return 0;
        if(s.length()==1)
            return 1;

        int[] dp = new int[s.length()];
        dp[0]=1;
        if(Integer.parseInt(s.substring(0,2))>26){
            if(s.charAt(1)!='0'){
                dp[1]=1;
            }else{
                dp[1]=0;
            }
        }else{
            if(s.charAt(1)!='0'){
                dp[1]=2;
            }else{
                dp[1]=1;
            }
        }

        for(int i=2; i<s.length(); i++){
            if(s.charAt(i)!='0'){
                dp[i]+=dp[i-1];
            }

            int val = Integer.parseInt(s.substring(i-1, i+1));
            if(val<=26 && val >=10){
                dp[i]+=dp[i-2];
            }
        }

        return dp[s.length()-1];
    }

}
