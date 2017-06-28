/** 602 Russian Doll Envelopes
*/


bool cmp(const pair<int,int>&x, const pair<int, int>&y) {
  return x.first != y.first ? x.first < y.first : x.second > y.second;
}

class Solution {
public:
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    int maxEnvelopes(vector<pair<int, int>>& envelopes) {
        // Write your code here
        int n = envelopes.size();
        if (n == 0) {
            return 0;
        }

        sort(envelopes.begin(), envelopes.end(), cmp);
        vector<int> dp(n), height(n+1, INT_MAX);
        for (int i = 0; i < n; i++) {
            int k = lower_bound(height.begin(), height.end(), envelopes[i].second) - height.begin() ;
            dp[i] = k;
            height[k] = envelopes[i].second;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = max(ans, dp[i]);
        }
        return ans + 1;
    }
};