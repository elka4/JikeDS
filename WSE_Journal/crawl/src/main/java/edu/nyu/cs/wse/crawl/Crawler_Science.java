package edu.nyu.cs.wse.crawl;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

@SuppressWarnings("all")
public class Crawler_Science extends Crawler {

    private boolean SC_DEBUG = true;

    @Value("${config.science_interval}")
    private long interval;
/////////////////////////////////////////////////////////////////////

    public void run(JournalUrl jUrl, HashSet<String> cache_set, WebDriver webDriver) {

        initialize(jUrl, cache_set, webDriver);

        System.out.println("run jUrl.getLink():" + jUrl.getLink());

        System.out.println("run this.newUrlQueue:" + this.newUrlQueue);

        if(SC_DEBUG)System.out.println("----------start Crawler_Science------------");


		while (this.level < this.LEVEL_LIMIT && !this.newUrlQueue.isEmpty()) {
            System.out.println("this.level < this.LEVEL_LIMIT && !this.newUrlQueue.isEmpty()");
			URL url = newUrlQueue.poll();

			this.curNum--;

            getDownloadUrl(url);
            if (curNum == 0) {
                this.level++;
                curNum = last;
                last = 0;
            }
		}

        System.out.println("this.newUrlQueue: " + this.newUrlQueue);

        if(SC_DEBUG) System.out.println("--------------end Crawler_Science-----------");

		downloadPages();
	}



/////////////////////////////////////////////////////////////////////

	private void getDownloadUrl(URL url) {
        if(SC_DEBUG) System.out.println("<<<<<<<<<<<<<start getDownloadUrl<<<<<<<<<<<<<");

        if (!robotSafe(url)) {
            return;
        }

        webDriver.get(url.toString());

        String res = webDriver.getPageSource();
        String[] tmp = url.toString().split("/");
        String vol = tmp[tmp.length - 2];
        String iss = tmp[tmp.length - 1];
        Pattern p = Pattern.compile(vol + "\\\\/" + iss + "\\\\/" + "\\d+(\\.\\d*)?\"");
        Matcher m = p.matcher(res);
        while (m.find()) {
            String s = jUrl.getLink() + "/" + m.group().split("\\/")[2];
            s = s.substring(0, s.length() - 1);
            try {

                last_Level_Url_Set.add(new URL(s));
            } catch (MalformedURLException e) {
            }
        }
        System.out.println("getDownloadUrl last_Level_Url_Set: " + last_Level_Url_Set);
        if(SC_DEBUG) System.out.println("<<<<<<<<<end getDownloadUrl<<<<<<<<<<<<");
	}

/////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////
    public void downloadPages() {
        if(SC_DEBUG) System.out.println(">>>>>>>>>>start downloadPages>>>>>>>>>>");

        Iterator itr = this.last_Level_Url_Set.iterator();
        URL url = null;
        while (itr.hasNext()) {
            url = (URL) itr.next();
            String fileName = url.getFile().trim().replace("/", "_");
            if (cache_set.contains(fileName)){
                System.out.println("file: " + fileName + "exists.");
                continue;
            }
            boolean isSuccess = DownloadPagesHtmlUnit(url);
            if (isSuccess){
                timeInterval(interval);
            } else if (containsFile(fileName)){
                System.out.println("No Downloard: containsFile");
            } else {
                System.out.println("No Downloard: fast");
            }
        }

        if(SC_DEBUG) System.out.println(">>>>>>>>>>>>>>end downloadPages>>>>>>>");
    }




}