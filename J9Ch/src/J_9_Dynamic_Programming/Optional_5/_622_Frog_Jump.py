# /** 622 Frog Jump


class Solution:
    # @param {int[]} stones a list of stones' positions in sorted ascending order
    # @return {boolean} true if the frog is able to cross the river or false
    def canCross(self, stones):
        # Write your code here
        dp = {}
        for stone in stones:
            dp[stone] = set([])

        dp[0].add(0)

        for stone in stones:
            for k in dp[stone]:
                # k - 1
                if k - 1 > 0 and stone + k - 1 in dp:
                    dp[stone + k - 1].add(k - 1)
                # k
                if stone + k in dp:
                    dp[stone + k].add(k)
                # k + 1
                if stone + k + 1 in dp:
                    dp[stone + k + 1].add(k + 1)

        return len(dp[stones[-1]]) > 0