
class Solution:
    # @param nums: a list of integers
    # @return: the maximum difference
    def maximumGap(self, nums):
        # write your code here
        if (len(nums)<2): return 0
        minNum = -1
        maxNum = -1
        n = len(nums)
        for i in xrange(n):
            minNum = self.min(nums[i], minNum)
            maxNum = self.max(nums[i], maxNum)
        if maxNum==minNum: return 0
        average = (maxNum-minNum) * 1.0 / (n-1)
        if average==0: average += 1
        localMin = []
        localMax = []
        for i in xrange(n):
            localMin.append(-1)
            localMax.append(-1)
        for i in xrange(n):
            t = int((nums[i]-minNum) / average)
            localMin[t] = self.min(localMin[t], nums[i])
            localMax[t] = self.max(localMax[t], nums[i])
        ans = average
        left = 0
        right = 1
        while left<n-1:
            while right<n and localMin[right]==-1: right += 1
            if right>=n: break
            ans = self.max(ans, localMin[right]-localMax[left])
            left = right
            right += 1
        return ans
    def min(self, a, b):
        if (a==-1): return b
        elif (b==-1): return a
        elif (a<b): return a
        else: return b
    def max(self, a, b):
        if (a==-1): return b
        elif (b==-1): return a
        elif (a>b): return a
        else: return b