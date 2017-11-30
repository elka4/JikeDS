package _05_DFS._Back_2DArray;
import java.util.*;

//  37. Sudoku Solver
//  https://leetcode.com/problems/sudoku-solver/description/
//
public class _037_Sudoku_Solver_H {
    //Straight Forward Java Solution Using Backtracking
    public class Solution {
        public void solveSudoku(char[][] board) {
            if(board == null || board.length == 0)
                return;
            solve(board);
        }

        public boolean solve(char[][] board){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    if(board[i][j] == '.'){
                        for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                            if(isValid(board, i, j, c)){
                                board[i][j] = c; //Put c for this cell

                                if(solve(board))
                                    return true; //If it's the solution return true
                                else
                                    board[i][j] = '.'; //Otherwise go back
                            }
                        }

                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValid(char[][] board, int row, int col, char c){
            for(int i = 0; i < 9; i++) {
                if(board[i][col] != '.' && board[i][col] == c) return false; //check row
                if(board[row][i] != '.' && board[row][i] == c) return false; //check column
                if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                        board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
            }
            return true;
        }
    }

//-------------------------------------------------------------------------
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


//-------------------------------------------------------------------------

    // 9Ch
    public class Jiuzhang {
        public void solveSudoku(char[][] board){
            solve(board);
        }

        public boolean solve(char[][] board) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++){
                    if(board[i][j] != '.'){
                        continue;
                    }
                    for(int k = 1; k <= 9; k++){
                        board[i][j] = (char) (k + '0');
                        if (isValid(board, i, j) && solve(board)){
                            return true;
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
            return true;
        }


        public boolean isValid(char[][] board, int a, int b){
            Set<Character> contained = new HashSet<Character>();
            for(int j=0;j<9;j++){
                if(contained.contains(board[a][j])) return false;
                if(board[a][j]>'0' && board[a][j]<='9')
                    contained.add(board[a][j]);
            }



            contained = new HashSet<Character>();
            for(int j=0;j<9;j++){
                if (contained.contains(board[j][b])) {
                    return false;
                }
                if (board[j][b]>'0' && board[j][b]<='9') {
                    contained.add(board[j][b]);
                }
            }


            contained = new HashSet<Character>();
            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++){
                    int x = a / 3 * 3 + m, y = b / 3 * 3 + n;
                    if (contained.contains(board[x][y])) {
                        return false;
                    }
                    if (board[x][y] > '0' && board[x][y] <= '9') {
                        contained.add(board[x][y]);
                    }
                }
            }

            return true;
        }
    }

//-------------------------------------------------------------------------
}
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
 */