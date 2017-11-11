package _05_DFS._Back_2DArray;
import java.util.*;

//  51. N-Queens
//  https://leetcode.com/problems/n-queens/description/
//  http://www.lintcode.com/zh-cn/problem/n-queens/
public class _051_BackTracking_N_Queens_H {
    //    My easy understanding Java Solution
    public class Solution {
        public List<List<String>> solveNQueens(int n) {
            char[][] board = new char[n][n];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    board[i][j] = '.';
            List<List<String>> res = new ArrayList<List<String>>();
            dfs(board, 0, res);
            return res;
        }

        private void dfs(char[][] board, int colIndex, List<List<String>> res) {
            if(colIndex == board.length) {
                res.add(construct(board));
                return;
            }

            for(int i = 0; i < board.length; i++) {
                if(validate(board, i, colIndex)) {
                    board[i][colIndex] = 'Q';
                    dfs(board, colIndex + 1, res);
                    board[i][colIndex] = '.';
                }
            }
        }

        private boolean validate(char[][] board, int x, int y) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < y; j++) {
                    if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                        return false;
                }
            }

            return true;
        }

        private List<String> construct(char[][] board) {
            List<String> res = new LinkedList<String>();
            for(int i = 0; i < board.length; i++) {
                String s = new String(board[i]);
                res.add(s);
            }
            return res;
        }
    }

////////////////////////////////////////////////////////////////////////////////////////
    //Comparably concise Java code
    // We just remember the busy columns and diagonals and recursively try to put the queen into the next row.
    public class Solution2 {
        public List<String[]> solveNQueens(int n) {
            List<String[]> res = new ArrayList<>();
            helper(0, new boolean[n], new boolean[2*n], new boolean[2*n],
                    new String[n], res);
            return res;
        }

        private void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2,
                            String[] board, List<String[]> res) {
            if (r == board.length) res.add(board.clone());
            else {
                for (int c = 0; c < board.length; c++) {
                    int id1 = r - c + board.length, id2 = 2*board.length - r - c - 1;
                    if (!cols[c] && !d1[id1] && !d2[id2]) {
                        char[] row = new char[board.length];
                        Arrays.fill(row, '.'); row[c] = 'Q';
                        board[r] = new String(row);

                        cols[c] = true; d1[id1] = true; d2[id2] = true;

                        helper(r+1, cols, d1, d2, board, res);

                        cols[c] = false; d1[id1] = false; d2[id2] = false;
                    }
                }
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    class Jiuzhang {
        /**
         * Get all distinct N-Queen solutions
         * @param n: The number of queens
         * @return: All distinct solutions
         * For example, A string '...Q' shows a queen on forth position
         */
        List<List<String>> solveNQueens(int n) {
            List<List<String>> results = new ArrayList<>();
            if (n <= 0) {
                return results;
            }

            search(results, new ArrayList<Integer>(), n);

            return results;
        }

        /*
         * results store all of the chessboards
         * cols store the column indices for each row
         */
        private void search(List<List<String>> results,
                            List<Integer> cols,
                            int n) {
            if (cols.size() == n) {
                results.add(drawChessboard(cols));
                return;
            }

            for (int colIndex = 0; colIndex < n; colIndex++) {
                if (!isValid(cols, colIndex)) {
                    continue;
                }
                cols.add(colIndex);
                search(results, cols, n);
                cols.remove(cols.size() - 1);
            }
        }

        private List<String> drawChessboard(List<Integer> cols) {
            List<String> chessboard = new ArrayList<>();
            for (int i = 0; i < cols.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < cols.size(); j++) {
                    sb.append(j == cols.get(i) ? 'Q' : '.');
                }
                chessboard.add(sb.toString());
            }
            return chessboard;
        }

        private boolean isValid(List<Integer> cols, int column) {
            int row = cols.size();
            for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
                if (cols.get(rowIndex) == column) {
                    return false;
                }
                if (rowIndex + cols.get(rowIndex) == row + column) {
                    return false;
                }
                if (rowIndex - cols.get(rowIndex) == row - column) {
                    return false;
                }
            }
            return true;
        }
    }

/////////////////////////////////////////////////////////////////////////////
}
/*
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

 */

/*
n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。

给定一个整数n，返回所有不同的n皇后问题的解决方案。

每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
您在真实的面试中是否遇到过这个题？
样例

对于4皇后问题存在两种解决的方案：

[

    [".Q..", // Solution 1

     "...Q",

     "Q...",

     "..Q."],

    ["..Q.", // Solution 2

     "Q...",

     "...Q",

     ".Q.."]

]

 */