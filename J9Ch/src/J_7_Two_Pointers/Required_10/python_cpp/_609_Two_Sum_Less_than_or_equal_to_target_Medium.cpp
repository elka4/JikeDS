/** 609 Two Sum - Less than or equal to target
 * Medium*/


class Solution {
public:
    /**
     * @param nums an array of integer
     * @param target an integer
     * @return an integer
     */
    int twoSum5(vector<int> &nums, int target) {
        // Write your code here
        int n = nums.size(), cnt = 0;
        sort(nums.begin(), nums.end());
        int l = 0, r = nums.size() -1;
        while (l < r){
            if (nums[l] + nums[r] > target){
                r--;
            } else {
                cnt += r - l;
                l++;
            }
        }
        return cnt;
    }
};