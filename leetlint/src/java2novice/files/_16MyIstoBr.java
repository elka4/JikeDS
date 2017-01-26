package java2novice.files;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class _16MyIstoBr {
 
    public static void main(String a[]){
         
        InputStream is = null;
        BufferedReader bfReader = null;
        try {
            is = new FileInputStream("C:/sample.txt");
            bfReader = new BufferedReader(new InputStreamReader(is));
            String temp = null;
            while((temp = bfReader.readLine()) != null){
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

/*Program: How to convert inputstream to reader or BufferedReader?

Sometimes we need to convert inputstream object to reader object, so that we can handle the stream according to our logic. Below example shows how to convert inputstream to bufferedreader object.

- See more at: http://www.java2novice.com/java-file-io-operations/stream-to-reader/#sthash.iQrlIM7K.dpuf*/