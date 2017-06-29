package S_4_Binary_Search_Sweep_Line.Required_10;

/**
141
Sqrt(x)

 * Created by tianhuizhu on 6/28/17.
 */
public class _141_Sqrtx_Easy {
    class Solution {
        /**
         * @param x: An integer
         * @return: The sqrt of x
         */
        public int sqrt(int x) {
            // find the last number which square of it <= x
            long start = 1, end = x;
            while (start + 1 < end) {
                long mid = start + (end - start) / 2;
                if (mid * mid <= x) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (end * end <= x) {
                return (int) end;
            }
            return (int) start;
        }
    }
}
