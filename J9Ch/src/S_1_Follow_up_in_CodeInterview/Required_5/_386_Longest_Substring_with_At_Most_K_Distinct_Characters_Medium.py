

class Solution:
    # @param s : A string
    # @return : An integer
    def lengthOfLongestSubstringKDistinct(self, s, k):
        if k == 0:
            return 0

        hash = {}
        head, tail = 0, 0
        longest = 0
        while head < len(s):
            while head < len(s) and (s[head] in hash or len(hash) < k):
                hash.setdefault(s[head], 0)
                hash[s[head]] += 1
                head += 1
            longest = max(longest, head - tail)
            while tail < head and len(hash) == k:
                hash[s[tail]] -= 1
                if hash[s[tail]] == 0:
                    del hash[s[tail]]
                tail += 1
        return longest