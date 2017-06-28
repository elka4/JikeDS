'''/** 603 Largest Divisible Subset
 * Medium'''


class Solution:
    # @param {int[]} nums a set of distinct positive integers
    # @return {int[]} the largest subset
    def largestDivisibleSubset(self, nums):
        # Write your code here
        n = len(nums)
        dp = [1] * n
        father = [-1] * n

        nums.sort()
        m, index = 0, -1
        for i in xrange(n):
            for j in xrange(i):
                if nums[i] % nums[j] == 0:
                    if 1 + dp[j] > dp[i]:
                        dp[i] = dp[j] + 1
                        father[i] = j

            if dp[i] >= m:
                m = dp[i]
                index = i

        result = []
        for i in xrange(m):
            result.append(nums[index])
            index = father[index]

        return result