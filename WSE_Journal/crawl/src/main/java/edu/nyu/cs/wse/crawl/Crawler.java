package edu.nyu.cs.wse.crawl;

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


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import lombok.*;


public class Crawler {
    void run(){
        System.out.println("----------------- run crawler -----------------");
    }


//--------------------------------------------------------------------------------------
@SuppressWarnings("all")
    protected WebDriver webDriver;
    private boolean CrawlerDEBUG = false;


    protected static int LEVEL_LIMIT = 20;

    public static final String DISALLOW = "Disallow:";

    private String parentPath = new File(System.getProperty("user.dir")).getParent();
    protected String downloadsFolder = parentPath + "/" + "down";
    protected String cacheFile = parentPath + "/" + "cache.txt";

    protected HashSet<String> cache_set;

    protected TreeSet<String> newAddedFileNameSet;

    protected JournalUrl jUrl;

    protected int level = 0;

    protected int curNum = 1;

    protected int last = 0;

    protected Queue<URL> newUrlQueue = new LinkedList<URL>();

    protected Hashtable<URL, Integer> knownURLs;

    public HashMap<String, String> imagecache;



    protected TreeSet<URL> last_Level_Url_Set;
//    Set<URL> ts


    @Value("${config.num}")
    private int weeknum;
//--------------------------------------------------------------------------------------
    @SuppressWarnings("all")
    protected void initialize(JournalUrl jUrl, HashSet<String> cache_set, WebDriver webDriver) {
        System.out.println("---------------"+"start init cralwer"+"--------------");

        this.jUrl = jUrl;
        this.webDriver = webDriver;
        this.cache_set = cache_set;
        this.LEVEL_LIMIT = jUrl.getLevel();
        Comparator<URL> byName =
                (URL o1, URL o2)->storageFileName(o1).compareTo(storageFileName(o2));
        this.last_Level_Url_Set = new TreeSet<URL>(byName);


        this.newAddedFileNameSet = new TreeSet<String>();

        this.knownURLs = new Hashtable<URL, Integer>();



        this.imagecache = new HashMap<String, String>();

        try {
            URL url = new URL(jUrl.getLink());

            System.out.println("initialize: url:" + url);
            System.out.println("initialize: jUrl.getLink(): " + jUrl.getLink());


            knownURLs.put(url, new Integer(1));

            System.out.println("initialize: newUrlQueue.add(url); " + newUrlQueue.add(url));
            System.out.println("initialize: newUrlQueue: " + newUrlQueue);


            //System.out.println("Starting Crawling. Initial URL: " + url.toString());
        } catch (MalformedURLException e) {
            //System.out.println("MalformedURLException: " + jUrl.getLink());
            return;
        }

        Properties properties = new Properties(System.getProperties());
        properties.put("http.proxySet", "true");
        properties.put("http.proxyHost", "webcache-cup");
        properties.put("http.proxyPort", "8080");
        System.setProperties(new Properties(properties));



        System.out.println("-------------------------"+"end init cralwer"+"------------------------");
    }//initialize

//--------------------------------------------------------------------------------------
    @SuppressWarnings("all")
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



//--------------------------------------------------------------------------------------
    @SuppressWarnings("all")
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

        //if(CrawlerDEBUG) System.out.println("****end isNewUrlToAdd********");
        return false;
    }


//--------------------------------------------------------------------------------------

/*
    @Test
    public void testDown() throws Exception{
        URL url = new URL("http://science.sciencemag.org/content/358/6368/1307");
        String name = url.getFile().replace("/", "_");
        System.out.println("name: " + name);
        DownloadPagesHtmlUnit2(url);
    }
*/

    private String storageFileName(URL url){
        return url.getFile().trim().replace("/", "_");
    }

    @SuppressWarnings("all")
    public boolean DownloadPagesHtmlUnit(URL url) {
        String name = url.getFile().trim().replace("/", "_");
        System.out.println("URLNAME: " + name);

        if (cache_set.contains(name)){
            return false;
        }
        try {
            String path = downloadsFolder + "/" + name;
            File file = new File(path);

            webDriver.get(url.toString());
            String data = webDriver.getPageSource();
            FileUtils.writeStringToFile(file, data);

            newAddedFileNameSet.add(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected boolean containsFile(String fileName){
        File f = new File(downloadsFolder + "/" + fileName);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return true;
    }
//--------------------------------------------------------------------------------------
    @SuppressWarnings("all")
    public void updateCache() {
        File file = new File(cacheFile);
        List<String> lines;
        try{//remove outdated cache records
            lines = FileUtils.readLines(file, "UTF-8");
/*            for (int i = 0; i < lines.size(); i++){
                String[] parts = lines.get(i).split(" ");

                if (parts == null || parts.length != 2 ||
                        parts[0].length() != 13 || !StringUtils.isNumeric(parts[0])){
                    lines.remove(i--);
                    continue;
                }

                long currentTime = new Date().getTime();
                long modifiedTime = Long.parseLong(parts[0]);
                long longestPeriod = getLongestTimeRange();
                if (currentTime - modifiedTime > longestPeriod){
                    lines.remove(i--);
                }
            }*/

            Iterator itr = newAddedFileNameSet.iterator();
            while(itr.hasNext()){
                String item = (String) itr.next();
                lines.add(new Date().getTime() + " " + item);
            }

            FileUtils.writeLines(file, lines);

        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    protected void timeInterval(long interval){
        try{
            System.out.println("sleep for " + interval + " ms.");
            Thread.sleep(interval);
        }catch (InterruptedException ite){
            ite.printStackTrace();
        }

    }


//--------------------------------------------------------------------------------------

private long getLongestTimeRange(){

    return weeknum * 7 * 24 * 60 * 60 * 1000;
}
//--------------------------------------------------------------------------------------


}
