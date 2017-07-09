/**120 Word Ladder*/


// version LintCode
class Solution {
public:
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return an integer
      */
    int ladderLength(string start, string end, unordered_set<string> &dict) {
        if (start == end) {
            return 1;
        }
        int n = start.size();
        if (n < 1 || n != end.size()) {
            return 0;
        }

        queue<string> Q;
        Q.push(start);
        dict.erase(start);
        int length = 2;

        while (!Q.empty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                string word = Q.front(); Q.pop();
                for (int i = 0; i < n; i++) {
                    char oldChar = word[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == oldChar) continue;
                        word[i] = c;
                        if (word == end) {
                            return length;
                        }
                        if (dict.find(word) != dict.end()) {
                            Q.push(word);
                            dict.erase(word);
                        }
                    }
                    word[i] = oldChar;
                }
            } // for size
            length++;
        }
        return 0;
    }
};


// version LeetCode
class Solution {
public:
    int ladderLength(string start, string end, vector<string>& wordList) {
        if (start == end) {
            return 1;
        }

        int n = start.size();
        if (n < 1 || n != end.size()) {
            return 0;
        }

        unordered_set<string> dict;
        for (int i = 0; i < wordList.size(); i++) {
            dict.insert(wordList[i]);
        }

        queue<string> Q;
        Q.push(start);
        dict.erase(start);
        int length = 2;

        while (!Q.empty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                string word = Q.front(); Q.pop();
                for (int i = 0; i < n; i++) {
                    char oldChar = word[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == oldChar) continue;
                        word[i] = c;
                        if (dict.find(word) != dict.end()) {
                            if (word == end) {
                                return length;
                            }
                            Q.push(word);
                            dict.erase(word);
                        }
                    }
                    word[i] = oldChar;
                }
            } // for size
            length++;
        }
        return 0;
    }
};