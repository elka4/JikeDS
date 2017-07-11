/** 111 Climbing Stairs
 * Easy*/


class Solution {
public:
    /**
     * @param n: an integer
     * @return: an integer
     */
    int climbStairs(int n) {
        // write your code here
        if(n <= 2)
            return n;
        int* step = new int[n];
        step[0] = 1;
        step[1] = 2;
        for(int i = 2; i < n; i++) {
            step[i] = step[i-1] + step[i-2];
        }
        return step[n-1];
    }
};
