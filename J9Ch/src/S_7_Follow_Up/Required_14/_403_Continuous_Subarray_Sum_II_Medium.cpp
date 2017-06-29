
class Solution {
public:
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of
     *          the first number and the index of the last number
     */
    vector<int> continuousSubarraySumII(vector<int>& A) {
        // Write your code here
        vector<int> result;
        result.push_back(0);
        result.push_back(0);

        int total = 0;
        int len = A.size();
        int start = 0, end = 0;
        int sum = 0;
        int ans = -0x7fffffff;
        for (int i = 0; i < len; ++i) {
            total += A[i];
            if (sum < 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (sum >= ans) {
                ans = sum;
                result[0] = start;
                result[1] = end;
            }
        }
        sum = 0;
        start = 0, end = -1;
        for (int i = 0; i < len; ++i) {
            if (sum > 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (total - sum > ans && !(start == 0 && end == len-1)) {
                ans = total - sum;
                result[0] = (end + 1) % len;
                result[1] = (start - 1 + len) % len;
            }
        }
        return result;
    }
};