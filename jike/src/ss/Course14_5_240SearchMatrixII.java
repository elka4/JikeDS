package ss;

import org.junit.Test;

public class Course14_5_240SearchMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		} else {
			int m = matrix.length;
			int n = matrix[0].length;
			int row = 0;
			int col = n - 1;
			while (row < m && col >= 0) {
				if (matrix[row][col] == target) {
					return true;
				} else if (target < matrix[row][col]) {
					col--;
				} else {
					row++;
				}
			}
			return false;
		}
	}
	@Test
	public void test() {
		int[][] matrix = {
				{1, 4, 7, 11, 15}, 
				{2, 5, 8, 12, 19},
				{3, 6, 9, 16, 22}, 
				{10, 13, 14, 17, 24}, 
				{18, 21, 23, 26, 30}};
		System.out.println(searchMatrix(matrix, 1));
		System.out.println(searchMatrix(matrix, 30));
		System.out.println(searchMatrix(matrix, 10));
		System.out.println(searchMatrix(matrix, 20));
		System.out.println(searchMatrix(matrix, 0));
		System.out.println(searchMatrix(matrix, 31));
	}
}
