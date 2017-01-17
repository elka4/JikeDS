package com.java2novice.files;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
 
public class _08ReadFromConsole {
 
    public static void main(String a[]){
        BufferedReader br = null;
        Reader r = new InputStreamReader(System.in);
        br = new BufferedReader(r);
        String str = null;
        try {
            do{
                System.out.println("Enter Input, exit to quit.");
                str = br.readLine();
                System.out.println(str);
            } while (!str.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(br != null) br.close();
            }catch(Exception ex){}
        }
    }
}
/*How to read input from java console in java?

Description:
Below example shows how to read input from java console. You have to pass System.in object to InputStreamReader class. Create BufferedReader object by passing InputStreamReader, readLine() method can helps you to get the typed commands.

- See more at: http://www.java2novice.com/java-file-io-operations/read-input-from-console/#sthash.buQCOnbq.dpuf*/