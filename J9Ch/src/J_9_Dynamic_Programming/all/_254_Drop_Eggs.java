package J_9_Dynamic_Programming.all;

/** 254 Drop Eggs


 * Created by tianhuizhu on 6/28/17.
 */
public class _254_Drop_Eggs {
    /**
     * @param n an integer
     * @return an integer
     */
    public int dropEggs(int n) {
        // Write your code here
        long ans = 0;
        for (int i = 1; ; ++i) {
            ans += (long)i;
            if (ans >= (long)n)
                return i;
        }
    }



}