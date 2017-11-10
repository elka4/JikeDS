package _BinarySearch.Drop_Eggs_J; import org.junit.Test;


//
//
//
public class _254_Drop_eggs {
    /**
     * @param n an integer
     * @return an integer
     */
    public int dropEggs(int n) {
        // Write your code here
        long ans = 0;
        for (int i = 1; ; ++i) {
            ans += (long)i;
            System.out.println("i: " + i + "; ans: " + ans);
            if (ans >= (long)n)
                return i;
        }
    }

    public int dropEggs_mine(int n) {
        // Write your code here
        long ans = 0;
        int i = 0;
        while(true) {
            ans += (long)++i;
            if (ans >= (long)n)
                return i;
        }
    }

/*  https://www.jiuzhang.com/qa/2711/
1.
首先，我们有两个鸡蛋，那么第一个鸡蛋一定是按照一定的间距来扔的，通过第一个鸡蛋找到的间距中再一个一个的试过去。
举个例子，假如当前我们要解决的问题是100层楼，那么假如我们一开始按照10作为间距的话，如果n是9的话，我们就要扔一次第一个鸡蛋，扔9次第二个鸡蛋，也就是需要10次，但这个明显不是最坏的情况，如果n是99的话，我们就需要扔10次第一个鸡蛋，扔9次第二个鸡蛋，那么需要19次。这种情况就是最坏的情况，现在再反过来想，我们一开始以10作为间距真的是最优的解吗，很明显如果我们是以等间距扔第一个鸡蛋的话我们每一个区间的最坏查询情况都是不相等的，最坏的查询情况肯定是最后一个区间的最后一个数。

2.
那么再想，如果我们能够让每一个区间的最坏查询次数都相等的话，最坏的查询就能够达到最优的方案，那么怎么才能达到这个条件呢。
仔细思考，查询到第二个区间的时候已经扔了一次第一个鸡蛋了，查询到第x个区间的时候已经扔了x-1次第一个鸡蛋了，所以显然如果我们等间距的话我们每一个区间的查询都要比前面一个区间多1次，所以我们可以每过一个区间把区间长度-1，这样就能够达到最佳的方案。
那么最大的那个区间怎么进行计算呢？
其实到这一步不难发现，这个最大区间就是我们要求的最坏的情况的查询数了。
n + (n-1) + (n-2)…… + 1 = N。
这样我们就可以用简单的循环来求得这个n。
 */


//http://blog.csdn.net/joylnwang/article/details/6769160

//http://www.it610.com/article/5223615.htm

//
    @Test
    public void test02(){
        System.out.println(dropEggs(10));
        System.out.println(dropEggs_mine(10));


        System.out.println(dropEggs(100));
        System.out.println(dropEggs_mine(100));

    }

}
/*
There is a building of 100 floors. If an egg drops from the Nth floor or above, it will break. If it's dropped from any floor below, it will not break. You're given two eggs. Find N, while minimizing the number of drops for the worst case.

Let us check the DP solution , Thanks to @zhouyangnk

sub-problem def.

dp[n][m] : the minimal max drops we need if we have n-floor building and m eggs.

base case def.

dp[0][m] = 0 (m >= 1)

dp[n][1] = n (n >= 1)

dp[n][m] = min { max { dp[i-1][m-1], dp[n-i][m] } } + 1 ( n >= i >= 1 )
 */