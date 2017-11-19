package DP;
import org.junit.Test;

public class DP_Backpack {

    //  https://zhengyang2015.gitbooks.io/lintcode/backpack.html



//-------------------------------------------------------------------------/////////////////
/*
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
Notice
You can not divide any item into small pieces.
Example+

If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
You function should return the max size we can fill in the given backpack.
Challenge
O(n x m) time and O(m) memory.
O(n x m) memory is also acceptable if you do not know how to optimize memory.
 */

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
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i-1]] + nums[i-1]);
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
                //边界情况：f[i-1][w - Ai-1] 只能在 w≥Ai-1 时使用
                if (j >= nums[i - 1]) {
                    //f[i - 1][j]： 不放当前item nums[i-1]，取顶头
                    //f[i - 1][j - A[i - 1]]： 放当前item nums[i-1]， 取斜上方。。。。
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i-1]] + nums[i-1]);
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


    // 9Ch DP
    public int backPack2(int m, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;

        for (int j = 1; j <= m; j++) {
            f[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]];
                }
            }
        }

        for (int i = m; i >= 0; --i) {
            if (f[n][i]) return i;
        }
        return 0;
    }


    public int backPack3(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        f[0][0] = true;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1] && f[i-1][j - A[i-1]]) {
                    f[i][j] = true;
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) return i;
        }
        return 0;
    }

    /*  这两个表达同义
            if (j >= nums[i - 1]) {
                f[i][j] = f[i][j] || f[i - 1][j - nums[i - 1]];
            }
            if (j >= A[i - 1] && f[i-1][j - A[i-1]]) {
                f[i][j] = true;
            }
     */

//------------------------------------------------------------------------------------------------
//  https://zhengyang2015.gitbooks.io/lintcode/backpack_i_92.html

//第一种方法为dp[i][j]表示i容量的背包在j件物品中能取的最大值。分两种情况：一种情况为不要第j件物品，则最优情况为背包容量为i时前j－1件物品的最优值；第二种情况为要第j件物品，则最优情况为背包容量为i-第j件物品体积时前j－1件物品的最优值加上第j件物品的值，这种情况要求i-第j件物品体积 >= 0。取两种情况里面较大的作为dp[i][j]的值。
    public int backPack01(int m, int[] A) {
        // write your code here
        if(m == 0 || A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[][] fillPack = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            fillPack[i][0] = 0;
        }

        for(int j = 0; j <= n; j++){
            fillPack[0][j] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i - A[j - 1] >= 0){
                    //一种情况为不要第j件物品，则最优情况为背包容量为i时前j－1件物品的最优值；
                    //第二种情况为要第j件物品，则最优情况为背包容量为i-第j件物品体积时前j－1件物品的最优值加上第j件物品的值。
                    fillPack[i][j] = Math.max(fillPack[i][j - 1], fillPack[i - A[j - 1]][j - 1] + A[j - 1]);
                }else{
                    fillPack[i][j] = fillPack[i][j - 1];
                }
            }
        }

        return fillPack[m][n];
    }

//第二种方法dp[i][j]表示前i件物品能否填满容量为j的背包。分两种情况：一种情况为不要第i件物品，则dp[i][j]=dp[i-1][j]；第二种情况为要第j件物品，则dp[i][j] = dp[i-1][j - A[j]],不过这种情况要求j - A[j]>0。取两种情况里面较大的作为dp[i][j]的值。
    public int backPack02(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i + 1][j] = f[i][j];
                if (j >= A[i] && f[i][j - A[i]]) {
                    f[i + 1][j] = true;
                }
            } // for j
        } // for i

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }
//------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------/////////////////
/*
Backpack II 125

Question

Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?
*/


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

    public int backPackII1(int m, int[] A, int V[]) {
        int[][] dp = new int[A.length + 1][m + 1]; //设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)

        for(int i = 0; i <= A.length; i++){
            for(int j = 0; j <= m; j++){
                //f[0][0] = 0: 0个物品可以拼出重量0，最大总价值是0
                if(i == 0 || j == 0) dp[i][j] = 0;

                else if(A[i-1] > j) dp[i][j] = dp[i - 1][j];

                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i-1]] + V[i-1]);
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
    }//9


    // 9Ch DP
    public int backPackII3(int m, int[] A, int V[]) {
        int n = A.length;
        if (n == 0) return 0;
        //• 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
        int[] f = new int[m + 1];
        f[0] = 0;
        for (int i = 1; i <= m; i++) {
            f[i] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int w = m; w >= 0; --w) {
                //f[i-1][w-Ai-1]只能在w≥Ai-1，并且f[i-1][w-Ai-1] ≠-1时使用
                if (w >= A[i - 1] && f[w - A[i - 1]] != -1) {
                    f[w] = Math.max(f[w], f[w - A[i - 1]] + V[i - 1]);
                }
            }
        }

        int res = 0;
        for (int i = 0; i <= m; i++) {
            if (f[i] != -1) res = Math.max(res, f[i]);
        }
        return  res;
    }
    @Test
    public void testII_03(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII3(10, A, V));
    }//9

