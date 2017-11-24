package _TwoPointer.Reverse;

//  344. Reverse String
//  https://leetcode.com/problems/reverse-string/description/
//  9:
//  Two Pointers, String
//  _345_TwoPointer_Reverse_Vowels_of_a_String_E
//  _541_String_Reverse_String_II_E
public class _344_TwoPointer_Reverse_String_E {
//---------------------------------------------------------------------------
    //1
    //  [JAVA] Simple and Clean with Explanations [6 Solutions]
    public class Solution1 {
        public String reverseString(String s) {
            char[] word = s.toCharArray();
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                char temp = word[i];
                word[i] = word[j];
                word[j] = temp;
                i++;
                j--;
            }
            return new String(word);
        }
    }
//---------------------------------------------------------------------------
    //2
/*    Complexity Analysis

    Time Complexity: `O(n)` (Average Case) and `O(n)` (Worst Case) where `n` is the total number character in the input string. The algorithm need to reverse the whole string.

    Auxiliary Space: `O(n)` space is used where `n` is the total number character in the input string. Space is needed to transform string to character array.

            Algorithm

    Approach: Iterative Swapping Using Two Pointers

    One pointer is pointing at the start of the string while the other pointer is pointing at the end of the string. Both pointers will keep swapping its element and travel towards each other. The algorithm basically simulating rotation of a string with respect to its midpoint.*/

    public class Solution2 {
        public String reverseString(String s) {
            byte[] bytes = s.getBytes();
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                byte temp = bytes[i];
                bytes[i] = bytes[j];
                bytes[j] = temp;
                i++;
                j--;
            }
            return new String(bytes);
        }
    }
//---------------------------------------------------------------------------
    //3
/*    Complexity Analysis

    Time Complexity: `O(n)` (Average Case) and `O(n)` (Worst Case) where `n` is the total number character in the input string. The algorithm need to reverse the whole string. Each character is `1` byte.

    Auxiliary Space: `O(n)` space is used where `n` is the total number character in the input string. Space is needed to transform string to byte array.

    Algorithm

    Approach: Iterative Swapping Using Two Pointers

    One pointer is pointing at the start of the byte array while the other pointer is pointing at the end of the byte array. Both pointers will keep swapping its element and travel towards each other. The algorithm basically simulating rotation of a string with respect to its midpoint.

    Note that this assume that the input string is encoded using ASCII format. This will not work with Unicode value where one character may be more than 1 byte.*/

    public class Solution3 {
        public String reverseString(String s) {
            char[] word = s.toCharArray();
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                word[i] = (char) (word[i] ^ word[j]);
                word[j] = (char) (word[i] ^ word[j]);
                word[i] = (char) (word[i] ^ word[j]);
                i++;
                j--;
            }
            return new String(word);
        }
    }
//---------------------------------------------------------------------------
    //4
    /*Complexity Analysis

    Time Complexity: `O(n)` (Average Case) and `O(n)` (Worst Case) where `n` is the total number character in the input string. The algorithm need to reverse the whole string.

    Auxiliary Space: `O(n)` space is used where `n` is the total number character in the input string. Space is needed to transform string to character array.

            Algorithm

    Approach: Iterative Swapping Using Two Pointers

    One pointer is pointing at the start of the string while the other pointer is pointing at the end of the string. Both pointers will keep swapping its element and travel towards each other. The algorithm basically simulating rotation of a string with respect to its midpoint. The swapping is done by using XOR swapping algorithm.

            Operation	Result
`a = a \oplus b`	`a = a \oplus b`
            `b = a \oplus b`	`b = (a \oplus b) \oplus b = a \oplus b \oplus b = a`
            `a = a \oplus b`	`a = (a \oplus b) \oplus a = a \oplus b \oplus a = b`
    Note that this assume that the input string is encoded using ASCII format. This will not work with Unicode value where one character may be more than 1 byte.*/

    public class Solution4 {
        public String reverseString(String s) {
            byte[] bytes = s.getBytes();
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                bytes[i] = (byte) (bytes[i] ^ bytes[j]);
                bytes[j] = (byte) (bytes[i] ^ bytes[j]);
                bytes[i] = (byte) (bytes[i] ^ bytes[j]);
                i++;
                j--;
            }
            return new String(bytes);
        }
    }
