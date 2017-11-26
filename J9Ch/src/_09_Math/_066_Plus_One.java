package _09_Math;

//  66. Plus One
//  https://leetcode.com/problems/plus-one/description/
//  http://www.lintcode.com/zh-cn/problem/plus-one/
//  Array, Math
//  _043_Multiply_Strings
//  Add Binary
//  Plus One Linked List
//  5:
public class _066_Plus_One {
//-----------------------------------------------------------------------
    //1
    // To solve this problem, we can use a flag to mark if the current digit needs to be changed.
    public int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0)
            return new int[0];

        int carry = 1;
        for(int i=digits.length-1; i>=0; i--){
            int sum = digits[i]+carry;
            if(sum>=10){
                carry=1;
            }else{
                carry=0;
            }
            digits[i]=sum%10;
        }

        if(carry==1){
            int[] result = new int[digits.length+1];
            System.arraycopy(digits, 0, result, 1, digits.length);
            result[0]=1;
            return result;
        }else{
            //int[] result = new int[digits.length];
            return digits;
        }
    }
//-----------------------------------------------------------------------
    //2
    // 9Ch

    public class Solution3 {
        // The complexity is O(1)
        // f(n) = 9/10 + 1/10 * O(n-1)
        //  ==>  O(n) =  10 / 9 = 1.1111 = O(1)

        public int[] plusOne(int[] digits) {
            int carries = 1;
            // fast break when carries equals zero
            for(int i = digits.length-1; i>=0 && carries > 0; i--){
                int sum = digits[i] + carries;
                digits[i] = sum % 10;
                carries = sum / 10;
            }
            if(carries == 0)
                return digits;

            int[] rst = new int[digits.length+1];
            rst[0] = 1;
            for(int i=1; i< rst.length; i++){
                rst[i] = digits[i-1];
            }
            return rst;
        }
    }



//-----------------------------------------------------------------------
    //3
    //My Simple Java Solution
    class Solution4{
        public int[] plusOne(int[] digits) {

            int n = digits.length;
            for(int i=n-1; i>=0; i--) {
                if(digits[i] < 9) {
                    digits[i]++;
                    return digits;
                }

                digits[i] = 0;
            }

            int[] newNumber = new int [n+1];
            newNumber[0] = 1;

            return newNumber;
        }
    }
//-----------------------------------------------------------------------
    //4
    //Simple java solution
    class Solution5{
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >=0; i--) {
                if (digits[i] != 9) {
                    digits[i]++;
                    break;
                } else {
                    digits[i] = 0;
                }
            }
            if (digits[0] == 0) {
                int[] res = new int[digits.length+1];
                res[0] = 1;
                return res;
            }
            return digits;
        }
    }

//-----------------------------------------------------------------------

}
/*//-----------------------------------------------------------------------
LeetCode – Plus One (Java)

Given a non-negative number represented as an array of digits, plus one to the number. The digits are stored such that the most significant digit is at the head of the list.
 */

/*//-----------------------------------------------------------------------
407. 加一

给定一个非负数，表示一个数字数组，在该数的基础上+1，返回一个新的数组。

该数字按照大小进行排列，最大的数在列表的最前面。

样例
给定 [1,2,3] 表示 123, 返回 [1,2,4].

给定 [9,9,9] 表示 999, 返回 [1,0,0,0].

标签
数组 谷歌
相关题目
中等 两个整数相除 17 %
容易 二进制求和
 */