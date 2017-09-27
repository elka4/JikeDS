/**
437
Copy Books
*/


class Solution {
public:
    /**
     * @param pages: a vector of integers
     * @param k: an integer
     * @return: an integer
     */
    int copyBooks(vector<int> &pages, int k) {
        // write your code here
        int n = pages.size();
        if (n ==0)
            return 0;
        if (k>n) k = n;
        vector<int> sum(n);
        sum[0] = pages[0];
        for (int i=1; i<n; ++i) sum[i] = sum[i-1]+pages[i];
        vector<vector<int> > f(n, vector<int>(k));
        for (int i=0; i<n; ++i) f[i][0] = sum[i];
        for (int j=1; j<k; ++j) {
            int p = 0;
            f[0][j] = pages[0];
            for (int i=1; i<j; ++i) f[i][j] = max(f[i-1][j], pages[i]);
            for (int i=j; i<n; ++i) {
                while (p<i && f[p][j-1]<sum[i]-sum[p]) ++p;
                f[i][j] = max(f[p][j-1], sum[i]-sum[p]);
                if (p>0) --p;
                f[i][j] = min(f[i][j], max(f[p][j-1], sum[i]-sum[p]));
            }
        }
        return f[n-1][k-1];
    }
};