package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _004_BinarySearch_Median_of_Two_Sorted_Arrays_H {
//Approach #1 Recursive Approach [Accepted]

    // Topological Sort Based Solution // An Alternative Solution
    public class Solution {
        private final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        private int m, n;

        public int longestIncreasingPath(int[][] grid) {
            int m = grid.length;
            if (m == 0) return 0;
            int n = grid[0].length;
            // padding the matrix with zero as boundaries
            // assuming all positive integer, otherwise use INT_MIN as boundaries
            int[][] matrix = new int[m + 2][n + 2];
            for (int i = 0; i < m; ++i)
                System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);
            // calculate outdegrees
            int[][] outdegree = new int[m + 2][n + 2];
            for (int i = 1; i <= m; ++i)
                for (int j = 1; j <= n; ++j)
                    for (int[] d : dir)
                        if (matrix[i][j] < matrix[i + d[0]][j + d[1]]) outdegree[i][j]++;
            // find leaves who have zero out degree as the initial level n += 2; m += 2;
            List<int[]> leaves = new ArrayList<>();
            for (int i = 1; i < m - 1; ++i)
                for (int j = 1; j < n - 1; ++j)
                    if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});
            // remove leaves level by level in topological order
            int height = 0;
            while (!leaves.isEmpty()) {
                height++;
                List<int[]> newLeaves = new ArrayList<>();
                for (int[] node : leaves) {
                    for (int[] d : dir) {
                        int x = node[0] + d[0], y = node[1] + d[1];
                        if (matrix[node[0]][node[1]] > matrix[x][y])
                            if (--outdegree[x][y] == 0)
                                newLeaves.add(new int[]{x, y});
                    }
                }
                leaves = newLeaves;
            }
            return height;
        }
    }
/////////////////////////////////////////////////////////////////////////////
    //Jiuzhang
public class Jiuzhang {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        return (
                findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)
        ) / 2.0;
    }

    // find kth number of two sorted array
    public int findKth(int[] A, int A_start,
                              int[] B, int B_start,
                              int k){
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = A_start + k / 2 - 1 < A.length
                ? A[A_start + k / 2 - 1]
                : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length
                ? B[B_start + k / 2 - 1]
                : Integer.MAX_VALUE;

        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
}
}
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */