# /** 610 Two Sum - Difference equals to target



class Solution:
    """
    @param nums {int[]} n array of Integer
    @param target {int} an integer
    @return {int[]} [index1 + 1, index2 + 1] (index1 < index2)
    """
    def twoSum7(self, nums, target):
        # Write your code here
        nums = [(num, i) for i, num in enumerate(nums)]
        target = abs(target)
        n, indexs = len(nums), []

        nums = sorted(nums, key=lambda x: x[0])

        j = 0
        for i in xrange(n):
            if i == j:
                j += 1
            while j < n and nums[j][0] - nums[i][0] < target:
                j += 1
            if j < n and nums[j][0] - nums[i][0] == target:
                indexs = [nums[i][1] + 1, nums[j][1] + 1]

        if indexs[0] > indexs[1]:
            indexs[0], indexs[1] = indexs[1], indexs[0]

        return indexs