//------------------------------------------------------------------------------------------------
//https://zhengyang2015.gitbooks.io/lintcode/backpack_ii_125.html
/*
和I类似。 value[i][j]表示容量为i的背包在前j件物品中能取的最大价值。分两种情况考虑：不要第j件物品，则value[i][j] = value[i][j-1]；要第j件物品，则value[i][j]=value[i－A［j］][j-1]+V[j]，这种情况要求i-A[j]>=0。取两种情况里面较大的作为value[i][j]的值。这种方法空间复杂度为O(n * m)。

第二种方法可以将空间复杂度优化到O(m)。f[j]表示背包容量为j时在前i件物品中能够选取的最大价值。到第i件物品时，考虑第i件物品装还是不装。背包容量j从最大的容量m开始依次递减遍历，只考虑更新背包容量大于A[i]的情况，即加上V[i]之后是否结果会更优，背包容量小于A[i]的情况不用考虑因为第i件物品根本装不进此时容量的背包。若结果更优，则更新此时的f[j]（即装第i件物品时情况更优），否则不变（即不装第i件物品时情况更优）。因为f会记录i之前所有的最优情况，因此只需要m空间即可。物品从0遍历到n－1，即可求出前n件物品的最优值。

其实第二种方法相当于使用滚动数组。因为状态函数为
value[i][j]=max(value[i－A［j］][j-1]+V[j], value[i][j-1])
可以看出只和j－1有关，因此保存两行信息就足够了。使用滚动数组必须逐行填写，因为在计算下一行的时候需要上一行的信息，像第一种方法中那样逐列填写就不行。
 */
    //space O(n * m)
    public int backPackII01(int m, int[] A, int V[]) {
        // write your code here
        if(m == 0 || A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[][] value = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i -A[j - 1] >= 0){
                    value[i][j] = Math.max(value[i][j - 1], value[i - A[j - 1]][j - 1] + V[j - 1]);
                }else{
                    value[i][j] = value[i][j - 1];
                }
            }
        }

        return value[m][n];
    }

    //space O(m)：
    public int backPackII02(int m, int[] A, int V[]) {
        // write your code here
        int[] f = new int[m + 1];
        int n = A.length;
        for(int i = 0; i < n; i++){
            for(int j = m; j >= A[i]; j--){
                if(f[j] < f[j - A[i]] + V[i]){
                    f[j] = f[j - A[i]] + V[i];
                }
            }
        }
        return f[m];
    }
    @Test
    public void test_backPackII02(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII02(10, A, V));
    }//9



    //滚动数组：
    public int backPackII03(int m, int[] A, int V[]) {
        // write your code here
        if(m == 0 || A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[][] value = new int[m + 1][2];

        for(int j = 1; j <= n; j++){
            for(int i = 1; i <= m; i++){
                if(i -A[j - 1] >= 0){
                    value[i][j % 2] = Math.max(value[i][(j - 1) % 2], value[i - A[j - 1]][(j - 1) % 2] + V[j - 1]);
                }else{
                    value[i][j % 2] = value[i][(j - 1) % 2];
                }
            }
        }
        return value[m][0];
    }
    @Test
    public void test_backPackII03(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackII03(10, A, V));
    }//9

