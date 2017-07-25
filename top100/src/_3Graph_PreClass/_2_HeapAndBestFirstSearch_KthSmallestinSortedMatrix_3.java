package _3Graph_PreClass;


// Time: O(mlog(max - min))
// Space: O(1)

public class _2_HeapAndBestFirstSearch_KthSmallestinSortedMatrix_3 {
	public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null)
        return Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = matrix[0][0], end = matrix[row - 1][col - 1];

        //Classic Binary Search
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int count = notLarger(matrix, mid);
            if (count < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    // Get the count of elems not larger than mid
    private int notLarger(int[][] m, int cur) {
        int count = 0;
        int row = m.length;
        int col = m[0].length;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (cur < m[i][j]) {
//                j�;//move to left col
                j--;//move to left col
            } else {
                //All elems left in cur row are valid
                i++;
                count += j + 1;
            }
        }
        return count;
  }


}
