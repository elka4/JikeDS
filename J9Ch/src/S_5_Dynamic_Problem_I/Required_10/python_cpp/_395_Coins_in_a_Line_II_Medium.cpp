
class Solution {
public:
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    bool firstWillWin(vector<int> &values) {
        // write your code here
        vector<int> sum, f;
        int n = values.size(), total = 0;
        if (n<3) return true;
        for (int i=0; i<n; ++i) total += values[i];
        for (int i=0; i<n; ++i) {
            sum.push_back(total);
            total -= values[i];
        }
        f.push_back(sum[n-1]);
        f.push_back(sum[n-2]);
        for (int i=n-3; i>=0; --i)
            f.push_back(max(values[i]+(sum[i+1]-f[n-1-i-1]), values[i]+values[i+1]+(sum[i+2]-f[n-1-i-2])));
        if (f[n-1]<sum[0]-f[n-1]) return false;
        else return true;
    }
};