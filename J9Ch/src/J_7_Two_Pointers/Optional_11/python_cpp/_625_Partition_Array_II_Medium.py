# /** 625 Partition Array II


# 参考程序1
class Solution:
    # @param {int[]} nums an integer array
    # @param {int} low an integer
    # @param {int} high an integer
    # @return {int[]} any of the possible solutions
    def partition2(self, nums, low, high):
        # Write your code here
        if len(nums) <= 1:
            return

        pl, pr = 0, len(nums) - 1
        i = 0
        while i <= pr:
            if nums[i] < low:
                nums[pl], nums[i] = nums[i], nums[pl]
                pl += 1
                i += 1
            elif nums[i] > high:
                nums[pr], nums[i] = nums[i], nums[pr]
                pr -= 1
            else:
                i += 1

# 参考程序2
class Solution:
    # @param {int[]} nums an integer array
    # @param {int} low an integer
    # @param {int} high an integer
    # @return nothing
    def partition2(self, nums, low, high):
        # Write your code here
        left = 0
        right = len(nums) - 1

        # 首先把区间分为 < low 和 >= low 的两个部分
        while left <= right:
            while left <= right and nums[left] < low:
                left = left + 1
            while left <= right and nums[right] >= low:
                right = right - 1
            if left <= right:
                nums[right],nums[left] = nums[left],nums[right]
                left = left + 1
                right = right - 1

        right = len(nums) - 1
        # 然后从 >= low 的部分里分出 <= high 和 > high 的两个部分
        while left <= right:
            while left <= right and nums[left] <= high:
                left = left + 1
            while left <= right and nums[right] > high:
                right = right - 1
            if left <= right:
                nums[right],nums[left] = nums[left],nums[right]
                left = left + 1
                right = right - 1