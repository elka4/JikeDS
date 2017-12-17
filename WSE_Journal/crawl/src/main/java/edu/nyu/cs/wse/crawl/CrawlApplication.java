package edu.nyu.cs.wse.crawl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings({ "unchecked", "rawtypes" })

public class CrawlApplication implements CommandLineRunner{

    @Autowired
    private Crawl_Index crawl_index;

    @Override
    public void run(String... args) {
        crawl_index.run();
    }


	public static void main(String[] args) throws Exception{

	    SpringApplication.run(CrawlApplication.class, args);

	}

}
