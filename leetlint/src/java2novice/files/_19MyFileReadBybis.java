package java2novice.files;
 
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
 @SuppressWarnings("all")

public class _19MyFileReadBybis {
 
    public static void main(String a[]){
         
        InputStream is = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            is = new FileInputStream("/Users/ngootooru/sample.txt");
            bis = new BufferedInputStream(is);
            dis = new DataInputStream(bis);
            String temp = null;
            while((temp = dis.readLine()) != null){
                System.out.println(temp);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
/*Program: How to read a file using BufferedInputStream?

Below example shows how to read a file using BufferedInputStream.

- See more at: http://www.java2novice.com/java-file-io-operations/read-buffered-stream/#sthash.Ot1zwYNV.dpuf*/