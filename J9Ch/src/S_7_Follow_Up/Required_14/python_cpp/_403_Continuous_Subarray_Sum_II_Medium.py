

class Solution:
    # @param {int[]} A an integer array
    # @return {int[]}  A list of integers includes the index of the
    #                  first number and the index of the last number
    def continuousSubarraySumII(self, A):
        # Write your code here
        ans = -0x7fffffff
        sum, total = 0, 0
        start, end = 0, -1
        result = [-1, -1]
        length = len(A)
        for x in A:
            total += x
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


        start, end = 0, -1
        sum = 0
        for x in A:
            if sum > 0:
                sum = x
                start = end + 1
                end = start
            else:
                sum += x
                end += 1
            if start == 0 and end == length-1:
                continue
            if total - sum > ans:
                ans = total - sum
                result = [(end + 1) % length, (start - 1 + length) % length]

        return result