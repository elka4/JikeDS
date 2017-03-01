package top100.Enhance._2Tree_PreClass;

/**
 * Created by tzh on 1/27/17.
 */@SuppressWarnings("all")

public class _3_BinarySearch_2Medium_Search2DMatrix {
    public boolean searchMatrixI(int[][] matrix, int target){
        if(matrix == null || matrix.length == 0){
            return false;
        }

        if(matrix[0] == null || matrix[0].length == 0){
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n -1;

        while(end >= start){
            int mid = start + (end - start) / 2;
            if(matrix[mid / n][mid % n] == target){
                return true;
            } else if (matrix[mid / n][mid % n] > target){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }
        return false;
    }

    //2D MatrixII
    //method
    public boolean searchMatrixII_1(int[][] matrix, int target){
        //corner case checked
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearch(matrix, target, 0, 0, m - 1, n - 1);
    }
    private boolean binarySearch(int[][] matrix, int target, int startX, int startY, int endX, int endY){
        if(startX > endX || startY > endY){
            return  false;
        }
        int midX = startX + (endX - startX) / 2;
        int midY = startY + (endY - startY) / 2;

        if(matrix[midX][midY] == target){
            return true;
        } else if (matrix[midX][midY] > target){//Case2: larger than target, go into left or up submatrix
            return binarySearch(matrix, target, startX, startY, endX, midY - 1) ||
                    binarySearch(matrix, target, startX, midY, midX - 1, endY);
        } else {//case3: smaller than target, go into right or down submatrix
            return binarySearch(matrix, target, startX, midY + 1, endX, endY) ||
                    binarySearch(matrix, target, midX + 1, startY, endX, midY);
        }
    }

    //method2   Time: O（m+n）
    public boolean searchMatrixII_2(int[][] matrix, int target){
        if(matrix == null){
            return false;
        }
        int row = matrix.length;
        if(row == 0 || matrix[0] == null){
            return false;
        }
        int col = matrix[0].length;
        if(col == 0){
            return  false;
        }

        //start from the top-right point
        int curRow = 0;
        int curCol = col - 1;

        while(curRow < row && curCol >= 0){
            if (matrix[curRow][curCol] > target){//larger than target, go left
                curCol--;
            } else if (matrix[curRow][curCol] < target){//smaller than target, go right
                curRow++;
            } else {
                return true;
            }
        }
        return false;
    }


}
