package java2novice.files;
 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
 
public class _13MyFileXmlStore {
     
    public static void main(String a[]) throws IOException{
         
        OutputStream os = null;
        Properties prop = new Properties();
        prop.setProperty("name", "java2novice");
        prop.setProperty("domain", "www.java2novice.com");
        prop.setProperty("email", "java2novice@gmail.com");
        try {
            os = new FileOutputStream("MyProp.xml");
            prop.storeToXML(os, "Dynamic Property File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
    }
}
/*Program: How to store property file as xml file?

Description:
We can also create property files in xml format dynamically. First creat Properties object, add all property key-value pair by using setProperty() method. Then write this file to FileOutputStream object to store is as a property file as xml. Call storeToXML() method to store it to the file system. Below example shows how to create it.

- See more at: http://www.java2novice.com/java-file-io-operations/store-property-file-as-xml/#sthash.FL1bbVIw.dpuf*/