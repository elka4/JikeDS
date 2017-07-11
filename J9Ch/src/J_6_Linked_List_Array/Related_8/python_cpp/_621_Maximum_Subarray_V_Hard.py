# /** 621  Maximum Subarray V


class Solution:
    # @param {int[]} nums an array of integers
    # @param {int} k1 an integer
    # @param {int} k2 an integer
    # @return {int} the largest sum
    def maxSubarray5(self, nums, k1, k2):
        # Write your code here
        n = len(nums)
        if n < k1:
            return 0

        import sys
        result = -sys.maxint

        sum = [0 for _ in xrange(n + 1)]

        from collections import deque
        queue = deque()

        for i in xrange(1, n + 1):
            sum[i] = sum[i - 1] + nums[i - 1]
            if len(queue) and queue[0] < i- k2:
                queue.popleft()

            if i >= k1:
                while len(queue) and sum[queue[-1]] > sum[i - k1]:
                    queue.pop()
                queue.append(i - k1)

            # [i - k2, i - k1]
            if len(queue) and sum[i] - sum[queue[0]] > result:
                result = max(result, sum[i] - sum[queue[0]])

        return result