class Solution {
public:
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    int backPack(int m, vector<int> A) {
        bool f[m + 1];
        for (int i = 0; i <= m; i++) {
            f[i] = false;
        }
        f[0] = true;
        for (int j = 0; j < A.size(); j++) {
            for (int i = m; i >= A[j]; i--) {
                f[i] = f[i] || f[i - A[j]];
            } // for j
        } // for i

        for (int i = m; i >= 0; i--) {
            if (f[i]) {
                return i;
            }
        }
        return 0;
    }
};