package java2novice.files;
 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class _17MyByteArrayToReader {
 
    public static void main(String a[]){
         
        String str = "converting to input stream"+
                        "\n and this is second line";
        byte[] content = str.getBytes();
        InputStream is = null;
        BufferedReader bfReader = null;
        try {
            is = new ByteArrayInputStream(content);
            bfReader = new BufferedReader(new InputStreamReader(is));
            String temp = null;
            while((temp = bfReader.readLine()) != null){
                System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(is != null) is.close();
            } catch (Exception ex){
                 
            }
        }
         
    }
}

/*Program: How to convert byte array to reader or BufferedReader?

Sometimes we need to convert byte array object to reader object. Below example shows how to convert byte array to bufferedreader object.

- See more at: http://www.java2novice.com/java-file-io-operations/byte-array-to-reader/#sthash.bQDMnMeR.dpuf*/