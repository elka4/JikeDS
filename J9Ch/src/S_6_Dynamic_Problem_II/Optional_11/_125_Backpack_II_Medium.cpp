
class Solution {
public:
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     */
    int backPackII(int m, vector<int> A, vector<int> V) {
        // write your code here
        int f[m + 1];
        for (int i = 0; i <=m ; ++i) f[i] = 0;
        int n = A.size() , i, j;
        for(i = 0; i < n; i++){
            for(j = m; j >= A[i]; j--){
                f[j] = max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }
};