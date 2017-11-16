package _05_DFS._Back_2DArray;
import java.util.*;



/*
1.  Sudoku Solver
2.  N-Queens
3.  N-QueensII

 */
public class Back_Matrix {
////////////////////////////////////////////////////////////////////////////
//  37. Sudoku Solver
//  https://leetcode.com/problems/sudoku-solver/description/
//

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
 */

    /*Two very Simple and Neat Java DFS/Backtracking solutions

    11
        M mingjun
        Reputation:  503
                1.The first one uses three 2D-array to check valid. Running time is about 256-320ms.*/
    class Solution2{
        private char[][] b;
        private boolean[][] row = new boolean[9][9];
        private boolean[][] col = new boolean[9][9];
        private boolean[][] block = new boolean[9][9];
        public void solveSudoku(char[][] board) {
            b = board;
            int num, k;
            for (int i=0; i<9; i++) {
                for (int j=0; j<9; j++) {
                    if(board[i][j]!='.') {
                        num = board[i][j]-'1';
                        k = i/3*3 + j/3;
                        row[i][num] = col[j][num] = block[k][num] = true;
                    }
                }
            }
            Helper(0);
        }
        public boolean Helper(int ind){
            if(ind==81) return true;
            int i=ind/9, j=ind%9, num, k;
            if(b[i][j]!='.') return Helper(ind+1);
            else{
                for(char f='1'; f<='9'; f++){
                    num = f-'1';
                    k= i/3*3 + j/3;
                    if(!row[i][num] && !col[j][num] && !block[k][num]){
                        b[i][j]= f;
                        row[i][num] = col[j][num] = block[k][num] = true;
                        if(Helper(ind+1)) return true;
                        b[i][j]='.';
                        row[i][num] = col[j][num] = block[k][num] = false;
                    }
                }
                return false;
            }
        }
    }

    /*2.The second one check with board itself. The code is neat and simple. Running time is about 252-332ms. The time difference between these two version is small. But it's huge in C++ answer.*/
    class Solution3{
        private char[][] b;
        public void solveSudoku(char[][] board) {
            if(board == null || board.length < 9) return;
            b = board;
            solve(0);
        }
        public boolean solve(int ind){
            if(ind==81) return true;
            int i=ind/9, j=ind%9;
            if(b[i][j]!='.') return solve(ind+1);
            else{
                for(char f = '1'; f <= '9'; f++){
                    if(isValidFill(i, j, f)){
                        b[i][j]= f;
                        if(solve(ind+1)) return true;
                        b[i][j]='.';
                    }
                }
                return false;
            }
        }
        public boolean isValidFill(int i, int j, char fill){
            for(int k=0; k<9; k++){
                int r= i/3*3+j/3;   //select the block
                if(b[i][k]==fill || b[k][j]==fill || b[r/3*3+k/3][r%3*3+k%3]==fill)
                    return false; //check row, column, block
            }
            return true;
        }

    }
////////////////////////////////////////////////////////////////////////////
//  51. N-Queens
//  https://leetcode.com/problems/n-queens/description/
//  http://www.lintcode.com/zh-cn/problem/n-queens/

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

    //    My easy understanding Java Solution
    public class NQueens {
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


////////////////////////////////////////////////////////////////////////////

//  52. N-Queens II
//  https://leetcode.com/problems/n-queens-ii/description/
//  http://www.lintcode.com/zh-cn/problem/n-queens-ii/



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
////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////



}
