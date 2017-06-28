/** 110 Minimum Path Sum
 * Easy*/


class Solution {
public:
    /**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    int minPathSum(vector<vector<int> > &grid) {
        // write your code here
        int f[1000][1000];
        if (grid.size() == 0 || grid[0].size() == 0)
            return 0;
        f[0][0] = grid[0][0];
        for(int i = 1; i < grid.size(); i++)
            f[i][0] = f[i-1][0] + grid[i][0];
        for(int i = 1; i < grid[0].size(); i++)
            f[0][i] = f[0][i-1] + grid[0][i];
        for(int i = 1; i < grid.size(); i++)
            for(int j = 1; j < grid[0].size(); j++)
                f[i][j] = min(f[i-1][j], f[i][j-1]) + grid[i][j];

        return f[grid.size()-1][grid[0].size()-1];
    }
};
