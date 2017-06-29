
class Solution:
    #param matrix: a matrix of 0 and 1
    #return: an integer
    def maxSquare(self, matrix):
        # write your code here
        m = len(matrix)
        n = len(matrix[0])
        f = []
        for i in xrange(m): f.append([])
        for i in xrange(m):
            for j in xrange(n): f[i].append(0)
        for i in xrange(n): f[0][i] = matrix[0][i]
        for i in xrange(1, m): f[i][0] = matrix[i][0]
        for i in xrange(1, m):
            for j in xrange(1, n):
                if (matrix[i][j]==0): f[i][j] = 0
                else: f[i][j] = 1+min(f[i-1][j], min(f[i-1][j-1], f[i][j-1]))
        ans = 0
        for i in xrange(m):
            for j in xrange(n): ans = max(ans, f[i][j])
        return ans*ans