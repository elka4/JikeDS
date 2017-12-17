package edu.nyu.cs.wse.crawl;

public interface I_Crawl_Index {
    void prepareDownloadsFolder();

    //void deleteOutdateIndex();

    void prepareCacheFile();

    void prepareIndexFolder();

    void readUrls();

    void crawl();

    void index();
}
