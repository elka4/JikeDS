class Solution:
    # @param nums: The integer array
    # @param target: Target number to find
    # @return the first position of target in nums, position start from 0
    def binarySearch(self, nums, target):
        # write your code here
        left, right = 0, len(nums)
        while left + 1 < right :
            mid = (left + right) / 2
            if nums[mid] < target :
                left = mid
            else :
                right = mid
        if nums[left] == target :
            return left
        elif nums[right] == target :
            return right
        return -1;


if __name__ == "__main__":
    sol = Solution()
    '''nums = [1, 2]'''
    '''nums = {1, 2}'''
    A = (1, 2, 3, 4, 5, 5, 5, 8, 9)

    print sol.binarySearch(A, 5)