package J_2_BinarySearch;

//Binary Search Once
public class _28Search_a_2D_Matrix_Once {
	public boolean searchMatrix(int[][] matrix, int target) {
	   if (matrix == null || matrix.length == 0) {
	       return false;
	   }
	   if (matrix[0] == null || matrix[0].length == 0) {
	       return false;
	   }
	   
	   int row = matrix.length, column = matrix[0].length;
	   int start = 0, end = row * column - 1;
	   
	   while (start + 1 < end) {
	       int mid = start + (end - start) / 2;
	       int number = matrix[mid / column][mid % column];
	       if (number == target) {
	           return true;
	       } else if (number < target) {
	           start = mid;
	       } else {
	           end = mid;
	       }
	   }
	   
	   if (matrix[start / column][start % column] == target) {
	       return true;
	   } else if (matrix[end / column][end % column] == target) {
	       return true;
	   }
	   
	   return false;
	}
	
	public boolean searchMatrix2(int[][] matrix, int target){
		 if (matrix == null || matrix.length == 0){
			 return false;
		 }
		 
		 int n = matrix.length;
		 
		 if(matrix[0] == null || matrix[0].length == 0){
			 return false;
		 }
		 
		 int m = matrix[0].length;
		 int start = 0, end = n * m - 1;
		 
		 while (start + 1 < end){
			 int mid = start + (end - start) / 2;
			 int row = mid / m;
			 int col = mid % m;
			 if (matrix[row][col] < target){
				 start = mid;
			 } else{
				 end = mid;
			 }
		 }
		 
		 if(matrix[start / m][start % m] == target){
			 return true;
		 }
		 if(matrix[end / m][end % m] == target){
			 return true;
		 }
		 return false;
	}
}

