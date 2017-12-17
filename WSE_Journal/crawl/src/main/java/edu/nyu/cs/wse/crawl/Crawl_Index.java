package edu.nyu.cs.wse.crawl;


import java.io.*;
import java.util.*;


import javafx.collections.transformation.FilteredList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Value;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.commons.io.FileUtils;
import lombok.*;

@Component
@Scope("prototype")
@SuppressWarnings("all")
public class Crawl_Index implements I_Crawl_Index {



//--------------------------------------------------------------------------------------
    private HashMap<String, JournalUrl> linkStr_JUrl = new HashMap<String, JournalUrl>();

    private ArrayList<String> startingUrls = new ArrayList<String>();
    private HashSet<String> cache_set = new HashSet<String>();

    private String parentPath = new File(System.getProperty("user.dir")).getParent();
    protected String downloadsFolder = parentPath + "/" + "down";
    protected String cacheFile = parentPath + "/" + "cache.txt";
    protected String indexFolder = parentPath + "/" + "index";
    protected String urlFile = parentPath + "/" + "urlFile.txt";
    @Value("config.weeknum")
    private int weeknum;
    long miliSecInADay = 24 * 60 * 60 * 1000;


//--------------------------------------------------------------------------------------

    public void run(){
        System.out.println("----------------- run crawler -----------------");
//        System.out.println("urlFile: " + urlFile);
//        System.out.println("weeknum: " + weeknum);
        prepareDownloadsFolder();
        prepareCacheFile();
        System.out.println("cache_set" + cache_set);
        readUrls();
        crawl();
        index();
//        System.out.println("startingUrls: " + startingUrls);
//        System.out.println("linkStr_JUrl: " + linkStr_JUrl);

    }
//--------------------------------------------------------------------------------------
    // 1.1 create download folder if not exits
    public void prepareDownloadsFolder(){
        // 1.1 create download folder if not exits
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
                    fileToDelete.delete();
                }
            }
        }
    }



    // 1.2 read cache file; create cache file if not exits; read NOT outdated records
    public void prepareCacheFile(){
        System.out.println("cacheFile: " + cacheFile);
        File file = new File(cacheFile);
        List<String> lines;

        if (!file.exists() || !file.isFile()) {
            try{//there is no cache file
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        } else{ //there is cache file
            try{//remove outdated cache records, read the others into cache_set
                lines = FileUtils.readLines(file, "UTF-8");
                for (int i = 0; i < lines.size(); i++){

                    String[] parts = lines.get(i).trim().split(" ");

/*                    for (int j = 0; j < parts.length; j++) {
                        System.out.println(parts[j]);
                    }*/

                    if(parts == null ||
                            parts[0].length() != 13 || !StringUtils.isNumeric(parts[0])){
                        System.out.println("%%%%%%%%%%%%%%%%1111111%%%%%%%%%%%%%%%%");
                         continue;
                    }
                    if (parts.length != 2 ){
                        System.out.println("lparts.length != 2: " + parts.length );
                        System.out.println("%%%%%%%%%%%%%%%%22222222%%%%%%%%%%%%%%%%");

//                        lines.remove(i--);
                        continue;
                    }else {
                        System.out.println("lines.size(): " + parts.length);
                        System.out.println("%%%%%%%%%%%%%%%%%%%3333333%%%%%%%%%%%%%%%%%%");

                    }

                    long currentTime = new Date().getTime();
                    long modifiedTime = Long.parseLong(parts[0]);
                    long longestPeriod = getLongestTimeRange();

                    if (currentTime - modifiedTime > longestPeriod){
                        lines.remove(i--);
                    } else {
                        System.out.println("parts[1].trim(): " + parts[1].trim());
                        System.out.println("parts[1].trim(): " + parts[1].trim());
                        System.out.println("parts[1].trim(): " + parts[1].trim());
                        cache_set.add(parts[1].trim());
                    }
                }
                org.apache.commons.io.FileUtils.writeLines(file, lines);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        System.out.println("cache_setcache_setcache_set: " + cache_set);
    }



    // 1.3 create index folder if not exits
    public void prepareIndexFolder(){
        //delete the exiting folder first
        System.out.println("indexFolder: " + indexFolder);
        File indexFolder_File = new File(indexFolder);
        if (indexFolder_File.exists()) {
            try{
                FileUtils.deleteDirectory(indexFolder_File);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        indexFolder_File.mkdir();
    }

/*
JOURNAL;Science
Link;http://science.sciencemag.org/content/
Level;1
 */

    public void readUrls() {
        System.out.println("===============start readUrls================");
        System.out.println("urlFile: " + urlFile);

        try {

            File file = new File(urlFile);
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            String line = null;
            String code = "";
            String content = "";
            JournalUrl jUrl = new JournalUrl();

            for (int i = 0; i < lines.size(); i++){
                System.out.println("------------------line-------------------");
                System.out.println("line: " + lines.get(i));
                line = lines.get(i);
                // get the first part "code", the second part "contetn"
                if (line.contains(";")){
                    String[] parts = line.split(";");
                    code = parts[0].trim();
                    content = parts[1].trim();
                    System.out.println("code: " + code);
                    System.out.println("content: " + content);
                    System.out.println(".................................");
                } else {
                    continue;
                }

                switch (code) {
                    case "":
                        break;

                    case "Journal":
                        //if(content.equals("Nature")){ processNature(jUrl); }

                        System.out.println("Journal: " + content);

                        if (!jUrl.getName().equals("")) {
                            linkStr_JUrl.put(jUrl.getLink(), jUrl);
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
                        processScience(jUrl);
                        jUrl = new JournalUrl();
                        System.out.println("Level: " + Integer.parseInt(content));
                        break;

                    case "First_Level_Pre_Filter":
                        //First_Level_Pre_Filter; research
                        jUrl.setFlpre(content);
                        System.out.println("contentcontentcontentcontent: "+ content);

                        //processNature(jUrl);

                        if (jUrl.getName().equals("Nature")) {
                            //processNature(jUrl);
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

            }

            if (!jUrl.getName().equals("")) {
                linkStr_JUrl.put(jUrl.getLink(), jUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("======================end readUrls===========================");

    }
//--------------------------------------------------------------------------------------
    private void processScience(JournalUrl jUrl) {
        System.out.println("------------------processScience------------------");
        if (!jUrl.getName().equals("Science")) {
            return;
        }
        // 2015 Jan 02 Vol 347, Iss 6217
        int stdVol = 347;
        int stdIss = 6217;

        for (int i = 1; i <= weeknum; i++) {
            long volume = stdVol;
            long issue = stdIss;

            Calendar now2 = new GregorianCalendar();
            Date last = new Date(new Date().getTime() -
                    (i - 1) * (7 * 24 * 3600 * 1000));
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
    
            long tmp = (new Date().getTime() / 86400000 - standard.getTime() / 86400000) / 7;
    
            issue += tmp - i - 1;
    
            JournalUrl newJUrl = new JournalUrl();
    
            newJUrl.setName(jUrl.getName());
    
            newJUrl.setLink(jUrl.getLink() + volume + "/" + issue);
            System.out.println("1. jUrl.getLink(): " + jUrl.getLink());

            newJUrl.setLevel(jUrl.getLevel());


            newJUrl.setFlpost(jUrl.getFlpost());

            System.out.println("2. newJUrl: " + newJUrl);

            linkStr_JUrl.put(newJUrl.getLink(), newJUrl);
    
            startingUrls.add(newJUrl.getLink());
            
            //jUrl = new JournalUrl();
        }
    }
//--------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------
    public void crawl(){
        System.out.println("======================start crawl===========================");

        WebDriver webDriver = new HtmlUnitDriver();

        for (int i = 0; i < startingUrls.size(); i++) {
            if (startingUrls.get(i).contains("science.sciencemag.org")) {
                Crawler_Science wc = new Crawler_Science();
                wc.run(linkStr_JUrl.get(startingUrls.get(i)), cache_set, webDriver);
                wc.updateCache();
                continue;
            }
        }
    }
//--------------------------------------------------------------------------------------
    boolean debugIndex = true;
    public void index(){
        if(debugIndex){ System.out.println("========start createIndex=========");  }

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

        if(debugIndex) System.out.println("==============end createIndex================");

        deleteOutdateIndex();
    }

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

//--------------------------------------------------------------------------------------

    private long getLongestTimeRange(){

        return weeknum * 7 * 24 * 60 * 60 * 1000;
    }

//--------------------------------------------------------------------------------------



//--------------------------------------------------------------------------------------



//--------------------------------------------------------------------------------------



//--------------------------------------------------------------------------------------


}
