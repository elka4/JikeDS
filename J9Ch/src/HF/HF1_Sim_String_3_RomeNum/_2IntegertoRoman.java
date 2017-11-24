package HF.HF1_Sim_String_3_RomeNum;
import java.util.*;

/*
Example:
• 421= CDXXI
• 数位分离之后直接转换
• 如何数位分离? % k / k
• (扩展)如何将一个数转成k进制?  %10 /10
 */

/*
 小技巧总结:
• 如何数位分离? %10 /10 • (扩展)如何将一个数转成k进制? % k / k
 */

/*
能力维度:
1. 理解问题
3. 基础数据结构/算法
 */

import org.junit.Test;

//Integer to Roman
public class _2IntegertoRoman {
        /*
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        // write your code here
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[n / 1000] + C[(n/100) % 10] + X[(n/10) % 10] + I[n % 10];
    }

    List<Integer>  change(int n, int k){
        int i = 0;
//        int k = 2;
//        int[] digit = new int[10];
        List<Integer> digit = new LinkedList<>();
        while (n != 0) {
            digit.add(0, n % k);
            n /= k;
        }
        return digit;
    }

    @Test
    public void test01(){
        System.out.println(change(1,2));
        System.out.println(change(2,2));
        System.out.println(change(3,2));
        System.out.println(change(4,2));
        System.out.println(change(5,2));

    }


//------------------------------------------------------------------------------//////////

    public String intToRoman2(int num) {
        if(num <= 0) {
            return "";
        }
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        int digit=0;
        while (num > 0) {
            int times = num / nums[digit];
            num -= nums[digit] * times;
            for ( ; times > 0; times--) {
                res.append(symbols[digit]);
            }
            digit++;
        }
        return res.toString();
    }


//------------------------------------------------------------------------------//////////

    // version: 高频题班
    /**
     * @param n The integer
     * @return Roman representation
     */
    public String intToRoman3(int n) {
        // Write your code here
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[n / 1000] + C[(n / 100) % 10] + X[(n / 10) % 10] + I[n % 10];
    }


}
