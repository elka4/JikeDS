/**
 * 624 Remove Substrings*/


class Solution {
public:
    /**
     * @param s a string
     * @param dict a set of n substrings
     * @return the minimum length
     */
    int minLength(string& s, set<string>& dict) {
        // Write your code here
        queue<string> que;
        unordered_set<string> hash;

        int min = s.size();
        que.push(s);
        hash.insert(s);

        while (!que.empty()) {
            string s = que.front();
            que.pop();
            for (auto& sub : dict) {
                int found = s.find(sub);
                while (found != -1) {
                    string new_s = s.substr(0, found) +
                        s.substr(found + sub.size(), s.size() - found - sub.size());
                    if (hash.find(new_s) == hash.end()) {
                        if (new_s.size() < min)
                            min = new_s.size();
                        que.push(new_s);
                        hash.insert(new_s);
                    }
                    found = s.find(sub, found + 1);
                }
            }
        }
        return min;
    }
};