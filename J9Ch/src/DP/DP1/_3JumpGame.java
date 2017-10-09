package DP.DP1;

//存在型动态规划

//• 坐标型动态规划

/*
   f[j] = OR 0<=i<j(f[i] AND i + a[i] >= j)

f[j]:           青蛙能不能跳到 石头j
0<=i<j:         枚举上一个跳到 的石头i
f[i]:           青蛙能不能跳到 石头i
i + a[i] >= j:  最后一步的距离 不能超过ai

• 设f[j]表示青蛙能不能跳到石头j
• 初始条件:f[0] = True，因为青蛙一开始就在石头0

• 设f[j]表示青蛙能不能跳到石头j
• f[j] = OR0<=i<j(f[i] AND i + a[i] >= j)
• 初始化f[0]=True
• 计算f[1], f[2], ..., f[n-1]
• 答案是f[n-1]
• 时间复杂度:O(N2)，空间复杂度(数组大小):O(N)

 */

public class _3JumpGame {
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

//////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////

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


//////////////////////////////////////////////////////////////////
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
