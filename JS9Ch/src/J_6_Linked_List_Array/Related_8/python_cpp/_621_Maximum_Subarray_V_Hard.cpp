/** 621  Maximum Subarray V */


class Solution {
public:
    /**
     * @param nums an array of integers
     * @param k1 an integer
     * @param k2 an integer
     * @return the largest sum
     */
    int maxSubarray5(vector<int>& nums, int k1, int k2) {
        // Write your code here
        int n = nums.size();
        if (n < k1)
            return 0;

        int result = INT_MIN;

        vector<int> sum(n + 1);
        sum[0] = 0;

        // 6 [2, 4]
        deque<int> queue;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (!queue.empty() && queue.front() < i- k2) {
                queue.pop_front();
            }
            if (i >= k1) {
                while (!queue.empty() && sum[queue.back()] > sum[i - k1]) {
                    queue.pop_back();
                }
                queue.push_back(i - k1);
            }

            // [i - k2, i - k1]
            if (!queue.empty() && sum[i] - sum[queue.front()] > result) {
                result = max(result, sum[i] - sum[queue.front()]);
            }
        }
        return result;
    }
};