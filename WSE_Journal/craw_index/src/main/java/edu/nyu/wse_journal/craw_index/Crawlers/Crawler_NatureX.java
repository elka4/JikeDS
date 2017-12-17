package edu.nyu.wse_journal.craw_index.Crawlers;

import edu.nyu.wse_journal.craw_index.JournalUrl;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("all")
@NoArgsConstructor
@Component
public class Crawler_NatureX extends Crawler {
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
            doc = Jsoup.connect(url.toString()).timeout(3000).get();

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
                URL newUrl = new URL(url, es.get(i).attr("href"));

                if (isNewUrlToAdd(newUrl)) {
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
            System.out.println("=====================Crawler_NatureX_DEBUG===================");
            System.out.println("-------------------------"+str+"------------------------");
        }
    }

}
