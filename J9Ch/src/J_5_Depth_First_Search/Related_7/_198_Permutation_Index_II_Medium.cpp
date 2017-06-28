/** 198 Permutation Index II*/

class Solution {
public:
    /**
     * @param A an integer array
     * @return a long integer
     */
    long long permutationIndexII(vector<int>& A) {
        if (A.size() == 0)
            return 0;

        unordered_map<int, int> counter;
        long long index = 1, factor = 1, multiFact = 1;
        for (int i = A.size() - 1; i >= 0; --i) {
            int rank = 0;
            counter[A[i]] ++;
            multiFact *= counter[A[i]];
            for (int j = i + 1; j < A.size(); ++j) {
                if (A[i] > A[j]) {
                    rank ++;
                }
            }

            index += rank * factor / multiFact;
            factor *= (A.size() - i);
        }

        return index;
    }
};