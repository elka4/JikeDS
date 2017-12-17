package com.example.demo;

import com.example.demo.Crawlers.CrawIndex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("all")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


//        System.out.println("downloadsFolder \t"+downloadsFolder);
//        System.out.println("cacheFile \t"+cacheFile);
//        System.out.println("indexFolder \t"+indexFolder);
//        System.out.println("urlFile \t"+urlFile);

        System.out.println("----------------------------------------------");

        CrawIndex ci = new CrawIndex();

        System.out.println("ci.downloadsFolder "+ci.downloadsFolder);

        //ci.readUrls();




        System.out.println("----------------------------------------------");
    }
}
