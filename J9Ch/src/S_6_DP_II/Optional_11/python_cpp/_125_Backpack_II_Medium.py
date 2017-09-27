
class Solution:
    # @param m: An integer m denotes the size of a backpack
    # @param A & V: Given n items with size A[i] and value V[i]
    def backPackII(self, m, A, V):
        # write your code here
        f = [0 for i in xrange(m+1)]
        n = len(A)
        for i in range(n):
            for j in xrange(m, A[i]-1, -1):
                f[j] = max(f[j] , f[j-A[i]] + V[i])
        return f[m]

