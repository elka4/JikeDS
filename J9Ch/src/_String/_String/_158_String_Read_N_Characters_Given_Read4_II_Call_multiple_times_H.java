package _String._String;
import java.util.*;
import org.junit.Test;

//  158. Read N Characters Given Read4 II - Call multiple times
//  https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/description/
//  http://www.lintcode.com/problem/read-n-characters-given-read4-ii-call-multiple-times/
//
//接口：int read4(char * buf)一次从文件中读取 4 个字符。
//返回值是实际读取的字符数。 例如，如果文件中只剩下 3 个字符，则返回 3。
//通过使用read4 接口，实现从文件读取 n 个字符的函数int read(char * buf，int n)。
//
//4:
//
public class _158_String_Read_N_Characters_Given_Read4_II_Call_multiple_times_H {
//------------------------------------------------------------------------------
interface Reader4{
    int read(char[] buf, int n);
}
    int read4(char[] buf) {return 1;}
//------------------------------------------------------------------------------
    //1
    //A simple Java code
    class Solution1{
        private int buffPtr = 0;
        private int buffCnt = 0;
        private char[] buff = new char[4];
        public int read(char[] buf, int n) {
            int ptr = 0;
            while (ptr < n) {
                if (buffPtr == 0) {
                    buffCnt = read4(buff);
                }
                if (buffCnt == 0) break;
                while (ptr < n && buffPtr < buffCnt) {
                    buf[ptr++] = buff[buffPtr++];
                }
                if (buffPtr >= buffCnt) buffPtr = 0;
            }
            return ptr;
        }
    }

//I used buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls. In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.


//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
    //2
/*    Simple short Java / C++
    Just keep copying as long as more is both desired and available (read more on the fly when needed).

    It's also fast, 2 ms for Java and 0 ms for C++, there are no faster ones.

    Java*/

    public class Solution2 {
        public int read(char[] buf, int n) {
            int i = 0;
            while (i < n && (i4 < n4 || (i4 = 0) < (n4 = read4(buf4))))
                buf[i++] = buf4[i4++];
            return i;
        }
        char[] buf4 = new char[4];
        int i4 = 0, n4 = 0;
    }
//------------------------------------------------------------------------------
    //What is the difference between call once and call multiple times?
/*
Call once: Assume you are always going to read from the start of the file/bufer.
Call multiple times: Start reading from where you left off. This means that you have to store the last place (ptr) where you stopped and store the read but uncopied bytes to the buffer.


 */

//------------------------------------------------------------------------------
    //3
/*Clean solution in Java
    Keep a buffer of size 4 as a class variable, call it prevBuf.
    Whenever we call read(n), read from prevBuf first until all characters in prevBuf are consumed (to do this, we need 2 more int variables prevSize and prevIndex, which tracks the actual size of prevBuf and the index of next character to read from prevBuf). Then call read4 to read characters into prevBuf.
    The code is quite clean I think.*/

    /* The read4 API is defined in the parent class Reader4.
          int read4(char[] buf); */
// 2ms
// beats 58%
    public class Solution3 {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        char[] prevBuf = new char[4];
        int prevSize = 0;
        int prevIndex = 0;

        public int read(char[] buf, int n) {
            int counter = 0;

            while (counter < n) {
                if (prevIndex < prevSize) {
                    buf[counter++] = prevBuf[prevIndex++];
                } else {
                    prevSize = read4(prevBuf);
                    prevIndex = 0;
                    if (prevSize == 0) {
                        // no more data to consume from stream
                        break;
                    }
                }
            }
            return counter;
        }
    }
//------------------------------------------------------------------------------
    //4
    //9Ch
    public class Jiuzhang {
        private char[] buf4 = new char[4];
        private int buf4Index = 4;
        private int buf4Size = 4;

        private boolean readNext(char[] buf, int index) {
            if (buf4Index >= buf4Size) {
                buf4Size = read4(buf4);
                buf4Index = 0;
                if (buf4Size == 0) {
                    return false;
                }
            }

            buf[index] = buf4[buf4Index++];
            return true;
        }

        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         */
        public int read(char[] buf, int n) {
            for (int i = 0; i < n; i++) {
                if (!readNext(buf, i)) {
                    return i;
                }
            }

            return n;
        }
    }

//------------------------------------------------------------------------------
}
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Companies
Google Facebook Bloomberg

Related Topics
String

Similar Questions
Read N Characters Given Read4
 */