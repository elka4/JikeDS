package java2novice.files;
 
import java.io.File;
import java.io.IOException;
 
public class _20MyTmpFile {
 
    public static void main(String a[]){
         
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("MyTempFile", ".tmp");
            System.out.println("Created Temp File Location : " + tmpFile.getAbsolutePath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
/*Program: How to create temporary file in java?

Below example shows how to create a temporary file in java.

- See more at: http://www.java2novice.com/java-file-io-operations/create-temp-file/#sthash.vdGYnqEK.dpuf*/