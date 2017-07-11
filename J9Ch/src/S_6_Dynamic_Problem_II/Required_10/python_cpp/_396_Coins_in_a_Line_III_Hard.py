
class Solution:
    # @param values: a list of integers
    # @return: a boolean which equals to True if the first player will win
    def firstWillWin(self, values):
        # write your code here
        n = len(values)
        sumv, f = [], []
        for i in xrange(n): sumv.append(values[i])
        for i in xrange(1, n): sumv[i] += sumv[i-1]
        f.append([])
        for j in xrange(n): f[0].append(values[j])
        for i in xrange(1, n):
            f.append([])
            f[i].append(max(values[0]+sumv[i]-sumv[0]-f[i-1][1], values[i]+sumv[i-1]-f[i-1][0]))
            for j in xrange(1, n-i):
                f[i].append(max(values[j]+sumv[j+i]-sumv[j]-f[i-1][j+1], values[j+i]+sumv[j+i-1]-sumv[j-1]-f[i-1][j]))
        if f[n-1][0]<sumv[n-1]-f[n-1][0]: return False
        else: return True