package DP.DP4;
import org.junit.Test;

public class DP_Backpack {


///////////////////////////////////////////////////////////////////////////////
/*
    在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
    如果有4个物品[2, 3, 5, 7]
    如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
    如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。
    函数需要返回最多能装满的空间大小。
    -----------------------------------------------------------------------------------------------
    •  f[i][w] = 能否用前i个物品拼出重量 w (TRUE / FALSE)

    f[i][w] = f[i-1][w] OR f[i-1][w-Ai-1]

    f[i][w]： 能否用前i个物品拼出重量w
    f[i-1][w]： 能否用前i-1个物品拼出重量w
    f[i-1][w-Ai-1]：能否用前i-1个物品拼出重量w-Ai-1， 再加上第i个物品

    •  初始条件：
    – f[0][0] = TRUE:       0个物品可以拼出重量0
    – f[0][1..M] = FALSE:   0 个物品不能拼出大于0的重量

    •  边界情况：
    – f[i-1][w - Ai-1] 只能在 w≥Ai-1 时使用

    •  记录前i个物品能拼出哪些重量
    •  前i个物品能拼出的重量
    - 前i-1个物品能拼出的重量
    - 前i-1个物品能拼出的重量 + 第i个物品的重量Ai-1
    -----------------------------------------------------------------------------------------------
    背包问题总结 (Backpack Problem)

    设dp[i][j] 为前i个数，取size <= j 的最大值，则有两种可能。
        1. 不拿item i，dp[i][j] = dp[i-1][j];
        2. 拿item i，dp[i][j] = dp[i-1][j-A[i]] + A[i]。所以通项公式为：
    dp[i][j] = max(dp[i-1][j] + dp[i-1][j-A[i]] + A[i])
    -----------------------------------------------------------------------------------------------
*/

