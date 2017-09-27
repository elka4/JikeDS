'''/** 608 Two Sum - Input array is sorted
 * Medium'''


class Solution:
    """
    @param nums {int[]} n array of Integer
    @param target {int} = nums[index1] + nums[index2]
    @return {int[]} [index1 + 1, index2 + 1] (index1 < index2)
    """
    def twoSum(self, nums, target):
        # Write your code here
        l, r = 0, len(nums)-1
        while l < r:
            value = nums[l] + nums[r]
            if value == target:
                return [l + 1, r + 1]
            elif value < target:
                l += 1
            else:
                r -= 1
        return []