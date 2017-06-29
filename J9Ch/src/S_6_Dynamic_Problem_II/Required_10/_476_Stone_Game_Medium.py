
class Solution:
    # @param {int[]} A an integer array
    # @return {int} an integer
    def stoneGame(self, A):
        n = len(A)
        if n < 2:
            return 0
        dp = [[0 for _ in xrange(n)] for _ in xrange(n)]
        sum = [[0 for _ in xrange(n)] for _ in xrange(n)]

        # 0, 1, 2, ... n-2, n-1
        for i in xrange(n):
            dp[i][i] = 0
        for i in xrange(0, n-1):
            dp[i][i+1] = A[i]+A[i+1]

        for i in xrange(n):
            sum[i][i] = A[i]
            for j in xrange(i+1, n):
                sum[i][j] = sum[i][j-1] + A[j]

        for x in xrange(3, n+1):
            i = 0
            while i+x-1 < n:
                j = i+x-1
                k = i
                dp[i][j] = sys.maxint
                while k+1 <= j:
                    t = dp[i][k] + dp[k+1][j] + sum[i][k] + sum[k+1][j]
                    dp[i][j] = min(dp[i][j], t)
                    k += 1
                i += 1
        return dp[0][n-1]