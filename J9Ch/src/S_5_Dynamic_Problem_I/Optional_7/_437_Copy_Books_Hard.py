'''/**
437
Copy Books
'''

# version 1: Binary Search
class Solution:
    # @param pages: a list of integers
    # @param k: an integer
    # @return: an integer

    def copyBooks(self, pages, k):
        # write your code here
        if len(pages) == 0:
            return 0

        def copiersNeed(pages, limit):
            copiers = 1
            sum = pages[0]
            for i in range(1, len(pages)):
                if (sum + pages[i]) > limit:
                    copiers += 1
                    sum = 0
                sum += pages[i]
            return copiers

        max = pages[0]
        total = 0
        for i in range(len(pages)):
            total += pages[i]
            if max < pages[i]:
                max = pages[i]
        start = max
        end = total

        while(start + 1 < end):
            mid = (end - start)/2 + start
            if(copiersNeed(pages, mid) <= k):
                end = mid
            elif(copiersNeed(pages, mid) > k):
                start = mid
        if(copiersNeed(pages, start) <= k):
            return start;
        else:
            return end;


# version 2: dp
class Solution:
    # @param pages: a list of integers
    # @param k: an integer
    # @return: an integer
    def copyBooks(self, pages, k):
        # write your code here
        n = len(pages)
        if (k>n): k = n
        if n == 0:
            return 0
        sum = []
        for i in xrange(n): sum.append(0)
        sum[0] = pages[0]
        for i in xrange(1, n): sum[i] = sum[i-1]+pages[i]
        f = []
        for i in xrange(n):
            f.append([])
            for j in xrange(k): f[i].append(0)
        for i in xrange(n): f[i][0] = sum[i];
        for j in xrange(1, k):
            p = 0
            f[0][j] = pages[0]
            for i in xrange(1, j): f[i][j] = max(f[i-1][j], pages[i])
            for i in xrange(j, n):
                while (p<i and f[p][j-1]<sum[i]-sum[p]): p += 1
                f[i][j] = max(f[p][j-1], sum[i]-sum[p])
                if (p>0): p -= 1
                f[i][j] = min(f[i][j], max(f[p][j-1], sum[i]-sum[p]))
        return f[n-1][k-1]