package top100.Enhance._4DP_Class;

public class _8_RangeSumQuery_Mutable2D {
	private int[][] bit;
    private int[][] m;
    public _8_RangeSumQuery_Mutable2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        this.bit = new int[matrix.length + 1][matrix[0].length + 1];
        this.m = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                init(i, j, matrix[i][j]);
            }
        }
    }
    private void init(int row, int col, int val) {
        for (int i = row + 1; i <= m.length; i += i & (-i)) {
            for (int j = col + 1; j <= m[0].length; j += j & (-j)) {
                bit[i][j] += val;
            }
        }
    }
    public void update(int row, int col, int val) {
        int diff = val - m[row][col];
        m[row][col] = val;
        init(row, col, diff);
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }
    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                sum += bit[i][j];
            }
        }
        return sum;
    }
}
