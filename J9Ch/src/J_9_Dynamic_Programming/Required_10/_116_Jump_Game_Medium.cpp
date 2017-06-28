/** 116 Jump Game
 * Medium*/


class Solution {
public:
    bool canJump(vector<int> A) {
        int tmpMax = 0;
        int n = A.size();
        for (int i = 0; i < n; i++) {
            if (i > tmpMax) return false;
            if (tmpMax < i + A[i])
                tmpMax = i + A[i];
        }
        return true;
    }
};