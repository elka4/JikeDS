package J_2_Binary_Search.Optional_7.all;

/**
462
Total Occurrence of Target

 * Created by tianhuizhu on 6/28/17.
 */
public class _462_Total_Occurrence_of_Target {
    public class Solution {
        /**
         * @param A an integer array sorted in ascending order
         * @param target an integer
         * @return an integer
         */
        public int totalOccurrence(int[] A, int target) {
            // Write your code here
            int n = A.length;
            if (n == 0)
                return 0;
            if (A[n-1] < target || A[0] > target)
                return 0;

            int l = 0, r = n - 1;
            int start = 0;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (A[mid] >= target) {
                    start = mid;
                    r = mid - 1;
                } else
                    l = mid + 1;
            }
            if (A[start] != target)
                return 0;

            int end = n-1;
            l = 0; r = n-1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (A[mid] <= target) {
                    end = mid;
                    l = mid + 1;
                } else
                    r = mid - 1;
            }
            return end - start + 1;
        }
    }
}
