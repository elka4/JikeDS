package top100.Enhance._2Tree_Class;

import java.util.*;

public class _2_SpiralMatrix {

public List<Integer> spiralOrder(int[][] matrix) {
        //Using recursion: print all boundary elem in spiral order in each level, using boundary size to move into next level
        List<Integer> res = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return res;
        if(matrix[0] == null || matrix[0].length == 0)
            return res;

        int row = matrix.length;
        int col = matrix[0].length;
        helper(res, matrix, row, col, 0);
        return res;
}

private void helper(List<Integer> res, int[][] m, int row, int col, int offset) {
        //Base Cases:
        if(row == 0 || col == 0)
            return;

        if(row == 1) {
            for(int i = offset; i < col + offset; i++) {
                res.add(m[offset][i]);
            }
            return;
        }

        if(col == 1) {
            for(int i = offset; i < row + offset; i++) {
                res.add(m[i][offset]);
            }
            return;
        }

        //1. up row
        for(int i = offset; i < col - 1 + offset; i++) {
            res.add(m[offset][i]);
        }
        //2. right col
        for(int i = offset; i < row - 1 + offset; i++) {
            res.add(m[i][col - 1 + offset]);
        }
        //3. down row
        for(int i = col - 1 + offset; i > offset; i--) {
            res.add(m[row - 1 + offset][i]);
        }
        //4. left col
        for(int i = row - 1 + offset; i > offset; i--) {
            res.add(m[i][offset]);
        }

        //Next level
        helper(res, m, row - 2, col - 2, offset + 1);
    }
}