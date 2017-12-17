package edu.nyu.wse_journal.craw_index;

import java.io.*;
import java.util.*;

import edu.nyu.wse_journal.craw_index.Crawlers.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.LongPoint;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("all")
@SpringBootApplication
public class CrawIndexApplication implements CommandLineRunner {
    HashMap<String, JournalUrl> link_JournalUrl_Hash = new HashMap<String, JournalUrl>();
//    Config config = new Config();
    ArrayList<String> startingUrls = new ArrayList<String>();
    HashSet<String> cache = new HashSet<String>();

    @Autowired
    private Config config;

    @Autowired
    private HTMLHandler htmlHandler;

    @Autowired
    private Crawler_Nature crawler_Nature;

    @Autowired
    private Crawler_NatureNM crawler_NatureX;

    boolean debugIndex = true;

    @Override
    public void run(String... args) {

//        readCache();
//
//
//
        readUrls();
        deleteOldFiles();
        crawl();

        index();

//        deleteOutdateIndex();

    }

	public static void main(String[] args) {
        SpringApplication.run(CrawIndexApplication.class, args);
	}

/////////////////////////////////////////////////////////////////////

    private long getLongestTimeRange(){

        return config.getWeeknum() * 7 * 24 * 60 * 60 * 1000;
    }
/////////////////////////////////////////////////////////////////////

