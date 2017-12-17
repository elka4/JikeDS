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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@SuppressWarnings("all")
@SpringBootApplication
//@EnableScheduling
public class CrawIndexApplication {

//    @Autowired
//    private CrawIndex crawIndex;

    //@Override
//    @Scheduled(fixedRate = 1000 * 60 * 5)
//    public void run() {
//        CrawIndex crawIndex = new CrawIndex();
//        crawIndex.prepare();
//        crawIndex.readUrls();
//        crawIndex.crawl();
//        crawIndex.index();
//        System.out.println("hahahahahaah");
//    }


	public static void main(String[] args) {
        SpringApplication.run(CrawIndexApplication.class, args);

        CrawIndex crawIndex = new CrawIndex();

        crawIndex.prepare();

        crawIndex.readUrls();

        crawIndex.crawl();

//        crawIndex.index();

        System.out.println("hahahahahaah");
        System.out.println("hahahahahaah");


//        String parentPath = new File(System.getProperty("user.dir")).getParent();
//        System.out.println("parentPath: " + parentPath);
//        System.out.println("currentPath: " + new File(System.getProperty("user.dir")));

//        System.out.println("hahahahahaah");
//        System.out.println("hahahahahaah");
//        System.out.println("hahahahahaah");
	}

/////////////////////////////////////////////////////////////////////



}
