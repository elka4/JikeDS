package _01_Array.DFS;

/*
LeetCode – Shortest Palindrome (Java)

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example, given "aacecaaa", return "aaacecaaa"; given "abcd", return "dcbabcd".
 */


public class Shortest_Palindrome {

    //和下一个方法同样想法
    /*
    向心型双指针找到中间Palindrome的右端位置：

    中间Palindrome的右端位置到末端是suffix

    recursion找到中间的Palindrome，返回这个String作为mid。

    reverse suffix 得到 prefix

     */

    /*
    这题的要点，
    一个是要记住String找位置的index写法

    一个是要知道Java里reverse String可以用StringBuilder来做： StringBuilder(suffix).reverse().toString();
     */
    public String shortestPalindrome(String s) {
        int i=0;
        int j=s.length()-1;

        while(j>=0){
            if(s.charAt(i)==s.charAt(j)){
                i++;
            }
            j--;
        }

        if(i==s.length())
            return s;

        String suffix = s.substring(i);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome(s.substring(0, i));
        return prefix+mid+suffix;
    }

//-------------------------------------------------------------------------//////


    /*My 7-lines recursive Java solution
    和上一个方法相同思路
    The idea is to use two anchors j and i to compare the String from beginning and end.

    If j can reach the end, the String itself is Palindrome.

    Otherwise, we divide the String by j, and get mid = s.substring(0, j) and suffix.

    We reverse suffix as beginning of result and recursively call shortestPalindrome to get result of mid then appedn suffix to get result.*/

    public String shortestPalindrome4(String s) {

        int j = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j += 1;
            }
        }

        if (j == s.length()) {
            return s;
        }

        String suffix = s.substring(j);

        return new StringBuffer(suffix).reverse().toString() +
                shortestPalindrome(s.substring(0, j)) + suffix;


    }

/*    We can solve this problem by using one of the methods which is used to solve the longest palindrome substring problem.

            Specifically, we can start from the center and scan two sides. If read the left boundary, then the shortest palindrome is identified.*/

    public String shortestPalindrome2(String s) {
        if (s == null || s.length() <= 1)
            return s;

        String result = null;

        int len = s.length();
        int mid = len / 2;

        for (int i = mid; i >= 1; i--) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                if ((result = scanFromCenter(s, i - 1, i)) != null)
                    return result;
            } else {
                if ((result = scanFromCenter(s, i - 1, i - 1)) != null)
                    return result;
            }
        }

        return result;
    }

    private String scanFromCenter(String s, int l, int r) {
        int i = 1;
        //scan from center to both sides
        for (; l - i >= 0; i++) {
            if (s.charAt(l - i) != s.charAt(r + i))
                break;
        }

        //if not end at the beginning of s, return null
        if (l - i >= 0)
            return null;

        StringBuilder sb = new StringBuilder(s.substring(r + i));
        sb.reverse();

        return sb.append(s).toString();
    }




//-------------------------------------------------------------------------//////


    //Clean KMP solution with super detailed explanation

    public String shortestPalindrome3(String s) {
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

                //by assigning index to table[i-1],
                // we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until
                    // we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach
                // the boundary and still no luck
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






//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////





//-------------------------------------------------------------------------//////






}
