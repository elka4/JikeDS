package Ch_10_Sorting_and_Searching.Q10_09_Sorted_Matrix_Search;

import CtCILibrary.AssortedMethods;

public class QuestionA {

	public static boolean findElement(int[][] matrix, int elem) {
		int row = 0;
		int col = matrix[0].length - 1; 
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == elem) {
				return true;
			} else if (matrix[row][col] > elem) {
				col--;
			} else {
				row++; 
			}
		} 
		return false; 
	}
	
	public static void main(String[] args) {
		int M = 10;
		int N = 5;
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = 10 * i + j;
			}
		}
		
		AssortedMethods.printMatrix(matrix);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int v = 10 * i + j;
				System.out.println(v + ": " + findElement(matrix, v));
			}
		}
		
	}
/*
                        0    1    2    3    4
                       10   11   12   13   14
                       20   21   22   23   24
                       30   31   32   33   34
                       40   41   42   43   44
                       50   51   52   53   54
                       60   61   62   63   64
                       70   71   72   73   74
                       80   81   82   83   84
                       90   91   92   93   94
                    0: true
                    1: true
                    2: true
                    3: true
                    4: true
                    5: false
                    6: false
                    7: false
                    8: false
                    9: false
                    10: true
                    11: true
                    12: true
                    13: true
                    14: true
                    15: false
                    16: false
                    17: false
                    18: false
                    19: false
                    20: true
                    21: true
                    22: true
                    23: true
                    24: true
                    25: false
                    26: false
                    27: false
                    28: false
                    29: false
                    30: true
                    31: true
                    32: true
                    33: true
                    34: true
                    35: false
                    36: false
                    37: false
                    38: false
                    39: false
                    40: true
                    41: true
                    42: true
                    43: true
                    44: true
                    45: false
                    46: false
                    47: false
                    48: false
                    49: false
                    50: true
                    51: true
                    52: true
                    53: true
                    54: true
                    55: false
                    56: false
                    57: false
                    58: false
                    59: false
                    60: true
                    61: true
                    62: true
                    63: true
                    64: true
                    65: false
                    66: false
                    67: false
                    68: false
                    69: false
                    70: true
                    71: true
                    72: true
                    73: true
                    74: true
                    75: false
                    76: false
                    77: false
                    78: false
                    79: false
                    80: true
                    81: true
                    82: true
                    83: true
                    84: true
                    85: false
                    86: false
                    87: false
                    88: false
                    89: false
                    90: true
                    91: true
                    92: true
                    93: true
                    94: true
                    95: false
                    96: false
                    97: false
                    98: false
                    99: false
 */
}
