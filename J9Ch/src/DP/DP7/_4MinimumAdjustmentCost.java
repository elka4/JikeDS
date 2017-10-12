package DP.DP7;

import java.util.ArrayList;

/*
-----------------------------------------------------------------------------------------------
LintCode 91 Minimum Adjustment Cost

• 题意:
• 给定数组A，每个元素是不超过100的正整数
• 将A中每个元素修改后形成数组B
• 要求B中任意两个相邻的元素的差不能超过Target
• 求最小修改代价，即|A[0]-B[0]| + ... + |A[n-1]-B[n-1]|
• 例子:
• 输入:A=[1, 4, 2, 3], Target = 1
• 输出:2 (B=[2, 3, 2, 3])
-----------------------------------------------------------------------------------------------
题目分析

• 可以证明，最优策略中B的每个元素也一定是不超过100的正整数
• 否则，将B中小于1的数改成1，大于100的数改成100
• 总的修改代价更小，且仍然满足B的任意两个相邻元素的差不大于Target
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:将A改成B，A[n-1]改成X，这一步代价是|A[n-1]-X|
• 需要确保|X-B[n-2]| <= Target
• 前面n-1个元素A[0..n-2]改成B[0..n-2]，需要知道最小代价，并确保 B[0..n-2]中任意两个相邻的元素的差不超过Target 子问题
• 但是有一个问题，改A[n-1]时不知道B[n-2]是多少
    –只有知道了B[n-2]，才能确定A[n-1]能改成B[n-2]-Target <= X <=B[n-2]+Target
• 不知道是多少就记录下来:序列加状态
• 设状态f[i][j]为将A前i个元素改成B的最小代价，确保前i个改好的元素中 任意两个相邻的元素的差不超过Target，并且A[i-1]改成j
• 这样，如果A[i-1]改成j，A[i-2]就必须改成j-Target <= k <= j+Target
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程


• 设f[i][j]表示将A前i个元素改成B的最小代价，确保前i个改好的元素中 任意两个相邻的元素的差不超过Target，并且A[i-1]改成j
f[i][j] = minj-Target<=k<=j+Target, 1<=k<=100{f[i-1][k] + |j-A[i-1]|}

f[i][j]   将A前i个元素改成B的最 小代价，A[i-1]改成j
f[i-1][k] 将A前i-1个元素改成B的 最小代价，A[i-2]改成k
j-A[i-1]  A[i-1]改成j的代价
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[i][j]表示将A前i个元素改成B的最小代价，确保前i个改好的元素中 任意两个相邻的元素的差不超过Target，并且A[i-1]改成j
• 初始条件:A的第一个元素可以变换成任意数字
    – 因为之前没有相邻的元素
    – f[1][j]=|j-a[0]| (j = 1, 2, ..., 100)
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[1][1], f[1][2], ..., f[1][100]
•...
• f[N][1], f[N][2], ..., f[N][100]
• 答案是min{f[N][1], f[N][2], ..., f[N][100]}
• 时间复杂度O(1002N)，空间复杂度O(100N)，可以用滚动数组优化至 O(100)
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
 */

//  Minimum Adjustment Cost
public class _4MinimumAdjustmentCost {

    // 9Ch DP
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        int n = A.size();
        int[][] f = new int[n + 1][100 + 1];
        int i, j, k, res;
        // init
        for (i = 0; i <= 100; i++) {
            f[1][i] = Math.abs(A.get(0) - i);
        }

        for (i = 2; i <= n; i++) {
            for (j = 1; j <= 100; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (k = j - target; k <= j + target; k++) {
                    if (k < 1 || k > 100) {
                        continue;
                    }
                    f[i][j] = Math.min(f[i][j], f[i - 1][k] + Math.abs(A.get(i - 1) - j));
                }
            }
        }
        res = Integer.MAX_VALUE;
        for (i = 1; i <= 100; i++) {
            res = Math.min(res, f[n][i]);

        }
        return res;
    }


//////////////////////////////////////////////////////////////////////////
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost2(ArrayList<Integer> A, int target) {
        // write your code here
        int n = A.size();
        int[][] f = new int[n + 1][101];
        for (int i = 0; i <= n ; ++i)
            for (int j = 0; j <=100; ++j)
                f[i][j] = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i)
            f[0][i] = 0;
        for (int i = 1; i <=n; ++i)
            for (int  j = 0; j <= 100;++j)
                if (f[i-1][j] != Integer.MAX_VALUE)
                    for (int k = 0; k <= 100; ++k)
                        if (Math.abs(j-k) <= target)
                            if (f[i][k] > f[i-1][j] + Math.abs(A.get(i-1)-k))
                                f[i][k] = f[i-1][j] + Math.abs(A.get(i-1)-k);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i)
            if (f[n][i] < ans)
                ans = f[n][i];
        return ans;
    }

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

}
/*
Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

 Notice

You can assume each number in the array is a positive integer and not greater than 100.

Have you met this question in a real interview? Yes
Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.
 */
