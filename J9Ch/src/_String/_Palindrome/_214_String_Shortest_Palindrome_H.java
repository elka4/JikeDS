package _String._Palindrome;
import java.util.*;
import org.junit.Test;

//  214. Shortest Palindrome
//  https://leetcode.com/problems/shortest-palindrome/description/
//  http://www.lintcode.com/problem/shortest-palindrome/
//  给一个字符串 S, 你可以通过在前面添加字符将其转换为回文串.找到并返回用这种方式转换的最短回文串.
//  Given "aacecaaa", return "aaacecaaa".   Given "abcd", return "dcbabcd".
//  5 Longest Palindromic Substring
//  Implement strStr()
//  336 Palindrome Pairs
//  6:6
//
public class _214_String_Shortest_Palindrome_H {

//------------------------------------------------------------------------------
    //6
    //9Ch
    public class Jiuzhang {
        public String shortestPalindrome(String s) {
            int j = 0;
            //找到第一个使他不回文的位置
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    j += 1;
                }
            }
            //本身是回文
            if (j == s.length()) {
                return s;
            }
            // 后缀不能够匹配的字符串
            String suffix = s.substring(j);

            // 前面补充prefix让他和suffix回文匹配
            String prefix = new StringBuilder(suffix).reverse().toString();

            //递归调用找 [0,j]要最少可以补充多少个字符让他回文
            String mid = shortestPalindrome(s.substring(0, j));

            String ans = prefix + mid  + suffix;
            return  ans;
        }
    }
//------------------------------------------------------------------------------
    //https://leetcode.com/problems/shortest-palindrome/solution/
    //C++
//------------------------------------------------------------------------------
    //1
    //Clean KMP solution with super detailed explanation
    class Solution1{
        public String shortestPalindrome(String s) {
            String temp = s + "#" + new StringBuilder(s).reverse().toString();
            int[] table = getTable(temp);

            //get the maximum palin part in s starts from 0
            return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
        }

        public int[] getTable(String s){
            //get lookup table
            int[] table = new int[s.length()];

            //pointer that points to matched char in prefix part

            int index = 0;
            //skip index 0, we will not match a string with itself
            for(int i = 1; i < s.length(); i++){
                if(s.charAt(index) == s.charAt(i)){
                    //we can extend match in prefix and postfix
                    table[i] = table[i-1] + 1;
                    index ++;
                }else{
                    //match failed, we try to match a shorter substring

                    //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                    //prefix part that we used to match postfix ended at i - 1
                    index = table[i-1];

                    while(index > 0 && s.charAt(index) != s.charAt(i)){
                        //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                        index = table[index-1];
                    }

                    //when we are here may either found a match char or we reach the boundary and still no luck
                    //so we need check char match
                    if(s.charAt(index) == s.charAt(i)){
                        //if match, then extend one char
                        index ++ ;
                    }

                    table[i] = index;
                }

            }

            return table;
        }
    }
//------------------------------------------------------------------------------
    //2
/*My 9-lines three pointers Java solution with explanation
Explanation
The key point is to find the longest palindrome starting from the first character, and then reverse the remaining part as the prefix to s. Any advice will be welcome!*/
    class Solution2{
        public String shortestPalindrome(String s) {
            int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
            while(i < j) {
                if (chs[i] == chs[j]) {
                    i++; j--;
                } else {
                    i = 0; end--; j = end;
                }
            }
            return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
        }
    }

//------------------------------------------------------------------------------
    //3
    //Easy Java solution
/*
First, we can find the longest palindrome which include the first character in s, then we just need to reverse the suffix and add it to the front of string s.

When looking for the longest palindrome, we start from the center and traverse to left and right, and we need to think the length of the palindrome could be odd or even.
*/
    public class Solution3 {
        public String shortestPalindrome(String s) {
            int n = s.length();
            if(n<=1){
                return s;
            }
            int idx = 0;
            for(int center = n/2; center>=0; center--){
                //is the longest valid palindrome? (assume length is even)
                if(valid(s, center, 1)){
                    idx = 2*center+1;
                    break;
                }
                if(valid(s, center, 0)){  //assume length is odd
                    idx = 2*center;
                    break;
                }

            }
            String suffix = s.substring(idx+1);
            StringBuilder b = new StringBuilder(suffix);
            return b.reverse().toString()+s;
        }
        boolean valid(String s, int center, int shift){
            int i = center, j = center+shift;
            while(i>=0 && j<s.length()){
                if(s.charAt(i)!=s.charAt(j)){
                    break;
                }
                i--;
                j++;
            }
            return i<0;
        }
    }


//------------------------------------------------------------------------------
    //4
    //Using Longest Palindrome
    class Solution4{
        public String shortestPalindrome(String s) {
            if (s == null || s.length() <= 1) return s;
            // find the longest palin beginning at the left
            int l = s.length();
            int maxL = 0;
            for(int i = 0; i <= l /2 ; i++) {
                maxL = Math.max(maxL, Math.max(expand(s, i, false),expand(s, i, true)));
            }
            // use maxL as point
            String suffix = s.substring(maxL+1);
            return new StringBuffer(suffix).reverse().toString() + s.substring(0, maxL+1) + suffix;
        }

        // return the end index if the palin starts at beginning
        private int expand(String s, int i, boolean isCenter) {
            int j = isCenter? i: i+1;
            while(i >=0 && j < s.length() && s.charAt(i) == s.charAt(j)){
                i--;
                j++;
            }
            // only return if goes to the start
            if (i < 0 ) return --j;
            return -1;
        }
    }
//------------------------------------------------------------------------------
    //5
    //Java Solution AC O(N^2) with lots of comments.
    public class Solution5 {
        public String shortestPalindrome(String s) {
            //a or empty string
            if (s.length()<2) return s;

            char[] c=s.toCharArray();
            //We can always use the first character of an odd length solution
            int r=s.length()%2;

            //start at the end, the first one we find will be the longest internal palindrome
            for(int j=c.length-1;j>=0;j--) {
                if(c[0]==c[j]) {
                    int i=j;
                    int nk=j;
                    boolean fail=false;
                    //check that it's a palindrome till we hit the halfway mark
                    for(;i>=j/2;i--) {
                        if(c[i]==c[j]) {
                            //keep track of where to pick up if this fails
                            nk=j;
                        }
                        if (c[j-i]!=c[i]) {
                            //not a palindrome
                            fail=true;
                            break;
                        }
                    }
                    if (!fail) {
                        //we found the longest one, we're done
                        r=j;
                        break;
                    } else {
                        //pick up at a possible palindrome spot
                        j=nk;
                    }
                }
            }
            //If the whole thing is a palindrome then return it unchanged
            if (r==s.length()) return s;
            StringBuffer prepend=new StringBuffer();
            //otherwise reverse prepend to the string
            for(int i=c.length-1;i>r;i--) {
                prepend.append(c[i]);
            }
            //and put the rest of the word on the end.
            prepend.append(s);


            return prepend.toString();
        }
    }


//------------------------------------------------------------------------------
}
/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".   Given "abcd", return "dcbabcd".

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.

Seen this question in a real interview before?   Yes  No

Companies
Google Pocket Gems

Related Topics
String

Similar Questions
Longest Palindromic Substring
Implement strStr()
Palindrome Pairs
 */