    public int backPack1(int m, int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j >= nums[i - 1]) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i - 1]] + nums[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        return f[n][m];
    }

    public int backPack11(int m, int[] nums) {
        int n = nums.length;
        //n是物品个数，m是物品大小。
        //f[i][j]: 能否用前i个物品拼出重量 j (TRUE / FALSE)
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {//i是指nums里第几个物品
            for (int j = 1; j <= m; j++) {//j是物品大小

                if (j >= nums[i - 1]) {
                    //f[i - 1][j]： 不放当前，取顶头
                    //f[i - 1][j - A[i - 1]]： 放当前+ nums[i - 1]， 取斜上方。。。。
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i - 1]] + nums[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        return f[n][m];
    }

    @Test
    public void testI_01(){
        int[] nums = {2, 3, 5, 7};
        System.out.println(backPack1(11, nums));//10
        System.out.println(backPack1(12, nums));//12
    }

///////////////////////////////////////////////////////////////////////////////
/*
    给定N个物品，重量分别为正整数A0, A1, ..., AN-1，价值分别为正整数V0, V1, ..., VN-1
    • 一个背包最大承重是正整数M
    • 最多能带走多大价值的物品

    给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
    对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
    -----------------------------------------------------------------------------------------------

    • 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
    f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1 | w≥Ai-1 且f[i-1][w-Ai-1] ≠-1}

    f[i][w]:                用前i个物品拼出重量 w时最大总价值
    f[i-1][w]:              用前i-1个物品拼出 重量w时最大总价值
    f[i-1][w-Ai-1] + Vi-1:  用前i-1个物品拼出重量w-Ai-1

    • 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
    • f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1 | w≥Ai-1 且f[i-1][w-Ai-1] ≠-1}
    • 初始条件:
    – f[0][0] = 0: 0个物品可以拼出重量0，最大总价值是0
    – f[0][1..M] = -1: 0个物品不能拼出大于0的重量
    • 边界情况:
    – f[i-1][w-Ai-1]只能在w≥Ai-1，并且f[i-1][w-Ai-1] ≠-1时使用

    • 答案:max0<=j<=M{f[N][j] | f[N][j] ≠-1}
    • 时间复杂度(计算步数):O(MN)，空间复杂度(数组大小):优化后 可以达到O(M)
    -----------------------------------------------------------------------------------------------

    -----------------------------------------------------------------------------------------------
 */

/*
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a
backpack with size 10. The maximum value is 9.
 */

    //设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
    public int backPackII1(int m, int[] A, int V[]) {
        // write your code here
        int[][] dp = new int[A.length + 1][m + 1];

        for(int i = 0; i <= A.length; i++){
            for(int j = 0; j <= m; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else if(A[i - 1] > j){
                    dp[i][j] = dp[(i - 1)][j];
                }
                else{
                    dp[i][j] = Math.max(dp[(i - 1)][j], dp[(i - 1)][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[A.length][m];
    }

    @Test
    public void testII_01(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII1(10, A, V));
    }


    // 方法二
    public int backPackII2(int m, int[] nums, int V[]) {
        // write your code here
        int[] f = new int[m+1];
        for (int i = 0; i <=m ; ++i) f[i] = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = m; j >= nums[i]; j--){
                if (f[j] < f[j - nums[i]] + V[i])
                    f[j] = f[j - nums[i]] + V[i];
            }
        }
        return f[m];
    }



    @Test
    public void testII_02(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII2(10, A, V));
    }

///////////////////////////////////////////////////////////////////////////////

/*
• 和上一题唯一的不同是:每种物品都有无穷多个
• 一个背包最大承重是M
• BackpackII的转移方程
• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)

f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1}
f[i][w]:                用前i个物品拼出重量 w时最大总价值
f[i-1][w]:              用前i-1个物品拼出 重量w时最大总价值
f[i-1][w-Ai-1] + Vi-1:  用前i-1个物品拼出重量w-Ai-1 时最大总价值，加上第i个物品

• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)

-----------------------------------------------------------------------------------------------
Backpack III 是无限背包，表示每一个item可以取无数次。
所以通项定义稍有变化。dp[i][j] 为当前到第i个item，拿到size <= j 的最大价值。所以有最后不取第i个item，dp[i][j] = dp[i-1][j], 取第i个item，由于之前也可以取item i，dp[i][j] = dp[i][j-A[i]] + A[i]

通项公式为：
dp[i][j] = max(dp[i-1][j] + dp[i][j-A[i]] + A[i])
-----------------------------------------------------------------------------------------------
*/


    public int backPackIII(int[] A, int[] V, int m) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] f = new int[m + 1];
        int i, j;
        for (i = 0; i <= m; i++) {
            f[i] = Integer.MIN_VALUE;
        }

        f[0] = 0;
        for (i = 0; i < n; i++) {
            // f[0] f[1]
            for (j = 0; j <= m - A[i]; j++) {
                // updated f[w]: slides f[i][w]
                // original f[w]: slides f[i-1][w]
                // max{f[i - 1][w], f[i-1][w-Ai-1] + Vi-1 | w >= Ai-1 且f[i-1][w-Ai-1] != -1}

                if (f[j] != Integer.MIN_VALUE) {
                    f[j + A[j]] = Math.max(f[j + A[i]], f[j] + V[i]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (i = 0; i <= m; i++) {
            res = Math.max(res, f[i]);
        }
        return  res;
    }

    @Test
    public void testIII_00(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackIII( A, V, 10));
    }

    public int backPackIII1(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[] f = new int[m+1];
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <= m; ++j)
                if (f[j - A[i]] + V[i] > f[j])
                    f[j] = f[j - A[i]] + V[i];
        return f[m];
    }

    @Test
    public void testIII_01(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackIII1( A, V, 10));
    }

    // 2D version, 如果你无法理解一维的solution, 可以从二维的solution入手,然后思考空间的优化
    public int backPackIII2(int[] A, int[] V, int m) {
        // Write your code here
        int n = A.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i)
            for (int j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1])
                    f[i][j] = Math.max(f[i][j - A[i - 1]] + V[i - 1], f[i][j]);
            }
        return f[n][m];
    }

    @Test
    public void testIII_02(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackIII2( A, V, 10));
    }

