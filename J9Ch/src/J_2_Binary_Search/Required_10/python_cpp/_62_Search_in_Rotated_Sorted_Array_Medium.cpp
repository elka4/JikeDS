/**62. Search in Rotated Sorted Array
 * Medium
 * Created by tianhuizhu on 6/27/17.
 */


#include <vector>

using namespace std;

class Solution {
    /**
     * param A : an integer ratated sorted array
     * param target :  an integer to be searched
     * return : an integer
     */
private:
int find(vector<int> &A, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int idx = -1;
        if (A[l] <= A[r]) {
            int left = l, right = r, mid = 0;
            while (left <= right) {
                mid = (left + right) >> 1;
                if (A[mid] == target) {
                    idx = mid;
                    break;
                }
                else if (A[mid] > target) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
        }
        else {
            int mid = (l + r) >> 1;
            if (A[mid] == target) {
                idx = mid;
            }
            else {
                idx = find(A, l, mid - 1, target);
                idx = idx == -1 ? find(A, mid + 1, r, target) : idx;
            }
        }
        return idx;
}
public:
    int search(vector<int> &A, int target) {
        // write your code here
        int n = A.size();
        return find(A,0,n-1,target);
    }
};