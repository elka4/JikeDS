package _1_Array.TreeSet;

import java.util.TreeSet;
/*
LeetCode – Max Sum of Rectangle No Larger Than K (Java)

Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is
the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Analysis

We can solve this problem by comparing each submatrix. This method is trivial and
we need a better solution. The key to the optimal solution is using a tree set to
calculate the maximum sum of subarray close to k.
 */


public class Max_Sum_of_Rectangle_No_Larger_Than_K {

    //Java Solution 1
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;

        int m=matrix.length;
        int n=matrix[0].length;

        int result = Integer.MIN_VALUE;

        for(int c1=0; c1<n; c1++){
            int[] each = new int[m];
            for(int c2=c1; c2>=0; c2--){

                for(int r=0; r<m; r++){
                    each[r]+=matrix[r][c2];
                }

                result = Math.max(result, getLargestSumCloseToK(each, k));
            }
        }

        return result;
    }


    public int getLargestSumCloseToK(int[] arr, int k){
        int sum=0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        int result=Integer.MIN_VALUE;
        set.add(0);

        for(int i=0; i<arr.length; i++){
            sum=sum+arr[i];

            Integer ceiling = set.ceiling(sum-k);
            if(ceiling!=null){
                result = Math.max(result, sum-ceiling);
            }

            set.add(sum);
        }

        return result;
    }

    /*    The time complexity is O(n*n*m*log(m)). If m is greater than n, this solution
    is fine. However, if m is less than n, then this solution is not optimal.
    In this case,  we should reverse the row and column, like Solution 2.*/



    //Java Solution 2
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if(matrix==null||matrix.length==0||matrix[0].length==0)
            return 0;

        int row=matrix.length;
        int col=matrix[0].length;

        int m = Math.max(row, col);
        int n = Math.min(row, col);
        boolean isRowLarger = false;
        if(row>col)
            isRowLarger=true;

        int result = Integer.MIN_VALUE;

        for(int c1=0; c1<n; c1++){

            int[] each = new int[m];
            for(int c2=c1; c2>=0; c2--){

                for(int r=0; r<m; r++){
                    each[r]+=isRowLarger?matrix[r][c2]:matrix[c2][r];
                }

                result = Math.max(result, getLargestSumCloseToK2(each, k));
            }
        }

        return result;
    }


    public int getLargestSumCloseToK2(int[] arr, int k){
        int sum=0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        int result=Integer.MIN_VALUE;
        set.add(0);

        for(int i=0; i<arr.length; i++){
            sum=sum+arr[i];

            Integer ceiling = set.ceiling(sum-k);
            if(ceiling!=null){
                result = Math.max(result, sum-ceiling);
            }

            set.add(sum);
        }

        return result;
    }


////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////






}
