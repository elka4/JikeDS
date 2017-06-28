# /** 43 Maximum Subarray III


class Solution:
    """
    @param nums: A list of integers
    @param k: An integer denote to find k non-overlapping subarrays
    @return: An integer denote the sum of max k non-overlapping subarrays
    """
    def maxSubArray(self, nums, k):
        oo = 2 ** 32
        n = len(nums)
        f = [[-oo] * (k + 1), [-oo] * (k + 1)]
        g = [[-oo] * (k + 1), [-oo] * (k + 1)]

        f[0][0] = 0
        g[0][0] = 0
        for i in range(1, n + 1):
            f[i % 2][0] = 0
            g[i % 2][0] = 0
            for j in range(1, k + 1):
                f[i % 2][j] = max(f[(i - 1) % 2][j] + nums[i - 1],
                                  g[(i - 1) % 2][j - 1] + nums[i - 1])
                g[i % 2][j] = max(g[(i - 1) % 2][j], f[i % 2][j])
        return g[n % 2][k]