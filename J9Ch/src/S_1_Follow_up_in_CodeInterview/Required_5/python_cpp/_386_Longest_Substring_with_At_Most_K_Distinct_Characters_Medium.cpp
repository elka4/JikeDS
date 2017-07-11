


class Solution {
public:
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        // write your code here
        int start = 0, cnt = 0;
        int char_set[256] = {0};
        int ans = 0;
        for (int i = 0; i < s.size(); i++) {
            if (char_set[s[i]]++ == 0) cnt++;
            while (cnt > k) {
                char_set[s[start]]--;
                if (char_set[s[start++]] == 0) cnt--;
            }
            ans = max(i - start + 1, ans);
        }
        return ans;
    }
};