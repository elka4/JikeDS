package DP.DP7;
import groovy.transform.ToString;
import org.junit.Test;
import java.util.*;
import java.util.HashSet;

/*
-----------------------------------------------------------------------------------------------
LintCode 622 Frog Jump

• 题意:
• 有一条小河上有N个石头，位置依次在a0<a1<...<an-1
• 有一只青蛙在第一个石头上
• 青蛙一开始可以向右跳距离为1
• 它必须一致向右跳，并且落在石头上
• 如果上次跳的距离是L，这次跳的距离可以是L-1, L或者L
• 问能否到达最后一个石头
• 例子:
• 输入:[0,1,3,5,6,8,12,17]
• 输出:true(0à1à3à5à8à12à17)
-----------------------------------------------------------------------------------------------
动态规划组成部分一:确定状态

• 最后一步:如果可以跳到最后一个石头an-1，考虑最后跳的一步L
• 青蛙一定是从某块石头ai= an-1-L跳过来的
• 所以考虑是否能跳到ai
• 但是倒数第二跳只能是L-1,L或者L+1

-----------------------------------------------------------------------------------------------
子问题

• 要求是否能最后一跳L跳到最后一个石头an-1
• 需要知道能否最后一跳L-1, L或者L跳到石头ai= an-1-L
• 子问题
• 状态:设f[i][j]表示是否能最后一跳j跳到石头ai
• 坐标+状态型动态规划


-----------------------------------------------------------------------------------------------
动态规划组成部分二:转移方程

• 设f[i][j]表示是否能最后一跳j跳到石头ai
• 设上一块石头是ak=ai-j，可以通过一个哈希表(akàk)快速找到k     不需要枚举

f[i][j] = f[k][j-1] OR f[k][j] OR f[k][j+1]
f[k][j-1] 能最后一跳j-1跳到石头ak
f[k][j]   能最后一跳j跳到石头ak
f[k][j+1] 能最后一跳j+1跳到石头ak

-----------------------------------------------------------------------------------------------
动态规划组成部分三:初始条件和边界情况

• 因为第一步跳的距离是1，一直向右跳，最多跳N-1步，所以一步最大跳 跃距离是N-1
• 简单情况:如果只有一块石头，直接输出TRUE
• 如果石头1和石头0距离不是1，直接输出FALSE
• 第一步跳跃距离必须是1:f[1][1] = TRUE, f[1][2] = ... = f[1][N-1] = FALSE
-----------------------------------------------------------------------------------------------
动态规划组成部分四:计算顺序

• f[1][1], f[1][2], ..., f[1][N-1]
•...
• f[N-1][1], f[N-1][2], ..., f[N-1][N-1]
• 如果f[N-1][1], f[N-1][2], ..., f[N-1][N-1]中有任何一个是true，答案是true， 否则是false
• 时间复杂度O(N2)，空间复杂度O(N2)，不能用滚动数组优化，因为f[i][j] 有可能依赖之前任何一个f[h][k]

-----------------------------------------------------------------------------------------------
优化:动态规划加哈希表

• 在实际操作中，可以到达一个石头的最后一跳的值经常很少
    – 即f[i][1..N-1]中很多都是FALSE
    – 没有必要计算，因为只关心f[i][j]=TRUE的i和j
• f[i][j] = f[k][j-1] OR f[k][j] OR f[k][j+1]
• 反过来想，如果已知f[k][j]=TRUE，即可以最后一跳j到达石头ak • 则可以跳到ak+j-1, ak+j和ak+j+1，如果那里恰好有石头的话
-----------------------------------------------------------------------------------------------
动态规划加哈希表

• 我们用一个集合Si保存能跳到一个石头ai的可能的最后一跳
    –其实就是原来的转移方程中f[i][j] =TRUE的那些j
-----------------------------------------------------------------------------------------------
优化:动态规划加哈希表

• 枚举每一个在集合Si中的L，从石头i尝试往后跳L-1, L, L+1
• 如果跳了M距离之后有一个石头j，则把M加到Sj中，表示可以最后一步跳M到达石头j
    – 也就是f[j][M] = TRUE
• 实际使用空间小
-----------------------------------------------------------------------------------------------
 */

