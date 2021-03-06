package _String._String;
import java.util.*;
import org.junit.Test;

//  https://leetcode.com/problems/read-n-characters-given-read4/description/
//  http://www.lintcode.com/problem/read-n-characters-given-read4/
//
//  3:
//
public class _157_String_Read_N_Characters_Given_Read4_E {
//------------------------------------------------------------------------------
    interface Reader4{
        int read(char[] buf, int n);
    }
    int read4(char[] buf) {return 1;}
//------------------------------------------------------------------------------
    //1
    //Another accepted Java solution
    class Solution1{
        public int read(char[] buf, int n) {
            boolean eof = false;      // end of file flag
            int total = 0;            // total bytes have read
            char[] tmp = new char[4]; // temp buffer

            while (!eof && total < n) {
                int count = read4(tmp);

                // check if it's the end of the file
                eof = count < 4;

                // get the actual count
                count = Math.min(count, n - total);

                // copy from temp buffer to buf
                for (int i = 0; i < count; i++)
                    buf[total++] = tmp[i];
            }

            return total;
        }
    }
//------------------------------------------------------------------------------
    //2
    //DescriptionHintsSubmissionsDiscussSolution
    //Accepted clean java solution
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

    public class Solution2 {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {

            char[] buffer = new char[4];
            boolean endOfFile = false;
            int readBytes = 0;

            while (readBytes < n && !endOfFile) {
                int currReadBytes = read4(buffer);
                if (currReadBytes !=4) {
                    endOfFile = true;
                }
                int length = Math.min(n - readBytes, currReadBytes);
                for (int i=0; i<length; i++) {
                    buf[readBytes + i] = buffer[i];
                }
                readBytes += length;
            }
            return readBytes;
        }
    }

//------------------------------------------------------------------------------
    //3
    //9Ch
    class Solution {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {
            char[] buf4 = new char[4];
            int offset = 0;

            while (true) {
                int size = read4(buf4);
                for (int i = 0; i < size && offset < n; i++) {
                    buf[offset++] = buf4[i];
                }
                if (size == 0 || offset == n) {
                    return offset;
                }
            }
        }
    }

//------------------------------------------------------------------------------
}
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.

Companies
Facebook

Related Topics
String

Similar Questions
Read N Characters Given Read4 II - Call multiple times
 */