package edu.nyu.wse_journal.craw_index.Crawlers;


import edu.nyu.wse_journal.craw_index.JournalUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import lombok.*;

@SuppressWarnings("all")
@NoArgsConstructor
@Component
public class Crawler_NatureNM extends Crawler {
//    static HashMap<String, String> imagecache = new HashMap<String, String>();

    private boolean CNX_DEBUG = true;


/////////////////////////////////////////////////////////////////////


    public void run(
                    JournalUrl jUrl,
                    HashSet<String> cache,
                    WebDriver webDriver) {

        initialize(jUrl, cache, webDriver);

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

    }


/////////////////////////////////////////////////////////////////////

    private void getDownloadUrl(URL url) {

        if (!robotSafe(url)) {
            return;
        }

        Document doc = null;
        Elements es = null;

        try {
            System.out.println("================"+url+"================");
/*            doc = Jsoup.connect(url.toString()).data("query", "Java")
                    .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                    .post();*/

            // OK for natureNM
            /*doc = Jsoup.connect(url.toString()).data("query", "Java")
                    .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
                    .post();*/

            doc = Jsoup.connect(url.toString()).data("query", "Java")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                    .cookie("auth", "token")
                    .referrer("http://www.google.com").timeout(3000).post();
            //.ignoreHttpErrors(true)

            //System.out.println("doc.html(): " + doc.html());

            if (this.level == this.LEVEL_LIMIT - 1) {
                AddImageUrl(doc);
            }
            es = doc.getAllElements();
            // System.out.println(es.html());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        for (int i = 0; i < es.size(); i++) {
            try {
                URL newUrl = null;
                String href = es.get(i).attr("href");

                if ((href != null) && !href.startsWith("javascript")) {
//                    System.out.println("!href.startsWith(javascript)");
                    newUrl = new URL(url, href);
                } else{
                    //System.out.println("href.startsWith(javascript)");
                    continue;
                }



/*                String href = listOfElements.get(1).getAttribute("href");
                if ((href != null) && !href.startsWith("javascript")) {
                    int responseCode=getResponseCode(href);
                    log.info("Response code of element at index 1 is:: " + responseCode);
                }*/

                if (newUrl != null && isNewUrlToAdd(newUrl)) {
                    newUrlQueue.add(newUrl);
                    last++;

                    // System.out.println(this.lv);
                    // System.out.println(this.LEVEL_LIMIT);

                    if (this.level == this.LEVEL_LIMIT - 1) {
                        this.lastLevelUrlSet.add(newUrl);
                    }
                }
            } catch (Exception e1) {
                 e1.printStackTrace();
                continue;
            }
        }
    }


/////////////////////////////////////////////////////////////////////

    public void downloadPages() {
        if(CNX_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>start downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Iterator itr = this.lastLevelUrlSet.iterator();

        URL url = null;

        while (itr.hasNext()) {
            url = (URL) itr.next();
            DownloadPagesHtmlUnit(url);
//            timeInterval();
        }

        if(CNX_DEBUG) System.out.println(">>>>>>>>>>>>>>>>>>>>>>>end downloadPages>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

/////////////////////////////////////////////////////////////////////

    private void AddImageUrl(Document doc) {
        Elements es = doc.getElementsByTag("img");

        for (Element e : es) {
            String imageUrlStr = e.attr("src");
            String[] tmp = imageUrlStr.split("/|-");
            if (tmp == null || tmp.length < 2){
                continue;
            }

            String name = tmp[tmp.length - 2].trim();

            imagecache.put(name, "http://www.nature.com" + imageUrlStr);
        }
    }



/////////////////////////////////////////////////////////////////////

    private void debug(String str){
        if(CNX_DEBUG){
            System.out.println("=====================Crawler_NatureNM_DEBUG===================");
            System.out.println("-------------------------"+str+"------------------------");
        }
    }

}