//------------------------------------------------------------------------------------------------


//-------------------------------------------------------------------------/////////////////
/*
Backpack III 440

Question

Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. What's the maximum value can you put into the backpack?
Notice
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.
 */

/*  Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.

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

    //  9Ch
    //  每种物品都有无穷多个, 最大承重m
    public int backPackIII3(int[] A, int[] V, int m) {
        int n = A.length;
        //  • 设f[i][w] = 用前i个物品拼出重量w时最大总价值 (-1表示不能拼出w)
        int[][] f = new int[n + 1][m + 1];

        //f[i][w] = max{f[i-1][w], f[i-1][w-Ai-1] + Vi-1}
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
        System.out.println(backPackIII3( A, V, 10));
    }//15


    //空间优化
    public int backPackIII1(int[] A, int[] V, int m) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int[] f = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            f[i] = -1;
        }
        f[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= m; w++) {
                if (w >= A[i - 1] && f[w - A[i - 1]] != -1) {
                    f[w] = Math.max(f[w], f[w - A[i - 1]] + V[i - 1]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i <= m; i++) {
            res = Math.max(res, f[i]);
        }
        return  res;
    }

    @Test
    public void testIII_00(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackIII1( A, V, 10));
    }


    // 9Ch
/*
这道题和II的思想一样，f[j]表示容量为j的背包对前i件物品能取的最大值，其中物品可以重复选取。对物品从0遍历到n－1，每次只有比A[i]大的背包容量才有可能被更新。+

和II不同的是，这道题物品可以重复选择，所以内层遍历j的时候从小到大遍历，这样物品可以重复选取。比如一开始在j的时候取了i，然后随着j的增大，在j'的时候又取了i，而恰好j = j' - A[i]，在这种情况下i就被重复选取。如果从大往小遍历则所有物品只能取一次，所以II中是从大往小遍历。
因此可以重复取元素则背包容量从小到大遍历，反之从大到小遍历。
*/
    public int backPackIII2(int[] A, int[] V, int m) {
        int n = A.length;
        int[] f = new int[m+1];
        for (int i = 0; i < n; ++i)
            for (int j = A[i]; j <= m; ++j)
                //对于当前物品i，若j从小到大的话，很可能在j之前的j-A[i]时已经放过第i件物品了，
                // 在j时再放就是重复放入；若j从大到小，则j之前的所有情况都没有更新过，
                // 不可能放过第i件物品，所以不会重复放入。

                if (f[j - A[i]] + V[i] > f[j])
                    f[j] = f[j - A[i]] + V[i];
        return f[m];
    }
    @Test
    public void testIII_01(){
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        System.out.println(backPackIII2( A, V, 10));
    }//15

//------------------------------------------------------------------------------------------------
//https://zhengyang2015.gitbooks.io/lintcode/backpack_iii_440.html
/*
这道题和II的思想一样，f[j]表示容量为j的背包对前i件物品能取的最大值，其中物品可以重复选取。对物品从0遍历到n－1，每次只有比A[i]大的背包容量才有可能被更新。
和II不同的是，这道题物品可以重复选择，所以内层遍历j的时候从小到大遍历，这样物品可以重复选取。比如一开始在j的时候取了i，然后随着j的增大，在j'的时候又取了i，而恰好j = j' - A[i]，在这种情况下i就被重复选取。如果从大往小遍历则所有物品只能取一次，所以II中是从大往小遍历。
因此可以重复取元素则背包容量从小到大遍历，反之从大到小遍历。
 */

    public int backPackIII01(int[] A, int[] V, int m) {
        // Write your code here
        int[] f = new int[m + 1];

        for(int i = 0; i < A.length; i++){
            for(int j = A[i]; j <= m; j++){
                //对于当前物品i，若j从小到大的话，很可能在j之前的j-A[i]时已经放过第i件物品了，在j时再放就是重复放入；若j从大到小，则j之前的所有情况都没有更新过，不可能放过第i件物品，所以不会重复放入。
                if(f[j - A[i]] + V[i] > f[j]){
                    f[j] = f[j - A[i]] + V[i];
                }
            }
        }

        return f[m];
    }
