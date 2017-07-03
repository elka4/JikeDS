package lintcode._1strstr;

import org.junit.Test;

/**
 * Created by tzh on 1/14/17.
 */
public class _01strstr {
    public int strStr(String source, String target) {
        // write your code here

        if (source == null || target == null  ||source.length() < target.length()) {
            return -1;
        }
        if (source == target) {
            return 0;
        }



        for (int i = 0; i < source.length() - target.length(); i++) {
            int j = 0;
            for (; j < target.length(); j++) {
                if (target.charAt(j) != source.charAt(i + j)) {
                    break;
                }

            }

            if (j == target.length()) {
                return i;
            }

        }

        return -1;
    }

    @Test
    public void test01() {
        String source = "abcdabcdefg";
        String target = "bcd";

        int result = strStr(source, target);
        System.out.println(result);
    }

    @Test
    public void test02 () {
        String source = "";
        String target = "";
        int result = strStr(source, target);
        System.out.print(result);
    }

    @Test
    public void test03() {
        String source = "lintcode";
        String target = "lintcode";
        int result = strStr(source, target);
        System.out.print(result);
    }

    @Test
    public void test04() {
        String source = "abcde";
        String target = "e";
        int result = strStr(source, target);
        System.out.print(result);
    }
}
