package _05_DFS._DFS_Matrix;
import java.util.*;

//  329. Longest Increasing Path in a Matrix
//  https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
//
public class _329_DFS_Longest_Increasing_Path_in_a_Matrix_M {
    //https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solution/

    //Approach #1 (Naive DFS) [Time Limit Exceeded]
    // Naive DFS Solution
    // Time Limit Exceeded
    public class Solution01 {
        private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) return 0;
            m = matrix.length;
            n = matrix[0].length;
            int ans = 0;
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    ans = Math.max(ans, dfs(matrix, i, j));
            return ans;
        }

        private int dfs(int[][] matrix, int i, int j) {
            int ans = 0;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                    ans = Math.max(ans, dfs(matrix, x, y));
            }
            return ++ans;
        }
    }

//-------------------------------------------------------------------------//
    //Approach #2 (DFS + Memoization) [Accepted]
    // DFS + Memoization Solution
    // Accepted and Recommended
    public class Solution02 {
        private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) return 0;
            m = matrix.length; n = matrix[0].length;
            int[][] cache = new int[m][n];
            int ans = 0;
            for (int i = 0; i < m; ++i)
                for (int j = 0; j < n; ++j)
                    ans = Math.max(ans, dfs(matrix, i, j, cache));
            return ans;
        }

        private int dfs(int[][] matrix, int i, int j, int[][] cache) {
            if (cache[i][j] != 0) return cache[i][j];
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                    cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
            }
            return ++cache[i][j];
        }
    }

//-------------------------------------------------------------------------///
    //Approach #3 (Peeling Onion) [Accepted]
    // Topological Sort Based Solution
    // An Alternative Solution
    public class Solution03 {
        private final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;
        public int longestIncreasingPath(int[][] grid) {
            int m = grid.length;
            if (m == 0) return 0;
            int n = grid[0].length;
            // padding the matrix with zero as boundaries
            // assuming all positive integer, otherwise use INT_MIN as boundaries
            int[][] matrix = new int[m + 2][n + 2];
            for (int i = 0; i < m; ++i)
                System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

            // calculate outdegrees
            int[][] outdegree = new int[m + 2][n + 2];
            for (int i = 1; i <= m; ++i)
                for (int j = 1; j <= n; ++j)
                    for (int[] d: dir)
                        if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                            outdegree[i][j]++;

            // find leaves who have zero out degree as the initial level
            n += 2;
            m += 2;
            List<int[]> leaves = new ArrayList<>();
            for (int i = 1; i < m - 1; ++i)
                for (int j = 1; j < n - 1; ++j)
                    if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});

            // remove leaves level by level in topological order
            int height = 0;
            while (!leaves.isEmpty()) {
                height++;
                List<int[]> newLeaves = new ArrayList<>();
                for (int[] node : leaves) {
                    for (int[] d:dir) {
                        int x = node[0] + d[0], y = node[1] + d[1];
                        if (matrix[node[0]][node[1]] > matrix[x][y])
                            if (--outdegree[x][y] == 0)
                                newLeaves.add(new int[]{x, y});
                    }
                }
                leaves = newLeaves;
            }
            return height;
        }
    }

//-------------------------------------------------------------------------///
    class Solution{
        public final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int longestIncreasingPath(int[][] matrix) {
            if(matrix.length == 0) return 0;
            int m = matrix.length, n = matrix[0].length;
            int[][] cache = new int[m][n];
            int max = 1;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int len = dfs(matrix, i, j, m, n, cache);
                    max = Math.max(max, len);
                }
            }
            return max;
        }

        public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
            if(cache[i][j] != 0) return cache[i][j];
            int max = 1;
            for(int[] dir: dirs) {
                int x = i + dir[0], y = j + dir[1];
                if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
                int len = 1 + dfs(matrix, x, y, m, n, cache);
                max = Math.max(max, len);
            }
            cache[i][j] = max;
            return max;
        }
    }

//-------------------------------------------------------------------------///
    public class Solution2 {
        private final  int[] dx = {0, -1, 1, 0};
        private final  int[] dy = {-1, 0, 0, 1};

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length < 1) return 0;
            int n = matrix.length, m = matrix[0].length, len = m * n;
            int[][] opt = new int[n][m];
            int[][] h = new int[len][2];
            for (int i = 0; i < len; i++) {
                h[i][0] = matrix[i / m][i % m];
                h[i][1] = i;
            }

            Arrays.sort(h, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });

            int ans = 0;
            for (int[] cur : h) {
                int i = cur[1] / m, j = cur[1] % m, tmp = 1;
                for (int dir = 0; dir < 4; dir++)
                    if (i + dx[dir] >= 0 && i + dx[dir] < n
                            && j + dy[dir] >= 0 && j + dy[dir] < m
                            && matrix[i + dx[dir]][j + dy[dir]] < cur[0])
                        tmp = Math.max(tmp, opt[i + dx[dir]][j + dy[dir]] + 1);
                opt[i][j] = tmp;
                ans = Math.max(ans, tmp);
            }
            return ans;
        }
    }

//-------------------------------------------------------------------------///
    public class Solution3 {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int[][] cache = new int[matrix.length][matrix[0].length];
            int max = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    int length = findSmallAround(i, j, matrix, cache, Integer.MAX_VALUE);
                    max = Math.max(length, max);
                }
            }
            return max;
        }

        private int findSmallAround(int i, int j, int[][] matrix, int[][] cache, int pre) {
            // if out of bond OR current cell value larger than previous cell value.
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] >= pre) {
                return 0;
            }
            // if calculated before, no need to do it again
            if (cache[i][j] > 0) {
                return cache[i][j];
            } else {
                int cur = matrix[i][j];
                int tempMax = 0;
                tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur), tempMax);
                tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur), tempMax);
                tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur), tempMax);
                tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur), tempMax);
                cache[i][j] = ++tempMax;
                return tempMax;
            }
        }
    }

//-------------------------------------------------------------------------///
}
/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Credits:
 */