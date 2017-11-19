package HF.HF5_Math_Graphic_Bit_2Calc;

import org.junit.Test;

// Add Binary
public class _2AddBinary {
    public String addBinary(String a, String b) {
        if(a.length() < b.length()){
            String tmp = a;
            a = b;
            b = tmp;
        }

        int pa = a.length()-1;
        int pb = b.length()-1;
        int carries = 0;
        String rst = "";

        while(pb >= 0){
            int sum = (int)(a.charAt(pa) - '0') + (int)(b.charAt(pb) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa --;
            pb --;
        }

        while(pa >= 0){
            int sum = (int)(a.charAt(pa) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa --;
        }

        if (carries == 1)
            rst = "1" + rst;
        return rst;
    }

    @Test
    public void test01(){
        String a = "11", b = "1";
        System.out.println(addBinary(a,b));
    }

//-------------------------------------------------------------------------/////////////

    // version: 高频题班

    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary2(String a, String b) {
        // Write your code here
        String ans = "";

        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += (i >= 0) ? a.charAt(i) - '0' : 0;
            sum += (j >= 0) ? b.charAt(j) - '0' : 0;
            ans = (sum % 2) + ans;
            carry = sum / 2;
        }
        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }
}
/*

 */

/*
给定两个二进制字符串，返回他们的和（用二进制表示）。

样例
a = 11

b = 1

返回 100

 */