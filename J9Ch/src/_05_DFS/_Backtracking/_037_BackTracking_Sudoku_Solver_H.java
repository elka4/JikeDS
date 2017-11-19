package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _037_BackTracking_Sudoku_Solver_H {
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

    //jiuzhang
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
}
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
 */