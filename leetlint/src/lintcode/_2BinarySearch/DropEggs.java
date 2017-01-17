package lintcode._2BinarySearch;

import org.junit.Test;

/**
 * Created by tzh on 1/15/17.
 */
public class DropEggs {

    public int dropEggs(int n) {
        // Write your code here
        long ans = 0;
        for (int i = 1; ; ++i) {
            ans += (long)i;
            if (ans >= (long)n)
                return i;
        }
    }

    @Test
    public void test01() {
        int result = dropEggs(10);
        System.out.print(result);
    }

    @Test
    public void test02() {
        int result = dropEggs(100);
        System.out.print(result);
    }

}
