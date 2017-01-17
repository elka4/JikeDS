package top100.Enhance._2Tree_Class;

public class _2_SpiralMatrix_II {
	public int[][] generateMatrix(int n) {
        if(n <= 0)
            return new int[0][0];

        int[] cur = {0};
        int[][] res = new int[n][n];
        helper(res, cur, n, 0);
        return res;
	}
	private void helper(int[][] res, int[] cur, int size, int offset) {
        //Base cases:
        if(size == 0)
            return;
        if(size == 1) {
            res[offset][offset] = ++cur[0];
            return;
        }

        //1. first row
        for(int i = offset; i < size - 1 + offset; i++) {
            res[offset][i] = ++cur[0];
        }
        //2. right col
        for(int i = offset; i < size - 1 + offset; i++) {
            res[i][size - 1 + offset] = ++cur[0];
        }
        //3. last row
        for(int i = size - 1 + offset; i > offset; i--) {
            res[size - 1 + offset][i] = ++cur[0];
        }
        //4. left col
        for(int i = size - 1 + offset; i > offset; i--) {
            res[i][offset] = ++cur[0];
        }

        //Next level
        helper(res, cur, size - 2, offset + 1);
	}
}
