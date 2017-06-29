
class Solution {
public:
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    vector<int> find(int x1, int y1, int x2,  int y2, vector<vector<int> > &A, int flag) {
        if (flag) {
            int mid = (x1 + x2) / 2;
            int m = -1;
            int index = -1;
            for (int i = y1; i <= y2; ++i)
                if (A[mid][i] > m) {
                    m = A[mid][i];
                    index = i;
                }

            if (m > A[mid-1][index] && m > A[mid+1][index]) {
                vector<int> result;
                result.push_back(mid);
                result.push_back(index);
                return result;
            } else if (m < A[mid-1][index])
                return find(x1, y1, mid, y2, A, 0);
            else
                return find(mid+1, y1, x2, y2, A, 0);
        } else {
            int mid = (y1 + y2) / 2;
            int m = -1;
            int index = -1;
            for (int i = x1; i <= x2; ++i)
                if (A[i][mid] > m) {
                    m = A[i][mid];
                    index = i;
                }

            if (m > A[index][mid-1] && m > A[index][mid+1]) {
                vector<int> result;
                result.push_back(index);
                result.push_back(mid);
                return result;
            } else if (m < A[index][mid-1])
                return find(x1, y1, x2, mid, A, 1);
            else
                return find(x1, mid+1, x2, y2, A, 1);
        }
    }
    vector<int> findPeakII(vector<vector<int> > A) {
        // write your code here
        int n = A.size();
        int m = A[0].size();
        return find(0, 0, n-1, m-1, A, 0);
    }
};