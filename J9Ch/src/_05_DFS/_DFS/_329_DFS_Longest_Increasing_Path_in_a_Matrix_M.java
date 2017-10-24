package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _329_DFS_Longest_Increasing_Path_in_a_Matrix_M {

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
}
