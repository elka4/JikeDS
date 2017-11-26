package _09_Math;

//
//  https://leetcode.com/problems/excel-sheet-column-title/description/
//
public class _168_Excel_Sheet_Column_Title {
//-----------------------------------------------------------------------


//-----------------------------------------------------------------------

    /*
    Java Solution
    This problem is the reverse version of Excel Sheet Column Number.
    The key is n--. The minimum in 26-bit number is mapped to 1, not 0.
     */

    public String convertToTitle(int n) {
        if(n <= 0){
            throw new IllegalArgumentException("Input is not valid!");
        }

        StringBuilder sb = new StringBuilder();

        while(n > 0){
            n--;
            char ch = (char) (n % 26 + 'A');
            n /= 26;
            sb.append(ch);
        }

        sb.reverse();
        return sb.toString();
    }
//-----------------------------------------------------------------------
/*
1、题目

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

2、分析

excel中的序是这样排的：A~Z，AA~ZZ，AAA~ZZZ.......
本质是进制转换，将n转化为26进制，转化过程如下（括号里的是26进制数）：
1->(1)->A
2->(2)->B
...
26->(10)->Z
27->(11)->AA
28->(12)->AB
.....
52->(20)->AZ
53->(21)->BA

因此可以将n转化为26进制表示的数，然后对每一位的数，根据『1->A,2->B,3->C....25->Y,26->Z』来转化。
当然，按照进制转换的方法（不断地除26取余数），不可能有26的余数，比如：52->(20)->AZ，此时余数是0，这种情况要特殊处理，很简单，如下面的代码所示：
 */


//-----------------------------------------------------------------------
/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
-> A
-> B
-> C
    ...
-> Z
-> AA
-> AB
 */

/*
这道题是我微软onsite时遇到的一道题，没做过遇到这道题确实有点难一下子理得很清楚（我当时这道题应该做的不好，从most significant digit做，而且忘了n要-1）。这道题说白了其实就是十进制转换26进制，而且是从1开始的1十进制的转换

本质是进制转换，将n转化为26进制，转化过程如下（括号里的是26进制数）：

1->(1)->A
2->(2)->B
...
26->(10)->Z
27->(11)->AA
28->(12)->AB
.....
52->(20)->AZ
53->(21)->BA
从least significant digit开始，不断地除以26取余数

这是我的方法：因为A是1，而不是0，相当于26进制的数都整体减1，才能对应上从0开始的十进制数。
*/
public class Solution {
    public String convertToTitle(int n) {
        if (n <= 0) return "";
        StringBuffer res = new StringBuffer();
        while (n > 0) {
            res.insert(0, (char)('A' + (n-1)%26));
            n = (n-1) / 26;
        }
        return res.toString();
    }
}
/*
 这道题首先因为26个数字为一组，26次变一次，所以肯定是26进制，如果是1-26, 那么26号数字没法跟前面保持一致，比如都是一位25/26=0 而26/26=1. 所以应该回归0-based，1-26各数减一变成0-25，对应A到Z。但新的问题又出现了：AA本来是27，减了一之后是26, 26%26==0，最后一位是A没错，但是前一位26/26 == 1，又对应A，刚才0才对应A来着。所以，每一循环都要减一，以确保是0-based
 */
    class Solution2{
        public String convertToTitle(int n) {
            if(n <= 0){
                throw new IllegalArgumentException("Input is not valid!");
            }

            StringBuilder sb = new StringBuilder();

            while(n > 0){
                n--;
                char ch = (char) (n % 26 + 'A');
                n /= 26;
                sb.append(ch);
            }

            sb.reverse();
            return sb.toString();
        }
    }

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
}
/*
LeetCode – Excel Sheet Column Title (Java)

Problem

Given a positive integer, return its corresponding column title as appear in an Excel sheet. For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
//-----------------------------------------------------------------------
 */