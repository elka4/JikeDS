package July._4Array;

public class _3BurstBalloons_312 {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int [][]dp = new int [n+2][n+2];
        int [][]visit = new int[n+2][n+2];
        int [] arr = new int [n+2];
        for (int i = 1; i <= n; i++){
            arr[i] = nums[i-1];
        }
        arr[0] = 1;
        arr[n+1] = 1;

        return search(arr, dp, visit, 1 , n);
    }

    public int search(int []arr, int [][]dp, int [][]visit, int left, int right) {
        if(visit[left][right] == 1)
            return dp[left][right];

        int res = 0;

        for (int k = left; k <= right; ++k) {
            int midValue =  arr[left - 1] * arr[k] * arr[right + 1];

            int leftValue = search(arr, dp, visit, left, k - 1);
            int rightValue = search(arr, dp, visit, k + 1, right);

            res = Math.max(res, leftValue + midValue + rightValue);
        }

        visit[left][right] = 1;
        dp[left][right] = res;
        return res;
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


//------------------------------------------------------------------------------


}
/*死胡同:容易想到的一个思路从小往大,枚举第一次在哪吹爆气球?
• 记忆化搜索的思路,从大到小,先考虑最后的0-n-1 合并的总价值 • State:
• dp[i][j] 表示把第i到第j个气球打爆的最大价值
• Function:
• 对于所有k属于{i,j}, 表示第k号气球最后打爆。
• midValue = arr[i- 1] * arr[k] * arr[j+ 1];
• dp[i][j] = min(dp[i][k-1]+d[k+1][j]+midvalue)
• Intialize:
• for each i
• dp[i][i] = 0
• Answer:
• dp[0][n-1]
￼￼*/

/*Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 *  You are asked to burst all the balloons.
 *  If the you burst balloon i you will get
 *  nums[left] * nums[i] * nums[right] coins.
 *  Here left and right are adjacent indices of i.
 *  After the burst, the left and right then becomes adjacent.


Find the maximum coins you can collect by bursting the balloons wisely.
- You may imagine nums[-1] = nums[n] = 1.
They are not real therefore you can not burst them.
- 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Have you met this question in a real interview? Yes
Example
Given [4, 1, 5, 10]
Return 270

nums = [4, 1, 5, 10] burst 1, get coins 4 * 1 * 5 = 20
nums = [4, 5, 10]    burst 5, get coins 4 * 5 * 10 = 200
nums = [4, 10]       burst 4, get coins 1 * 4 * 10 = 40
nums = [10]          burst 10, get coins 1 * 10 * 1 = 10

Total coins 20 + 200 + 40 + 10 = 270
Tags
Related Problems
Medium Stone Game*/