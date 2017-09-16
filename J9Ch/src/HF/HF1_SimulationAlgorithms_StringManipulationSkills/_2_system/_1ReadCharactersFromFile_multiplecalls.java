package HF.HF1_SimulationAlgorithms_StringManipulationSkills._2_system;

public class _1ReadCharactersFromFile_multiplecalls {

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
}
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 注意事项

The read function may be called multiple times.
 */