package _10_DS._Queue;
import java.util.*;

public class _363_Max_Sum_of_Rectangle_No_Larger_Than_K {
//    Java Binary Search solution time complexity min(m,n)^2*max(m,n)*log(max(m,n))
    /* first  consider the situation matrix is 1D
        we can save every sum of 0~i(0<=i<len) and binary search previous sum to find
        possible result for every index, time complexity is O(NlogN).
        so in 2D matrix, we can sum up all values from row i to row j and create a 1D array
        to use 1D array solution.
        If col number is less than row number, we can sum up all values from col i to col j
        then use 1D array solution.
    */
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0)return 0;
        int col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<m;i++){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i;j>=0;j--){
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for(int k = 0;k<n;k++){
                    array[k]=array[k]+(colIsBig?matrix[j][k]:matrix[k][j]);
                    val = val + array[k];
                    //use  TreeMap to binary search previous sum to get possible result
                    Integer subres = set.ceiling(val-target);
                    if(null!=subres){
                        res=Math.max(res,val-subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }



/*    2 Accepted Java Solution
    Decide to post because I was actually asked this question during my interview!
    There is a simple version of O(n^4).
    The idea is to calculate every rectangle [[r1,c1], [r2,c2]], and simply pick the max area <= k.
    An improved version takes O(n^3logn). It borrows the idea to find max subarray with sum <= k in 1D array, and apply here: we find all rectangles bounded between r1 & r2, with columns from 0 to end. Pick a pair from tree.
    I remember the interviewer said there could be an even better solution, but I haven't figured that out...

    Solution I, O(n^4):*/

    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] areas = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int area = matrix[r][c];
                if (r-1 >= 0)
                    area += areas[r-1][c];
                if (c-1 >= 0)
                    area += areas[r][c-1];
                if (r-1 >= 0 && c-1 >= 0)
                    area -= areas[r-1][c-1];
                areas[r][c] = area;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                for (int r2 = r1; r2 < rows; r2++) {
                    for (int c2 = c1; c2 < cols; c2++) {
                        int area = areas[r2][c2];
                        if (r1-1 >= 0)
                            area -= areas[r1-1][c2];
                        if (c1-1 >= 0)
                            area -= areas[r2][c1-1];
                        if (r1-1 >= 0 && c1 -1 >= 0)
                            area += areas[r1-1][c1-1];
                        if (area <= k)
                            max = Math.max(max, area);
                    }
                }
            }
        }
        return max;
    }
//    Solution II (O(n^3logn)

    public int maxSumSubmatrix3(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] areas = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int area = matrix[r][c];
                if (r-1 >= 0)
                    area += areas[r-1][c];
                if (c-1 >= 0)
                    area += areas[r][c-1];
                if (r-1 >= 0 && c-1 >= 0)
                    area -= areas[r-1][c-1];
                areas[r][c] = area;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < rows; r1++) {
            for (int r2 = r1; r2 < rows; r2++) {
                TreeSet<Integer> tree = new TreeSet<>();
                tree.add(0);    // padding
                for (int c = 0; c < cols; c++) {
                    int area = areas[r2][c];
                    if (r1-1 >= 0)
                        area -= areas[r1-1][c];
                    Integer ceiling = tree.ceiling(area - k);
                    if (ceiling != null)
                        max = Math.max(max, area - ceiling);
                    tree.add(area);
                }
            }
        }
        return max;
    }
}
/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

 */