package S_6_Dynamic_Problem_II.Required_10;
import java.util.*;
/** 77 Longest Common Subsequence


 * Created by tianhuizhu on 6/28/17.
 */
public class _77_Longest_Common_Subsequence_Medium {

    public class Solution {
        /**
         * @param A, B: Two strings.
         * @return: The length of longest common subsequence of A and B.
         */
        public int longestCommonSubsequence(String A, String B) {
            int n = A.length();
            int m = B.length();
            int f[][] = new int[n + 1][m + 1];
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= m; j++){
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                    if(A.charAt(i - 1) == B.charAt(j - 1))
                        f[i][j] = f[i - 1][j - 1] + 1;
                }
            }
            return f[n][m];
        }
    }
}
