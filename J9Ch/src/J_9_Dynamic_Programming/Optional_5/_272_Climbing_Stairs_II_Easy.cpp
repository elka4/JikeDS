/** 272 Climbing Stairs II
*/


class Solution {
public:
    /**
     * @param n an integer
     * @return an integer
     */
    int climbStairs2(int n) {
        // Write your code here
        if(n <= 1)
            return 1;

        int* step = new int[n + 1];
        step[0] = 1;
        step[1] = 1;
        step[2] = 2;

        for(int i = 3; i <= n; i++) {
            step[i] = step[i - 1] + step[i-2] + step[i - 3];
        }
        return step[n];
    }
};