/** 443 Two Sum - Greater than target*/


class Solution {
public:
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    int twoSum2(vector<int> &nums, int target) {
        // Write your code here
        int n = nums.size(), cnt = 0;
        sort(nums.begin(), nums.end());
        for (int i = 0; i < n; ++i) {
            cnt += find(i+1, n-1, nums, target-nums[i]);
        }
        return cnt;
    }
    int find(int l, int r, vector<int> &nums, int target) {
        int end = r;
        if (l > r)
            return 0;
        if (nums[l] > target)
            return r - l + 1;
        if (nums[r] <= target)
            return 0;
        int ans = r + 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > target) {
                ans = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return end - ans + 1;
    }
};