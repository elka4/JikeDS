package _0BinarySearch.binary;

import org.junit.Test;

/**
 * Created by tzh on 1/16/17.
 */
public class sqr {


    public int mySqrt(int x) {
        long start = 1;

        long end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            long sqr = mid * mid;
            if (sqr == x) {
                start = mid;
            } else if (sqr < x) {
                start = mid;
            } else {
                end = mid;
            }

        }
        if (end * end <= x) {
            return (int)end;
        } else {
            return (int)start;
        }
    }

    @Test
    public void test01() {
        int result = mySqrt(0);
        System.out.print(result);
    }

}
