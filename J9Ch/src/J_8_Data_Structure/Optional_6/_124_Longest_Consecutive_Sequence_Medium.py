# /** 124 Longest Consecutive Sequence


class Solution:
    # @param num, a list of integer
    # @return an integer
    def longestConsecutive(self, num):
        num.sort()
        l = num[0]
        ans = 1
        tmp = 1
        for n in num:
            if(n - l == 0):
                continue;
            elif(n - l == 1):
                tmp += 1
            else:
                if tmp > ans:
                    ans = tmp
                tmp = 1
            l = n
        if tmp > ans:
            ans = tmp
        return ans