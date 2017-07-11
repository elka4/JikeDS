



class Solution {
public:
  /**
   * @param words a set of words without duplicates
   * @return all word squares
   */
  int wordLen;
  unordered_map<string, vector<string> > prefix;
  vector<string> squares;
  vector<vector<string> > ans;

  void initPrefix(vector<string>& words) {
    for (int i = 0; i < words.size(); i++) {
      string item = words[i];
      prefix[""].push_back(item);
      for (int j = 0; j < item.length(); j++)
        prefix[item.substr(0, j + 1)].push_back(item);
    }
  }

  bool checkPrefix(int l, string nextWord) {
    for (int j = l + 1; j < wordLen; j++) {
      string pre;
      for (int k = 0; k < l; k++) {
        pre += squares[k][j];
      }
      pre += nextWord[j];
      if (prefix[pre].size() == 0) {
        return false;
      }
    }
    return true;
  }

  void dfs(int l) {
    if (l == wordLen) {
      ans.push_back(squares);
      return;
    }
    string pre;
    for (int i = 0; i < l; i++)
      pre += squares[i][l];
    vector<string> w = prefix[pre];

    for (int i = 0; i < w.size(); i++) {
      if (!checkPrefix(l, w[i])) {
        continue;
      }
      squares.push_back(w[i]);
      dfs(l + 1);
      squares.pop_back();
    }
  }

  vector<vector<string>> wordSquares(vector<string>& words) {
    // Write your code here
    if (words.size() == 0) {
      return ans;
    }
    initPrefix(words);
    wordLen = words[0].length();
    dfs(0);
    return ans;
  }
};