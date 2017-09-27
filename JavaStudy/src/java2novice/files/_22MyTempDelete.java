package java2novice.files;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class _22MyTempDelete {
 
    public static void main(String a[]){
        File tempFile = null;
        BufferedWriter writer = null;
        try {
            tempFile = File.createTempFile("MyTempFile", ".tmp");
            writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write("Writing data into temp file!!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                if(writer != null) writer.close();
            }catch(Exception ex){}
        }
        System.out.println("Stored data in temporary file.");
        /*
         * Write your application logic here
         * Once your application done with the temp data,
         * delete the temp file
         */
        //below method deletes the temp file on program exists.
        tempFile.deleteOnExit();
        //below method deletes the temp file immediately
        //tempFile.delete();
    }
}
/*Program: How to delete temporary file in java?

Below example shows how to delete a temporary file in java. The method deleteOnExit(), deletes the temp file on program exists. The method delete() deletes the file immediately.

- See more at: http://www.java2novice.com/java-file-io-operations/delete-temp-file/#sthash.juGzWG2Y.dpuf*/