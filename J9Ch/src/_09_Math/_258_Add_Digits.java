package _09_Math;

//  258. Add Digits
//  https://leetcode.com/problems/add-digits/description/
//  http://www.lintcode.com/zh-cn/problem/add-digits/
//  Math
//  _202_Happy_Number
//  4:
public class _258_Add_Digits {
//-----------------------------------------------------------------------
    //1
    //Java Solution 1 - Recusion
    public int addDigits(int num) {
        int sum=0;

        String s = String.valueOf(num);
        for(int i=0; i<s.length(); i++){
            sum = sum + (s.charAt(i)-'0');
        }

        if(sum < 10){
            return sum;
        }else{
            return addDigits(sum);
        }
    }
//-----------------------------------------------------------------------
    //2
    //Java Solution 2 - Math

    public int addDigits2(int num) {

        return num - 9*((num-1)/9);
    }
//-----------------------------------------------------------------------
    //3
    //1 line Java Solution
/*
    這已經是偏向數學的範圍，怎麼證明這邊就跳過了，麻煩自己上網搜尋， 簡單說就是判斷一個數是否為9的倍數，可以從每個位數相加是否能被9整除直接判斷，運用這樣的想法，直接取這個數除9的餘數。
var addDigits = function(num) {
    if(num == 0) return 0;
    if(num%9==0) return 9;
    return num%9;
};
*/
    //其实这个很简单，回想小学数学，3的倍数就是所有位数加在一起是3的倍数
    public class Solution3 {
        public int addDigits(int num) {

            return num==0?0:(num%9==0?9:(num%9));
        }
    }
//-----------------------------------------------------------------------
    //4
    // 9Ch
    public class Solution {
        public int addDigits(int num) {
            if (num == 0) {
                return 0;
            }
            int ans = 0;
            for (; num != 0; ) {
                int digit = num % 10;
                ans = (ans * 10 + digit) % 9;
                num /= 10;
            }
            return ans == 0 ? 9 : ans;
        }
    }

//-----------------------------------------------------------------------
}
/*
LeetCode – Add Digits (Java)

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
-----------------------------------------------------------------------
 */


/*
569. 各位相加

给出一个非负整数 num，反复的将所有位上的数字相加，直到得到一个一位的整数。

样例
给出 num = 38。

相加的过程如下：3 + 8 = 11，1 + 1 = 2。因为 2 只剩下一个数字，所以返回 2。

挑战
你可以不用任何的循环或者递归算法，在 O(1) 的时间内解决这个问题么？

标签
数学
相关题目
容易 快乐数
-----------------------------------------------------------------------
 */