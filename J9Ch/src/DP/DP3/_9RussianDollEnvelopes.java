package DP.DP3;


import java.util.Arrays;
import java.util.Comparator;

//• 坐标型

/*


-----------------------------------------------------------------------------------------------
LintCode 602 Russian Doll Envelopes
• 题意:
• 给定N个信封的长度和宽度
• 如果一个信封的长和宽都分别小于另一个信封的长和宽，则这个信封可 以放入另一个信封
• 问最多嵌套多少个信封
• 例子:
• 输入:[[5,4],[6,4],[6,7],[2,3]]
• 输出:3 ([2,3] => [5,4] => [6,7])

-----------------------------------------------------------------------------------------------
最长序列型动态规划
• 可能出现一个信封A能放入信封B和信封C，但是信封B和信封C互相不能放入
• 将所有信封按照长度从小到大排序:E0, E1, ..., En-1
• 这样，如果信封Ei能够放入信封Ej里，一定有i<j
• 排序后，如果一个信封Ej是最外层的信封，那么它里面的第一层信封Ei 一定满足i<j
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:设最优策略中最后一个信封，即最外层的信封，是Ei
• 考虑次外层信封是哪个
– 一定是某个Ej，j < i
• 最优策略里，以Ej为最外层信封的嵌套层数也一定是最多的
-----------------------------------------------------------------------------------------------
子问题
• 要求以Ei为最外层信封时最多的嵌套层数
• 需要知道以Ej为最外层信封时最多的嵌套层数(j < i)
• 子问题
• 状态:设f[i]表示以Ei为最外层信封时最多的嵌套层数

-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

设 f[i] 表示以Ei为最外层信封时最多的嵌套层数

f[i] = max{1, f[j]+1 | j<i 且Ej能放在Ei里 }

f[i] 以Ei为最外层信封时最多的嵌套层数
1 只用Ei这一个信封
f[j]+1 | j<i 以Ej为此外层信封时，最多的嵌套层数，加上Ei

• 计算顺序 f[0], f[1], ..., f[N-1]
•      时间O(N2)      空间O(N)
-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 设f[i]表示以Ei为最外层信封时最多的嵌套层数
• f[i] = max{1, f[j]+1| Ej 能放在Ei里，j<i}
• 无初始条件
• 需要先把所有信封按照长度排序

-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[0], f[1], ..., f[N-1]
• 时间复杂度O(N2)，空间复杂度O(N)
-----------------------------------------------------------------------------------------------
*/

//  354. Russian Doll Envelopes
//  https://leetcode.com/problems/russian-doll-envelopes/description/
//  http://www.lintcode.com/zh-cn/problem/russian-doll-envelopes/
//  5:4
public class _9RussianDollEnvelopes {
//-----------------------------------------------------------------------------
    //4
    public int maxEnvelopes_mine(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        int[] f = new int[n];
        int result = 0;
        Arrays.sort(envelopes, (a, b) ->
        {return a[1] != b[1] ? a[1]-b[1] : a[0]-b[0];});

        for (int i = 0; i < n; i++) {
            f[i] = 1; //怎么样都可以取一个信封
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] >= envelopes[i][0] |
                    envelopes[j][1] >= envelopes[i][1]){
                    continue;
                }
                //不放，f[i]//放， f[j] + 1。1就是envelope i。
                f[i] = Math.max(f[i], f[j] + 1);
            }
            result = Math.max(result, f[i]);
        }
        return result;
    }

    //重写九章给的算法，首先把ij互换
    public int longestIncreasingSubsequence_J1(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] f = new int[n];
        int result = 0;

        for (int i = 0; i < n; i++) {
            f[i] = 1;//init: 至少可以取一个数字

            //j指向的是前面的数字，i指向的是后面的数字
            for (int j = 0; j < i; j++) {//case 2
                if (nums[j] < nums[i]) {
                    //f[j]: 以f[j]结尾的最长上升子序列的长度
                    //f[i]：不取nums[j]；
                    //f[j] + 1：取nums[j]， 而nums[j] < nums[i]， nums[i]指的是1
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            result = Math.max(result, f[i]);
        }
        return  result;
    }

//------------------------------------------------------------------------------
    //1
/*
    Java NLogN Solution with Explanation
    Sort the array. Ascend on width and descend on height if width are same.
    Find the longest increasing subsequence based on height.
    Since the width is increasing, we only need to consider height.
            [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
*/

    public int maxEnvelopes01(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);

            if(index < 0)
                index = -(index + 1);

            dp[index] = envelope[1];

            if(index == len) //这个看着很眼熟啊，为什么相等的时候就可以len++？
                len++;
        }
        return len;
    }


//------------------------------------------------------------------------------
    //2
    //Simple DP solution
    public int maxEnvelopes02(int[][] envelopes) {
        if (   envelopes           == null
                || envelopes.length    == 0
                || envelopes[0]        == null
                || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int   n  = envelopes.length;
        int[] dp = new int[n];

        int ret = 0;

        for (int i = 0; i < n; i++){
            dp[i] = 1;

            for (int j = 0; j < i; j++){
                if (envelopes[i][0] > envelopes[j][0] &&
                    envelopes[i][1] > envelopes[j][1]){

                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
//------------------------------------------------------------------------------
    //3
    // 9Ch DP
    //
    public int maxEnvelopes(int[][] A) {
        if(A == null || A.length == 0) {
            return 0;
        }

        Arrays.sort(A, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0])
                    return a[1] - b[1];
                else
                    return a[0] - b[0];
            }
        });

        int n = A.length;
        int[] f = new int[n];
        int i, j, res = 0;

        for (i = 0; i < n; i++) {
            f[i] = 1; //怎么样都可以取一个信封
            for (j = 0; j < i; j++) {
                // envelope j can be put inside envelope i  ！！！！
                if (A[j][0] < A[i][0] && A[j][1] < A[i][1]) {
                    //不放，f[i]
                    //放， f[j] + 1。1就是envelope i。
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }

        return res;
    }
//------------------------------------------------------------------------------

    //5
    // 9Ch
    /**
     * @param envelopes a number of envelopes with widths and heights
     * @return the maximum number of envelopes
     */
    public int maxEnvelopes2(int[][] envelopes) {
        // Write your code here
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);

            if(index < 0)
                index = -index - 1;

            dp[index] = envelope[1];

            if (index == len)
                len++;
        }
        return len;
    }


//------------------------------------------------------------------------------
}
/*
俄罗斯套娃信封

给一定数量的信封，带有整数对 (w, h) 分别代表信封宽度和高度。一个信封的宽高均大于另一个信封时可以放下另一个信封。
求最大的信封嵌套层数。

样例
给一些信封 [[5,4],[6,4],[6,7],[2,3]] ，最大的信封嵌套层数是 3([2,3] => [5,4] => [6,7])。
 */

/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Have you met this question in a real interview? Yes
Example
Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
