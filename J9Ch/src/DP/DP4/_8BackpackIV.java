package DP.DP4;



/*

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------


-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

 */

/*
Backpack IV:
http://www.lintcode.com/en/problem/backpack-iv/#

求方案个数，求方案个数就是把取max，变成求和。由于还是无限背包，即每个item可以用无数次，所以还是看同一行。通项公式如下：
dp[i][j] 为取到前i个数，组成size <= j 的总方案个数。
dp[i][j] = dp[i-1][j] + dp[i][j-A[i]];

初始化时，要把起始整个column都设为1.

int backPackIV(vector<int>& nums, int target) {
        // Write your code here
        if(nums.empty()) return 0;
        int n = nums.size();
        vector<vector<int>> dp(n+1, vector<int>(target+1, 0));
        for(int i=1; i<=n; i++){
            dp[i][0] = 1;
            for(int j=1; j<=target; j++){
                if(j < nums[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }

作者：stepsma
链接：http://www.jianshu.com/p/7f192e75d734
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

// Backpack IV
public class _8BackpackIV {

///////////////////////////////////////////////////////////////////////////////////////
    // 方法一
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackIV1(int[] nums, int target) {
        // Write your code here
        int m = target;
        int []A = nums;
        int f[][] = new int[A.length + 1][m + 1];

        f[0][0] = 1;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                int k = 0;
                while(k * A[i-1] <= j) {
                    f[i][j] += f[i-1][j-A[i-1]*k];
                    k+=1;
                }
            } // for j
        } // for i
        return f[A.length][target];
    }

///////////////////////////////////////////////////////////////////////////////////////

    // 方法二

    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackIV2(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = nums[i]; j <= target; ++j)
                f[j] += f[j - nums[i]];

        return f[target];
    }
///////////////////////////////////////////////////////////////////////////////////////
}
/*
Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times
 */


