
class Solution {
public:
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    long long houseRobber(vector<int> A) {
        // write your code here
        long long result = 0;
        long long f = 0, g = 0, f1 = 0, g1 = 0;
        int len = A.size();
        for (int i = 0; i < len; ++i) {
            f1 = g + A[i];
            g1 = max(f, g);
            g = g1, f = f1;
        }
        return max(g, f);
    }
};