package _5_DFS.HF4_Search_DFS;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//N-Queens
public class _8N_Queens {
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

    @Test
    public void test01(){
        List<List<String>> result = solveNQueens(4);
        for (List list:result) {
            for (Object str:list) {
                System.out.println((String)str);
            }
            System.out.println();
        }
    }
}
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
For example, There exist two distinct solutions to the 4-queens puzzle:
[ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
 */
