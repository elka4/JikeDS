package com.java2novice.files;
 
import java.io.File;
import java.io.FilenameFilter;
 
public class _09MyFilteredFileList {
     
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        File[] files = file.listFiles(new FilenameFilter() {
             
            @Override
            public boolean accept(File dir, String name) {
                if(name.toLowerCase().endsWith(".csv")){
                    return true;
                } else {
                    return false;
                }
            }
        });
        for(File f:files){
            System.out.println(f.getName());
        }
    }
}

/*Program: How to get file list from a folder filtered by extensions?

Description:
Below example shows how to get list of all file objects from the given folder. First create File object by passing folder path. Call listFiles() method on file object to get list of file names in the given folder.

- See more at: http://www.java2novice.com/java-file-io-operations/file-list-by-file-filter/#sthash.8msmiU3e.dpuf*/