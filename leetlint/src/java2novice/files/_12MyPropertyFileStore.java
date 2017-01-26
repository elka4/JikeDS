package java2novice.files;
 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
public class _12MyPropertyFileStore {
 
    public static void main(String a[]) throws IOException{
         
        OutputStream os = null;
        Properties prop = new Properties();
        prop.setProperty("name", "java2novice");
        prop.setProperty("domain", "www.java2novice.com");
        prop.setProperty("email", "java2novice@gmail.com");
        try {
            os = new FileOutputStream("MyProp.properties");
            prop.store(os, "Dynamic Property File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
    }
}

/*How to create and store property file dynamically?

Description:
We can also create property files dynamically. First creat Properties object, add all property key-value pair by using setProperty() method. Then write this file to FileOutputStream object to store is as a property file. Below example shows how to create it.

- See more at: http://www.java2novice.com/java-file-io-operations/create-store-property-file/#sthash.rRF6UYfz.dpuf*/