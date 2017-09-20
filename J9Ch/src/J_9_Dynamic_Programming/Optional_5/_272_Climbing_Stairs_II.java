package J_9_Dynamic_Programming.Optional_5;

/** 272 Climbing Stairs II


 * Created by tianhuizhu on 6/28/17.
 */
public class _272_Climbing_Stairs_II {
    /**
     * @param n an integer
     * @return an integer
     */
    public int climbStairs2(int n) {
        int[] f = new int[n+1];
        f[0] = 1;
        for (int i = 0; i <= n; i++)
            for (int j = 1; j <= 3; j++) {
                if (i >= j) {
                    f[i] += f[i-j];
                }
            }
        return f[n];
    }


}
