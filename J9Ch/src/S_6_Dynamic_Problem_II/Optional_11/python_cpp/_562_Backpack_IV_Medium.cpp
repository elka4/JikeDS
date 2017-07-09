
class Solution {
public:
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    int backPackIV(vector<int>& nums, int target) {
        // Write your code here
        vector<int> dp(target + 1);
        dp[0] = 1;
        for (auto a : nums) {
            for (int i = a; i <= target; ++i) {
                dp[i] += dp[i - a];
            }
        }
        return dp.back();
    }
};