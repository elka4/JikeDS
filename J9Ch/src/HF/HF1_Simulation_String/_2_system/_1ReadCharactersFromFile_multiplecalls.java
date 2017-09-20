package HF.HF1_Simulation_String._2_system;


/*
Example : 1 2 3 4 5 6 7 8 9
• 每次读的个数 6 1 4
• 第一次:1 2 3 4 5 6
• 第二次:7
• 第三次:8 9
难点:
• 如果只读3个，剩下的一个怎么处理?(读多的怎么留着给下次用?)
• Read4 这个函数只读了2个怎么办? (读到末尾时，没有读全4个)
 */

/*

思路:
• 类似内存从磁盘读数据
• 做一个buffer， buffer是一个队列:
– 队列先进先出可以保持顺序不变
– 队列为空时就进队(read4)
– 队列不为空时就满足内存的请求，也就是出队
• Example : 1 2 3 4 5 6 7 8 9
• 每次读的个数 6 1 4
• 具体实现
 */

/*
• Company Tags: Google Facebook
考点:
• 对队列这种数据结构的理解
• 对细节的处理，大家都会但不容易写对 • Onsite
• 25min
 */

/*
能力维度:
3. 基础数据结构/算法
5. 细节处理(corner case)
7. debug能力
 */
public class _1ReadCharactersFromFile_multiplecalls {
    //in class
    char[] buffer = new char[4];
    int head = 0, tail = 0;

    public int read(char[] buf, int n){
        int i = 0;
        while(i < n){
            if (head == tail) {              //queue is empty
                head = 0;
                tail = read4(buffer);        //enqueue
                if (tail == 0) {
                    break;
                }
            }
            while(i < n && head < tail) {
                buf[i++] = buffer[head++];   //dequeue
            }
        }
        return i;
    }

    int read4(char[] buffer){
        return 1;
    }

/////////////////////////////////////////////////////////////////////

    //jiuzhang

     class Solution {
        /**
         * @param buf destination buffer
         * @param n maximum number of characters to read
         * @return the number of characters read
         */
        public int read(char[] buf, int n) {
            // Write your code here
            int i = 0;
            while (i < n && (i4 < n4 || (i4 = 0) < (n4 = read4(buf4))))
                buf[i++] = buf4[i4++];
            return i;
        }
        char[] buf4 = new char[4];
        int i4 = 0, n4 = 0;
    }

/////////////////////////////////////////////////////////////////////

    // version: 高频题班
     class Solution2 {
        /**
         * @param buf destination buffer
         * @param n maximum number of characters to read
         * @return the number of characters read
         */
        char[] buffer = new char[4];
        int head = 0, tail = 0;

        public int read(char[] buf, int n) {
            // Write your code here
            int i = 0;
            while (i < n) {
                if (head == tail) {               // queue is empty
                    head = 0;
                    tail = read4(buffer);         // enqueue
                    if (tail == 0) {
                        break;
                    }
                }
                while (i < n && head < tail) {
                    buf[i++] = buffer[head++];    // dequeue
                }
            }
            return i;
        }
    }

/////////////////////////////////////////////////////////////////////

}
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 注意事项

The read function may be called multiple times.
 */