
class Solution:
    # @param {int[]} A an integer array
    # @param {int} k an integer
    # @return {int} an integer
    def get_w(self):
        for i in xrange(1, self.n - 1):
            for j in xrange(i, self.n):
                if (i+j)%2 == 1:
                    self.w[i][j] = self.s[j]-self.s[(i+j)/2]*2+self.s[i-1]
                else:
                    self.w[i][j] = self.s[j]-self.s[(i+j)/2]-self.s[(i+j)/2-1]+self.s[i-1]

    def get_dp(self):
        dp = [[0 for j in xrange(self.n + 10)] for i in xrange(self.k  + 10)]
        p = [[0 for j in xrange(self.n + 10)] for i in xrange(self.k  + 10)]
        for i in xrange(1, self.n):
            dp[1][i] = self.w[1][i]
            p[1][i] = 0

        n = self.n - 1
        for c in xrange(2, self.k + 1):
            p[c][n+1] = n
            for i in xrange(n, 0, -1):
                tmp=0x7fffffff
                k = 0
                for j in xrange(p[c-1][i], p[c][i+1] + 1):
                    if dp[c-1][j] + self.w[j+1][i] < tmp:
                        tmp = dp[c-1][j] + self.w[j+1][i]
                        k = j
                dp[c][i]=tmp
                p[c][i]=k

        return dp[self.k][self.n - 1]

    def postOffice(self, A, k):
        # Write your code here
        A = sorted(A)
        if k >= len(A):
            return 0

        self.n = len(A) + 1
        self.k = k
        self.s = [0]
        self.w = [[0 for j in xrange(self.n + 10)] for i in xrange(self.n  + 10)]
        for x in A:
            self.s.append(self.s[-1] + x)

        self.get_w()
        return self.get_dp()
