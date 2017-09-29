package HF.HF5_Math_Graphic_Bit_1Matrix;

import org.junit.Test;

public class _2RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int length = matrix.length;

        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < (length + 1) / 2; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length -j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = tmp;
            }
        }
    }

    private void print(int[][] matrix){
        for (int[] i:matrix
                ) {
            for (int j:i
                    ) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    @Test
    public void test01(){
        int[][] matrix = {{1,2}, {3,4}};
        print(matrix);
        rotate(matrix);
        print(matrix);
    }

/////////////////////////////////////////////////////////////////////////////

    // version: 高频题班
//方法一

    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    public void rotate2(int[][] matrix) {
        // write your code here
        int n = matrix.length;
        for (int r = 0; r < (n + 1) / 2; r++) {
            for (int c = 0; c < n / 2; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[n - 1 - c][r];
                matrix[n - 1 - c][r] = matrix[n - 1 - r][n - 1 - c];
                matrix[n - 1 - r][n - 1 - c] = matrix[c][n - 1 - r];
                matrix[c][n - 1 - r] = tmp;
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////
    //方法二：

    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    public void rotate3(int[][] matrix) {
        // write your code here
        int n = matrix.length;
        for (int r = 0; r < n / 2; r++) {
            for (int c = 0; c < n; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[n - r - 1][c];
                matrix[n - r - 1][c] = tmp;
            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = r; c < n; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = tmp;
            }
        }
    }
}

/*
给定一个N×N的二维矩阵表示图像，90度顺时针旋转图像。



样例
给出一个矩形[[1,2],[3,4]]，90度顺时针旋转后，返回[[3,1],[4,2]]
 */
