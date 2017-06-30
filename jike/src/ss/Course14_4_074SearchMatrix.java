package ss;

import org.junit.Test;

public class Course14_4_074SearchMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int row = searchRow(matrix, m, n, target);
		if (row >= m || row < 0) {
			return false;
		} else {
			return searchCol(matrix, row, n, target);
		}
	}
	private int searchRow(int[][] matrix, int m, int n, int target) {
		int start = 0, end = m - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (target == matrix[mid][n - 1]) {
				return mid;
			} else if (target < matrix[mid][n - 1]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	private boolean searchCol(int[][] matrix, int row, int n, int target) {
		int start = 0, end = n - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (target == matrix[row][mid]) {
				return true;
			} else if (target < matrix[row][mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false;
	}
	@Test
	public void test() {
		int[][] matrix = {
				{11, 13, 15, 17}, 
				{19, 21, 23, 25}, 
				{27, 29, 31, 33},
				{35, 37, 39, 41}, 
				{43, 45, 47, 49}};
		System.out.println(searchMatrix(matrix, 11));
		System.out.println(searchMatrix(matrix, 49));
		System.out.println(searchMatrix(matrix, 31));
		System.out.println(searchMatrix(matrix, 30));
		System.out.println(searchMatrix(matrix, 10));
		System.out.println(searchMatrix(matrix, 51));
	}
}
