package _1strstr;

import org.junit.Test;

/**
 * Created by tzh on 1/14/17.
 */
public class _3TestBinary {

    public int bin(int input, int fold) {
        return input << fold;
    }

    @Test
    public void test01() {
        int result = bin(1, 3);
        System.out.print(result);
    }
}
