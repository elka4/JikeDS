/**
141
Sqrt(x)
*/

class Solution {
public:
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    int sqrt(int x) {
        // write your code here
        long left = 0;
        if (x == 1)
            return 1;
        long right = x;
        long mid = left + (right-left)/2;
        while (left+1 < right) {
            if (x > mid*mid) {
                left = mid;
            } else if (x < mid*mid) {
                right = mid;
            } else {
                return mid;
            }
            mid = left + (right-left)/2;
        }
        return left;
    }
};