package edu.nyu.wse_journal.craw_index.Crawlers;

import edu.nyu.wse_journal.craw_index.JournalUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class Crawler_Science extends Crawler {

    private boolean SC_DEBUG = true;

/////////////////////////////////////////////////////////////////////

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
            DownloadPagesHtmlUnit(url);
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

        webDriver.get(url.toString());

        String res = webDriver.getPageSource();
        String[] tmp = url.toString().split("/");
        String vol = tmp[tmp.length - 2];
        String iss = tmp[tmp.length - 1];
        Pattern p = Pattern.compile(vol + "\\\\/" + iss + "\\\\/"
                + "\\d+(\\.\\d*)?\"");
        Matcher m = p.matcher(res);
        while (m.find()) {
            String s = jUrl.getLink() + "/" + m.group().split("\\/")[2];
            s = s.substring(0, s.length() - 1);
            try {
                lastLevelUrlSet.add(new URL(s));
            } catch (MalformedURLException e) {
            }
        }
        if(SC_DEBUG) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<end getDownloadUrl<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

/////////////////////////////////////////////////////////////////////

}