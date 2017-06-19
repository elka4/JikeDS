

class Solution:
    # @param {double} x a double
    # @return {double} the square root of x
    def sqrt(self, x):
        # Write your code here

        left = 0.0
        right = x
        eps = 1e-12

        if x < 1.0:
            right = 1.0

        while(right - left > eps):
            mid = (right + left) / 2
            if mid * mid < x:
                left = mid
            else:
                right = mid

        return left