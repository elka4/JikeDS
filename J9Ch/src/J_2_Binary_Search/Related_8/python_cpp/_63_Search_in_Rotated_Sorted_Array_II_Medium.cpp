/**
63
Search in Rotated Sorted Array II
*/

#include <vector>

using namespace std;

class Solution {
    /**
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean
     */
public:
    bool search(vector<int> &A, int target) {
        // write your code here
        int ans = -1;
        int len = A.size();
        int cnt = 0;
        for ( int i = 0; i < len; ++i) {
            if (A[i] == target) {
                ans = i;
            }
            if (i != 0)
                if (A[i] < A[i-1])
                    cnt ++;
        }
        if (cnt  > 1)
            ans = -ans;
        return (ans>=0);
    }
};