
class Solution:
    # @param {int[][]} matrix an integer array of n * m matrix
    # @param {int} k an integer
    # @return {int} the maximum number
    def maxSlidingWindow2(self, matrix, k):
        # Write your code here
        n = len(matrix)
        if n == 0 or n < k:
            return 0
        m = len(matrix[0])
        if m == 0 or m < k:
            return 0

        sum = [[0 for j in xrange(m + 1)] for i in xrange(n + 1)]
        for i in xrange(1, n + 1):
            sum[i][0] = 0;
        for i in xrange(1, m + 1):
            sum[0][i] = 0;

        for i in xrange(1, n + 1):
            for j in xrange(1, m + 1):
                sum[i][j] = matrix[i - 1][j - 1] + \
                            sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1]

        max_value = -0x80000000
        for i in xrange(k, n + 1):
            for j in xrange(k, m + 1):
                value = sum[i][j] - sum[i - k][j] - \
                        sum[i][j - k] + sum[i - k][j - k]

                if value > max_value:
                    max_value = value

        return max_value