///////////////////////////////////////////////////////////////////////////////
/*
Backpack IV:
http://www.lintcode.com/en/problem/backpack-iv/#

求方案个数，求方案个数就是把取max，变成求和。由于还是无限背包，即每个item可以用无数次，所以还是看同一行。通项公式如下：
dp[i][j] 为取到前i个数，组成size <= j 的总方案个数。
dp[i][j] = dp[i-1][j] + dp[i][j-A[i]];

初始化时，要把起始整个column都设为1.
*/

/*
Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times
 */

    // 方法一
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
            }
        }
        return f[A.length][target];
    }
    @Test
    public void testIV_01() {
        int[] A = {2,3,7};
        int T = 8;
        System.out.println(backPackIV1(A, T));//14
    }

    // 方法二
    public int backPackIV2(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i)
            for (int  j = nums[i]; j <= target; ++j)
                f[j] += f[j - nums[i]];

        return f[target];
    }
    @Test
    public void testIV_02() {
        int[] A = {1,2,3,7};
        int T = 7;
        System.out.println(backPackIV2(A, T));//14
    }

///////////////////////////////////////////////////////////////////////////////

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
Backpack V:
http://www.lintcode.com/en/problem/backpack-v/

这道题就是与IV相比，去掉了无限背包的条件，每个item用一次。
dp[i][j] 为前i个item，去size <= j 的方案个数。通项公式为
dp[i][j] = dp[i-1][j] + dp[i-1][j-A[i]]; 与I and II 类似。
-----------------------------------------------------------------------------------------------
 */


/*
Given n items with size nums[i] which an integer array and all positive numbers.

An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may only be used once

{1,2,3,3,7} 7 return 2
 */

    // 9 Ch DP
    public int backPackV1(int[] A, int T){
        int i, j;
        int n = A.length;
        if (n == 0) return 0;
        int[] f = new int[T + 1];
        f[0] = 1;

        for (i = 1; i <= T; i++) {
            f[i] = 0;
        }

        for (i = 1; i <= n; i++) {
            // important, T downto 0
            for (j = T; j >= 0; --j) {
                // f'[j]  // cover f[j]
                if (j >= A[i - 1]) {
                    f[j] += f[j - A[i - 1]];
                }
            }
        }
        return f[T];
    }

    @Test
    public void testV01() {
        int[] A = {1,2,3,3,7};
        int T = 7;
        System.out.println(backPackV1(A, T));
    }

    public int backPackV2(int[] nums, int target) {
        // Write your code here
        int[] f = new int[target + 1];
        f[0] = 1;

        for (int i = 0; i < nums.length; ++i)
            for (int  j = target; j >= nums[i]; --j)
                f[j] += f[j - nums[i]];

        return f[target];
    }

    @Test
    public void test02() {
        int[] A = {1,2,3,3,7};
        int T = 7;
        System.out.println(backPackV2(A, T));
    }


///////////////////////////////////////////////////////////////////////////////
/*
f[i] = f[i-A0] + f[i-A1] +...+ f[i-AN-1]

f[i]:多少种组合能拼出i
f[i-A0]：多少种组合能拼出i - A0
f[i-A1] +...+ f[i-AN-1]: 多少种组合能拼出i - AN-1

-----------------------------------------------------------------------------------------------
这道题就是combination sum
-----------------------------------------------------------------------------------------------
*/

    // 9CH DP, 不记录状态
    public int backPackVI (int[] nums, int T) {
        int[] f = new int[T + 1];
        f[0] = 1;
        int i, j;
        for (i = 1; i <= T; i++) {
            f[i] = 0;
            for (j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    f[i] += f[i - nums[j]];
                }
            }
        }
        return f[T];
    }
    /*
    Given nums = [1, 2, 4], target = 4 return 6
                   5, 7, 13, 17   32 return 22
     */
    @Test
    public void testVI01(){
        int[] A = {1, 2, 4};
        int T = 4;
        System.out.println(backPackVI(A, T));//6

        A = new int[]{5, 7, 13, 17};
        T = 32;
        System.out.println(backPackVI(A, T));//22

    }

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////



}
