
class Solution {
public:
    /**
     * @param A an integer array
     * @return an integer
     */
    int stoneGame2(vector<int>& A) {
        // Write your code here
        int n = A.size();
        if (n <= 1)
            return 0;
        vector<int> sum(2 * n + 1);
        vector<vector<int> > dp(2 * n, vector<int>(2 * n));

        sum[0] = 0;
        for (int i = 0; i < 2 * n; i++) {
            sum[i + 1] = sum[i] + A[i % n];
            dp[i][i] = 0;

        }

        for (int len = 2; len <= 2 * n; len++)
            for (int i = 0; i < 2 * n && i + len <= 2 * n; i++) {
                int j = i + len - 1;
                dp[i][j] = 0x7fffffff / 4;
                for ( int k = i; k < j; k++)
                    if (dp[i][k] + dp[k + 1][j] + sum[j + 1] - sum[i] < dp[i][j]) {
                        dp[i][j] = dp[i][k]+dp[k + 1][j] + sum[j + 1] - sum[i];
                }
            }

        int ans = 0x7fffffff / 4;
        for (int i = 0; i < n; ++i)
            if (dp[i][i + n - 1] < ans)
                ans = dp[i][i + n - 1];
        return ans;
    }
};