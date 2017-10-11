package DP.DP4;

/*
A0, A1, ... AN-1 加起来等于target有几种方式

每个Ai只能用一次

情况一：用前N-1个物品拼出W
情况二：用前N-1个物品能拼出W-A【N-1】，再加上最后的物品[N-1]， 拼出W
情况一的个数 + 情况二的个数 = 用前N个物品拼出W的方式

要求前N个物品都有多少种方式拼出重量0，1，。。。。Target
需要知道前N-1个物品有多少种方式拼出重量0，1，。。。。target
状态：设f[i][w] = 用前i个物品有多少种方式拼出重量w

f[i][w] = f[i-1][w] + f[i-1][w-Ai-1]

f[i][w] ：用前i个物品有多少种方式拼出重量w
f[i-1][w] ：用前i-1个物品有多少种方式拼出重量w
f[i-1][w-Ai-1]：用前i-1个物品有多少种方式拼出重量w-Ai-1，再加上第i个物品

初始条件：
f[0][0] = 1: 0个物品可以有一种方式拼出重量0
f[0][1...M] = 0: 0个物品不能拼出大于0的重量

边界情况：
f[i-1][w-Ai-1]只能在 w≥Ai-1 时使用

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */


/*
Backpack V:
http://www.lintcode.com/en/problem/backpack-v/

这道题就是与IV相比，去掉了无限背包的条件，每个item用一次。
dp[i][j] 为前i个item，去size <= j 的方案个数。通项公式为
dp[i][j] = dp[i-1][j] + dp[i-1][j-A[i]]; 与I and II 类似。

int backPackV(vector<int>& nums, int target) {
        // Write your code here
        if(nums.empty() || target <= 0) return 0;
        int n = nums.size();
        vector<vector<int>> dp(n+1, vector<int>(target+1, 0));
        dp[0][0] = 1;
        for(int i=1; i<=n; i++){
            dp[i][0] = 1;
            for(int j=1; j<=target; j++){
                if(j<nums[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
或者用下面的方法

 int findSum(vector<int>& nums, int target){
        int len = nums.size();
        int *dp = new int[target+1];
        fill_n(dp, target+1, 0);
        dp[0] = 1;
        for(int i=0; i<nums.size(); i++){
            for(int j=target; j>=nums[i]; j--){
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }

作者：stepsma
链接：http://www.jianshu.com/p/7f192e75d734
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

import org.junit.Test;

//Backpack V
public class _6BackpackV {
     // 9 Ch DP

    public int backPackV(int[] A, int T){
        int i, j;
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[T + 1];
        f[0] = 1;
        for (i = 1; i <= T; i++) {
            f[i] = 0;
        }

        for (i = 1; i <= n; i++) {
            // importan, T downto 0
            for (j = T; j >= 0; --j) {
                // f'[j]
                // cover f[j]
                if (j >= A[i - 1]) {
                    f[j] += f[j - A[i - 1]];
                }
            }
        }
        return f[T];
    }

    @Test
    public void test01() {
        int[] A = {1,2,3,3,7};
        int T = 7;
        System.out.println(backPackV(A, T));
    }
/////////////////////////////////////////////////////////////////
    /**
     * @param nums an integer array and all positive numbers
     * @param target an integer
     * @return an integer
     */
    public int backPackV1(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = target; j >= nums[i]; --j)
                f[j] += f[j - nums[i]];

        return f[target];
    }
/////////////////////////////////////////////////////////////////
    /**
     * @param nums an integer array and all positive numbers
     * @param target an integer
     * @return an integer
     */
    public int backPackV2(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = target; j >= nums[i]; --j)
                f[j] += f[j - nums[i]];

        return f[target];
    }
/////////////////////////////////////////////////////////////////
}

/*
Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may only be used once

{1,2,3,3,7} 7 return 2
 */
