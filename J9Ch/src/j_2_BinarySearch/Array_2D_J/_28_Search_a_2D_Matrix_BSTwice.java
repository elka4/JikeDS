package j_2_BinarySearch.Array_2D_J;

//Search a 2D Matrix

//Binary Search Twice
public class _28_Search_a_2D_Matrix_BSTwice {


//------------------------------------------------------------------------------//
 // 9Ch
 public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
        return false;
    }
    if (matrix[0] == null || matrix[0].length == 0) {
        return false;
    }
    
    int row = matrix.length;
    int column = matrix[0].length;
    
    // find the row index, the last number <= target 
    int start = 0, end = row - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (matrix[mid][0] == target) {
            return true;
        } else if (matrix[mid][0] < target) {
            start = mid;
        } else {
            end = mid;
        }
    }
    //这两个判断顺序不能变！！！为什么呢？
    //因为以上循环的结果是判断出，如果target在这个二维数组中，一定是在这两行中
    //[start][0]或者[end][0]，应该先判断target是否在后一行
    //因为如果target在这个二维数组中，
    //第一行第一个元素[start][0]肯定是满足条件matrix[start][0] <= target的
    //这样的话如果target是在第二行[end][0]，就会判断错行数
    if (matrix[end][0] <= target) {
        row = end;
    } else if (matrix[start][0] <= target) {
        row = start;
    } else {
        return false;
    }
    
    // find the column index, the number equal to target
    start = 0;
    end = column - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (matrix[row][mid] == target) {
            return true;
        } else if (matrix[row][mid] < target) {
            start = mid;
        } else {
            end = mid;
        }
    }
    //这两行的顺序没有关系，因为循环结束就是两个数，不是这个就是那个，要么都不是
    if (matrix[row][start] == target) {
        return true;
    } else if (matrix[row][end] == target) {
        return true;
    }
    return false;
 }
}

/*
Write an efficient algorithm that searches for a value 
in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integerof each row is greater than the last integer of the previous row.
 
Example: Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]

Given target = 3, return true.

Challenge: O(log(n) + log(m)) time

Tags: 
Binary Search Matrix
Related Problems 
Medium Search in Rotated Sorted Array 29 %
Medium Search a 2D Matrix II 33 %
 * 
 * */
