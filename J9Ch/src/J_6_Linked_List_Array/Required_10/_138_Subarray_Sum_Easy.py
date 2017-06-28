'''/** 138 Subarray Sum
 * Easy'''


class Solution:
    """
    @param nums: A list of integers
    @return: A list of integers includes the index of the first number
             and the index of the last number
    """
    def subarraySum(self, nums):
        hs = {0:-1}
        sum = 0
        for i in range(len(nums)):
            # if A[i] == 0:
            #     return [i, i]
            sum += nums[i]
            if sum in hs:
                return [hs[sum] + 1, i]
            hs[sum] = i
        return