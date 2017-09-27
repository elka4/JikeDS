
class Solution {
public:
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    int longestCommonSubsequence(string A, string B) {
        // write your code here
        /* state function:
            dp[i+1][j+1] = dp[i][j]    A[i] = B[j]
                         = max(dp[i-1][j], dp[i][j-1])
            dp[0][j] = dp[i][0] = 0
        */

        int len_a = A.size();
        int len_b = B.size();
        if(len_a == 0 || len_b == 0) {
            return 0;
        }

        int **dp = (int **)malloc((len_a+1) * sizeof(int *));
        for (int i = 0; i < len_a + 1; i++)
            dp[i] = (int *)malloc((len_b+1) * sizeof(int));

        /*
        vector<vector<int> > dp;
        for(int i = 0; i < len_a+1; i ++) {
            vector<int> one(len_b+1, 0);
            dp.push_back(one);
        }
        */

        for(int i = 0; i < len_a+1; i ++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < len_b+1; j ++) {
            dp[0][j] = 0;
        }

        for(int i = 1; i < len_a+1; i ++) {
            for(int j = 1; j < len_b+1; j ++) {
                if(A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[len_a][len_b];
    }
};
