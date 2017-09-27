# /** 630 Knight Shortest Path II

class Solution:
    # @param {boolean[][]} grid a chessboard included 0 and 1
    # @return {int} the shortest path
    def shortestPath2(self, grid):
        # Write your code here
        n = len(grid)
        if n == 0:
            return -1

        m = len(grid[0])
        if m == 0:
            return -1

        f = [ [sys.maxint for j in xrange(m)] for _ in xrange(n)]

        f[0][0] = 0
        for j in xrange(m):
            for i in xrange(n):
                if not grid[i][j]:
                    if i >= 1 and j >= 2 and f[i - 1][j - 2] != sys.maxint:
                        f[i][j] = min(f[i][j], f[i - 1][j - 2] + 1)
                    if i + 1 < n and j >= 2 and f[i + 1][j - 2] != sys.maxint:
                        f[i][j] = min(f[i][j], f[i + 1][j - 2] + 1)
                    if i >= 2 and j >= 1 and f[i - 2][j - 1] != sys.maxint:
                        f[i][j] = min(f[i][j], f[i - 2][j - 1] + 1)
                    if i + 2 < n and j >= 1 and f[i + 2][j - 1] != sys.maxint:
                        f[i][j] = min(f[i][j], f[i + 2][j - 1] + 1)

        if f[n - 1][m - 1] == sys.maxint:
            return -1

        return f[n - 1][m - 1]