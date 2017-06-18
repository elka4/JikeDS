
class Solution:
    #@param A: An list of list integer
    #@return: The index of position is a list of integer, for example [2,2]
    def findPeakII(self, A):
        if len(A) == 0 or len(A[0]) == 0:
            return [-1, -1]

        left, up = 0, 0
        right, down = len(A[0]) - 1, len(A) - 1
        while left + 1  < right or up + 1 < down:
            if right - left > down - up:
                c = (left + right) / 2
                r = self.findColumnPeak(A, c, up, down)
                if self.isPeak(A, r, c):
                    return [r, c]
                elif A[r][c] < A[r][c - 1]:
                    right = c
                else:
                    left = c
            else:
                r = (up + down) / 2
                c = self.findRowPeak(A, r, left, right)
                if self.isPeak(A, r, c):
                    return [r, c]
                elif A[r][c] < A[r - 1][c]:
                    down = r
                else:
                    up = r

        for r in [left, right]:
            for c in [up, down]:
                if self.isPeak(A, r, c):
                    return [r, c]
        return [-1, -1]

    def isPeak(self, A, r, c):
        return A[r][c] > max(A[r][c-1], A[r][c+1], A[r-1][c], A[r+1][c])

    def findColumnPeak(self, A, c, up, down):
        value = max(A[r][c] for r in range(up, down + 1))
        for r in range(up, down + 1):
            if A[r][c] == value:
                return r

    def findRowPeak(self, A, r, left, right):
        value = max(A[r][c] for c in range(left, right + 1))
        for c in range(left, right + 1):
            if A[r][c] == value:
                return c