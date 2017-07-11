# /** 548 Intersection of Two Arrays II
import collections

class Solution:
    # @param {int[]} nums1 an integer array
    # @param {int[]} nums2 an integer array
    # @return {int[]} an integer array
    def intersection(self, nums1, nums2):
        # Write your code here
        counts = collections.Counter(nums1)
        result = []

        for num in nums2:
            if counts[num] > 0:
                result.append(num)
                counts[num] -= 1

        return result