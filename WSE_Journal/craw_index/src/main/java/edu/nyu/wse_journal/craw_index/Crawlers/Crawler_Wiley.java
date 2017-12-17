package edu.nyu.wse_journal.craw_index.Crawlers;

import edu.nyu.wse_journal.craw_index.JournalUrl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;


@SuppressWarnings("all")
public class Crawler_Wiley extends Crawler{
    private boolean SC_DEBUG = true;

    public void run(JournalUrl jUrl, HashSet<String> cache, WebDriver webDriver) {

        initialize(jUrl, cache, webDriver);

        if(SC_DEBUG)System.out.println("-------------------------start Crawler_Science------------------------");


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

        if(SC_DEBUG) System.out.println("-------------------------end Crawler_Nature------------------------");

        downloadPages();
    }


    /////////////////////////////////////////////////////////////////////
    public void downloadPages() {
        if(SC_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>start downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Iterator itr = this.lastLevelUrlSet.iterator();

        URL url = null;

        while (itr.hasNext()) {
            url = (URL) itr.next();
            DownloadPagesJsoup(url);
            //            timeInterval();
        }

        if(SC_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>end downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

/////////////////////////////////////////////////////////////////////

    private void getDownloadUrl(URL url) {
        if(SC_DEBUG) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<start getDownloadUrl<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        if (!robotSafe(url)) {
            return;
        }

        Document doc = null;
        Elements es = null;
        try {
            doc = Jsoup.connect(url.toString()).timeout(3000).get();

            //AddImageUrl(doc);

            es = doc.getAllElements();
            // System.out.println(es.html());
        } catch (IOException e) {
            if (SC_DEBUG)
                e.printStackTrace();
            return;
        }
        for (int i = 0; i < es.size(); i++) {
            try {
                URL newUrl = new URL(url, es.get(i).attr("href"));
                if (SC_DEBUG)
                    System.out.println(newUrl);
                if (isNewUrlToAdd(newUrl)) {
                    // System.out.println(newUrl);
                    newUrlQueue.add(newUrl);
                    last++;
                    // System.out.println(this.lv);
                    // System.out.println(this.LEVEL_LIMIT);
                    if (this.level == this.LEVEL_LIMIT - 1) {
                        // System.out.println(newUrl);
                        this.lastLevelUrlSet.add(newUrl);
                    }
                }
            } catch (Exception e1) {
                // e1.printStackTrace();
                continue;
            }
        }

        if(SC_DEBUG) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<end getDownloadUrl<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
