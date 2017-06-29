
class Solution {
public:
    /**
     * @param nums: a vector of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    int minimumSize(vector<int> &nums, int s) {
        // write your code here
        int n = nums.size();
        if (n==0) return -1;
        int left = 0, right = 0, total = 0, ans = n+1;
        while (right<n) {
            while (right<n && total<s) total += nums[right++];
            if (total<s) break;
            while (left<right && total>=s) total -= nums[left++];
            ans = min(ans, right-left+1);
        }
        if (ans==n+1) return -1;
        else return ans;
    }
};