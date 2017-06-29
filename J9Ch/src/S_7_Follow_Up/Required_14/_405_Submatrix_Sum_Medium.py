
class Solution:
    # @param {int[][]} matrix an integer matrix
    # @return {int[][]} the coordinate of the left-up and right-down number
    def submatrixSum(self, matrix):
        # Write your code here
        lenM = len(matrix)
        lenN = len(matrix[0])

        # for i in xrange()

        if lenM == lenN == 1 and matrix[0][0] == 0: return [[0, 0], [0, 0]]
        f = [[0 for x in xrange(lenN+1)] for y in xrange(lenM+1)]

        for i in xrange(1, lenM+1):
            for j in xrange(1, lenN+1):
                f[i][j] = f[i-1][j] + f[i][j-1] - f[i-1][j-1] + matrix[i-1][j-1]
                for m in xrange(i):
                    for n in xrange(j):
                        if f[i][j] == f[i][n] - f[m][n] + f[m][j]:
                            return [[m, n], [i-1, j-1]]