package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _069_BinarySearch_Sqrt_x_M {
//    A Binary Search Solution
//    Instead of using fancy Newton's method, this plain binary search approach also works.

    public int sqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

}
