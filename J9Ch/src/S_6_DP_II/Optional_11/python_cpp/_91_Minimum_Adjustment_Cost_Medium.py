

class Solution:
    # @param A: An integer array.
    # @param target: An integer.
    def MinAdjustmentCost(self, A, target):
        # write your code here
        f = [[ sys.maxint for j in xrange(101)] for i in xrange(len(A)+1)]
        for i in xrange(101):
            f[0][i] = 0
        n = len(A)
        for i in xrange(1,n+1):
            for j in xrange(101):
                if f[i-1][j] != sys.maxint:
                    for k in xrange(101):
                        if abs(j-k) <= target:
                            f[i][k] = min(f[i][k], f[i-1][j] + abs(A[i-1]-k))
        ans = f[n][100]
        for i in xrange(101):
            if f[n][i] < ans:
                ans = f[n][i]

        return ans