//---------------------------------------------------------------------------
    //5
    /*Complexity Analysis

    Time Complexity: `O(n)` (Average Case) and `O(n)` (Worst Case) where `n` is the total number character in the input string. The algorithm need to reverse the whole string. Each character is `1` byte.

    Auxiliary Space: `O(n)` space is used where `n` is the total number character in the input string. Space is needed to transform string to byte array.

    Algorithm

    Approach: Iterative Swapping Using Two Pointers

    One pointer is pointing at the start of the byte array while the other pointer is pointing at the end of the byte array. Both pointers will keep swapping its element and travel towards each other. The algorithm basically simulating rotation of a string with respect to its midpoint. The swapping is done by using XOR swapping algorithm.

            Operation	Result
`a = a \oplus b`	`a = a \oplus b`
            `b = a \oplus b`	`b = (a \oplus b) \oplus b = a \oplus b \oplus b = a`
            `a = a \oplus b`	`a = (a \oplus b) \oplus a = a \oplus b \oplus a = b`
    Note that this assume that the input string is encoded using ASCII format. This will not work with Unicode value where one character may be more than 1 byte.*/

    public class Solution5 {
        public String reverseString(String s) {
            return new StringBuilder(s).reverse().toString();
        }
    }
//---------------------------------------------------------------------------
    //6
/*    Complexity Analysis

    Time Complexity: `O(n)` (Average Case) and `O(n)` (Worst Case) where `n` is the total number character in the input string. Depending on the implementation. However, it is not possible to reverse string in less than `O(n)`.

    Auxiliary Space: `O(n)` space is used where `n` is the total number character in the input string. Space is needed to transform immutable string into character buffer in StringBuilder.

    Algorithm

    Approach: Using Java Library

    Java's library is probably slower that direct implementation due to extra overhead in check various edge cases such as surrogate pairs.*/

    public class Solution6 {
        public String reverseString(String s) {
            int length = s.length();
            if (length <= 1) return s;
            String leftStr = s.substring(0, length / 2);
            String rightStr = s.substring(length / 2, length);
            return reverseString(rightStr) + reverseString(leftStr);
        }
    }
//---------------------------------------------------------------------------
    //7
/*    Complexity Analysis

    Time Complexity: `O(n log(n))` (Average Case) and `O(n * log(n))` (Worst Case) where `n` is the total number character in the input string. The recurrence equation is `T(n) = 2 * T(n/2) + O(n)`. `O(n)` is due to the fact that concatenation function takes linear time. The recurrence equation can be solved to get `O(n * log(n))`.

    Auxiliary Space: `O(h)` space is used where `h` is the depth of recursion tree generated which is `log(n)`. Space is needed for activation stack during recursion calls.

            Algorithm

    Approach: Divide and Conquer (Recursive)

    The string is split into half. Each substring will be further divided. This process continues until the string can no longer be divided (length `<= 1`). The conquering process will take they previously split strings and concatenate them in reverse order.*/

    //Java- easiest method- 2-line code, attached another method
    class Solution7{
        //method 1: use StringBuilder
        public String reverseString1(String s) {
            StringBuilder sb = new StringBuilder(s);
            return sb.reverse().toString();
        }

        //method 2: use swap method
        public String reverseString2(String s){
            if(s == null || s.length() == 0)
                return "";
            char[] cs = s.toCharArray();
            int begin = 0, end = s.length() - 1;
            while(begin <= end){
                char c = cs[begin];
                cs[begin] = cs[end];
                cs[end] = c;
                begin++;
                end--;
            }

            return new String(cs);
        }
    }
//---------------------------------------------------------------------------
    //8
    //  1-line:
    public class Solution8 {
        public String reverseString(String s) {
            return new StringBuilder(s).reverse().toString();
        }
    }
//---------------------------------------------------------------------------
    //9
    //Java swapping char array.
    public String reverseString9(String s) {
        char[] c = s.toCharArray();
        for (int i=0,j=c.length-1;i<j;i++,j--){
            char temp = c[i];
            c[i]=c[j];
            c[j]=temp;
        }
        return new String(c);
    }

//---------------------------------------------------------------------------
}
/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
 */

