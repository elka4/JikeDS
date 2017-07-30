package Ch_01_Arrays_and_Strings.Q1_07_Rotate_Matrix;
import CtCILibrary.AssortedMethods;

/*
Rotate Matrix: Given an image represented by an NxN matrix,
where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
 (an you do this in place?
 */
/*
    5    2    6
    4    8    0
    5    5    0

    5    4    5
    5    8    2
    0    0    6
 */

public class Question {

	public static boolean rotate(int[][] matrix) {
        // Not a square
		if (matrix.length == 0 || matrix.length != matrix[0].length)
		    return false;

		int n = matrix.length;
		
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;


			for(int i = first; i < last; i++) {
				int offset = i - first;
				int top = matrix[first][i]; // save top

				// left -> top
				matrix[first][i] = matrix[last-offset][first]; 			

				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset]; 

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}

}
