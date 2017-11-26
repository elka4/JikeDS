package DP.DP1;

//存在型动态规划

//• 坐标型动态规划

/*
-----------------------------------------------------------------------------------------------
LintCode 116 Jump Game

• 有n块石头分别在x轴的0, 1, ..., n-1位置
• 一只青蛙在石头0，想跳到石头n-1
• 如果青蛙在第i块石头上，它最多可以向右跳距离ai
• 问青蛙能否跳到石头n-1
• 例子:
• 输入:a=[2, 3, 1, 1, 4] • 输出:True
• 输入:a=[3, 2, 1, 0, 4] • 输出:False
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:如果青蛙能跳到最后一块石头n-1，我们考虑它跳的最后一步
• 这一步是从石头i跳过来，i<n-1
• 这需要两个条件同时满足:
    – 青蛙可以跳到石头i
    – 最后一步不超过跳跃的最大距离:n-1-i<=ai
-----------------------------------------------------------------------------------------------
子问题

• 那么，我们需要知道青蛙能不能跳到石头i (i<n-1)
• 而我们原来要求青蛙能不能跳到石头n-1
• 子问题
• 状态:设f[j]表示青蛙能不能跳到石头j
-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[j]表示青蛙能不能跳到石头j

   f[j] = OR 0<=i<j(f[i] AND i + a[i] >= j)

f[j]:           青蛙能不能跳到 石头j
0<=i<j:         枚举上一个跳到 的石头i
f[i]:           青蛙能不能跳到 石头i
i + a[i] >= j:  最后一步的距离 不能超过ai
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[j]表示青蛙能不能跳到石头j
• 初始条件:f[0] = True，因为青蛙一开始就在石头0
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• 设f[j]表示青蛙能不能跳到石头j
• f[j] = OR0<=i<j(f[i] AND i + a[i] >= j)
• 初始化f[0]=True
• 计算f[1], f[2], ..., f[n-1]
• 答案是f[n-1]
• 时间复杂度:O(N2)，空间复杂度(数组大小):O(N)
-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
 */

//  55. Jump Game
//  https://leetcode.com/problems/jump-game/description/
//
public class _3JumpGame {
//----------------------------------------------------------------------------
    //https://leetcode.com/problems/jump-game/solution/
//----------------------------------------------------------------------------

//----------------------------------------------------------------------------
    //1
    // version 1: Dynamic Programming
    // 这个方法，复杂度是 O(n^2) 可能会超时，但是依然需要掌握。
    public boolean canJump(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[A.length - 1];
    }

//-----------------------------------------------------------------------------
    //2
    // version 2: Greedy
    public boolean canJump2(int[] A) {
        // think it as merging n intervals
        if (A == null || A.length == 0) {
            return false;
        }
        int farthest = A[0];
        for (int i = 1; i < A.length; i++) {
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= A.length - 1;
    }

//-----------------------------------------------------------------------------
    //3
    // 9CH DP
    /*
    stone 1: A[0]
    stone 2: A[1]
    ...

    stone n: A[n - 1]
     */
    public boolean canJump3(int[] A) {
        if(A == null || A.length == 0) {
            return false;
        }
        int n = A.length;
        boolean[] f = new boolean[n];

        //init
        f[0] = true;

        for (int  j = 1; j < n; ++j) {
            //previous stone (last step)
            f[j] = false;
            for (int i = 0; i < j; ++i) {
                // 这里就是 OR 操作。 一旦满足就break。
                if (f[j] && i + A[i] >= j) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n - 1];
    }


//-----------------------------------------------------------------------------
}

/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 Notice

This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n).

The time complexity of Dynamic Programming method is O(n^2).

We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.

Have you met this question in a real interview? Yes
Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 */
