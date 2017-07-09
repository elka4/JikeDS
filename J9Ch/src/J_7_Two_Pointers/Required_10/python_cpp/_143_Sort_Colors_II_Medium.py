'''/** 143 Sort Colors II
 * Medium'''



class Solution:
    """
    @param colors: A list of integer
    @param k: An integer
    @return: nothing
    """
    def sortColors2(self, colors, k):
        # write your code here
        #for i in xrange(len(colors)):
        i = 0
        n = len(colors)
        while i < n:
            if colors[i] > 0:
                if colors[colors[i]-1] > 0:
                    tmp = colors[i]
                    colors[i] = colors[colors[i]-1]
                    colors[tmp-1] = -1
                    i = i - 1
                else:
                    colors[colors[i]-1] -= 1
                    colors[i] = 0
            i = i + 1

        i = len(colors)-1
        k = i
        while i >= 0:
            if colors[i] < 0:
                pos = k + colors[i]
                while k > pos:
                    colors[k] = i+1
                    k -= 1
            i -= 1