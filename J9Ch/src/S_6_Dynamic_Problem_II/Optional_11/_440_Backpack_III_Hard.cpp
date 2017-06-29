
class Solution {
public:
    /**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    int backPackIII(vector<int>& A, vector<int>& V, int m) {
        // Write your code here
        int n = A.size();
        int* f = new int[m+1];
        for (int i = 0; i <= m; ++i) f[i] = 0;
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <=m; ++j)
                if (f[j - A[i]] + V[i] > f[j])
                    f[j] = f[j - A[i]] + V[i];

        return f[m];
    }
};