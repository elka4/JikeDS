/** 604 Window Sum */


class Solution {
public:
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving
     */
    vector<int> winSum(vector<int> &nums, int k) {
        // write your code here
        if (nums.size() < k || k <= 0)
            return vector<int>();

        int n = nums.size();
        vector<int> sums(n - k + 1, 0);

        for (int i = 0; i < k; i++)
            sums[0] += nums[i];
        for (int i = 1; i < n - k + 1; i++) {
            sums[i] = sums[i-1] - nums[i-1] + nums[i + k-1];
        }
        return sums;
    }
};