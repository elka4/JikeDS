
class Solution:
    # @param {int[]} A an integer array
    # @return {int[]}  A list of integers includes the index of the
    #                  first number and the index of the last number
    def continuousSubarraySum(self, A):
        # Write your code here
        ans = -0x7fffffff
        sum = 0
        start, end = 0, -1
        result = [-1, -1]
        for x in A:
            if sum < 0:
                sum = x
                start = end + 1
                end = start
            else:
                sum += x
                end += 1
            if sum > ans:
                ans = sum
                result = [start, end]

        return result