    private void readCache() {
        BufferedReader br = null;
        try {
            File cacheFile = new File((config.getCachePath()));
            br = new BufferedReader(new FileReader(cacheFile));
            String cacheLine = null;
            while ((cacheLine = br.readLine()) != null) {
                cache.add(cacheLine.split(" ")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

/////////////////////////////////////////////////////////////////////

    private void deleteOldFiles() {
        File downloadsFolder = new File(config.getDownloadsFolder());
        //create download folder if not exits
        if (!downloadsFolder.exists() && !downloadsFolder.isDirectory()) {
            downloadsFolder.mkdir();
            return;
        }

        //delete outdated files in downloads folder
        File[] files = downloadsFolder.listFiles();
        for (File fileToDelete : files) {
            long modifiedTime = fileToDelete.lastModified();
            long currentTime = new Date().getTime();
            long longestPeriod = getLongestTimeRange();
            if (currentTime - modifiedTime > longestPeriod){
                fileToDelete.delete();
            }
        }
    }

/////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////////////////////

    /*
    read urlFile
     */
    private void readUrls() {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(config.getUrlFile());
            br = new BufferedReader(fr);
            String str = null;
            JournalUrl jUrl = new JournalUrl();
            String code = "";
            String content = "";

            while ((str = br.readLine()) != null) {
                if (str.contains(";")){
                    code = str.split(";")[0].trim();
                    content = str.split(";")[1].trim();
                }

                switch (code) {
                    case "":
                        break;
                    case "JOURNAL":
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
                        }
                        break;
                    case "Level":
                        jUrl.setLevel(Integer.parseInt(content));
                        if (jUrl.getName().equals("Science")) {
                            AddLastTwoWeekJournal(jUrl);
                            jUrl = new JournalUrl();
                        }
                        break;
                    case "First_Level_Pre_Filter":
                        jUrl.setFlpre(content);
                        if (jUrl.getName().equals("Nature")) {
                            NAddLastFewWeekJournal(jUrl);
                            jUrl = new JournalUrl();
                        }
                        break;
                    case "First_Level_Post_Filter":
                        jUrl.setFlpost(content);
                        break;
                    case "Second_Level_Pre_Filter":
                        jUrl.setSecpre(content);
                        break;
                    case "Second_Level_Post_Filter":
                        jUrl.setSecpost(content);
                        break;
                }//switch

            }//while
            if (!jUrl.getName().equals("")) {
                link_JournalUrl_Hash.put(jUrl.getLink(), jUrl);
            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }//finally
        }//ReadJournalUrls()


/////////////////////////////////////////////////////////////////////

    private void AddLastTwoWeekJournal(JournalUrl u) {

        // 2015 Jan 02 Vol 347, Iss 6217
        int stdVol = 347;
        int stdIss = 6217;

        for (int i = 1; i <= config.getWeeknum(); i++) {
            long vol = stdVol;
            long iss = stdIss;
            Calendar now2 = new GregorianCalendar();
            Date last = new Date(new Date().getTime() - (i - 1)
                    * (7 * 24 * 3600 * 1000));
            now2.setTime(last);
            vol += (now2.get(Calendar.YEAR) - 2015) * 4;
            if (now2.get(Calendar.MONTH) <= 2) {
            } else if (now2.get(Calendar.MONTH) <= 5) {
                vol++;
            } else if (now2.get(Calendar.MONTH) <= 8) {
                vol += 2;
            } else if (now2.get(Calendar.MONTH) <= 11) {
                vol += 3;
            }

            Calendar c = Calendar.getInstance();
            c.set(2015, 0, 2);
            Date standard = c.getTime();
            long tmp = (new Date().getTime() / 86400000 - standard.getTime() / 86400000) / 7;
            iss += tmp - i;
            JournalUrl mr = new JournalUrl();
            mr.setName(u.getName());


            mr.setLink(u.getLink() + vol + "/" + iss);

            mr.setLevel(u.getLevel());

            mr.setFlpost(u.getFlpost());

            link_JournalUrl_Hash.put(mr.getLink(), mr);
            startingUrls.add(mr.getLink());
        }
    }
    
/////////////////////////////////////////////////////////////////////
    
    private void NAddLastFewWeekJournal(JournalUrl u) {
        // the start date
        Calendar start = new GregorianCalendar();
        start.setTime(new Date());
        start.add(start.DATE, -config.getWeeknum() * 7);
        // up to date
        Calendar now = new GregorianCalendar();
        Date today = new Date();
        now.setTime(today);

        int count;
        count = 12 * (now.get(Calendar.YEAR) - start.get(Calendar.YEAR))
                + now.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        int curYear = now.get(Calendar.YEAR);
        int curMonth = now.get(Calendar.MONTH) + 1;

        for (int c = 0; c <= count; c++) {
            JournalUrl news = new JournalUrl();
            news.setLink(u.getLink() + "?year=" + curYear + "&month=" + curMonth);
            news.setLevel(u.getLevel());
            news.setFlpre(u.getFlpre());

            link_JournalUrl_Hash.put(news.getLink(), news);
            startingUrls.add(news.getLink());

            curMonth--;
            if (curMonth == 0) {
                curYear--;
                curMonth = 12;
            }
        }

    }
    
/////////////////////////////////////////////////////////////////////
    
    private void crawl() {
        WebDriver driver = new HtmlUnitDriver(false);
        String downloadsFolder = config.getDownloadsFolder();
        
        for (int i = 0; i < startingUrls.size(); i++) {

//           if (startingUrls.get(i).contains("science.sciencemag.org")) {
//               Crawler_Sci wc = new Crawler_Sci();
//                wc.run(downloadsFolder, link_JournalUrl_Hash.get(startingUrls.get(i)), false, config, driver, cache);
//                wc.updateCache();
//            }
//
            if (startingUrls.get(i).contains("/nature/")) {
//                Crawler_Nature wc = new Crawler_Nature();
//                @Autowired
//                private Crawler_Nature wc;
                crawler_NatureX.run(downloadsFolder, link_JournalUrl_Hash.get(startingUrls.get(i)),
                        false, config, driver, cache);

                crawler_NatureX.updateCache();
            }

/*            if (startingUrls.get(i).contains(".nature.")) {
                Crawler_NatureNM wc = new Crawler_NatureNM();
                wc.run(downloadsFolder, link_JournalUrl_Hash.get(startingUrls.get(i)), false, config, driver, cache);
                wc.updateCache();
            }*/
        }
        driver.close();
    }

/////////////////////////////////////////////////////////////////////

    private void index() {
        if(debugIndex){
            System.out.println("====================start createIndex=====================");
        }

        File indexFolder = new File(config.getIndexFolder());
        if (!indexFolder.exists()) {
            indexFolder.mkdir();
        }

        //analyzaer and IndexWriterConfig
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        Directory indexDirectory = null;
        IndexWriter indexWriter = null;

        try {
            indexDirectory = FSDirectory.open(indexFolder.toPath());
            indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // process html files with HTMLhandler
        File downloadsFolder = new File(config.getDownloadsFolder());
        Document doc = null;
        for (File file : downloadsFolder.listFiles()) {


//            HTMLHandler htmlHandler;
//            HTMLHandler htmlHandler = new HTMLHandler();

            try {
                doc = htmlHandler.processHTML(file);
                if (doc != null) {
                    indexWriter.addDocument(doc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //delete file when indexing is done.
//                file.delete();
            }
        }
        try {
            indexWriter.close();
        } catch (IOException e) {
        }

        if(debugIndex){
            System.out.println("====================end createIndex=====================");
        }

    }//index

/////////////////////////////////////////////////////////////////////

    private void deleteOutdateIndex() {
        try {
            File indexFolder = new File(config.getIndexFolder());
            Directory indexDirectory = FSDirectory.open(indexFolder.toPath());
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
//            IndexWriter indexWriter = null;
            IndexWriter indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);


/*            public static LegacyNumericRangeQuery<Long> newLongRange(
                    String field, Long min, Long max, boolean minInclusive, boolean maxInclusive) {
                return new LegacyNumericRangeQuery(
                        field, 16, FieldType.LegacyNumericType.LONG, min, max, minInclusive, maxInclusive);
            }*/

//            Query q = LegacyNumericRangeQuery.newLongRange("date", (long) 0,
//                    new Date().getTime() - config.getWeeknum()
//                            * (7 * 24 * 60 * 60 * 1000), true, true);


            Query expiredQuery = LongPoint.newRangeQuery("date",
                    (long) 0, new Date().getTime() - getLongestTimeRange());

            indexWriter.deleteDocuments(expiredQuery);
//            IndexReader indexReader = DirectoryReader.open(indexWriter);
//            indexReader.close();
            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/////////////////////////////////////////////////////////////////////

}
