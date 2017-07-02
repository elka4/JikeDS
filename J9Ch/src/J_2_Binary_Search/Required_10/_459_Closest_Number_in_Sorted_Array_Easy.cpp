/**459. Closest Number in Sorted Array
 * Easy*/



class Solution {
public:
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    int closestNumber(vector<int>& A, int target) {
        // Write your code here
        int l = 0, r = A.size() - 1;
        if (r < 0)
            return -1;
        int ans = abs(A[0] - target);
        int index = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (ans > abs(A[mid] - target)) {
                ans = abs(A[mid] - target);
                index = mid;
            }
            if (A[mid] >= target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return index;
    }
};