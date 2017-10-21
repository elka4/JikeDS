package _01_Array.DP_2D;

/*
Longest Common Substring (Java)

In computer science, the longest common substring problem is to find the longest string that is a substring of two or more strings.


Analysis

Given two strings a and b, let dp[i][j] be the length of the common substring ending at a[i] and b[j].
 */
public class Longest_Common_Substring {
    public static int getLongestCommonSubstring(String a, String b){
        int m = a.length();
        int n = b.length();

        int max = 0;

        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a.charAt(i) == b.charAt(j)){
                    if(i==0 || j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j] = dp[i-1][j-1]+1;
                    }

                    if(max < dp[i][j])
                        max = dp[i][j];
                }

            }
        }

        return max;
    }
    /*This is a similar problem like longest common subsequence. The difference of the solution is that for this problem when a[i]!=b[j], dp[i][j] are all zeros by default. However, in the longest common subsequence problem, dp[i][j] values are carried from the previous values, i.e., dp[i-1][j] and dp[i][j-1].*/
}
