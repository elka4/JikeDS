/** 513 Perfect Squares
 * Medium*/


// version 1
class Solution1 {
public:
    /**
     * @param n a positive integer
     * @return an integer
     */
    int numSquares(int n) {
        // Write your code here
        vector<int> dp{ 0 };
        dp.resize(n + 1, INT_MAX);
        for (int i = 1, k; (k = i * i) <= n; ++i)
            for (int j = k; j <= n; ++j)
                if (dp[j] > dp[j - k] + 1)
                    dp[j] = dp[j - k] + 1;
        return dp[n];
    }
};


// version 2 Math
class Solution2 {
public:
    /**
     * @param n a positive integer
     * @return an integer
     */
    int numSquares(int n) {
        // Write your code here
        int ub = sqrt(n);
        for (int a = 0; a <= ub; ++a) {
            for (int b = a; b <= ub; ++b) {
            int c = sqrt(n - a * a - b * b);
                if (a * a + b * b + c * c == n)
                    return !!a + !!b + !!c;
            }
        }
        return 4;
    }
};

// version 3 Math II
class Solution3 {
public:
    /**
     * @param n a positive integer
     * @return an integer
     */
    int numSquares(int n) {
        // Write your code here
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;
        for (int i = 0; i * i <= n; ++i) {
            int j = sqrt(n - i * i);
            if (i * i + j * j == n)
                return !!i + !!j;
        }
        return 3;
    }
};