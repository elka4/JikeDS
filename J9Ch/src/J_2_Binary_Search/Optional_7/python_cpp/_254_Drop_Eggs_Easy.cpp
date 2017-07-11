/**
254
 Drop Eggs*/

class Solution {
public:
    /**
     * @param n an integer
     * @return an integer
     */
    int dropEggs(int n) {
        // Write your code here
        long long ans = 0;
        int x = 0;
        while (ans < n) {
            x += 1;
            ans += x;
        }
        return x;
    }
};