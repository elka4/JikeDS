package com.java2novice.files;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class _21MyTempFileWrite {
 
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
    }
}
/*Program: How to write or store data into temporary file in java?

Below example shows how to write or store data into a temporary file in java.

- See more at: http://www.java2novice.com/java-file-io-operations/write-temp-file/#sthash.ptMrPOjp.dpuf*/