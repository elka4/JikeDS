package com.java2novice.files;
 
import java.io.File;
 
public class _10MyFileUrl {
     
    public static void main(String a[]){
        File f = new File("C:/TestForm.txt");
        System.out.println(f.toURI());
    }
}
/*Program: How to get file URI reference?

Description:
Below example shows how to get URI reference to the given file. By calling toURI() method on file object, you can get the URI reference.

- See more at: http://www.java2novice.com/java-file-io-operations/file-uri-url/#sthash.PF5n5dts.dpuf*/