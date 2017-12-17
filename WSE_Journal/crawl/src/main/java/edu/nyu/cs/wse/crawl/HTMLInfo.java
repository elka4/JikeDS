package edu.nyu.cs.wse.crawl;

import lombok.Data;
import org.springframework.stereotype.Component;

//@Component
@Data
public class HTMLInfo {
    private  String title = "";
    private  String doi = "";
    private  String summary = "";
    private  String date = "";
    private  String url = "";
    private  String fullurl = "";
    private  String type = "";
    private  String journaltitle = "";
    private  String publisher = "";

    private  StringBuilder author = new StringBuilder("");
    private  StringBuilder keyword = new StringBuilder("");
    private  StringBuilder image = new StringBuilder("");

    private  String authors = "";
    private  String keywords = "";
    private  String images = "";

}
