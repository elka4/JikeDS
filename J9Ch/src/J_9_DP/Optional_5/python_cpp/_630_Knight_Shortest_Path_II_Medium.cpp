/** 630 Knight Shortest Path II */


class Solution {
public:
    /**
     * @param grid a chessboard included 0 and 1
     * @return the shortest path
     */
    int shortestPath2(vector<vector<bool>>& grid) {
        // Write your code here
        int n = grid.size();
        if (n == 0)
            return -1;
        int m = grid[0].size();
        if (m == 0)
            return -1;

        vector<vector<int>> f(n, vector<int>(m, INT_MAX));
        f[0][0] = 0;
        for (int j = 1; j < m; ++j)
          for (int i = 0; i < n; ++i)
            if (!grid[i][j]) {
                if (i >= 1 && j >= 2 && f[i - 1][j - 2] != INT_MAX)
                    f[i][j] = min(f[i][j], f[i - 1][j - 2] + 1);
                if (i + 1 < n && j >= 2 && f[i + 1][j - 2] != INT_MAX)
                    f[i][j] = min(f[i][j], f[i + 1][j - 2] + 1);
                if (i >= 2 && j >= 1 && f[i - 2][j - 1] != INT_MAX)
                    f[i][j] = min(f[i][j], f[i - 2][j - 1] + 1);
                if (i + 2 < n && j >= 1 && f[i + 2][j - 1] != INT_MAX)
                    f[i][j] = min(f[i][j], f[i + 2][j - 1] + 1);
            }

        if (f[n - 1][m - 1] == INT_MAX)
            return -1;

        return f[n - 1][m - 1];
    }
};