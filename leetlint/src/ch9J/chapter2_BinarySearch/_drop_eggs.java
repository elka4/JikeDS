package ch9J.chapter2_BinarySearch;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class _drop_eggs {
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
