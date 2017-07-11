'''/** 513 Perfect Squares
 * Medium'''


class Solution:
    # @param {int} n a positive integer
    # @return {int} an integer
    def numSquares(self, n):
        # Write your code here
        while n % 4 == 0:
            n /= 4
        if n % 8 == 7:
            return 4

        for i in xrange(n+1):
            temp = i * i
            if temp <= n:
                if int((n - temp)** 0.5 ) ** 2 + temp == n:
                    return 1 + (0 if temp == 0 else 1)
            else:
                break
        return 3