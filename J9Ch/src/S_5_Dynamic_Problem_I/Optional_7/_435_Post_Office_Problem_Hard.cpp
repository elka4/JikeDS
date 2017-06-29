
int f[1500][1500], w[1500][1500], a[1500], K[1500][1500];
class Solution {
public:
    /**
     * @param A an integer array
     * @param k an integer
     * @return an integer
     */
    int N, P;
	vector<int> tmp;
    void init() {
        int n = tmp.size();
        a[0] = 0;
        for (int i = 1; i <= n; i ++) {
            a[i] = a[i - 1] + tmp[i-1];
        }
    }

    int getw(int x, int y)  {
        int t = (x + y) / 2;
        return a[y] - a[t] - (y - t) * tmp[t - 1] + (t - x) * tmp[t - 1] - (a[t - 1] - a[x - 1]);
    }

    void solve(int N, int P) {
        for (int i = 0; i <= N; i ++) {
            f[i][i] = 0;
            K[i][i] = i;
        }
		int j;
        for (int p = 1; p <= N - P; p ++) {
            for (int i = 0; (j  = i + p) <= N; i ++)
                f[i][j] = 0x7fffffff / 3;
            int t;
            for (int i = 1; (j = i + p) <= N; i ++) {
                for (int k = K[i][j - 1]; k <= K[i + 1][j]; k ++)
                    if ((t = f[i - 1][k - 1] + getw(k, j)) < f[i][j]) {
                        f[i][j] = t;
                        K[i][j] = k;
                    }
            }
        }
    }

    void getAns(int k, int r, vector<int>& ret) {
        if ( k == 0 || r <= 0)
            return;
        int p = K[k][r];
        getAns(k - 1, p - 1, ret);
        ret.push_back(tmp[(p + r) / 2 - 1]);
    }

    int postOffice(vector<int>& A, int k) {
        // Write your code here
        sort(A.begin(), A.end());
		tmp = A;
        int n = A.size();
        init();
        solve(n, k);

        vector<int> result;
        getAns(k, n, result);
        return f[k][n];
    }
};