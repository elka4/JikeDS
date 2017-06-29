
class Solution {
public:
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    int longestIncreasingContinuousSubsequence(vector<int>& A) {
        // Write your code here
        int max = 1, s = 1, l = 1;
        int len = A.size();
        if (len == 0)
            return 0;
        for (int i = 1; i < len; ++i) {
            if (A[i] > A[i-1])
                s += 1;
            else {
                if (s > max) max = s;
                s = 1;
            }

            if (A[i] < A[i-1])
                l += 1;
            else {
                if (l > max) max = l;
                l = 1;
            }
        }
        if (s > max) max = s;
        if (l > max) max = l;
        return max;
    }
};