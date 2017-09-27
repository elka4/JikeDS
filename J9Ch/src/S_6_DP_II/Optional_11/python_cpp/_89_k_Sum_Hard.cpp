
class Solution {
public:
    int ans[105][1005];
    int kSum(vector<int> A, int K, int target) {
        ans[0][0] = 1;
        for(int i = 0; i < A.size(); ++i)
            for(int j = K; j > 0; j--)
                for(int k = target; k >= A[i]; k--) {
                    ans[j][k] += ans[j - 1][k - A[i]];
                    if(ans[j][k] > 2147483647)
                        return -1;
                }
        return ans[K][target];
    }
};