package edu.nyu.wse_journal.craw_index;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.io.File;

public class test {

//    protected  Config config = new Config();

    private String parentPath = new File(System.getProperty("user.dir")).getParent();

    //    @Value("${config.downloadsFolder}, required=true")
//    private String downloadsFolder_path;
    protected String downloadsFolder = parentPath + "/" + "down";

    //3
//    @Value("${config.cachePath}, required=true")
//    private String cacheFile_path;
    private String cacheFile = parentPath + "/" + "cache";

/*    @Value("${config.downloadsFolder, required=true}")
    private String downloadsFolder;

    @Value("${cacheFile}")
    private String cacheFile;*/

    //    @Value("${config.indexFolder}, required=true")
//    private String indexFolder_path;
    private String indexFolder = parentPath + "/" + "index";

    //    @Value("${config.url}, required=false")
//    private String urlFile_Path;
    private String urlFile = parentPath + "/" + "urlFile.txt";

//    @Test
//    public void test01(){
//
//    }
}
