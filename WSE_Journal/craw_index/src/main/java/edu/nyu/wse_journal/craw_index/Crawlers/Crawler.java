package edu.nyu.wse_journal.craw_index.Crawlers;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//
import edu.nyu.wse_journal.craw_index.JournalUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

import org.springframework.beans.factory.annotation.Value;
import lombok.*;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class Crawler {
    protected WebDriver webDriver;

    protected static int LEVEL_LIMIT = 20;
    private boolean CrawlerDEBUG = false;
    public static final String DISALLOW = "Disallow:";

    private String parent = new File(System.getProperty("user.dir")).getParent();
    protected String parentPath = new File(parent).getParent();
    protected String downloadsFolder = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/down/";
    protected String cacheFile = "/Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/cache.txt";

    protected HashSet<String> cache;

    protected HashSet<URL> lastLevelUrlSet;

    protected HashSet<String> newAddedFileNameSet;

    protected JournalUrl jUrl;

    protected int level = 0;

    protected int curNum = 1;

    protected int last = 0;

    protected Queue<URL> newUrlQueue;

    protected Hashtable<URL, Integer> knownURLs;

    @Getter
    public HashMap<String, String> imagecache ;



//protected  Config config = new Config();

//    @Value("${config.downloadsFolder}, required=true")
//    private String downloadsFolder_path;
    //protected String downloadsFolder = parentPath + "/" + "down/";

//    @Value("${config.cachePath}, required=true")
//    private String cacheFile_path;
    //protected String cacheFile = parentPath + "/" + "cache";


/////////////////////////////////////////////////////////////////////

    protected void initialize(JournalUrl jUrl, HashSet<String> cache, WebDriver webDriver) {
        System.out.println("-------------------------"+"start init cralwer"+"------------------------");

        this.jUrl = jUrl;
        this.webDriver = webDriver;
        this.cache = cache;
        this.LEVEL_LIMIT = jUrl.getLevel();

        this.lastLevelUrlSet = new HashSet<URL>();
        this.newUrlQueue = new LinkedList<URL>();
        this.newAddedFileNameSet = new HashSet<String>();
        this.imagecache = new HashMap<String, String>();

        this.knownURLs = new Hashtable<URL, Integer>();

        try {
            URL url = new URL(jUrl.getLink());

            knownURLs.put(url, new Integer(1));

            newUrlQueue.add(url);

            System.out.println("Starting Crawling. Initial URL: " + url.toString());
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + jUrl.getLink());
            return;
        }

        Properties properties = new Properties(System.getProperties());
        properties.put("http.proxySet", "true");
        properties.put("http.proxyHost", "webcache-cup");
        properties.put("http.proxyPort", "8080");
        System.setProperties(new Properties(properties));

        System.out.println("-------------------------"+"end init cralwer"+"------------------------");

    }//initialize


/////////////////////////////////////////////////////////////////////



    public boolean robotSafe(URL url) {
        String strHost = url.getHost();

        // form URL of the robots.txt file
        String strRobot = "http://" + strHost + "/robots.txt";
        // System.out.println(strRobot);
        URL urlRobot;
        try {
            urlRobot = new URL(strRobot);
        } catch (MalformedURLException e) {
            // something weird is happening, so don't trust it
            return false;
        }

        String strCommands;
        try {
            InputStream urlRobotStream = urlRobot.openStream();

            // read in entire file
            byte b[] = new byte[1000];
            int numRead = urlRobotStream.read(b);
            strCommands = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlRobotStream.read(b);
                if (numRead != -1) {
                    String newCommands = new String(b, 0, numRead);
                    strCommands += newCommands;
                }
            }
            urlRobotStream.close();
        } catch (IOException e) {
            // if there is no robots.txt file, it is OK to search
            return true;
        }

        // assume that this robots.txt refers to us and
        // search for "Disallow:" commands.
        String strURL = url.getFile();
        int index = 0;
        String specific = null;
        for (String valid : strCommands.split("User-agent:")) {
            if (valid.trim().startsWith("*")) {
                specific = valid;
                break;
            }
        }
        while ((index = specific.indexOf(DISALLOW, index)) != -1) {
            index += DISALLOW.length();
            String strPath = specific.substring(index);
            StringTokenizer st = new StringTokenizer(strPath);

            if (!st.hasMoreTokens())
                break;

            String strBadPath = st.nextToken();

            // if the URL starts with a disallowed path, it is not safe
            if (strURL.indexOf(strBadPath) == 0)
                return false;
        }

        return true;
    }//robotSafe



/////////////////////////////////////////////////////////////////////

    public boolean isNewUrlToAdd(URL url) {
        //if(CrawlerDEBUG) System.out.println("********start isNewUrlToAdd*********");
        //if(CrawlerDEBUG) System.out.println("level: "+ level);

        if (knownURLs.containsKey(url)) {
            return false;
        }


        String urlStr = url.toString().toLowerCase().trim();

        switch (this.level) {
            case 0:
                if (jUrl.getFlpre().startsWith("http://")) {
                    return urlStr.startsWith(jUrl.getFlpre().toLowerCase())
                            && urlStr.endsWith(jUrl.getFlpost().toLowerCase());
                } else {
                    return urlStr.startsWith(jUrl.getLink() + jUrl.getFlpre())
                            && urlStr.endsWith(jUrl.getFlpost())
                            && !jUrl.getLink().equals(url.toString().split("#")[0]);
                }
            case 1:
                if (jUrl.getSecpre().startsWith("http://")) {
                    return urlStr.startsWith(jUrl.getSecpre().toLowerCase());
                } else {
                    return urlStr.startsWith(jUrl.getLink() + jUrl.getSecpre())
                            && urlStr.endsWith(jUrl.getSecpost());
                }


        }

        //if(CrawlerDEBUG) System.out.println("********************end isNewUrlToAdd*************************");

        return false;
    }


