/** 471 Top K Frequent Words */


class Node {
public:
    int time;
    string word;
    Node(int _time, string _word) {
        time = _time, word = _word;
    };
    bool operator<(const Node& obj) const {
        return time > obj.time || time == obj.time && word < obj.word;
    }
};
class Solution {
public:
    /**
     * @param words an array of string
     * @param k an integer
     * @return an array of string
     */
    vector<string> topKFrequentWords(vector<string>& words, int k) {
        // Write your code here
        int n = words.size();
        map<string, int> mp1;
        map<string, int>::iterator it1;
        for (int i = 0; i < n; ++i) {
            if (mp1.find(words[i]) == mp1.end())
                mp1[words[i]] = 1;
            else
                mp1[words[i]] += 1;
        }
        vector<Node> p;
        for (it1 = mp1.begin(); it1 != mp1.end(); it1++)
            p.push_back(Node(it1->second, it1->first));
        sort(p.begin(), p.end());
        vector<string> result;
        for (int i = 0; i < k; ++i)
            result.push_back(p[i].word);
        return result;
    }
};