//  403. Frog Jump
//  https://leetcode.com/problems/frog-jump/description/
//  http://www.lintcode.com/zh-cn/problem/frog-jump/
public class _5FrogJump {
    //https://leetcode.com/problems/frog-jump/solution/

    //Approach #1 Brute Force [Time Limit Exceeded]
    public class Solution01 {
        public boolean canCross(int[] stones) {
            return can_Cross(stones, 0, 0);
        }
        public boolean can_Cross(int[] stones, int ind, int jumpsize) {
            for (int i = ind + 1; i < stones.length; i++) {
                int gap = stones[i] - stones[ind];
                if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                    if (can_Cross(stones, i, gap)) {
                        return true;
                    }
                }
            }
            return ind == stones.length - 1;
        }
    }

    //Approach #2 Better Brute Force[Time Limit Exceeded]
    public class Solution02 {
        public boolean canCross(int[] stones) {
            return can_Cross(stones, 0, 0);
        }
        public boolean can_Cross(int[] stones, int ind, int jumpsize) {
            if (ind == stones.length - 1) {
                return true;
            }
            int ind1 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize);
            if (ind1 >= 0 && can_Cross(stones, ind1, jumpsize)) {
                return true;
            }
            int ind2 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize - 1);
            if (ind2 >= 0 && can_Cross(stones, ind2, jumpsize - 1)) {
                return true;
            }
            int ind3 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize + 1);
            if (ind3 >= 0 && can_Cross(stones, ind3, jumpsize + 1)) {
                return true;
            }
            return false;
        }
    }

    //Approach #3 Using Memorization [Accepted]
    public class Solution03 {
        public boolean canCross(int[] stones) {
            int[][] memo = new int[stones.length][stones.length];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return can_Cross(stones, 0, 0, memo) == 1;
        }
        public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
            if (memo[ind][jumpsize] >= 0) {
                return memo[ind][jumpsize];
            }
            for (int i = ind + 1; i < stones.length; i++) {
                int gap = stones[i] - stones[ind];
                if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                    if (can_Cross(stones, i, gap, memo) == 1) {
                        memo[ind][gap] = 1;
                        return 1;
                    }
                }
            }
            memo[ind][jumpsize] = (ind == stones.length - 1) ? 1 : 0;
            return memo[ind][jumpsize];
        }
    }

    //Approach #4 Using Memorization with Binary Search [Accepted]
    public class Solution04 {
        public boolean canCross(int[] stones) {
            int[][] memo = new int[stones.length][stones.length];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return can_Cross(stones, 0, 0, memo) == 1;
        }
        public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
            if (memo[ind][jumpsize] >= 0) {
                return memo[ind][jumpsize];
            }
            int ind1 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize);
            if (ind1 >= 0 && can_Cross(stones, ind1, jumpsize, memo) == 1) {
                memo[ind][jumpsize] = 1;
                return 1;
            }
            int ind2 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize - 1);
            if (ind2 >= 0 && can_Cross(stones, ind2, jumpsize - 1, memo) == 1) {
                memo[ind][jumpsize - 1] = 1;
                return 1;
            }
            int ind3 = Arrays.binarySearch(stones, ind + 1, stones.length, stones[ind] + jumpsize + 1);
            if (ind3 >= 0 && can_Cross(stones, ind3, jumpsize + 1, memo) == 1) {
                memo[ind][jumpsize + 1] = 1;
                return 1;
            }
            memo[ind][jumpsize] = ((ind == stones.length - 1) ? 1 : 0);
            return memo[ind][jumpsize];
        }
    }

    //Approach #5 Using Dynamic Programming[Accepted]
    public class Solution05 {
        public boolean canCross(int[] stones) {
            HashMap<Integer, Set<Integer>> map = new HashMap<>();
            for (int i = 0; i < stones.length; i++) {
                map.put(stones[i], new HashSet<Integer>());
            }
            map.get(0).add(0);
            for (int i = 0; i < stones.length; i++) {
                for (int k : map.get(stones[i])) {
                    for (int step = k - 1; step <= k + 1; step++) {
                        if (step > 0 && map.containsKey(stones[i] + step)) {
                            map.get(stones[i] + step).add(step);
                        }
                    }
                }
            }
            return map.get(stones[stones.length - 1]).size() > 0;
        }
    }


