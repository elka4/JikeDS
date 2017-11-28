package _String._Math;


//  443. String Compression
//  https://leetcode.com/problems/string-compression/description/
//
//  38. Count and Say - String
//  271. Encode and Decode Strings - String
//  604. Design Compressed String Iterator - Design
//  3:
//
public class _443_String_Compression {
//------------------------------------------------------------------------------
//https://leetcode.com/problems/string-compression/solution/

//------------------------------------------------------------------------------
    //1
    //Approach #1: Read and Write Heads [Accepted]
    class Solution1 {
        public int compress(char[] chars) {
            int anchor = 0, write = 0;
            for (int read = 0; read < chars.length; read++) {
                if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                    chars[write++] = chars[anchor];
                    if (read > anchor) {
                        for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                            chars[write++] = c;
                        }
                    }
                    anchor = read + 1;
                }
            }
            return write;
        }
    }




//------------------------------------------------------------------------------
    //2
    //Java O(n), two pointers and a counter
    public int compress(char[] chars) {
        int start = 0;
        for(int end = 0, count = 0; end < chars.length; end++) {
            count++;
            if(end == chars.length-1 || chars[end] != chars[end + 1] ) {
                //We have found a difference or we are at the end of array
                chars[start] = chars[end]; // Update the character at start pointer
                start++;
                if(count != 1) {
                    // Copy over the character count to the array
                    char[] arr = String.valueOf(count).toCharArray();
                    for(int i=0;i<arr.length;i++, start++)
                        chars[start] = arr[i];
                }
                // Reset the counter
                count = 0;
            }
        }
        return start;
    }


//------------------------------------------------------------------------------
    //3
    //Simple Easy to Understand Java solution
    public int compress3(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray())
                    chars[indexAns++] = c;
        }
        return indexAns;
    }


//------------------------------------------------------------------------------
    //4
    //9Ch
    public class Jiuzhang {
        /**
         * @param str a string
         * @return a compressed string
         */
        public String compress(String str) {
            // Write your code here
            /* Check if compression would create a longer string */
            int size = countCompression(str);
            if (size >= str.length()) {
                return str;
            }

            char[] array = new char[size];
            int index = 0;
            char last = str.charAt(0);
            int count = 1;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == last) { // Found repeated character
                    count++;
                } else {
                    /* Update the repeated character count */
                    index = setChar(array, last, index, count);
                    last = str.charAt(i);
                    count = 1;
                }
            }
            /* Update string with the last set of repeated characters. */
            index = setChar(array, last, index, count);
            return String.valueOf(array);
        }

        public int setChar(char[] array, char c, int index, int count) {
            array[index] = c;
            index++;
            char[] cnt = String.valueOf(count).toCharArray();

            /* Copy characters from biggest digit to smallest */
            for (char x : cnt) {
                array[index] = x;
                index++;
            }
            return index;
        }

        int countCompression(String str) {
            if (str == null || str.isEmpty()) return 0;
            char last = str.charAt(0);
            int size = 0;
            int count = 1;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == last) {
                    count++;
                } else {
                    last = str.charAt(i);
                    size += 1 + String.valueOf(count).length();
                    count = 1;
                }
            }
            size += 1 + String.valueOf(count).length();
            return size;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?

------------------------------------------------------------------------------
Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
------------------------------------------------------------------------------
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
------------------------------------------------------------------------------
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
------------------------------------------------------------------------------
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.
Seen this question in a real interview before?   Yes  No

Companies
Microsoft Bloomberg Snapchat Yelp Expedia GoDaddy Lyft

Related Topics
String

Similar Questions
38. Count and Say - String
271. Encode and Decode Strings - String
604. Design Compressed String Iterator - Design
Java
 */




/*
设计一种方法，通过给重复字符计数来进行基本的字符串压缩。

例如，字符串 aabcccccaaa 可压缩为 a2b1c5a3 。而如果压缩后的字符数不小于原始的字符数，则返回原始的字符串。

可以假设字符串仅包括a-z的字母。

在线评测地址: http://www.lintcode.com/problem/string-compression/
 */