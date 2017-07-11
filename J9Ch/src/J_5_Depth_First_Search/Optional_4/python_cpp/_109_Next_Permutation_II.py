
class Solution:
    # @param nums: a list of integer
    # @return: return nothing (void), do not return anything, modify nums in-place instead
    def nextPermutation(self, nums):
        # write your code here
        i = len(nums)-1
        temp = []
        while i>0 and nums[i]<=nums[i-1]: i=i-1
        if i==0:
            for j in xrange(len(nums)-1, -1, -1): temp.append(nums[j])
            for j in xrange(len(nums)): nums[j] = temp[j]
            return
        for j in xrange(i-1): temp.append(nums[j])
        p = len(nums)-1
        while nums[p]<=nums[i-1]: p=p-1
        nums[p], nums[i-1] = nums[i-1], nums[p]
        temp.append(nums[i-1])
        for j in xrange(len(nums)-1, i-1, -1): temp.append(nums[j])
        for j in xrange(len(nums)): nums[j] = temp[j]
        return