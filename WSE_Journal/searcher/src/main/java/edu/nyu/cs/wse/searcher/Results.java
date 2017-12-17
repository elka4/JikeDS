package edu.nyu.cs.wse.searcher;

import lombok.*;

import java.util.LinkedList;
import java.util.List;


@ToString

public class Results {
    @Getter String queryStr = "";
    @Getter @Setter List<Result> resultList = new LinkedList<>();

    public Results(){
        System.out.println("public Results(){: " + "public Results(){");

        this.resultList = new Searcher().search(queryStr);
    }


    public Results(String queryStr){
        System.out.println("Results constructor: " + "public Results(String queryStr){");
//        String[] strArr = args.split("\\s+");
        this.queryStr = queryStr;

        this.resultList = new Searcher().search(queryStr);
    }

    public Results(String[] args){
        System.out.println("Results constructor: " + "public Results(String[] args){");
//        String[] strArr = args.split("\\s+");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            sb.append( args[i] + " ");
        }
        String mynewstring = sb.toString();

        this.queryStr = mynewstring;
        this.resultList = new Searcher().search(args);
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
        this.resultList = new Searcher().search(queryStr);
    }
}
