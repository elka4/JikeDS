
class Solution {
public:
    /**
     * @param matrix a matrix of 0 and 1
     * @return an integer
     */
    int maxSquare2(vector<vector<int>> &matrix) {
        // write your code here
        int n = matrix.size();
        if (n == 0)
            return 0;

        int m = matrix[0].size();
        if (m == 0)
            return 0;

        vector<vector<int>> f(n, vector<int>(m, 0));
        vector<vector<int>> u(n, vector<int>(m, 0));
        vector<vector<int>> l(n, vector<int>(m, 0));

        int length = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                    u[i][j] = l[i][j] = 1;
                    if (i > 0)
                        u[i][j] = u[i - 1][j] + 1;
                    if (j > 0)
                        l[i][j] = l[i][j - 1] + 1;
                } else {
                    u[i][j] = l[i][j] = 0;
                    if (i > 0 && j > 0)
                        f[i][j] = min(f[i - 1][j - 1], min(u[i - 1][j], l[i][j - 1])) + 1;
                    else
                        f[i][j] = 1;
                }
                length = max(length, f[i][j]);
            }
        return length * length;
    }
};