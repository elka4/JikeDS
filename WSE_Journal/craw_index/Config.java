package edu.nyu.wse_journal.craw_index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import lombok.*;

@Component
@AllArgsConstructor
public class Config {
    //Get the current path as the root path(run the program at the root of WSE_Journal)
    private String currentPath = new File(System.getProperty("user.dir")).getPath();
    private  String parentPath = new File(System.getProperty("user.dir")).getParent();

/*    @Getter(AccessLevel.PUBLIC)
    private String downloadsFolder = parentPath + "/downloads";;
    @Getter(AccessLevel.PUBLIC)
    private String indexFolder = parentPath + "/index";;
    @Getter(AccessLevel.PUBLIC)
    private String cachePath = parentPath + "/cacheFile";;
    @Getter(AccessLevel.PUBLIC)
    private String configFile = parentPath + "/config.txt";
    @Getter(AccessLevel.PUBLIC)
    private String urlFile = parentPath + "/urlFile.txt";
    @Getter(AccessLevel.PUBLIC)
    private int weeknum = 1;*/

    @Getter(AccessLevel.PUBLIC)
    private String configFile = parentPath + "/config.txt";
    @Getter(AccessLevel.PUBLIC)
    private String downloadsFolder;
    @Getter(AccessLevel.PUBLIC)
    private String indexFolder;
    @Getter(AccessLevel.PUBLIC)
    private String cachePath;

    @Getter(AccessLevel.PUBLIC)
    private String urlFile;
    @Getter(AccessLevel.PUBLIC)
    private int weeknum = 1;




/*    @Getter(AccessLevel.PUBLIC)
    private String impactFile = parentPath + "/impact.txt";
    @Getter(AccessLevel.PUBLIC)
    private HashMap<String, Float> impact;*/


    public Config() {
        try {
            @Cleanup FileReader fr = new FileReader(configFile);
            @Cleanup BufferedReader br = new BufferedReader(fr);

            String str = null;
            String code = "";
            String content = "";

            while ((str = br.readLine() ) != null && !str.trim().equals("") && str.contains(";")) {
                String[] strArr = str.split(";");
                if (strArr[0] != null && !strArr[0].equals("")){
                    code = strArr[0].trim();
                }
                if (strArr[1] != null && !strArr[1].equals("")){
                    content = strArr[1].trim();
                }

                switch (code){
                    case "downloadsFolder":
                        this.downloadsFolder = parentPath + "/" +  content;
                        System.out.println("downloadsFolder "+ content);
                        break;
                    case "weeknum":
                        this.weeknum = Integer.parseInt(content);
                        break;
                    case "cachePath":
                        this.cachePath = parentPath + "/" +  content;
                        break;
                    case "indexFolder":
                        this.indexFolder = parentPath + "/" +  content;
                        break;
                    case "urlFile":
                        this.urlFile = parentPath + "/" +  content;
                }
            }
            //readImpacFactor();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    private void readImpacFactor(){
        try {
            @Cleanup FileReader fr = new FileReader(impactFile);
            @Cleanup BufferedReader br = new BufferedReader(fr);

            String str = null;
            String code = "";
            String content = "";

            while ((str = br.readLine() ) != null && !str.trim().equals("") && str.contains(";")) {
                String[] strArr = str.split(";");
                if (strArr[0] != null && !strArr[0].equals("")){
                    code = strArr[0].trim();
                }
                if (strArr[1] != null && !strArr[1].equals("")){
                    content = strArr[1].trim();
                }
                if(!code.equals("") && !content.equals("")){
                    impact.put(code, Float.parseFloat(content));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



}
