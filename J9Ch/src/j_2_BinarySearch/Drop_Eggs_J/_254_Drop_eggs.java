package j_2_BinarySearch.Drop_Eggs_J; import org.junit.Test;

/**
 * Created by tianhuizhu on 6/19/17.
 */
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