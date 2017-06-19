
class Solution:
    # @param n: an integer
    # @param times: a list of integers
    # @return: an integer
    def copyBooksII(self, n, times):
        # write your code here
        k = len(times)
        ans = 0
        eachTime = []
        totalTime = []
        for i in xrange(k): self.heapAdd(eachTime, totalTime, times[i], 0)
        for i in xrange(n):
            ans = totalTime[0]
            x = eachTime[0]
            self.heapDelete(eachTime, totalTime)
            self.heapAdd(eachTime, totalTime, x, ans+x)
        ans = 0
        for i in xrange(len(totalTime)): ans = max(ans, totalTime[i])
        return ans

    def heapAdd(self, eachTime, totalTime, et, tt):
        eachTime.append(et)
        totalTime.append(tt)
        n = len(eachTime)-1
        while n>0 and eachTime[n]+totalTime[n]<eachTime[(n-1)/2]+totalTime[(n-1)/2]:
            eachTime[n], eachTime[(n-1)/2] = eachTime[(n-1)/2], eachTime[n]
            totalTime[n], totalTime[(n-1)/2] = totalTime[(n-1)/2], totalTime[n]
            n = (n-1)/2

    def heapDelete(self, eachTime, totalTime):
        n = len(eachTime)-1
        if n>=0: eachTime[0] = eachTime[n]
        if n>=0: totalTime[0] = totalTime[n]
        if len(eachTime)>0: eachTime.pop()
        if len(totalTime)>0: totalTime.pop()
        n = 0
        while n*2+1<len(eachTime):
            t = n*2+1
            if t+1<len(eachTime) and eachTime[t+1]+totalTime[t+1]<eachTime[t]+totalTime[t]: t += 1
            if eachTime[n]+totalTime[n]<=eachTime[t]+totalTime[t]: break
            eachTime[n], eachTime[t] = eachTime[t], eachTime[n]
            totalTime[n], totalTime[t] = totalTime[t], totalTime[n]
            n = t