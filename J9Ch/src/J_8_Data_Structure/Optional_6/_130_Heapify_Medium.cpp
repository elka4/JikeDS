/** 130 Heapify*/


class Solution {
public:
    /**
     * @param A: Given an integer array
     * @return: void
     */
    void min_heapify(vector<int> &A, int i, int len){
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int largest = i;
        if( l < len)
            if(A[l] < A[i])
                largest = l;

        if( r < len )
            if( A[r] < A[largest])
                largest = r;

        if(largest != i) {
            swap(A[i], A[largest]);
            min_heapify(A, largest, len);
        }
    }
    void heapify(vector<int> &A) {
        // write your code here
        int len = A.size();
        for(int i = len / 2; i >= 0;i--)
            min_heapify(A, i, len);
    }
};