# /** 602 Russian Doll Envelopes



class Solution:
    # @param {int[][]} envelopes a number of envelopes with widths and heights
    # @return {int} the maximum number of envelopes
    def maxEnvelopes(self, envelopes):
        # Write your code here
        height = [a[1] for a in sorted(envelopes, key = lambda x: (x[0], -x[1]))]
        dp, length = [0] * len(height), 0

        import bisect
        for h in height:
            i = bisect.bisect_left(dp, h, 0, length)
            dp[i] = h
            if i == length:
                length += 1
        return length