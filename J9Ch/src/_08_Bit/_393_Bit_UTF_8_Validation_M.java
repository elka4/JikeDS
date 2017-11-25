package _08_Bit;
import java.util.*;
import org.junit.Test;

//  393. UTF-8 Validation
//  https://leetcode.com/problems/utf-8-validation/description/
//  6:
public class _393_Bit_UTF_8_Validation_M {
//------------------------------------------------------------------------------
    //1
    //    Bit Manipulation, Java, 6ms
    public boolean validUtf8(int[] data) {
        if(data==null || data.length==0) return false;
        boolean isValid = true;
        for(int i=0;i<data.length;i++) {
            if(data[i]>255) return false; // 1 after 8th digit, 100000000
            int numberOfBytes = 0;
            if((data[i] & 128) == 0) { // 0xxxxxxx, 1 byte, 128(10000000)
                numberOfBytes = 1;
            } else if((data[i] & 224) == 192) { // 110xxxxx, 2 bytes, 224(11100000), 192(11000000)
                numberOfBytes = 2;
            } else if((data[i] & 240) == 224) { // 1110xxxx, 3 bytes, 240(11110000), 224(11100000)
                numberOfBytes = 3;
            } else if((data[i] & 248) == 240) { // 11110xxx, 4 bytes, 248(11111000), 240(11110000)
                numberOfBytes = 4;
            } else {
                return false;
            }
            for(int j=1;j<numberOfBytes;j++) { // check that the next n bytes start with 10xxxxxx
                if(i+j>=data.length) return false;
                if((data[i+j] & 192) != 128) return false; // 192(11000000), 128(10000000)
            }
            i=i+numberOfBytes-1;
        }
        return isValid;
    }

//------------------------------------------------------------------------------
    //2
    //    O(n) JAVA solution, with detailed explaination
    public class Solution2 {
    /*
     * Thought-way:
     * As long as every byte in the array is of right type, it is a valid UTF-8 encoding.
     *
     * Method:
     * Start from index 0, determine each byte's type and check its validity.
     *
     * There are five kinds of valid byte type: 0**, 10**, 110**,1110** and 11110**
     * Give them type numbers, 0, 1, 2, 3, 4 which are the index of the first 0 from left.
     * So, the index of the first 0 determines the byte type.
     *
     * if a byte belongs to one of them:
        1 : if it is type 0, continue
        2 : if it is type 2 or 3 or 4, check whether the following 1, 2, and 3 byte(s) are of type 1 or not
                if not, return false;
     * else if a byte is type 1 or not of valid type, return false
     *
     * Analysis :
     * The faster you can determine the type, the quicker you can get.
     * Time O(n), space O(1)
     * real performance: 7ms
     */

        // Hard code "masks" array to find the index of the first appearance
        // of 0 in the lower 8 bits of each integer.
        private int[] masks = {128, 64, 32, 16, 8};
        public boolean validUtf8(int[] data) {
            int len = data.length;
            for (int i = 0; i < len; i ++) {
                int curr = data[i];
                int type = getType(curr);
                if (type == 0) {
                    continue;
                } else if (type > 1 && i + type <= len) {
                    while (type-- > 1) {
                        if (getType(data[++i]) != 1) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            return true;
        }

        public int getType(int num) {
            for (int i = 0; i < 5; i ++) {
                if ((masks[i] & num) == 0) {
                    return i;
                }
            }
            return -1;
        }
    }

//------------------------------------------------------------------------------
    //3
    //Simple one pass concise Java solution beating 99%
    //    So, I wrote a literal translation of the problem statement in Java. This works in O(n), obviously.
    public class Solution3 {
        public boolean validUtf8(int[] data) {
            int varCharLeft = 0;
            for (int b: data) {
                if (varCharLeft == 0) {
                    if ((b & 0b010000000) == 0)  varCharLeft = 0;
                    else if ((b & 0b011100000) == 0b11000000)  varCharLeft = 1;
                    else if ((b & 0b011110000) == 0b11100000)  varCharLeft = 2;
                    else if ((b & 0b011111000) == 0b11110000)  varCharLeft = 3;
                    else return false;
                } else {
                    if ((b & 0b011000000) != 0b10000000)  return false;
                    varCharLeft--;
                }
            }
            return varCharLeft==0;
        }
    }

//------------------------------------------------------------------------------
    //4
    //O(n) solution using Java
    public class Solution4 {
        public boolean validUtf8(int[] data) {
            int n = data.length;
            if (n == 0) return true;
            int skip = 0b10000000;
            int check = 0;
            for (int i = 0; i < data.length; i++) {
                if (check > 0) {
                    if ((data[i] & skip) == skip) check--;
                    else return false;
                } else {
                    check = getOneBitCountFromHead(data[i]);
                    if (check < 0) return false;
                }
            }
            return check == 0;
        }
        private int getOneBitCountFromHead(int num) {
            if ((num & 0b11110000) == 0b11110000) return 3;
            if ((num & 0b11100000) == 0b11100000) return 2;
            if ((num & 0b11000000) == 0b11000000) return 1;
            if ((num & 0b10000000) == 0b10000000) return -1; //error
            return 0;
        }
    }

//------------------------------------------------------------------------------
    //5
    //Java solution
    class Solution5{
        public  boolean validUtf8(int[] data) {
            int count = 0;

            for(int n: data) {

                if(count > 0) {

                    if(n >= (2 << 6) && n < (3 << 6)) count--;

                    else return false;

                } else {

                    if(n < (1 << 7)) continue;

                    if(n >= (30 << 3) && n < (31 << 3)) count = 3;

                    else if(n >= (14 << 4) && n < (15 << 4)) count = 2;

                    else if(n >= (6 << 5) && n < (7 << 5)) count = 1;

                    else return false;
                }

            }

            if(count == 0) return true;

            return false;
        }
    }

//------------------------------------------------------------------------------
    //6
    public boolean validUtf08(int[] data) {
        int i=0;
        int count=0;
        while(i<data.length){
            int v = data[i];
            if(count==0){
                if((v&240)==240 && (v&248)==240){
                    count=3;
                }else if(((v&224)==224) && (v&240)==224){
                    count=2;
                }else if((v&192)==192 && (v&224)==192){
                    count=1;
                }else if((v|127)==127){
                    count=0;
                }else{
                    return false;
                }
            }else{
                if((v&128)==128 && (v&192)==128){
                    count--;
                }else{
                    return false;
                }
            }

            i++;
        }

        return count==0;
    }

//------------------------------------------------------------------------------
}
/*
A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.
 */

/*
LeetCode – UTF-8 Validation (Java)

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 */