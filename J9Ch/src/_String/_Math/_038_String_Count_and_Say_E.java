package _String._Math;
import java.util.*;
import org.junit.Test;

//  38. Count and Say
//  https://leetcode.com/problems/count-and-say/description/
//  String
//  271. Encode and Decode Strings - String
//  443. String Compression - String
//  3:
//
public class _038_String_Count_and_Say_E {
//------------------------------------------------------------------------------
    //1
/*Show an Answer in Java
    I found nobody answered this question in Java. Actually I got some trouble even this question is not so hard.

    Maybe many other people had some trouble too. So I put my answer here.*/

    public class Solution {
        public String countAndSay(int n) {
            StringBuilder curr=new StringBuilder("1");
            StringBuilder prev;
            int count;
            char say;
            for (int i=1;i<n;i++){
                prev=curr;
                curr=new StringBuilder();
                count=1;
                say=prev.charAt(0);

                for (int j=1,len=prev.length();j<len;j++){
                    if (prev.charAt(j)!=say){
                        curr.append(count).append(say);
                        count=1;
                        say=prev.charAt(j);
                    }
                    else count++;
                }
                curr.append(count).append(say);
            }
            return curr.toString();

        }
    }
/*@code StringBuilder.append() is the default way to append one string to another. While I have tried String.cancate(),which is not working properly.

    Any comment is welcomed.*/
//------------------------------------------------------------------------------
    //2
    //9Ch
    public class Jiuzhang {
        public String countAndSay(int n) {
            String oldString = "1";
            while (--n > 0) {
                StringBuilder sb = new StringBuilder();
                char [] oldChars = oldString.toCharArray();

                for (int i = 0; i < oldChars.length; i++) {
                    int count = 1;
                    while ((i+1) < oldChars.length && oldChars[i] == oldChars[i+1]) {
                        count++;
                        i++;
                    }
                    sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
                }
                oldString = sb.toString();
            }

            return oldString;
        }
    }


//------------------------------------------------------------------------------
    //3
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String str = "11";
        int ind = 2;
        while (ind < n) {
            StringBuffer sb = new StringBuffer();
            char[] arr = str.toCharArray();
            int count = 1;
            int type = Character.getNumericValue(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    count++;
                } else {
                    sb.append(count + "" + type);
                    type = Character.getNumericValue(arr[i]);
                    count = 1;
                }
            }
            ind++;
            sb.append(count + "" + type);
            str = sb.toString();
        }
        return str;
    }

//------------------------------------------------------------------------------
}
/*
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.
------------------------------------------------------------------------------
Example 1:

Input: 1
Output: "1"
------------------------------------------------------------------------------
Example 2:

Input: 4
Output: "1211"
------------------------------------------------------------------------------
Companies
Facebook

Related Topics
String

Similar Questions
271. Encode and Decode Strings - String
443. String Compression - String
 */

/*------------------------------------------------------------------------------
报数指的是，按照其中的整数的顺序进行报数，然后得到下一个数。如下所示：

1, 11, 21, 1211, 111221, ...

1 读作 "one 1" -> 11.

11 读作 "two 1s" -> 21.

21 读作 "one 2, then one 1" -> 1211.

给定一个整数 n, 返回 第 n 个顺序。

在线评测地址: http://www.lintcode.com/problem/count-and-say/
 */