package com.java2novice.files;
 
import java.io.File;
 
public class _02FileListFromFolder {
     
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        String[] fileList = file.list();
        for(String name:fileList){
            System.out.println(name);
        }
    }
}
/*How to get list of all file names from a folder in java?

Description:
Below example shows how to get list of all file names from the given folder. First create File object by passing folder path. Call list() method on file object to get list of file names in the folder.

- See more at: http://www.java2novice.com/java-file-io-operations/file-names-from-folder/#sthash.SxdiIHLu.dpuf*/