/**459. Closest Number in Sorted Array
 * Easy*/


class Solution {
public:
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    int lastPosition(vector<int>& A, int target) {
        // Write your code here
        int n = A.size();
        if (n == 0)
            return -1;
        if (A[n-1] < target || A[0] > target)
            return -1;

        int l = 0, r = n - 1;
        int end = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (A[mid] == target)
                end = mid;
            if (A[mid] <= target) {
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return end;
    }
};