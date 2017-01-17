package com.java2novice.files;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
 
public class _07ClassLoaderFileLoading {
 
    private static Properties appProp = null;
     
    static {
        try{
            InputStream is =
                _07ClassLoaderFileLoading.class.getResourceAsStream("/FileName.properties");
            appProp = new Properties();
            appProp.load(is);
        } catch(IOException ex){
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
/*Program: How to read property file in static context?

Description:
Below example shows how to read any file (property file, image file, etc) from a static method, or static block using getResourceAsStream() method. You can read any file in this way if it is in your class path. Make sure that the rendering file is in the class path.

- See more at: http://www.java2novice.com/java-file-io-operations/read-file-static-context/#sthash.H4LSLKTl.dpuf*/