package com.example.demo.Crawlers;

import com.example.demo.JournalUrl;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("all")
public class CrawIndex {


    int weeknum = 2;
    HashMap<String, JournalUrl> link_JournalUrl_Hash = new HashMap<String, JournalUrl>();
    ArrayList<String> startingUrls = new ArrayList<String>();



    public String parentPath = new File(System.getProperty("user.dir")).getParent();
    public String downloadsFolder = parentPath + "/" + "down";
    public String cacheFile = parentPath + "/" + "cache";

    public String indexFolder = parentPath + "/" + "index";

    public String urlFile = parentPath + "/" + "urlFile.txt";




    private void processNature(JournalUrl jUrl) {
        System.out.println("..................start processNature..................");

        // the start date to count
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(new Date());
        startDate.add(startDate.DATE, -weeknum * 7);

        // the end date: today
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        //the nature journal has year and month in the url
        int monthNum = (today.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)) * 12
                + today.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);

        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH) + 1;


        for (int c = 0; c <= monthNum; c++) {

            JournalUrl newJUrl = new JournalUrl();
            String linkStr = jUrl.getLink();
            if (linkStr == null || linkStr.equals("")){
                continue;
            }
            linkStr = jUrl.getLink() + "?year=" + curYear + "&month=" + curMonth;
            System.out.println("linkStr: " + linkStr);
            newJUrl.setLink(linkStr);

            newJUrl.setLevel(jUrl.getLevel());
            newJUrl.setFlpre(jUrl.getFlpre());

            link_JournalUrl_Hash.put(newJUrl.getLink(), newJUrl);

            startingUrls.add(linkStr);
            System.out.println("[startingUrls] " + startingUrls);

            curMonth--;
            if (curMonth == 0) {
                curYear--;
                curMonth = 12;
            }
        }


        System.out.println("..................end processNature..................");

    }


/////////////////////////////////////////////////////////////////////

    void readUrls() {
        System.out.println("======================start readUrls===========================");
        System.out.println("urlFile: " + urlFile);
//        System.out.println(urlFile);


        try {
            @Cleanup FileReader fr = new FileReader(urlFile);
            @Cleanup BufferedReader br = new BufferedReader(fr);
            String str = null;
            JournalUrl jUrl = new JournalUrl();
            String code = "";
            String content = "";

            while ((str = br.readLine()) != null) {

                if (str.contains(";")){
                    code = str.split(";")[0].trim();
                    content = str.split(";")[1].trim();
                    System.out.println("code: " + code);
                    System.out.println("content: " + content);
                }

                switch (code) {
                    case "":
                        break;

                    case "JOURNAL":
                        if(content.equals("Nature")){
                            processNature(jUrl);
                        }
                        System.out.println("JOURNAL: " + content);
                        if (!jUrl.getName().equals("")) {
                            link_JournalUrl_Hash.put(jUrl.getLink(), jUrl);
                            jUrl = new JournalUrl();
                        }
                        jUrl.setName(content);
                        break;

                    case "Link":
                        jUrl.setLink(content);
                        if (!jUrl.getName().equals("Nature") && !jUrl.getName().equals("Science")) {
                            startingUrls.add(jUrl.getLink());
                            System.out.println("jUrl.getLink(): "+ jUrl.getLink());
                            System.out.println("startingUrls: " + startingUrls);
                        }
                        System.out.println("jUrl.getLink(): "+ jUrl.getLink());
                        System.out.println("startingUrls: " + startingUrls);
                        break;

                    case "Level":
                        jUrl.setLevel(Integer.parseInt(content));
                        if (jUrl.getName().equals("Science")) {
                            //processScience(jUrl);
                            //jUrl = new JournalUrl();
                        }
                        System.out.println("Level: " + Integer.parseInt(content));
                        break;

                    case "First_Level_Pre_Filter":
                        //First_Level_Pre_Filter; research
                        jUrl.setFlpre(content);
                        System.out.println("contentcontentcontentcontent: "+ content);
                        if (jUrl.getName().equals("Nature")) {

                            processNature(jUrl);
                            jUrl = new JournalUrl();
                        }
                        System.out.println("Nature: First_Level_Pre_Filter");
                        break;

                    case "First_Level_Post_Filter":
                        jUrl.setFlpost(content);
                        System.out.println("Nature: First_Level_Post_Filter");

                        break;

                    case "Second_Level_Pre_Filter":
                        jUrl.setSecpre(content);
                        System.out.println("Nature: Second_Level_Pre_Filter");

                        break;

                    case "Second_Level_Post_Filter":
                        jUrl.setSecpost(content);
                        System.out.println("Nature: Second_Level_Post_Filter");

                        break;

                }//switch

            }//while

            if (!jUrl.getName().equals("")) {
                link_JournalUrl_Hash.put(jUrl.getLink(), jUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("======================end readUrls===========================");

    }//ReadJournalUrls()

/////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////


}
