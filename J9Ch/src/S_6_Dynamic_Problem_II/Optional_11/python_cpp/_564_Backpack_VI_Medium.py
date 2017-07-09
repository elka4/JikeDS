
class Solution:
    # @param {int[]} nums an integer array and all positive numbers, no duplicates
    # @param {int} target an integer
    # @return {int} an integer
    def backPackVI(self, nums, target):
        # Write your code here
        dp = [0 for i in xrange(target + 1)]
        dp[0] = 1

        for j in xrange(1, target+1):
            for a in nums:
                if j >= a:
                    dp[j] += dp[j - a]

        return dp[target]