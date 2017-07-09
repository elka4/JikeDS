#/** 620 Maximum Subarray IV


class Solution:
    # @param {int[]} nums an array of integers
    # @param {int} k an integer
    # @return {int} the largest sum
    def maxSubarray4(self, nums, k):
        # Write your code here
        n = len(nums)
        if n < k:
            return 0

        result = 0
        for i in xrange(k):
            result += nums[i]

        sum = [0 for _ in xrange(n + 1)]

        min_prefix = 0
        for i in xrange(1, n + 1):
            sum[i] = sum[i - 1] + nums[i - 1]
            if i >= k and sum[i] - min_prefix > result:
                result = max(result, sum[i] - min_prefix)

            if i >= k:
                min_prefix = min(min_prefix, sum[i - k + 1])

        return result