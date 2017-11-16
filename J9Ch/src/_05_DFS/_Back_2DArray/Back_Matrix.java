package _05_DFS._Back_2DArray;
import org.junit.Test;

import java.util.*;



/*
1.  Sudoku Solver
2.  N-Queens
3.  N-QueensII

其实2D matrix和1D String的模板非常类似，唯一需需要注意的就是
 */
public class Back_Matrix {
//////1.  Sudoku Solver  1.  Sudoku Solver   1.  Sudoku Solver   1.  Sudoku Solver  1.  Sudoku Solver
//  37. Sudoku Solver
//  https://leetcode.com/problems/sudoku-solver/description/
//

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
 */


    /*2.The second one check with board itself.
    The code is neat and simple. Running time is about 252-332ms.
    The time difference between these two version is small. But it's huge in C++ answer.*/

    class Solution3{
        private char[][] b;
        public void solveSudoku(char[][] board) {
            if(board == null || board.length < 9) return;
            b = board;
            solve(0);
        }
        public boolean solve(int index){
            if(index == 81) return true;
            int i = index / 9, j = index % 9;
            if(b[i][j] != '.') return solve(index + 1);
            for(char f = '1'; f <= '9'; f++){
                if(isValidFill(i, j, f)){
                    b[i][j] = f;
                    if(solve(index+1)) return true;
                    b[i][j] = '.';
                }
            }
            return false;
        }
        private boolean isValidFill(int i, int j, char fill){
            for(int k = 0; k < 9; k++){
                int r = i/9 + j/3;
                if(b[i][k] == fill || b[k][j] == fill ||
                        b[r/9 + k/3][r%9 + k%3] == fill)
                    return false;
            }
            return true;
        }

    }


    //还是这个清晰明了，把所有的method都独立做出来
    //这题的关键还不是在于backtracking，而是在于对于2D Matrix的熟练操作
    //在input上进行操作，把所有.的空格填满
    class Solution33{
        private char[][] b;//其实这题就把board设为global挺好
        public void solveSudoku(char[][] board) {
            if(board == null || board.length < 9) return;
            b = board;
            solve(0);
        }
        //每次都是solve一格
        public boolean solve(int index){
            if(index == 81) return true;
            int i = index / 9, j = index % 9;
            //是空格才处理，如果已经填好数字那就做下一格
            if(b[i][j] != '.') return solve(index + 1);
            //对每一格都分别尝试1-9
            for(char f = '1'; f <= '9'; f++){
                if(isValidFill(i, j, f)){
                    b[i][j] = f;
                    if(solve(index+1)) return true;
                    b[i][j] = '.';
                }
            }
            return false;
        }
        //i,j是在大格子里的坐标
        //其实这个对于
        public boolean isValidFill(int i, int j, char fill){
            for(int k = 0; k < 9; k++){
                //r是指第几个九宫格
                int r = i/9 + j/3;   //select the block

                if(b[i][k] == fill || b[k][j] == fill ||
                        //r/9, r%9是小的九宫格的起点，
                        //k/3, k%3是小的九宫格内部的坐标
                        //加在一起就是在大的九宫格的坐标
                        b[r/9 + k/3][r%9 + k%3] == fill)
                    return false; //check row, column, block
            }
            return true;
        }

    }

    @Test
    public void test1_01(){
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%3s",  " " + count++);
            }
            System.out.println();
        }
    }
    /*
          0  1  2  3  4  5  6  7  8
          9 10 11 12 13 14 15 16 17 (i = 1, j = 8, r = 2)
         18 19 20 21 22 23 24 25 26
         27 28 29 30 31 32 33 34 35
         36 37 38 39 40 41 42 43 44
         45 46 47 48 49 50 51 52 53
         54 55 56 57 58 59 60 61 62
         63 64 65 66 67 68 69 70 71
         72 73 74 75 76 77 78 79 80
     */
    @Test
    public void test1_02(){
        int index = 17;
        int i = index / 9, j = index % 9;
        System.out.println(i + ", " + j);

    }


    @Test
    public void test1_04(){
        int count = 0;
        int ch = 'A';
        String[][] b = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                b[i][j] = ""+count++;
                System.out.printf("%3s",  " " + b[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------------");

        char c = 'A';
        int index = 56;
        System.out.println("index: " + index);
//        int i = 1, j = 8;
        int i = index / 9, j = index % 9;
        System.out.println("i: " +i + ", j: " + j);
        int r = i / 3*3 + j / 3;
        System.out.println("r: " + r);
        for(int k = 0; k < 9; k++){
            b[r/9 + k/3][r%9 + k%3] = new String(c++ + "");
//            System.out.println((r/3*3 + k/3) + ", " + (r%3*3 + k%3));
        }
        System.out.println("------------------------------------");
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
//                b[i][j] = count++;
                System.out.printf("%3s",  " " + b[i][j]);
            }
            System.out.println();
        }

        System.out.println("------------------------------------");
        System.out.println("r:");
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
//                b[i][j] = count++;
                r = i / 3*3 + j / 3;
                System.out.printf("%3s",  " " + r);
            }
            System.out.println();
        }
        System.out.println("------------------------------------");

        /*
  0  1  2  3  4  5  6  7  8
  9 10 11 12 13 14 15 16 17
 18 19 20 21 22 23 24 25 26
 27 28 29 30 31 32 33 34 35
 36 37 38 39 40 41 42 43 44
 45 46 47 48 49 50 51 52 53
 54 55 56 57 58 59 60 61 62
 63 64 65 66 67 68 69 70 71
 72 73 74 75 76 77 78 79 80
------------------------------------
index: 56
i: 6, j: 2
r: 6
------------------------------------
  0  1  2  3  4  5  A  B  C
  9 10 11 12 13 14  D  E  F
 18 19 20 21 22 23  G  H  I
 27 28 29 30 31 32 33 34 35
 36 37 38 39 40 41 42 43 44
 45 46 47 48 49 50 51 52 53
 54 55 56 57 58 59 60 61 62
 63 64 65 66 67 68 69 70 71
 72 73 74 75 76 77 78 79 80
------------------------------------
r:
  0  0  0  1  1  1  2  2  2
  0  0  0  1  1  1  2  2  2
  0  0  0  1  1  1  2  2  2
  3  3  3  4  4  4  5  5  5
  3  3  3  4  4  4  5  5  5
  3  3  3  4  4  4  5  5  5
  6  6  6  7  7  7  8  8  8
  6  6  6  7  7  7  8  8  8
  6  6  6  7  7  7  8  8  8
------------------------------------

         */

    }
