
class Solution {
public:
    /**
     * @param matrix an integer array of n * m matrix
     * @param k an integer
     * @return the maximum number
     */
    int maxSlidingWindow2(vector<vector<int>>& matrix, int k) {
        // Write your code here
        int n = matrix.size();
        if (n == 0 || n < k)
            return 0;
        int m = matrix[0].size();
        if (m == 0 || m < k)
            return 0;

        vector<vector<int>> sum(n + 1, vector<int>(m + 1));
        for (int i = 0; i <= n; ++i) sum[i][0] = 0;
        for (int i = 0; i <= m; ++i) sum[0][i] = 0;

        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= m; ++j)
                sum[i][j] = matrix[i - 1][j - 1] +
                            sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];

        int max_value = INT_MIN;
        for (int i = k; i <= n; ++i)
            for (int j = k; j <= m; ++j) {
                int value = sum[i][j] - sum[i - k][j] -
                            sum[i][j - k] + sum[i - k][j - k];

                if (value > max_value)
                    max_value = value;
            }
        return max_value;
    }
};