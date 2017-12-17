package edu.nyu.wse_journal.craw_index.Crawlers;


import edu.nyu.wse_journal.craw_index.JournalUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;

import lombok.*;
import org.springframework.stereotype.Component;


@SuppressWarnings("all")
@Component
@NoArgsConstructor
public class Crawler_Nature extends Crawler {

    private boolean CN_DEBUG = true;

    public void run(JournalUrl jUrl, HashSet<String> cache, WebDriver webDriver) {

        initialize(jUrl, cache, webDriver);

        if(CN_DEBUG)System.out.println("-------------------------start Crawler_Nature------------------------");


        while (this.level < this.LEVEL_LIMIT && !newUrlQueue.isEmpty()) {

            URL url = newUrlQueue.poll();
            
            this.curNum--;

            getDownloadUrl(url);

            if (curNum == 0) {
                this.level++;
                curNum = last;
                last = 0;
            }
        }

        downloadPages();

        if(CN_DEBUG) System.out.println("-------------------------end Crawler_Nature------------------------");
    }

/////////////////////////////////////////////////////////////////////


    private void getDownloadUrl(URL url) {
        if(CN_DEBUG) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<start getDownloadUrl<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        if (!robotSafe(url)) {
            return;
        }

        Document doc = null;
        Elements es = null;
        try {
//            doc = Jsoup.connect(url.toString()).data("query", "Java").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36").cookie("auth", "token").timeout(3000).get();

            doc = Jsoup.connect(url.toString()).data("query", "Java")
                    .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                    .post();
            es = doc.getAllElements();

        } catch (IOException e) {
            if (CN_DEBUG){
                e.printStackTrace();
            }
            return;
        }


        for (int i = 0; i < es.size(); i++) {
            try {
                URL newUrl = new URL(url, es.get(i).attr("href"));

                //if(CN_DEBUG) System.out.println(newUrl.toString());

                // specail case for nature
                if (newUrl.toString().contains("abs") && isNewUrlToAdd(newUrl)) {

                    // System.out.println(newUrl);

                    newUrlQueue.add(newUrl);
                    last++;


                    if (this.level == this.LEVEL_LIMIT - 1) {
                        // System.out.println(newUrl);
                        this.lastLevelUrlSet.add(newUrl);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                continue;
            }
        }
        // sleep to avoid blocking
        //timeInterval();
        if(CN_DEBUG) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<end getDownloadUrl<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }//getDownloadUrl

////////////////////////////////////////////////////////////////////////////////////////////////





////////////////////////////////////////////////////////////////////////////////////////////////

    public void downloadPages() {
        if(CN_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>start downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Iterator itr = this.lastLevelUrlSet.iterator();

        URL url = null;

        while (itr.hasNext()) {
            url = (URL) itr.next();
            DownloadPagesJsoup(url);
//            timeInterval();
        }

        if(CN_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>end downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }



////////////////////////////////////////////////////////////////////////////////////////////////




    private void debug(String str){
        if(CN_DEBUG){
            System.out.println("=====================Crawler_Nature_DEBUG===================");
            System.out.println("-------------------------"+str+"------------------------");
        }
    }
}//downloadPages
