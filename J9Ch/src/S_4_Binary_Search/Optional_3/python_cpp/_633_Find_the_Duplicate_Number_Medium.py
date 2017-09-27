
class Solution:
    # @param {int[]} nums an array containing n + 1 integers which is between 1 and n
    # @return {int} the duplicate one
    def findDuplicate(self, nums):
        # Write your code here
        if len(nums) <= 1:
            return -1

        slow = nums[0]
        fast = nums[nums[0]]
        while slow != fast:
            slow = nums[slow]
            fast = nums[nums[fast]]

        fast = 0;
        while fast != slow:
            fast = nums[fast]
            slow = nums[slow]

        return slow