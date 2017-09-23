package HF.HF5_Math_Graphic_Bit._1Matrix;

import java.util.*;

public class _3SparseMatrixMultiplication {


    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = A[0].length;
        int k = B[0].length;

        int[][] C = new int[n][k];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (A[i][j] != 0)
                    for (int l = 0; l < k; ++l)
                        C[i][l] += A[i][j] * B[j][l];
        return C;
    }
//////////////////////////////////////////////////////////////////////
    // version: 高频题班
//常规做法
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply2(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < t; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

////////////////////////////////////////////////////////////////////

    //改进做法

    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply3(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < m; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

////////////////////////////////////////////////////////////////////

    //进一步改进
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply4(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        List<List<Integer>> val = new ArrayList<>();
        List<List<Integer>> col = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            val.add(new ArrayList<>());
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                    val.get(i).add(B[i][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] != 0) {
                    for (int p = 0; p < col.get(k).size(); p++) {
                        int j = col.get(k).get(p);
                        int v = val.get(k).get(p);
                        C[i][j] += A[i][k] * v;
                    }
                }
            }
        }
        return C;
    }
}
