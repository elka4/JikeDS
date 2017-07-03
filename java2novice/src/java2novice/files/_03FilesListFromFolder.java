package java2novice.files;
 
import java.io.File;
 
public class _03FilesListFromFolder {
     
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        File[] files = file.listFiles();
        for(File f: files){
            System.out.println(f.getName());
        }
    }
}
/*How to get list of all file objects from a folder in java?

Description:
Below example shows how to get list of all file objects from the given folder. First create File object by passing folder path. Call listFiles() method on file object to get list of file names in the given folder.

- See more at: http://www.java2novice.com/java-file-io-operations/file-list-from-folder/#sthash.yYStvPXO.dpuf*/