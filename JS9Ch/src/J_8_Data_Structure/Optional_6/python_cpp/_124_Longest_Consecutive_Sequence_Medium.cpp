/** 124 Longest Consecutive Sequence */

class Solution {
public:
  /**
   * @param nums: A list of integers
   * @return an integer
   */
  int longestConsecutive(vector<int> &num) {
    // write you code here
    unordered_set<int> map(num.begin(), num.end());
    int ans = 0;
    for (int i = 0; i < num.size(); i++) {
      if (map.find(num[i]) != map.end()) {
        map.erase(num[i]);
        int pre = num[i] - 1;
        int next = num[i] + 1;
        while (map.find(pre) != map.end()) {
          map.erase(pre);
          pre--;
        }
        while (map.find(next) != map.end()) {
          map.erase(next);
          next++;
        }
        ans = max(ans, next - pre - 1);
      }
    }
    return ans;
  }
};