////////////////////////////////////////////////////////////////////////
    // 9Ch DP
    public boolean canCross(int[] stones) {

        int n = stones.length;
        HashMap<Integer, HashSet<Integer>> f = new HashMap<>();

        int i, j;

        for (i = 0; i < n; i++) {
            f.put(stones[i], new HashSet<Integer>());
        }

        f.get(stones[0]).add(0);

        for (i = 0; i < n - 1; i++) {
            // tmp: f[A[i]]
            HashSet<Integer> tmp = new HashSet<Integer>(f.get(stones[i]));
            for (int k :tmp) {
                for (int delta = -1; delta <= 1; delta++) {
                    int t = stones[i] + (k + delta);
                    if (f.containsKey(t)) {
                        f.get(t).add(k + delta);
                    }
                }
            }
        }
        return !f.get(stones[n - 1]).isEmpty();
    }

    @Test
    public void test01() {
        int[] stones = {0,1,2,3,4,8,9,11};
        System.out.println(canCross(stones));
    }


////////////////////////////////////////////////////////////////////////
    /**
     * @param stones a list of stones' positions in sorted ascending order
     * @return true if the frog is able to cross the river or false
     */
    public boolean canCross2(int[] stones) {
        // Write your code here
        HashMap<Integer, HashSet<Integer>> dp =
                new HashMap<Integer, HashSet<Integer>>(stones.length);
        for (int i = 0; i < stones.length; i++) {
            dp.put(stones[i], new HashSet<Integer>() );
        }
        dp.get(0).add(0);

        for (int i = 0; i < stones.length - 1; ++i) {
            int stone = stones[i];
            for (int k : dp.get(stone)) {
                // k - 1
                if (k - 1 > 0 && dp.containsKey(stone + k - 1))
                    dp.get(stone + k - 1).add(k - 1);
                // k
                if (dp.containsKey(stone + k))
                    dp.get(stone + k).add(k);
                // k + 1
                if (dp.containsKey(stone + k + 1))
                    dp.get(stone + k + 1).add(k + 1);
            }
        }

        return !dp.get(stones[stones.length - 1]).isEmpty();
    }

////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////
}

/*
一只青蛙正要过河，这条河分成了 x 个单位，每个单位可能存在石头，青蛙可以跳到石头上，但它不能跳进水里。
按照顺序给出石头所在的位置，判断青蛙能否到达最后一块石头所在的位置。刚开始时青蛙在第一块石头上，假设青蛙第一次跳只能跳一个单位的长度。
如果青蛙最后一个跳 k 个单位，那么它下一次只能跳 k - 1 ，k 或者 k + 1 个单位。注意青蛙只能向前跳。

 注意事项

石头的个数 >= 2并且 <= 1100。
每块石头的位置是一个非负数并且 < 2^31。
第一块石头的位置总是 0.

样例
给出石头的位置为 [0,1,3,5,6,8,12,17]

总共8块石头。
第一块石头在 0 位置，第二块石头在 1 位置，第三块石头在 3 位置等等......
最后一块石头在 17 位置。

返回 true。青蛙可以通过跳 1 格到第二块石头，跳 2 格到第三块石头，跳 2 格到第四块石头，跳 3 格到第六块石头，跳 4 格到第七块石头，最后跳 5 格到第八块石头。

给出石头的位置为 `[0,1,2,3,4,8,9,11]`
返回 false。青蛙没有办法跳到最后一块石头因为第五块石头跟第六块石头的距离太大了。
 */


/*
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

 Notice

The number of stones is ≥ 2 and is < 1100.
Each stone's position will be a non-negative integer < 2^31.
The first stone's position is always 0.
Have you met this question in a real interview? Yes
Example
Given stones = [0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping
1 unit to the 2nd stone, then 2 units to the 3rd stone, then
2 units to the 4th stone, then 3 units to the 6th stone,
4 units to the 7th stone, and 5 units to the 8th stone.

Given stones = `[0,1,2,3,4,8,9,11]`

Return false. There is no way to jump to the last stone as
the gap between the 5th and 6th stone is too large.


 */