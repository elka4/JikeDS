package _08_Bit;

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
public class UTF_8_Validation {
    public boolean validUtf8(int[] data) {
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

    //Bit Manipulation, Java, 6ms
    public boolean validUtf8_2(int[] data) {
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



//------------------------------------------------------------------------------



//------------------------------------------------------------------------------



//------------------------------------------------------------------------------



}
