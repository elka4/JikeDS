package java2novice.files;
 
import java.io.File;
import java.io.FilenameFilter;
 
public class _04MyFileFilter {
 
    public static void main(String a[]){
        File file = new File("C:/MyFolder/");
        String[] files = file.list(new FilenameFilter() {
             
            @Override
            public boolean accept(File dir, String name) {
                if(name.toLowerCase().endsWith(".csv")){
                    return true;
                } else {
                    return false;
                }
            }
        });
        for(String f:files){
            System.out.println(f);
        }
    }
}

/*How to filter the files by file extensions and show the file names?

Description:
Below example shows how to get specific files from a folder. Sometimes we need to pic only specific extensions from the given folder. Implement FilenameFilter class and override accept() method, and add your filter logic here. Pass this object to list() method to get specific file extensions.

- See more at: http://www.java2novice.com/java-file-io-operations/file-names-by-file-filter/#sthash.MxkB085q.dpuf*/