//------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------/////////////////
/*
Backpack IV 562

Question

Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
Each item may be chosen unlimited number of times.
Example
Given candidate items [2,3,6,7] and target 7,
A solution set is:
[7]

[2, 2, 3]
return 2
 */

/*
Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.

Each item may be chosen unlimited number of times
 */

/*
    Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
    Each item may be chosen unlimited number of times.
    Example
    Given candidate items [2,3,6,7] and target 7,
    A solution set is:
    [7], [2, 2, 3]
    return 2
 */

/*
    Backpack IV:
    http://www.lintcode.com/en/problem/backpack-iv/#

    求方案个数，求方案个数就是把取max，变成求和。由于还是无限背包，即每个item可以用无数次，所以还是看同一行。通项公式如下：
    dp[i][j] 为取到前i个数，组成size <= j 的总方案个数。
    dp[i][j] = dp[i-1][j] + dp[i][j-A[i]];

    初始化时，要把起始整个column都设为1.
*/

/*
这道题思路和III几乎一样，dp[j]表示背包容量为j时，对前i中物品来说能填满背包的方法数。当前元素为i时，背包容量大于等于nums[i]的才有可能被更新。此时，对于j容量的背包，其新的方法数为前i－1件物品能装满j容量背包的方法数（即不装第i件物品的方法数）＋前i-1件物品能装满j-nums[i]容量的背包的方法数（即装第i件物品的方法数）。这里状态方程只是把III中的max改成了+。所有求总共有多少种方法的题都可以从最大值问题变换max为+得到。因此，状态函数为：
dp[j] = dp[j] + dp[j - nums[i]] (右边的dp[j]表示上一行中（即i－1件物品）能装满j容量的方法数)
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
        int[] A = {2,3,6,7};
        int T = 7;
        System.out.println(backPackIV1(A, T));//2
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
        int[] A = {2,3,6,7};
        int T = 7;
        System.out.println(backPackIV2(A, T));//2
    }

//------------------------------------------------------------------------------------------------
//https://zhengyang2015.gitbooks.io/lintcode/backpack_iv_562.html

/*
这道题思路和III几乎一样，dp[j]表示背包容量为j时，对前i中物品来说能填满背包的方法数。当前元素为i时，背包容量大于等于nums[i]的才有可能被更新。此时，对于j容量的背包，其新的方法数为前i－1件物品能装满j容量背包的方法数（即不装第i件物品的方法数）＋前i-1件物品能装满j-nums[i]容量的背包的方法数（即装第i件物品的方法数）。这里状态方程只是把III中的max改成了+。所有求总共有多少种方法的题都可以从最大值问题变换max为+得到。因此，状态函数为：+
Sign in to comment

dp[j] = dp[j] + dp[j - nums[i]] (右边的dp[j]表示上一行中（即i－1件物品）能装满j容量的方法数)
 */

    public int backPackIV01(int[] nums, int target) {
        // Write your code here
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j = nums[i]; j <= target; j++){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[target];
    }

//------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------/////////////////
/*
    Backpack V 563

    Question

    Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
    Each item may only be used once.+

    Example
    Given candidate items [1,2,3,3,7] and target 7,
    A solution set is:
    [7]

    [1, 3, 3]
    return 2
     */


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
    }//2

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
    }//2


//------------------------------------------------------------------------------------------------
//https://zhengyang2015.gitbooks.io/lintcode/backpack_v_563.html
/*
这题和IV几乎一样, 不同的是元素只能取一次，因此内层遍历j的时候从大到小遍历（解释见III）。dp[j]表示背包容量为j时，对前i件物品且每件物品只能取一次的情况下能取的最大值。dp[0]解释一下：就是将容量为0的背包装满的方法，显然只有一种，就是什么都不装。
 */

    public int backPackV01(int[] nums, int target) {
        // Write your code here
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[target];
    }

//------------------------------------------------------------------------------------------------


//-------------------------------------------------------------------------/////////////////
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

//-------------------------------------------------------------------------/////////////////
//-------------------------------------------------------------------------/////////////////
//-------------------------------------------------------------------------/////////////////



}
