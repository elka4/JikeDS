package _05_DFS._Back_2DArray;
import java.util.*;

//  52. N-Queens II
//  https://leetcode.com/problems/n-queens-ii/description/
//  http://www.lintcode.com/zh-cn/problem/n-queens-ii/
public class _052_BackTracking_N_Queens_II_H {
    //Accepted Java Solution
    class Solution{
        private final Set<Integer> occupiedCols = new HashSet<Integer>();
        private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
        private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();
        public int totalNQueens(int n) {
            return totalNQueensHelper(0, 0, n);
        }

        private int totalNQueensHelper(int row, int count, int n) {
            for (int col = 0; col < n; col++) {
                if (occupiedCols.contains(col))
                    continue;
                int diag1 = row - col;
                if (occupiedDiag1s.contains(diag1))
                    continue;
                int diag2 = row + col;
                if (occupiedDiag2s.contains(diag2))
                    continue;
                // we can now place a queen here
                if (row == n-1)
                    count++;
                else {
                    occupiedCols.add(col);
                    occupiedDiag1s.add(diag1);
                    occupiedDiag2s.add(diag2);
                    count = totalNQueensHelper(row+1, count, n);
                    // recover
                    occupiedCols.remove(col);
                    occupiedDiag1s.remove(diag1);
                    occupiedDiag2s.remove(diag2);
                }
            }

            return count;
        }
    }

//-------------------------------------------------------------------------///////
    public class Solution2 {
        int result = 0;
        public int totalNQueens(int n) {
            boolean[] column = new boolean[n];
            boolean[] dia45 = new boolean[2 * n - 1];
            boolean[] dia135 = new boolean[2 * n - 1];
            helper(0, n, column, dia45, dia135);
            return result;
        }
        private void helper(int row, int n, boolean[] column, boolean[] dia45, boolean[] dia135) {
            if (row == n) {
                result++;
                return;
            }
            for (int col = 0; col < n; col++) {
                if (!column[col] && !dia45[col + row] && !dia135[n - 1- row + col]) {
                    column[col] = dia45[col + row] = dia135[n - 1- row + col] = true;
                    helper(row + 1, n, column, dia45, dia135);
                    column[col] = dia45[col + row] = dia135[n - 1- row + col] = false;
                }
            }
        }
    }

//-------------------------------------------------------------------------///////
    class Solution3{
        int res = 0;
        public int totalNQueens(int n) {
            //int[] horizonal = new int[n];
            boolean[] vertical = new boolean[n];
            boolean[] leftfall = new boolean[2 * n - 1];
            boolean[] rightfall = new boolean[2 * n - 1];
            helper(n, vertical, leftfall, rightfall, 0);
            return res;
        }

        void helper(int n, boolean[] v, boolean[] l, boolean[] r, int row){

            if(row == n){
                res++;
                return;
            }

            for(int col = 0; col < n; col++){
                if(v[col] || l[row - col + n - 1] || r[row + col]) continue;
                v[col] = true;
                l[row - col + n - 1] = true;
                r[row + col] = true;

                helper(n, v, l, r, row + 1);

                v[col] = false;
                l[row - col + n - 1] = false;
                r[row + col] = false;
            }
        }
    }

//-------------------------------------------------------------------------///////
/*    Easiest Java Solution (1ms, 98.22%)
    This is a classic backtracking problem.

    Start row by row, and loop through columns. At each decision point, skip unsafe positions by using three boolean arrays.

    Start going back when we reach row n.

    Just FYI, if using HashSet, running time will be at least 3 times slower!*/

    public class Solution4 {
        int count = 0;
        public int totalNQueens(int n) {
            boolean[] cols = new boolean[n];     // columns   |
            boolean[] d1 = new boolean[2 * n];   // diagonals \
            boolean[] d2 = new boolean[2 * n];   // diagonals /
            backtracking(0, cols, d1, d2, n);
            return count;
        }

        public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
            if(row == n) count++;

            for(int col = 0; col < n; col++) {
                int id1 = col - row + n;
                int id2 = col + row;
                if(cols[col] || d1[id1] || d2[id2]) continue;

                cols[col] = true; d1[id1] = true; d2[id2] = true;
                backtracking(row + 1, cols, d1, d2, n);
                cols[col] = false; d1[id1] = false; d2[id2] = false;
            }
        }
    }

//-------------------------------------------------------------------------///////
//    Share my Java code (beats 97.83% run times)
    /*
        常规n-queens解法, 数答案个数.
        用column标记此行之前的哪些column已经放置了queen. 棋盘坐标(row, col)对应column的第col位(LSB --> MSB, 下同).
        用diag标记此位置之前的哪些主对角线已经放置了queen. 棋盘坐标(row, col)对应diag的第(n - 1 + row - col)位.
        用antiDiag标记此位置之前的哪些副对角线已经放置了queen. 棋盘坐标(row, col)对应antiDiag的第(row + col)位.
    */
    public class Solution5 {
        int count = 0;

        public int totalNQueens(int n) {
            dfs(0, n, 0, 0, 0);
            return count;
        }

        private void dfs(int row, int n, int column, int diag, int antiDiag) {
            if (row == n) {
                ++count;
                return;
            }
            for (int i = 0; i < n; ++i) {
                boolean isColSafe = ((1 << i) & column) == 0;
                boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
                boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
                if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                    dfs(row + 1, n, (1 << i) | column,
                            (1 << (n - 1 + row - i)) | diag,
                            (1 << (row + i)) | antiDiag);
                }
            }
        }
    }

//-------------------------------------------------------------------------///////
    //jiuzhang
    public class Jiuzhang {
        public int sum;
        public int totalNQueens(int n) {
            sum = 0;
            int[] usedColumns = new int[n];
            placeQueen(usedColumns, 0);
            return sum;
        }
        public void placeQueen(int[] usedColumns, int row) {
            int n = usedColumns.length;

            if (row == n) {
                sum ++;
                return;
            }

            for (int i = 0; i < n; i++) {
                if (isValid(usedColumns, row, i)) {
                    usedColumns[row] = i;
                    placeQueen(usedColumns, row + 1);
                }
            }
        }
        public boolean isValid(int[] usedColumns, int row, int col) {
            for (int i = 0; i < row; i++) {
                if (usedColumns[i] == col) {
                    return false;
                }
                if ((row - i) == Math.abs(col-usedColumns[i])) {
                    return false;
                }
            }
            return true;
        }
    }

//-------------------------------------------------------------------------///////
}

/*
N皇后问题 II

根据n皇后问题，现在返回n皇后不同的解决方案的数量而不是具体的放置布局。

样例
比如n=4，存在2种解决方案

标签
递归
相关题目
中等 组合 33 %
中等 N皇后问题
 */


/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
 */