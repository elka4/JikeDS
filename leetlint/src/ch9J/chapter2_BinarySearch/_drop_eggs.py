
class Solution:
    # @param {int} n an integer
    # @return {int} an integer
    def dropEggs(self, n):
        # Write your code here
        import math
        x = int(math.sqrt(n * 2))
        while x * (x + 1) / 2 < n:
            x += 1
        return x
