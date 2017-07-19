package J_9_Dynamic_Programming.all;

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


    public int climbStairs3(int n) {
        // Write your code here

        if (n <= 1) {
            return 1;
        }
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            f[i % 3] = f[(i - 1) % 3] + f[(i - 2) % 3] + f[(i - 3) % 3];
        }
        return f[n % 3];
    }

    /*
    A child is running up a staircase with n steps, and can hop either 1 step,
     2 steps, or 3 steps at a time. Implement a method to count how many possible
     ways the child can run up the stairs.


     */


}
