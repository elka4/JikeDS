
class Solution {
public:
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    bool firstWillWin(vector<int> &values) {
        // write your code here
        int n = values.size();
        vector<int> sum(values);
        vector<vector<int> > f(n, vector<int>(n));
        for (int i=1; i<n; ++i) sum[i] += sum[i-1];
        for (int j=0; j<n; ++j) f[0][j] = values[j];
        for (int i=1; i<n; ++i) {
            f[i][0] = max(values[0]+sum[i]-sum[0]-f[i-1][1], values[i]+sum[i-1]-f[i-1][0]);
            for (int j=1; j+i<n; ++j)
                f[i][j] = max(values[j]+sum[j+i]-sum[j]-f[i-1][j+1], values[j+i]+sum[j+i-1]-sum[j-1]-f[i-1][j]);
        }
        if (f[n-1][0]<sum[n-1]-f[n-1][0]) return false;
        else return true;
    }
};