
class Solution:
    # @param {int[]} nums an integer array and all positive numbers, no duplicates
    # @param {int} target an integer
    # @return {int} an integer
    def backPackIV(self, nums, target):
        # Write your code here
        dp = [0 for i in xrange(target + 1)]
        dp[0] = 1

        for a in nums:
            for j in xrange(a, target+1):
                dp[j] += dp[j - a]

        return dp[target]