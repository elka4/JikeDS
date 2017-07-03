package java2novice.files;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
 
/**
 * Reading contents from a file into byte array.
 *
 */
public class _05FileToByteArray {
 
    public static void main(String a[]){
         
        String fileName = "C:/MyFile.txt";
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            byte content[] = new byte[2*1024];
            int readCount = 0;
            while((readCount = is.read(content)) > 0){
                System.out.println(new String(content, 0, readCount-1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(is != null) is.close();
            } catch(Exception ex){
                 
            }
        }
    }
}

/*How to read file content using byte array?

Description:
Write a program to read a file using byte array. You need to read fixed size of content each time, read multiple times, till end of the file.

- See more at: http://www.java2novice.com/java-file-io-operations/read-as-byte-array-from-file/#sthash.aRW8az7I.dpuf*/