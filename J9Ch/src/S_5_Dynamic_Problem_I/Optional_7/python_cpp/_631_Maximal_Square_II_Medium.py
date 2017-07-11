
class Solution:
    #param {int[][]} matrix a matrix of 0 and 1
    #return {int} an integer
    def maxSquare2(self, matrix):
        # write your code here
        n = len(matrix)
        if n == 0:
            return 0

        m = len(matrix[0])
        if m == 0:
            return 0

        f = [[0 for j in xrange(m)] for i in xrange(n)]
        u = [[0 for j in xrange(m)] for i in xrange(n)]
        l = [[0 for j in xrange(m)] for i in xrange(n)]

        length = 0
        for i in xrange(n):
            for j in xrange(m):
                if matrix[i][j] == 0:
                    f[i][j] = 0
                    u[i][j] = l[i][j] = 1
                    if i > 0:
                        u[i][j] = u[i - 1][j] + 1
                    if j > 0:
                        l[i][j] = l[i][j - 1] + 1
                else:
                    u[i][j] = l[i][j] = 0
                    if i > 0 and j > 0:
                        f[i][j] = min(f[i - 1][j - 1], min(u[i - 1][j], l[i][j - 1])) + 1
                    else:
                        f[i][j] = 1

                length = max(length, f[i][j])

        return length * length