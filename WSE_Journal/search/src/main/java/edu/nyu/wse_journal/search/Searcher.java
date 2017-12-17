package edu.nyu.wse_journal.search;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("all")
@Component
public class Searcher {
    private boolean DEBUG = true;

    private Highlighter highlighter = null;

    @Value("${config.weeknum}")
    int weeknum;

    public Result result = new Result();

    String parentPath = new File(System.getProperty("user.dir")).getParent();
    String downloadsFolder = parentPath + "/" + "down";
    String cacheFile = parentPath + "/" + "cache.txt";
    String indexFolder = parentPath + "/" + "index";
    String urlFile = parentPath + "/" + "urlFile.txt";

    public void search(String[] args) {
        try {

            long miliSecInADay = 24 * 60 * 60 * 1000;


            IndexSearcher searcher = getIndexSearcher(indexFolder);

            Query query = getQuery(args[0]);

            TopDocs topDocs = getTopDocs(searcher, query);

            getHighlighter(query);

            printResultsNum(topDocs);

            printWeb(searcher, topDocs, args[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//search

//-----------------------------------------------------------------------------------------------


    private void debug(String str){
        if(DEBUG){
            System.out.println("============================"+str+"============================");
        }
    }
//-----------------------------------------------------------------------------------------------


    private IndexSearcher getIndexSearcher(String folder){
        debug("getIndexSearcher");

        File indexFolder = new File(folder);
        IndexSearcher searcher = null;
        try{
            Directory indexDirectory = FSDirectory.open(indexFolder.toPath());
            DirectoryReader reader = DirectoryReader.open(indexDirectory);
            searcher = new IndexSearcher(reader);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return searcher;
    }//getIndexSearcher

//-----------------------------------------------------------------------------------------------


    private Query getQuery(String arg0){
        debug("getQuery");

        String[] fields = { "title", "authors", "keywords", "abstract" };

        Occur[] oc = new Occur[4];
        Arrays.fill(oc, Occur.SHOULD);

        String[] stringQuery = new String[4];
        Arrays.fill(stringQuery, arg0);

        Query query = null;
        try{
            query = MultiFieldQueryParser.parse(stringQuery, fields, oc, new StandardAnalyzer());
        }catch (ParseException ioe) {

        }
        return query;
    }
//-----------------------------------------------------------------------------------------------


    private TopDocs getTopDocs(IndexSearcher searcher, Query query){
        debug("getTopDocs");
        TopDocs topDocs = null;
        try{
            topDocs = searcher.search(query, 50);
        }catch (IOException ioe){

        }
        return  topDocs;
    }//getTopDocs

//-----------------------------------------------------------------------------------------------

    private void getHighlighter(Query query){
        debug("getHighlighter");
        if(query != null){
            SimpleHTMLFormatter simpleHTMLFormatter =
                    new SimpleHTMLFormatter("<span style=\"background:yellow\">", "</span>");
            highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query, "title"));
            highlighter.setTextFragmenter(new SimpleFragmenter(1024));
        }
    }
//-----------------------------------------------------------------------------------------------


    //"MM-dd-yyyy",
    private String getFormatedTime(String format, String input){
        debug("getFormatedTime");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(Long.valueOf(input));
        String dateTime = simpleDateFormat.format(date);
        return dateTime;

    }//getFormatedTime

//-----------------------------------------------------------------------------------------------

    private void printResultsNum(TopDocs topDocs) {
        debug("printResultsNum");

        if (topDocs.scoreDocs.length == 0) {
            System.out.println("<div class=\"get2\">No results found.</div>");
        } else if (topDocs.scoreDocs.length < 50) {
            System.out.println("<div class=\"get2\">Found " + topDocs.scoreDocs.length + " results.</div>");
        } else {
            System.out.println("<div class=\"get2\">Showing the top " + topDocs.scoreDocs.length + " results.</div>");
        }
    }

//-----------------------------------------------------------------------------------------------

    private String getHighlighted(String fieldName, String readerStr, String arg0){
        debug("getHighlighted");
        
        TokenStream tokenStream = new StandardAnalyzer().tokenStream(fieldName, new StringReader(readerStr));
        String result = "";
        try{
            if (!arg0.contains("*") && !arg0.contains("~")) {
                result = highlighter.getBestFragment(tokenStream, readerStr);
            } else {
                result = readerStr;
            }
        }catch (InvalidTokenOffsetsException e){
            System.out.println("InvalidTokenOffsetsException: "+ "getHighlighted" + readerStr);
        }catch (IOException ioe){
            System.out.println("IOException: "+ "getHighlighted" + readerStr);
        }

        if (result == null || result.length() == 0){
            result = readerStr;
        }
        return result;
    }//getHighlighted

//-----------------------------------------------------------------------------------------------

    private void printWeb(IndexSearcher searcher, TopDocs topDocs, String arg0){
        debug("printWeb");
        //if(DEBUG)System.out.println("topDocs.scoreDocs.length" + topDocs.scoreDocs.length);
        
        Document doc = null;
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {

            try{
                doc = searcher.doc(topDocs.scoreDocs[i].doc);
            }catch (IOException e){
                System.out.println("IOException in printWeb");
            }
            String title = doc.get("title");
            String abs = doc.get("abstract");
            String authors = doc.get("authors");
            String keywords = doc.get("keywords");

            //String fieldName, String readerStr, String arg0
            String titleHighlighted = getHighlighted("title", title, arg0);
            String abstractHighlighted = getHighlighted("abs", abs, arg0);
            String authorsHighlighted = getHighlighted("authors", authors, arg0);
            String keywordsHighlighted = getHighlighted("keywords", keywords, arg0);
            
            String journaltitle = doc.get("journaltitle");
            String type = doc.get("type");
            String fullurl = doc.get("fullurl");
            String dateTime = getFormatedTime("MM-dd-yyyy", doc.get("date"));
            String doi = doc.get("doi");
            String images = doc.get("image");
            
            printSingleResult(titleHighlighted, abstractHighlighted,
                    authorsHighlighted, keywordsHighlighted,
                    journaltitle, type, fullurl, dateTime, doi, images,
                    i,
                    topDocs);

//            System.out.println("title: " + titleHighlighted);
//            System.out.println("abstract: " + abstractHighlighted);
//            System.out.println("authors: " + authorsHighlighted);
//            System.out.println("keywords: " + keywordsHighlighted);


        }//for
    }//printWeb

//-----------------------------------------------------------------------------------------------

    private void printHead(String titleHighlighted, String type, String fullurl){
        System.out.print(" class=\"blog-main\"><span class=\"am-badge am-badge-danger am-radius\">"
                + titleHighlighted.replace("‐", "-")
                + "</span>&nbsp;"
                + "<span class=\"am-badge am-badge-success am-radius\">"
                + type
                + "</span>"
                + "<div class=\"blog-title\"><a href=\""
                + fullurl
                + "\">"
                + titleHighlighted
                + "</a></h3>");
    }

    private void printAuthors(String authorsHighlighted){
        System.out.println("<div class=\"am-article-meta blog-meta blog-authors\">"
                + authorsHighlighted.substring(0, authorsHighlighted.length()-1) + "</div>");
    }

    private void printDateTime(String dateTime){
        System.out.println("<div class=\"am-article-meta blog-meta blog-date\">"
                + "Online Date: " + dateTime);
    }

    private void printDoi(String doi){
        System.out.println("&nbsp;DOI:" + doi + "</div>");
    }

    private void printImages(String images, int i){
        if (images != null && images.trim().length() > 0 && !images.trim().toLowerCase().equals("null")){
            System.out.println("<div class=\"am-g blog-content\"><div class=\"am-u-lg-5\"><div id='ninja-slider"
                    + i
                    + "'><div class=\"slider"
                    + i
                    + "-inner\"><ul>");
        }

        if (images != null && images.trim().length() > 0 && !images.trim().toLowerCase().equals("null")) {
            for (String im : images.split(",")) {
                System.out.println("<li><a class=\"ns-img\" href=\""
                        + im + "\"></a></li>");
            }
        }

        if (images != null && images.trim().length() > 0
                && !images.trim().toLowerCase().equals("null"))
            System.out.println("</ul>" + "</div></div></div> </div>");
    }

    private void printKeywords(String keywordsHighlighted){
        String[] tmp = keywordsHighlighted.split(",");

        for (int i1 = 0; i1 < tmp.length - 1; i1++) {
            if (tmp[i1].contains("</span>")) {
                System.out.print("<span class=\"am-badge am-badge-warning am-round\">"
                        + tmp[i1]
                        .replace(
                                "<span style=\"background:yellow\">",
                                "").replace("</span>",
                                "") + "</span>");
            } else {
                System.out
                        .print("<span class=\"am-badge am-round blog-keywords\">"
                                + tmp[i1] + "</span>");
            }
            if (i1 != tmp.length - 1)
                System.out.print("&nbsp;");
        }
    }
    private void printAbstract(String abstractHighlighted){
        if (abstractHighlighted != null && abstractHighlighted.length() != 0){
            System.out.println(
                    "<section data-am-widget=\"accordion\" class=\"am-accordion am-accordion-basic\" data-am-accordion='{  }'>"
                            + "<dl class=\"am-accordion-item blog-abs\">"
                            + "<dt class=\"am-accordion-title blog-abs\">"
                            + "Abstract"
                            + "</dt>"
                            + "<dd class=\"am-accordion-bd am-collapse blog-abs\">"
                            + "<div class=\"am-accordion-content blog-abs\">"
                            + abstractHighlighted
                            + "</div>"
                            + "</dd>"
                            + "</dl>"
                            + "</section></div>");
        }
    }
    private void printHead(){

    }
//-----------------------------------------------------------------------------------------------



    private void printSingleResult(String titleHighlighted, String abstractHighlighted,
                                          String authorsHighlighted, String keywordsHighlighted,
                                          String journaltitle, String type, String fullurl, String dateTime,
                                          String doi, String images,
                                          int i, TopDocs topDocs){
        debug("printSingleResult");

        //start <article>
        System.out.println("<article");

        printHead(titleHighlighted, type, fullurl);

        printAuthors(authorsHighlighted);

        printDateTime(dateTime);

        printDoi(doi);

        printImages(images, i);

        printKeywords(keywordsHighlighted);

        printAbstract(abstractHighlighted);

        System.out.println("  </article>");

        if (i != topDocs.scoreDocs.length - 1) {
            System.out.println("<hr class=\"am-article-divider blog-hr\">");
        }
    }//printSingleResult

//////////////////////////////////////////////////////////////////////////////////////////////////////



}
