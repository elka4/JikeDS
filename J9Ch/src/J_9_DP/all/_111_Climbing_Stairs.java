package J_9_DP.all;

/** 111 Climbing Stairs
 * Easy

 * Created by tianhuizhu on 6/28/17.
 */

/*
 * Climbing Stairs
￼￼• state: f[i]表示跳到第i个位置的方案总数 f[n + 1]
• function: f[i] = f[i-1] + f[i-2]
• initialize: f[0] = 1
• answer: f[n] // index from 0~n
 */

public class _111_Climbing_Stairs {

    //be careful with the edge value
    //这一版最原始，使用的空间为n
    //
    public int climbStairs_1(int n) {
        if (n <= 1){
            return 1;
        }
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

//-------------------------------------------------------------------------///////

    //因为只使用了f[i - 1] 和 f[i - 2]，说明i只和i-1，i-2
    //有关，因此可以简化f[n] 为 f[2]
    public int climbStairs_2(int n) {
        // write your code here
        if (n <= 1) {
            return 1;
        }
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            f[i % 2] = f[(i - 1) % 2] + f[(i - 2) % 2];
        }
        return f[n % 2];
    }

//-------------------------------------------------------------------------///////

    //和上面本质一样, 但是省去了 % mod 计算
    //  last     ~ f[0] ~ f[i - 2] ~  f[(i - 2) % 2]
    //  lastlast ~ f[1] ~ f[i - 1] ~  f[(i - 1) % 2]
    //  now      ~      ~ f[i]     ~  f[i % 2]
    public int climbStairs_3(int n) {
        if (n <= 1) {
            return 1;
        }
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }
}
