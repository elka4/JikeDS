package edu.nyu.wse_journal.craw_index;

import edu.nyu.wse_journal.craw_index.Crawlers.*;
import edu.nyu.wse_journal.craw_index.Crawlers.Crawler_NatureNM;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import lombok.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

@SuppressWarnings("all")
@Component
//@Scope("prototype")
public class CrawIndex {

    private HashMap<String, JournalUrl> link_JournalUrl_Hash = new HashMap<String, JournalUrl>();

    private ArrayList<String> startingUrls = new ArrayList<String>();

    private HashSet<String> cache = new HashSet<String>();

//    protected  Config config = new Config();

    //private String parentPath = new File(System.getProperty("user.dir")).getParent();

//    @Value("${config.downloadsFolder}, required=true")
//    private String downloadsFolder_path;
    //protected String downloadsFolder = parentPath + "/" + "down";
protected String downloadsFolder = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/down";

//3
//    @Value("${config.cachePath}, required=true")
//    private String cacheFile_path;
    //private String cacheFile = parentPath + "/" + "cache";
    private String cacheFile = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/cache.txt";



/*    @Value("${config.downloadsFolder, required=true}")
    private String downloadsFolder;

    @Value("${cacheFile}")
    private String cacheFile;*/

//    @Value("${config.indexFolder}, required=true")
//    private String indexFolder_path;
    //private String indexFolder = parentPath + "/" + "index";
    private String indexFolder = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/index";



//    @Value("${config.url}, required=false")
//    private String urlFile_Path;
    //private String urlFile = parentPath + "/" + "urlFile.txt";
    private String urlFile = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/urlFile.txt";

    //    @Value("${config.num}")
    private int weeknum = 2;

//    private int weeknum = Integer.parseInt(weeknumStr.trim());

//    urlFile = urlFile.txt



