package _06_BFS._Topological_Sort;
import java.util.*;
import org.junit.Test;
public class _329_Topological_Longest_Increasing_Path_in_a_Matrix {

//Approach #1 (Naive DFS) [Time Limit Exceeded]
//Naive DFS Solution // Time Limit Exceeded
 public class Solution {
    private  final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; private int m, n;
    public int longestIncreasingPath(int[][] matrix) { if (matrix.length == 0) return 0;
        m= matrix.length;
        n= matrix[0].length;
        int ans=0;
        for (int i= 0;i<m;++i)
            for (int j=0;j<n;++j)
                ans = Math.max(ans, dfs(matrix, i, j));
        return ans; }
    private int dfs(int[][] matrix, int i, int j) { int ans=0;
        for (int[] d : dirs) {
            int x=i+d[0],y=j+d[1]; if(0<=x&&x<m&&0<=y&&y<n&&matrix[x][y]>matrix[i][j])
                ans = Math.max(ans, dfs(matrix, x, y));
        }
        return ++ans;
    }
}


//Approach #2 (DFS + Memoization) [Accepted]

    // DFS + Memoization Solution // Accepted and Recommended
public class Solution2 {
        private  final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) return 0;
            m = matrix.length;
            n = matrix[0].length;
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
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                    cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
                }
            }
            return ++cache[i][j];
        }
    }

    //Approach #3 (Peeling Onion) [Accepted]
    // Topological Sort Based Solution // An Alternative Solution
/*    public class Solution {
        private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; private int m, n;
        public int longestIncreasingPath(int[][] grid) {
            int m = grid.length;
            if (m == 0) return 0;
            int n = grid[0].length;
// padding the matrix with zero as boundaries
// assuming all positive integer, otherwise use INT_MIN as boundaries int[][] matrix = new int[m + 2][n + 2];
            for (int i = 0; i < m; ++i)
                System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);
// calculate outdegrees
            int[][] outdegree = new int[m + 2][n + 2];*/


/////////////////////////////////////////////////////////////////////////////////////////////
/*15ms Concise Java Solution
    To get max length of increasing sequences:

    Do DFS from every cell
    Compare every 4 direction and skip cells that are out of boundary or smaller
    Get matrix max from every cell's max
    Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
    The key is to cache the distance because it's highly possible to revisit a cell
    Hope it helps!*/
    class Solution4{
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
/////////////////////////////////////////////////////////////////////////////////////////////
/*
Java 14ms relative short & easy to code solution with explanation. O(mn) time O(mn) space. DFS + DP
The idea is simple and intuitive:
1. For each cell, try it's left, right, up and down for smaller number.
2. If it's smaller, means we are on the right track and we should keep going. If larger, stop and return.
3. Treat each cell as a start cell. Calculate and memorize the longest distance for this cell, so we don't need to calculate it again in the future.

Questions and advices are welcome.
 */
public class Solution5 {
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


/*    Graph theory, Java solution, O(v^2), no DFS
    Treat matrix as a graph. Then we find the longest path in graph. In this way, it can be solved in polynomial time. I drew a picture in my blog, check my blog*/
    class Solution6{
        public class Point{
            int x;
            int y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public  int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
                return 0;
            int n = matrix.length, m = matrix[0].length, count = m * n, ans = 0;
            while (count > 0) {
                HashSet<Point> remove = new HashSet<Point>();
                // each round, remove the peak number.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (matrix[i][j] == Integer.MIN_VALUE)
                            continue;
                        boolean up = (i == 0 || matrix[i][j] >= matrix[i - 1][j]);
                        boolean bottom = (i == n - 1 || matrix[i][j] >= matrix[i + 1][j]);
                        boolean left = (j == 0 || matrix[i][j] >= matrix[i][j - 1]);
                        boolean right = (j == m - 1 || matrix[i][j] >= matrix[i][j + 1]);
                        if (up && bottom && left && right)
                            remove.add(new Point(i, j));
                    }
                }
                for (Point point : remove) {
                    matrix[point.x][point.y] = Integer.MIN_VALUE;
                    count--;
                }
                ans++;
            }
            return ans;
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////


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
 */