package edu.nyu.wse_journal.search;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@Component
@AllArgsConstructor
public class Config {
    //Get the current path as the root path(run the program at the root of WSE_Journal)
    private String currentPath = new File(System.getProperty("user.dir")).getPath();
    @Getter(AccessLevel.PUBLIC)
    private  String parentPath = new File(System.getProperty("user.dir")).getParent();
    @Getter(AccessLevel.PUBLIC)
    private String downloadsFolder = parentPath + "/downloads";
    @Getter(AccessLevel.PUBLIC)
    private String indexFolder= parentPath + "/index";
    @Getter(AccessLevel.PUBLIC)
    private String cachePath = parentPath + "/cacheFile";
    @Getter(AccessLevel.PUBLIC)
    private String configFile = parentPath + "/config.txt";
    @Getter(AccessLevel.PUBLIC)
    private String urlFile = parentPath + "/urlFile.txt";
    @Getter(AccessLevel.PUBLIC)
    private int weeknum = 1;

    public Config() {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            // read config file
            fr = new FileReader(configFile);
            br = new BufferedReader(fr);
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null){
                    br.close();
                }
                if (fr != null){
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
