/**
183
Wood Cut
*/

class Solution {
public:
    /**
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    bool  ok(vector<int> L, int mid, int k) {
        if (!mid) {
            return false;
        }
        int cnt = 0;
        int n = L.size();
        for (int i = 0; i < n; ++i){
            cnt += L[i] / mid;
        }
        return cnt >= k;
    }
    int woodCut(vector<int> L, int k) {
        // write your code here
        int l =1, r = 0;
        int n = L.size();
        for (int i = 0; i < n; ++i){
            r = max(r, L[i]);
        }
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (ok(L, mid, k)) {
                l = mid + 1;
                ans = mid;
            } else{
                r = mid - 1;
            }
        }
        return ans;
    }
};