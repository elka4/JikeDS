


class Solution {
public:
    int triangleCount(vector<int> &S) {
        sort(S.begin(), S.end());
        int ans = 0;
        for(int i = 0; i < S.size(); i++) {
            int k = i + 2;
            for(int j = i + 1; j < S.size(); j++) {
                while(k < S.size() && S[i] + S[j] > S[k])
                    k++;
                ans += k - j - 1;
            }

        }
        return ans;
    }
};