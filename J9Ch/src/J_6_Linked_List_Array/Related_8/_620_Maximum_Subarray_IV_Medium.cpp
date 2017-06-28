/** 620 Maximum Subarray IV*/


class Solution {
public:
    /**
     * @param nums an array of integers
     * @param k an integer
     * @return the largest sum
     */
    int maxSubarray4(vector<int>& nums, int k) {
        // Write your code here
        int n = nums.size();
        if (n < k)
            return 0;

        int result = 0;
        for (int i = 0; i < k; ++i)
            result += nums[i];

        vector<int> sum(n + 1);
        sum[0] = 0;

        int min_prefix = 0;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (i >= k && sum[i] - min_prefix > result) {
                result = max(result, sum[i] - min_prefix);
            }
            if (i >= k) {
                min_prefix = min(min_prefix, sum[i - k + 1]);
            }
        }
        return result;
    }
};