/** 107 Word Break*/


class Solution {
public:
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    int getMaxLength(unordered_set<string> &dict) {
        int maxLength = 0; // 试试看中文
        for (unordered_set<string>::iterator it = dict.begin(); it != dict.end(); ++it) {
            maxLength = maxLength > (*it).length() ? maxLength : (*it).length();
        }
        return maxLength;
    }

    bool wordBreak(string s, unordered_set<string> &dict) {
        int maxLength = getMaxLength(dict);
        bool *canSegment = new bool[s.length() + 1];
        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int j = 1; j <= maxLength && j <= i; j++) {
                if (!canSegment[i - j]) {
                    continue;
                }
                string word = s.substr(i - j, j);
                if (dict.find(word) != dict.end()) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }
};