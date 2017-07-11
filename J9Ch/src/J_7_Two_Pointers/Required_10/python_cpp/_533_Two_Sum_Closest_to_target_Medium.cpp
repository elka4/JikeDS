/** 533 Two Sum - Closest to target
 * Medium*/


class Solution {
public:
    /**
     * @param nums an integer array
     * @param target an integer
     * @return the difference between the sum and the target
     */
    int twoSumClosest(vector<int>& nums, int target) {
        // Write your code here
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int j = n - 1;
        int diff = 0x7fffffff;
        for (int i = 0; i < n; ++i) {
            while (i < j && nums[i] + nums[j] > target) {
                if (nums[i] + nums[j] - target < diff)
                    diff = nums[i] + nums[j] - target;
                j --;
            }

            if (i >= j)
                break;

            if (target - nums[i] - nums[j] < diff)
                diff = target - nums[i] - nums[j];
        }

        return diff;
    }
};