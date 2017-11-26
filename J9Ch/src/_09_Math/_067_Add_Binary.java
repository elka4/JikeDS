package _09_Math;



//  67. Add Binary
//  https://leetcode.com/problems/add-binary/description/
//  http://www.lintcode.com/zh-cn/problem/add-binary/
//  Math, String
//  _043_Multiply_Strings
//  _066_Plus_One
//  4: 1, 4
public class _067_Add_Binary {
//------------------------------------------------------
    //1
    //Short AC solution in Java with explanation
    public class Solution {
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int i = a.length() - 1, j = b.length() -1, carry = 0;
            while (i >= 0 || j >= 0) {
                int sum = carry;
                if (j >= 0) sum += b.charAt(j--) - '0';
                if (i >= 0) sum += a.charAt(i--) - '0';
                sb.append(sum % 2);
                carry = sum / 2;
            }
            if (carry != 0) sb.append(carry);
            return sb.reverse().toString();
        }
    }
//------------------------------------------------------
    //2
    //Simple accepted java solution
    public class Solution2 {
        public String addBinary(String a, String b) {
            if(a == null || a.isEmpty()) {
                return b;
            }
            if(b == null || b.isEmpty()) {
                return a;
            }
            char[] aArray = a.toCharArray();
            char[] bArray = b.toCharArray();
            StringBuilder stb = new StringBuilder();

            int i = aArray.length - 1;
            int j = bArray.length - 1;
            int aByte;
            int bByte;
            int carry = 0;
            int result;

            while(i > -1 || j > -1 || carry == 1) {
                aByte = (i > -1) ? Character.getNumericValue(aArray[i--]) : 0;
                bByte = (j > -1) ? Character.getNumericValue(bArray[j--]) : 0;
                result = aByte ^ bByte ^ carry;
                carry = ((aByte + bByte + carry) >= 2) ? 1 : 0;
                stb.append(result);
            }
            return stb.reverse().toString();
        }
    }
    //Addition bits are calculated by xor. Carry bit is calculated as simple integer addition.
//------------------------------------------------------
    //3
    // 9Ch
    public class Jiuzhang1 {
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
    }

//------------------------------------------------------
    //4
    // version: 高频题班
    public class Jiuzhang2 {
        public String addBinary(String a, String b) {
            String ans = "";
            int carry = 0;

            for (int i = a.length() - 1, j = b.length() - 1;
                 i >= 0 || j >= 0; i--, j--) {
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
//------------------------------------------------------


}
/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */

/*
408. 二进制求和

给定两个二进制字符串，返回他们的和（用二进制表示）。

样例
a = 11

b = 1

返回 100

标签
字符串处理 二进制 脸书
相关题目
中等 两个整数相除 17 %
容易 加一
 */