//////1.  Sudoku Solver  1.  Sudoku Solver   1.  Sudoku Solver   1.  Sudoku Solver  1.  Sudoku Solver

//////2. N-Queens   2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens
//  51. N-Queens
//  https://leetcode.com/problems/n-queens/description/
//  http://www.lintcode.com/zh-cn/problem/n-queens/

/*
n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。

给定一个整数n，返回所有不同的n皇后问题的解决方案。

每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。

对于4皇后问题存在两种解决的方案：
        ..Q.
        Q...
        ...Q
        .Q..

        .Q..
        ...Q
        Q...
        ..Q.
 */

    //    My easy understanding Java Solution
    public class NQueens {
        char[][] board2;
        char[][] board3;
        public List<List<String>> solveNQueens(int n) {
            board2 = new char[n][n];
            board3 = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board2[i][j] = '.';
                    board3[i][j] = '+';

                }
            }

            char[][] board = new char[n][n];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    board[i][j] = '.';
            List<List<String>> result = new ArrayList<List<String>>();
            dfs(board, 0, result);
            return result;
        }

        private void dfs(char[][] board, int colIndex, List<List<String>> result) {
            if(colIndex == board.length) {
                result.add(construct(board));
                return;
            }

            for(int i = 0; i < board.length; i++) {
                if(!validate(board, i, colIndex)) continue;
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, result);
                board[i][colIndex] = '.';
            }
        }

        private boolean validate(char[][] board, int x, int y) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < y; j++) { //    \                   /            __
                    if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i)){
                        return false;
                    }

                }
            }
            return true;
        }

        private boolean validate2(char[][] board, int x, int y) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < y; j++) { //看对角线
                    if(board[i][j] == 'Q' && (Math.abs(x - i) == Math.abs(y - j) || x == i)){
                        return false;
                    }

                }
            }
            return true;
        }

        //convert char[][] board to List<String>
        private List<String> construct(char[][] board) {
            List<String> list = new LinkedList<String>();
            for(int i = 0; i < board.length; i++) {
                String s = new String(board[i]);//board中一行char  char[] -> String
                list.add(s);
            }
            return list;//一个board
        }
    }


    void print(char[][] board2){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board2[i][j] + " ");;
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

    @Test
    public void testNQueens_02(){
        NQueens nq = new NQueens();
        for (List list:nq.solveNQueens(4)) {
            for (Object str : list) {
                System.out.println(str + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(nq.board2[i][j] + " ");;
            }
            System.out.println();
        }
    }


    @Test
    public void testNQueens_03(){
        char[][] board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = '.';
            }
        }
        go(board,1,2);
    }

    private void go(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
                if(x + j == y + i) {
                    System.out.println("x + j == y + i");
                    System.out.println("x:" + x +"j:"+j+ "i:"+i+ "y:" + y );
                    board[x][y] = 'A';
                    board[i][j] = 'X';
                    print(board);
                }else
                if (x + y == i + j) {
                    System.out.println("x + y == i + j");
                    System.out.println("x:" + x + "y:" + y + "i:"+i+"j:"+j);
                    board[x][y] = 'B';
                    board[i][j] = 'Y';
                    print(board);
                }else
                if (x == i) {
                    System.out.println("x == i");
                    System.out.println("x:" + x + "i:"+i);
                    board[x][y] = 'C';
                    board[i][j] = 'Z';
                    print(board);
                }

            }
        }
    }

    /*
x + j == y + i
x:1j:1i:0y:2
. X . .
. . A .
. . . .
. . . .
------------------------------
x == i
x:1i:1
. X . .
Z . C .
. . . .
. . . .
------------------------------
x == i
x:1i:1
. X . .
Z Z C .
. . . .
. . . .
------------------------------
x + y == i + j
x:1y:2i:2j:1
. X . .
Z Z B .
. Y . .
. . . .
------------------------------
x + y == i + j
x:1y:2i:3j:0
. X . .
Z Z B .
. Y . .
Y . . .
------------------------------
     */

//////2. N-Queens   2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens 2. N-Queens


//////3.N-Queens II     3.N-Queens II   3.N-Queens II   3.N-Queens II   3.N-Queens II

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
 */


/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
 */


    //jiuzhang
    public class JiuzhangN_Queens_II  {
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

//////3.N-Queens II     3.N-Queens II   3.N-Queens II   3.N-Queens II   3.N-Queens II


////////////////////////////////////////////////////////////////////////////



}
