
class Solution:
    # @param {int[]} A an integer array
    # @param {int[]} V an integer array
    # @param {int} m an integer
    # @return {int} an array
    def backPackIII(self, A, V, m):
        # Write your code here
        f = [0 for i in xrange(m+1)]
        for (a, v) in zip(A, V):
            for j in xrange(a, m+1):
                if f[j - a] + v > f[j]:
                    f[j] = f[j - a] + v
        return f[m]
