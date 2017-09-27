package j_2_BinarySearch;

import org.junit.Test;

/**
 * Created by tianhuizhu on 6/19/17.
 */
public class _254drop_eggs {
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
