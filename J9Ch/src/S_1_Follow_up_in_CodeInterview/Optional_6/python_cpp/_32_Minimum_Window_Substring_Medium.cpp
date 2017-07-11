
class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> mp;
        for (char now : t) {
            mp[now] ++;
        }
        int count = mp.size();
        int j = 0;
        int ans = INT_MAX;
        string res;
        for (int i = 0; i < s.size(); i++) {
            while(count != 0 && j < s.size()) {
                mp[s[j]]--;
                if (mp[s[j]] == 0) {
                    count--;
                }
                j++;
                if (count == 0) {
                    break;
                }
            }
            if (count == 0 && j - i< ans) {
                ans = j - i;
                res = s.substr(i, j - i);

            }
            if(mp[s[i]] == 0) {
                count++;
            }
            mp[s[i]]++;
        }
        return res;
    }
};