
class Solution {
public:
    /**
     * @param A an integer array
     * @return an integer
     */
    int stoneGame(vector<int>& A) {
        // Write your code here
        int n = A.size();
        if (n <= 1)
            return 0;
        vector<int> sum, num;
        sum.resize(n+1);
        num.resize(n+1);

        vector<vector<int> > f, s;
        for (int i = 0; i <= n; ++i) {
            f.push_back(num);
            s.push_back(num);
        }
        for (int i = 1; i <= n; ++i)
            num[i] = A[i-1];

        for (int i=1; i<=n; i++) {
            sum[i] = sum[i-1] + num[i];
            f[i][i] = 0;
            s[i][i] = i;

        }
        for (int len=2; len<=n; len++)
            for (int i=1; i<=n-len+1; i++) {
                int j=i + len - 1;
                f[i][j]= 0x7fffffff / 3;
                for ( int k=s[i][j-1]; k<=s[i+1][j]; k++)
                    if (f[i][k-1]+f[k][j]+sum[j]-sum[i-1] < f[i][j]) {
                        f[i][j] = f[i][k-1]+f[k][j]+sum[j]-sum[i-1];
                        s[i][j] = k;
                }

        }
        return f[1][n];
    }
};