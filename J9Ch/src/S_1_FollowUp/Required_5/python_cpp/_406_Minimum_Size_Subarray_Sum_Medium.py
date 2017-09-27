

class Solution:
    # @param nums: a list of integers
    # @param s: an integer
    # @return: an integer representing the minimum size of subarray
    def minimumSize(self, nums, s):
        # write your code here
        n = len(nums)
        if n==0: return -1
        left = right = total = 0
        ans = n+1
        while right<n:
            while right<n and total<s:
                total += nums[right]
                right += 1
            if total<s: break
            while left<right and total>=s:
                total -= nums[left]
                left += 1
            ans = min(ans, right-left+1)
        if ans==n+1: return -1
        else: return ans