
class Solution:
    # @param heights: a list of integers
    # @return: an integer
    def trapRainWater(self, heights):
        # write your code here
        n = len(heights)
        if n==0: return 0
        pos = []
        neg = []

        p = q = 0
        pos.append(p)
        while (q<n-1):
            q += 1
            if (heights[p]<=heights[q]):
                pos.append(q)
                p = q

        p = q = n-1;
        neg.append(p)
        while (q>0):
            q -= 1
            if (heights[q]>heights[p]):
                neg.append(q)
                p = q

        ans = 0
        for i in xrange(len(pos)-1):
            for j in xrange(pos[i]+1, pos[i+1]):
                ans += heights[pos[i]]-heights[j]
        for i in xrange(len(neg)-1):
            for j in xrange(neg[i+1]+1, neg[i]):
                ans += heights[neg[i]]-heights[j]
        return ans