    boolean debugIndex = true;



/////////////////////////////////////////////////////////////////////
    // 1
    //create file and folders if not exits
    void prepare(){
        System.out.println("void prepare(){");

        // 1.1 create download folder if not exits
        prepareDownloadsFolder();

        // 1.2 read cache file; create cache file if not exits
        prepareCacheFile();

        // 1.3 create index folder if not exits
        prepareIndexFolder();

    }

/////////////////////////////////////////////////////////////////////
    // 1.1
    // 1.1 create download folder if not exits
    private void prepareDownloadsFolder(){
        System.out.println("downloadsFolder: " + downloadsFolder);
        File downloadsFolder_File = new File(downloadsFolder);
        if (!downloadsFolder_File.exists() && !downloadsFolder_File.isDirectory()) {
            downloadsFolder_File.mkdir();
        } else{
            //delete outdated files in downloads folder
            File[] files = downloadsFolder_File.listFiles();
            for (File fileToDelete : files) {
                long modifiedTime = fileToDelete.lastModified();
                long currentTime = new Date().getTime();
                long longestPeriod = getLongestTimeRange();
                if (currentTime - modifiedTime > longestPeriod){
                    //fileToDelete.delete();
                }
            }
        }
    }

/*    //Chem
    private static void CheckFolder() {
        // TODO Auto-generated method stub
        File file = new File(config.dnfolder);
        if (!file.exists() && !file.isDirectory()) {
            // if folder does not exist, then make one.
            file.mkdir();
        } else {
            // check date
            File[] files = file.listFiles();
            for (File file2 : files) {
                // create time
                long modifiedTime = file.lastModified();
                // current time
                long now = new Date().getTime();

                // threshold
                long threshold = config.weeknum * 7 * 24 * 3600 * 1000;
                if (now - modifiedTime > threshold)
                    file2.delete();
            }
        }
    }*/

/////////////////////////////////////////////////////////////////////
    // 1.2
    // 1.2 read cache file; create cache file if not exits
    private void prepareCacheFile(){
        System.out.println("cacheFile: " + cacheFile);
        File cacheFile_File = new File(cacheFile);
        if (!cacheFile_File.exists() || !cacheFile_File.isFile()) {
            try{
                cacheFile_File.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try{
                @Cleanup BufferedReader br = new BufferedReader(new FileReader(cacheFile));
                String cacheLine = null;
                while ((cacheLine = br.readLine()) != null) {
                    cache.add(cacheLine.split(" ")[1]);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

/////////////////////////////////////////////////////////////////////
    // 1.3
    // 1.3 create index folder if not exits
    private void prepareIndexFolder(){
        //delete the exiting folder first
        System.out.println("indexFolder: " + indexFolder);
        File indexFolder_File = new File(indexFolder);
        if (indexFolder_File.exists()) {
            try{
                FileUtils.deleteDirectory(indexFolder_File);
            }catch (IOException ioe){

            }
        }
        indexFolder_File.mkdir();
    }

/////////////////////////////////////////////////////////////////////


    private void deleteOutdateIndex() {
        File indexFolder_File = new File(indexFolder);

        try {

            Directory indexDirectory = FSDirectory.open(indexFolder_File.toPath());
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);
            Query expiredQuery = LongPoint.newRangeQuery("date",
                    (long) 0, new Date().getTime() - getLongestTimeRange());

            indexWriter.deleteDocuments(expiredQuery);
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





/////////////////////////////////////////////////////////////////////
    // 2
    /*
    read urlFile
     */
    void readUrls() {
        System.out.println("======================start readUrls===========================");
        System.out.println("urlFile: " + urlFile);
//        System.out.println(urlFile);


        try {
            @Cleanup FileReader fr = new FileReader(urlFile);
            @Cleanup BufferedReader br = new BufferedReader(fr);
            String str = null;
            JournalUrl jUrl = new JournalUrl();
            String code = "";
            String content = "";

            while ((str = br.readLine()) != null) {

                if (str.contains(";")){
                    code = str.split(";")[0].trim();
                    content = str.split(";")[1].trim();
                    System.out.println("code: " + code);
                    System.out.println("content: " + content);
                }

                switch (code) {
                    case "":
                        break;

                    case "JOURNAL":
                        if(content.equals("Nature")){
                            processNature(jUrl);
                        }
                        System.out.println("JOURNAL: " + content);
                        if (!jUrl.getName().equals("")) {
                            link_JournalUrl_Hash.put(jUrl.getLink(), jUrl);
                            jUrl = new JournalUrl();
                        }
                        jUrl.setName(content);
                        break;

                    case "Link":
                        jUrl.setLink(content);
                        if (!jUrl.getName().equals("Nature") && !jUrl.getName().equals("Science")) {
                            startingUrls.add(jUrl.getLink());
                            System.out.println("jUrl.getLink(): "+ jUrl.getLink());
                            System.out.println("startingUrls: " + startingUrls);
                        }
                        System.out.println("jUrl.getLink(): "+ jUrl.getLink());
                        System.out.println("startingUrls: " + startingUrls);
                        break;

                    case "Level":
                        jUrl.setLevel(Integer.parseInt(content));
                        if (jUrl.getName().equals("Science")) {
                            processScience(jUrl);
                            jUrl = new JournalUrl();
                        }
                        System.out.println("Level: " + Integer.parseInt(content));
                        break;

                    case "First_Level_Pre_Filter":
                        //First_Level_Pre_Filter; research
                        jUrl.setFlpre(content);
                        System.out.println("contentcontentcontentcontent: "+ content);
                        if (jUrl.getName().equals("Nature")) {

                            processNature(jUrl);
                            jUrl = new JournalUrl();
                        }
                        System.out.println("Nature: First_Level_Pre_Filter");
                        break;

                    case "First_Level_Post_Filter":
                        jUrl.setFlpost(content);
                        System.out.println("Nature: First_Level_Post_Filter");

                        break;

                    case "Second_Level_Pre_Filter":
                        jUrl.setSecpre(content);
                        System.out.println("Nature: Second_Level_Pre_Filter");

                        break;

                    case "Second_Level_Post_Filter":
                        jUrl.setSecpost(content);
                        System.out.println("Nature: Second_Level_Post_Filter");

                        break;

                }//switch

            }//while

            if (!jUrl.getName().equals("")) {
                link_JournalUrl_Hash.put(jUrl.getLink(), jUrl);
            }

            } catch (IOException e) {
                e.printStackTrace();
            }

        System.out.println("======================end readUrls===========================");

    }//ReadJournalUrls()


/////////////////////////////////////////////////////////////////////
    //
    private void processScience(JournalUrl jUrl) {

        // 2015 Jan 02 Vol 347, Iss 6217
        int stdVol = 347;
        int stdIss = 6217;

        for (int i = 1; i <= weeknum; i++) {
            long volume = stdVol;
            long issue = stdIss;

            Calendar now2 = new GregorianCalendar();
            Date last = new Date(new Date().getTime() - (i - 1) * (7 * 24 * 3600 * 1000));
            now2.setTime(last);

            volume += (now2.get(Calendar.YEAR) - 2015) * 4;

            if (now2.get(Calendar.MONTH) <= 2) {
            } else if (now2.get(Calendar.MONTH) <= 5) {
                volume++;
            } else if (now2.get(Calendar.MONTH) <= 8) {
                volume += 2;
            } else if (now2.get(Calendar.MONTH) <= 11) {
                volume += 3;
            }

            Calendar calendar = Calendar.getInstance();

            calendar.set(2015, 0, 2);

            Date standard = calendar.getTime();

            long tmp = (new Date().getTime() / getMiliSecInADay() - standard.getTime() / getMiliSecInADay()) / 7;

            issue += tmp - i;

            JournalUrl newJUrl = new JournalUrl();

            newJUrl.setName(jUrl.getName());

            newJUrl.setLink(jUrl.getLink() + volume + "/" + issue);

            newJUrl.setLevel(jUrl.getLevel());

            newJUrl.setFlpost(jUrl.getFlpost());


            link_JournalUrl_Hash.put(newJUrl.getLink(), newJUrl);

            startingUrls.add(newJUrl.getLink());
        }
    }
    
/////////////////////////////////////////////////////////////////////
    
    private void processNature(JournalUrl jUrl) {
        System.out.println("..................start processNature..................");

        // the start date to count
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(new Date());
        startDate.add(startDate.DATE, -weeknum * 7);

        // the end date: today
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        //the nature journal has year and month in the url
        int monthNum = (today.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)) * 12
                + today.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);

        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH) + 1;


        for (int c = 0; c <= monthNum; c++) {

            JournalUrl newJUrl = new JournalUrl();
            String linkStr = jUrl.getLink();
            if (linkStr == null || linkStr.equals("")){
                continue;
            }
            linkStr = jUrl.getLink() + "?year=" + curYear + "&month=" + curMonth;
            System.out.println("linkStr: " + linkStr);
            newJUrl.setLink(linkStr);

            newJUrl.setLevel(jUrl.getLevel());
            newJUrl.setFlpre(jUrl.getFlpre());

            link_JournalUrl_Hash.put(newJUrl.getLink(), newJUrl);

            startingUrls.add(linkStr);
            System.out.println("[startingUrls] " + startingUrls);

            curMonth--;
            if (curMonth == 0) {
                curYear--;
                curMonth = 12;
            }
        }


        System.out.println("..................end processNature..................");

    }
    
/////////////////////////////////////////////////////////////////////
    // 3
    void crawl() {
        System.out.println("======================start crawl===========================");

        WebDriver webDriver = new HtmlUnitDriver(false);

        for (int i = 0; i < startingUrls.size(); i++) {

            System.out.println("startingUrls: " + startingUrls.get(i));

            //http://science.sciencemag.org/content/357/6355
            //http://science.sciencemag.org/content/357/6354

            if (startingUrls.get(i).contains("science.sciencemag.org")) {
               Crawler_Science wc = new Crawler_Science();
                wc.run(link_JournalUrl_Hash.get(startingUrls.get(i)), cache, webDriver);
                wc.updateCache();
                continue;
            }


            //"http://www.nature.com/nature/research/biological-sciences.html?year=2017&month=9"
            //http://www.nature.com/nature/research/biological-sciences.html?year=2017&month=8
            if (startingUrls.get(i).contains("/nature/")) {
                Crawler_Nature crawler_Nature = new Crawler_Nature();
//                System.out.println("startingUrls: " + startingUrls.get(i).toString());
                crawler_Nature.run(link_JournalUrl_Hash.get(startingUrls.get(i)), cache, webDriver);
                crawler_Nature.updateCache();
                continue;
            }
            //http://www.nature.com/nm/
            // OK for natureNM

            else if (startingUrls.get(i).contains("/nm/")) {
                System.out.println("startingUrls.get(i).contains(\"/nm/\")");
                Crawler_NatureNM crawler_NatureNM = new Crawler_NatureNM();
                crawler_NatureNM.run(link_JournalUrl_Hash.get(startingUrls.get(i)),  cache, webDriver);
                crawler_NatureNM.updateCache();
                continue;
            }

            else if (startingUrls.get(i).contains("/neuro/")) {
                System.out.println("startingUrls.get(i).contains(\"/neuro/\")");
                Crawler_NatureNeuro crawler_NatureNeuro = new Crawler_NatureNeuro();
                crawler_NatureNeuro.run(link_JournalUrl_Hash.get(startingUrls.get(i)),  cache, webDriver);
                crawler_NatureNeuro.updateCache();
                continue;
            }


            else if (startingUrls.get(i).contains("/nm/")) {
                Crawler_NatureNM crawler_NatureJsoup = new Crawler_NatureNM();
                System.out.println("startingUrls: " + startingUrls.get(i).toString());
                System.out.println("crawler_natureJsoup");
                crawler_NatureJsoup.run(link_JournalUrl_Hash.get(startingUrls.get(i)), cache, webDriver);
                crawler_NatureJsoup.updateCache();
                continue;
            }

            else if (startingUrls.get(i).contains("/neuro/")) {
                Crawler_NatureNeuro crawler_NatureHTML = new Crawler_NatureNeuro();
                System.out.println("startingUrls: " + startingUrls.get(i).toString());
                System.out.println("crawler_natureHTML");
                crawler_NatureHTML.run(link_JournalUrl_Hash.get(startingUrls.get(i)), cache, webDriver);
                crawler_NatureHTML.updateCache();
                continue;
            }


            else if (startingUrls.get(i).contains("wiley")) {
                Crawler_Wiley wc = new Crawler_Wiley();
                wc.run(link_JournalUrl_Hash.get(startingUrls.get(i)), cache, webDriver);
                wc.updateCache();
                continue;
            }


        }

        webDriver.close();

        System.out.println("======================end crawl===========================");

    }

/////////////////////////////////////////////////////////////////////
    // 4
    void index() {
        if(debugIndex){
            System.out.println("====================start createIndex=====================");
        }

        //analyzaer and IndexWriterConfig
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        Directory indexDirectory = null;
        IndexWriter indexWriter = null;

        File indexFolder_File = new File(indexFolder);
        try {
            indexDirectory = FSDirectory.open(indexFolder_File.toPath());
            indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // process html files with HTMLhandler
        File downloadsFolder_File = new File(downloadsFolder);
        Document doc = null;
        HTMLHandler htmlHandler = new HTMLHandler();

        for (File file : downloadsFolder_File.listFiles()) {
            try {
                doc = htmlHandler.processHTML(file);
                if (doc != null) {
                    indexWriter.addDocument(doc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(debugIndex) System.out.println("====================end createIndex=====================");

        //deleteOutdateIndex();

    }//index

/////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////

    private long getLongestTimeRange(){

        return weeknum * 7 * 24 * 60 * 60 * 1000;
    }


    private long getMiliSecInADay(){

        return 24 * 60 * 60 * 1000;
    }

/////////////////////////////////////////////////////////////////////
}
