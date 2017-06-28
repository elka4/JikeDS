'''/**
617
Maximum Average Subarray
'''


class Solution:
    # @param {int[]} nums an array with positive and negative numbers
    # @param {int} k an integer
    # @return {double} the maximum average
    def maxAverage(self, nums, k):
        # Write your code here
        l, r = min(nums), max(nums)
        n = len(nums)
        prefix = [0] * (n + 1)
        while r - l >= 1e-6:
            mid, check = (l + r) / 2.0, False
            min_pre = 0
            for i in xrange(1, n + 1):
                prefix[i] = prefix[i - 1] + nums[i - 1] - mid;
                if i >= k and prefix[i] >= min_pre:
                    check = True
                    break
                if i >= k:
                    min_pre = min(min_pre, prefix[i - k + 1])

            if check:
                l = mid
            else:
                r = mid
        return l