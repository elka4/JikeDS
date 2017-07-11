


class Solution {
public:
   /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is
     *          smaller than it and return count number array
     */
    int c[20000];
    int lowbit(int x) {
        return x&(-x);
    }
    int sum(int n) {
        if (n <= 0)
            return 0;
        int sum=0;
        while(n>0) {
            sum += c[n];
            n -= lowbit(n);
        }
        return sum;
    }

    void change(int i,int x, int n) {
        while (i <= n) {
          c[i] += x;
          i += lowbit(i);
        }
    }

    vector<int> countOfSmallerNumberII(vector<int> &A) {
        // write your code here
        vector<int> result;
        int len = A.size();
        int maxValue = 10000 + 1;
        for (int i = 0; i < len; ++i) {
            result.push_back(sum(A[i]));
            change(A[i] + 1, 1, maxValue);
        }
        return result;
    }
};