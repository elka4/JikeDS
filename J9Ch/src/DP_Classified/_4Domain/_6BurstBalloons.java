package DP_Classified._4Domain;

//Burst Balloons
public class _6BurstBalloons {
    //记忆化
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


}
