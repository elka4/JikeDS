


class Solution {
public:
    /* you may need to use some attributes here */
    vector<int> A;

    /**
     * @param A: An integer vector
     */
    Solution(vector<int> A) {
        // write your code here
        this->A = A;
    }

    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    long long query(int start, int end) {
        // write your code here
        long long sum = 0;
        int len = A.size();
        for (int i = start; i <= end && i < len; ++i)
            sum += this->A[i];
        return sum;
    }

    /**
     * @param index, value: modify A[index] to value.
     */
    void modify(int index, int value) {
        // write your code here
        //if (index < this->A.size())
        this->A[index] = value;
    }
};
