package DP.DP5;
import org.junit.Test;


/*
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
*/


//  Coins In A Line II
//  3:
public class _7CoinsInALine_II {
//------------------------------------------------------------------------------
/*  https://aaronice.gitbooks.io/lintcode/content/dynamic_programming/coins_in_a_line_ii.html
    Analysis
    动态规划4要素

    State:
    dp[i] 现在还剩i个硬币，现在当前取硬币的人最后最多取硬币价值

    Function:
    i 是所有硬币数目
    sum[i] 是后i个硬币的总和
    dp[i] = sum[i]-min(dp[i-1], dp[i-2])

    Intialize:
    dp[0] = 0
    dp[1] = coin[n-1]
    dp[2] = coin[n-2] + coin[n-1]

    Answer:
    dp[n]
    可以画一个树形图来解释：
                      [5, 1, 2, 10] dp[4]
            (take 5) /             \ (take 5, 1)
                    /               \
            [1, 2, 10] dp[3]         [2, 10] dp[2]
    (take 1) /     \ (take 1, 2)  (take 2) / \ (take 2, 10)
            /       \                     /   \
      [2, 10] dp[2]  [10] dp[1]     [10] dp[1] [] dp[0]

    也就是说，每次的剩余硬币价值最多值dp[i]，是当前所有剩余i个硬币价值之和sum[i]，
    减去下一手时对手所能拿到最多的硬币的价值，即 dp[i] = sum[i] - min(dp[i - 1], dp[i - 2])
     */

//----------------------------------------------------------------------------
    //1
    // linpz version
    //这个是最好的做法
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n = values.length;
        int[] sum = new int[n + 1];
        //A = [1,2,4] n = 3
        for (int i = 1; i <= n; ++i)//sum是从右向左的presum
            sum[i] = sum[i -  1] + values[n - i];
            /*
                i=1 sum[1] : sum[0] + values[2] = 4
                i=2 sum[2] : sum[1] + values[1] = 4 + 2 = 6
                i=3 sum[3] : sum[2] + values[0] = 6 + 1 = 7

             */



        int[] dp = new int[n + 1];//到
        dp[1] = values[n - 1];//4

        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.max(sum[i] - dp[i - 1], sum[i] - dp[i - 2]);
            System.out.println("dp[i]: " + dp[i]);
        }
        return dp[n]  > sum[n] / 2;
    }

    @Test
    public void test01(){
        int[] A = {1,2,4};
        System.out.println(firstWillWin(A));
    }
    /*
    dp[1]: 4
    dp[2]: 6
    dp[3]: 3
    false
     */

//------------------------------------------------------------------------------
    //2
    // 方法一
    public class Solution2 {
        /**
         * @param values: an array of integers
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int[] values) {
            // write your code here
            int []dp = new int[values.length + 1];
            boolean []flag =new boolean[values.length + 1];
            int sum = 0;
            for(int now : values)
                sum += now;

            return sum < 2*MemorySearch(values.length, dp, flag, values);
        }
        int MemorySearch(int n, int []dp, boolean []flag, int []values) {
            if(flag[n] == true)
                return dp[n];
            flag[n] = true;
            if(n == 0)  {
                dp[n] = 0;
            } else if(n == 1) {
                dp[n] = values[values.length-1];
            } else if(n == 2) {
                dp[n] = values[values.length-1] + values[values.length-2];
            } else if(n == 3){
                dp[n] = values[values.length-2] + values[values.length-3];
            } else {
                dp[n] = Math.max(
                        Math.min(MemorySearch(n-2, dp, flag,values) ,
                                MemorySearch(n-3, dp, flag, values)) + values[values.length-n],
                        Math.min(MemorySearch(n-3, dp, flag, values),
                                MemorySearch(n-4, dp, flag, values)) + values[values.length-n] +
                                values[values.length - n + 1]
                );
            }

            return dp[n];
        }
    }

//------------------------------------------------------------------------------
    //3
    // 方法二
    public class Solution3 {
        /**
         * @param values: an array of integers
         * @return: a boolean which equals to true if the first player will win
         */
        public boolean firstWillWin(int[] values) {
            // write your code here
            int n = values.length;
            int []dp = new int[n + 1];
            boolean []flag =new boolean[n + 1];
            int []sum = new int[n+1];
            int allsum = values[n-1];
            sum[n-1] = values[n-1];
            for(int i = n-2; i >= 0; i--) {
                sum[i] += sum[i+1] + values[i];
                allsum += values[i];
            }
            return allsum/2 < MemorySearch(0, n, dp, flag, values, sum);
        }
        int MemorySearch(int i, int n, int []dp, boolean []flag, int []values, int []sum) {
            if(flag[i] == true)
                return dp[i];
            flag[i] = true;
            if(i == n)  {
                dp[n] = 0;
            } else if(i == n-1) {
                dp[i] = values[i];
            } else if(i == n-2) {
                dp[i] = values[i] + values[i + 1];
            } else {
                dp[i] = sum[i] -
                        Math.min(MemorySearch(i+1, n, dp, flag, values, sum) , MemorySearch(i+2, n, dp, flag, values, sum));
            }
            return dp[i];
        }

    }

//------------------------------------------------------------------------------
}
/*
有 n 个不同价值的硬币排成一条线。两个参赛者轮流从左边依次拿走 1 或 2 个硬币，直到没有硬币为止。

计算两个人分别拿到的硬币总价值，价值高的人获胜。

请判定 第一个玩家 是输还是赢？

您在真实的面试中是否遇到过这个题？ Yes
样例
给定数组 A = [1,2,2], 返回 true.

给定数组 A = [1,2,4], 返回 false.

标签
 */