package java2novice.files;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class _06ReadLinesFromFile {
 
    public static void main(String a[]){
        BufferedReader br = null;
        String strLine = "";
        try {
            br = new BufferedReader( new FileReader("fileName"));
            while( (strLine = br.readLine()) != null){
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }
}
/*How to read file content line by line in java?

Description:
Below example shows how to read file content line by line. To get this, you have to use BufferedReader object. By calling readLine() method you can get file content line by line. readLine() returns one line at each iteration, we have to iterate it till it returns null.

- See more at: http://www.java2novice.com/java-file-io-operations/read-line-from-file/#sthash.ckuPUugy.dpuf*/