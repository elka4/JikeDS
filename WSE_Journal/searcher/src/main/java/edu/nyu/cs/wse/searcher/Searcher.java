package edu.nyu.cs.wse.searcher;


import lombok.ToString;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@SuppressWarnings("all")
@Component
@Scope("prototype")
@ToString
public class Searcher {

    private boolean DEBUG = true;

    @Value("${config.weeknum}")
    private int weeknum = 1;

//    public Result result = new Result();

    private String parentPath = new File(System.getProperty("user.dir")).getParent();
    private String downloadsFolder = parentPath + "/" + "down";
    private String cacheFile = parentPath + "/" + "cache.txt";
    private String indexFolder = parentPath + "/" + "index";
    private String urlFile = parentPath + "/" + "urlFile.txt";
    private long miliSecInADay = 24 * 60 * 60 * 1000;


    public List<Result> search(String argstr){
        System.out.println("Start List<Result> search(String argstr){");
        System.out.println("argstr: " + argstr);
        String[] args = argstr.trim().split("\\s+");
        return search(args);
    }


    public List<Result> search(String[] args) {
        System.out.println("Start search(String[] args)");
        System.out.println("args.length: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("args [" + i + "]: " +args[i]);
        }


        List<Result> results = new LinkedList<>();
        if(args == null || args.length == 0){
            return results;
        }
/*        for (int i = 0; i < args.length; i++) {
            System.out.println("args " + i + ": " +args[i]);
        }*/

/*
        List<Result> results = new LinkedList<>();

        Result result0 = new Result();
        result0.setTitle("argsargsargsargs: " + args);
        result0.setAbs("Abs0000000000000000000");
        results.add(result0);


        Result result1 = new Result();
        result1.setTitle("title_1");
        result1.setAbs("Abs11111111111111111111111111111");
        results.add(result1);

        Result result2 = new Result();
        result2.setTitle("title_2");
        result2.setAbs("Abs22222222222222222222222222222");
        results.add(result2);
*/

        try {
            //  1
            IndexSearcher searcher = getIndexSearcher(indexFolder);
            //  2
            Query query = getQuery(args[0]);
            //  3
            TopDocs topDocs = getTopDocs(searcher, query);
            System.out.println("topDocs: " + topDocs);

            results = getResult(searcher,topDocs);
            //  4
            //getHighlighter(query);
            //  5
            //printResultsNum(topDocs);
            //  6
            //printWeb(searcher, topDocs, args[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("results: " + results);
        return results;
    }//search

//-----------------------------------------------------------------------------------------------

    //  1
    private IndexSearcher getIndexSearcher(String folder){
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
    //  2
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
    //3
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

/*
public class Result {

    String title = "";
    String abs = "";
    String authors = "";
    String keywords = "";

    //String fieldName, String readerStr, String arg0
    String titleHighlighted = "";
    String abstractHighlighted = "";
    String authorsHighlighted = "";
    String keywordsHighlighted = "";

    String journaltitle = "";
    String type = "";
    String fullurl = "";
    String dateTime = "";
    String doi = "";
    String images = "";
}

 */

    private List<Result> getResult(IndexSearcher searcher, TopDocs topDocs){
        List<Result> resultList = new LinkedList<>();
        Document doc = null;

        System.out.println("topDocs.scoreDocs.length: " + topDocs.scoreDocs.length);
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {

            try{
                doc = searcher.doc(topDocs.scoreDocs[i].doc);
            }catch (IOException e){
                System.out.println("IOException in printWeb");
            }

            Result result = new Result();

            String title = doc.get("title");
            result.setTitle(title);
            System.out.println("String title: " + title);

            String abs = doc.get("abstract");
            result.setAbs(abs);
            System.out.println("String abs: " + abs);

            String authors = doc.get("authors");
            result.setAuthors(authors);
            System.out.println("String authors: " + authors);


            String keywords = doc.get("keywords");
            result.setKeywords(keywords);
            System.out.println("String keywords: " + keywords);


            String journaltitle = doc.get("journaltitle");
            result.setJournaltitle(journaltitle);
            String type = doc.get("type");
            result.setType(type);
            String fullurl = doc.get("fullurl");
            result.setFullurl(fullurl);
            String dateTime = getFormatedTime("MM-dd-yyyy", doc.get("date"));
            result.setDateTime(dateTime);
            String doi = doc.get("doi");
            result.setDateTime(doi);
            String images = doc.get("image");
            result.setImages(images);

            resultList.add(result);
        }
        return resultList;
    }


    //"MM-dd-yyyy",
    private String getFormatedTime(String format, String input){
        debug("getFormatedTime");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date(Long.valueOf(input));
        String dateTime = simpleDateFormat.format(date);
        return dateTime;

    }//getFormatedTime
//-----------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------



//-----------------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------


    private void debug(String str){
        if(DEBUG){
            System.out.println("============================"+str+"============================");
        }
    }
//-----------------------------------------------------------------------------------------------

}
