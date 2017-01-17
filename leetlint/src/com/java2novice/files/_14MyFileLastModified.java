package com.java2novice.files;
 
import java.io.File;
 
public class _14MyFileLastModified {
     
    public static void main(String a[]) {
         
        File file = new File("MyProp.xml");
        System.out.println(file.lastModified());
    }
 
}
/*Program: How to get file last modified time?

Description:
Below example shows how to get last modified time of a file. lastModified() method gives you the last modified time of a file object.

- See more at: http://www.java2novice.com/java-file-io-operations/file-last-modified-time/#sthash.EyRn5oSP.dpuf*/