/////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////
    public boolean DownloadPagesJsoup(URL url) {
        // TODO Auto-generated method stub
        String name = url.getFile().replace("/", "_");
        try {
            if (cache.contains(name)) {
                return false;
            }
            Document doc = Jsoup.connect(url.toString()).data("query", "Java")
                    .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                    .post();
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(downloadsFolder),"UTF-8"));
            out.write(new String(doc.html().getBytes(), Charset.forName("UTF-8")));
            out.close();
            newAddedFileNameSet.add(name);
            // System.out.println(doc.html());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (CrawlerDEBUG){
                e.printStackTrace();
            }

        }
        return true;
    }

/////////////////////////////////////////////////////////////////////
    public boolean DownloadPagesHtmlUnit(URL url) {
        String name = url.getFile().replace("/", "_");
        try {
            if (cache.contains(name)) {
                return false;
            }
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(downloadsFolder),"UTF-8"));
            out.write(getpageHUD(url));
            out.close();
            newAddedFileNameSet.add(name);
        } catch (IOException e) {
        }
        return true;
    }

    // get page using HtmlUnit Driver
    public String getpageHUD(URL url) {

        webDriver.get(url.toString());

        String res = webDriver.getPageSource();

        return res;
    }

/////////////////////////////////////////////////////////////////////
    public void updateCache() {
        try {
            @Cleanup FileWriter writer = new FileWriter(cacheFile, true);
            Iterator itr = newAddedFileNameSet.iterator();
            while(itr.hasNext()){
                String item = (String) itr.next();
                writer.write(new Date().getTime() + " " + item + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////////

    protected void timeInterval(){
        try{
            System.out.println("sleep for 0.5 s.");
            Thread.sleep(500);

        }catch (InterruptedException ite){

        }

    }


/////////////////////////////////////////////////////////////////////

    private void debug(String str){
        if(CrawlerDEBUG){
            System.out.println("=====================debug===================");
            System.out.println("-------------------------"+str+"------------------------");
        }
    }
/////////////////////////////////////////////////////////////////////
}



/*

    public void downloadPage(URL url) {
        if(CrawlerDEBUG) System.out.println("!!!!!!!!!!!!!!!!!!!!!start DownloadPages!!!!!!!!!!!!!!!!!!!!!!!!!" + "\n");

        //filename
        String name = url.getFile().replace("/", "_");

        if (cache.contains(name)) {
            return;
        }

        String[] strArr  = this.getClass().getName().split("\\.");
        String className = strArr[strArr.length - 1];
        String pageSource = "";


        try {
            System.out.println("className: " + className);

            @Cleanup BufferedWriter out  = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(downloadsFolder+"/"+name),"UTF-8"));

            switch (className){

                case "Crawler_Science":
                    webDriver.get(url.toString());
                    pageSource = webDriver.getPageSource();
                    break;

                // process with Jsoup
                case "Crawler_Nature":
                    Document doc = Jsoup.connect(url.toString()).data("query", "Java")
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36").cookie("auth", "token").timeout(1000*60*30)
                            .post();

                    pageSource = new String(doc.html().getBytes(), Charset.forName("UTF-8"));
                    break;


                //p process with WebDriver
                // OK for natureNM
                case "Crawler_NatureNM":
*/
/*                    Document doc2 = Jsoup.connect(url.toString()).data("query", "Java")
                            .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                            .post();

                    pageSource = new String(doc2.html().getBytes(), Charset.forName("UTF-8"));*//*


                    Document docNM = Jsoup.connect(url.toString()).data("query", "Java")
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                            .cookie("auth", "token")
                            .referrer("http://www.google.com").timeout(3000).post();
                    //.ignoreHttpErrors(true)

                    pageSource = new String(docNM.html().getBytes(), Charset.forName("UTF-8"));
                    break;



                case "Crawler_NatureNeuro":

                    Document docNeuro = Jsoup.connect(url.toString())
                            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                            .referrer("http://www.google.com").cookie("auth", "token").timeout(3000).ignoreHttpErrors(true).get();

                    pageSource = new String(docNeuro.html().getBytes(), Charset.forName("UTF-8"));

                    break;

                case "Crawler_NatureX":

                    webDriver.get(url.toString());
                    pageSource = webDriver.getPageSource();
                    break;

                case "Crawler_NatureHTML":

                    webDriver.get(url.toString());
                    pageSource = webDriver.getPageSource();
                    break;

                case "Crawler_NatureJsoup":
                    Document docJ = Jsoup.connect(url.toString()).data("query", "Java")
                            .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                            .post();

                    pageSource = new String(docJ.html().getBytes(), Charset.forName("UTF-8"));
                    break;

                default:


            }

            if(out != null){
                out.write(pageSource);
                newAddedFileNameSet.add(name);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(CrawlerDEBUG) System.out.println("!!!!!!!!!!!!!!!!!!!!!end DownloadPages!!!!!!!!!!!!!!!!!!!!!!!!!" + "\n");

    }

*/

/////////////////////////////////////////////////////////////////////
/*

    public void downloadPages() {
        if(CrawlerDEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>start downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Iterator itr = this.lastLevelUrlSet.iterator();

        URL url = null;

        while (itr.hasNext()) {
            url = (URL) itr.next();
            downloadPage(url);
//            timeInterval();
        }

        if(CrawlerDEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>end downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

*/
