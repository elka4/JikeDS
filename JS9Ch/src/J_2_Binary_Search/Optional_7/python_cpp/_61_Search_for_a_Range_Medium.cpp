/**
61
 Search for a Range
 */

class Solution {
  /**
   *@param A : an integer sorted array
   *@param target :  an integer to be inserted
   *return : a list of length 2, [index1, index2]
   */
public:
  vector<int> searchRange(vector<int> &A, int target) {
    // write your code here
    vector<int> ans;
    int ansl = -1;
    for (int l = 0, r = A.size() - 1; l <= r;) {
      int mid = l + (r - l) / 2;
      if (A[mid] > target) {
        r = mid - 1;
      }
      if (A[mid] < target) {
        l = mid + 1;
      }
      if (A[mid] == target) {
        ansl = mid;
        r = mid - 1;
      }
    }

    int ansr = -1;
    for (int l = 0, r = A.size() - 1; l <= r;) {
      int mid = l + (r - l) / 2;
      if (A[mid] > target) {
        r = mid - 1;
      }
      if (A[mid] < target) {
        l = mid + 1;
      }
      if (A[mid] == target) {
        ansr = mid;
        l = mid + 1;
      }
    }
    ans.push_back(ansl);
    ans.push_back(ansr);
    return ans;
  }
};