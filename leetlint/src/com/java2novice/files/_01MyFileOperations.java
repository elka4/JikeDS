package com.java2novice.files;
 
import java.io.File;
 
public class _01MyFileOperations {
 
    public static void main(String[] a){
         
        try{
            File file = new File("fileName");
            //Tests whether the application can read the file
            System.out.println(file.canRead());
            //Tests whether the application can modify the file
            System.out.println(file.canWrite());
            //Tests whether the application can modify the file
            System.out.println(file.createNewFile());
            //Deletes the file or directory
            System.out.println(file.delete());
            //Tests whether the file or directory exists.
            System.out.println(file.exists());
            //Returns the absolute pathname string.
            System.out.println(file.getAbsolutePath());
            //Tests whether the file is a directory or not.
            System.out.println(file.isDirectory());
            //Tests whether the file is a hidden file or not.
            System.out.println(file.isHidden());
            //Returns an array of strings naming the
            //files and directories in the directory.
            System.out.println(file.list());
        } catch(Exception ex){
             
        }
    }
}
/*Program: All file operations.

Description:
Below example shows basic operations on File object.

canRead(): Tests whether the application can read the file 
canWrite(): Tests whether the application can modify the file
createNewFile(): Tests whether the application can modify the file 
delete(): Deletes the file or directory
exists(): Tests whether the file or directory exists.
getAbsolutePath(): Returns the absolute pathname string.
isDirectory(): Tests whether the file is a directory or not.
isHidden(): Tests whether the file is a hidden file or not.
list(): Returns an array of strings naming the 
	files and directories in the directory.
- See more at: http://www.java2novice.com/java-file-io-operations/file-operations/#sthash.b8mXZnGd.dpuf*/