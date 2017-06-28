/** 622 Frog Jump */


class Solution {
public:
    /**
     * @param stones a list of stones' positions in sorted ascending order
     * @return true if the frog is able to cross the river or false
     */
    bool canCross(vector<int>& stones) {
        // Write your code here
        unordered_map<int, unordered_set<int>> dp;
        for (auto position : stones)
            dp[position] = unordered_set<int>();
        dp[0].insert(0);
        for (auto position : stones) {
            for (auto k : dp[position]) {
                // k - 1
                if (k - 1 > 0 && dp.find(position + k - 1) != dp.end())
                    dp[position + k - 1].insert(k - 1);
                // k
                if (dp.find(position + k) != dp.end())
                    dp[position + k].insert(k);
                // k + 1
                if (dp.find(position + k + 1) != dp.end())
                    dp[position + k + 1].insert(k + 1);
            }
        }
        return !dp[stones.back()].empty();
    }
};