package edu.nyu.cs.wse.crawl;

import lombok.NoArgsConstructor;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.BytesRef;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;


@SuppressWarnings("all")
//@AllArgsConstructor
@NoArgsConstructor
@Component
public class HTMLHandler {

    HashSet<String> importtitle = new HashSet<String>(){} ;

    void initImporttitle(){
        if (importtitle.size() == 0) {
            importtitle.add("Angewandte Chemie International Edition");
            importtitle.add("Science");
            importtitle.add("Nano Lett.");
            importtitle.add("ACS Nano");
            importtitle.add("J. Am. Chem. Soc.");
            importtitle.add("Nature");
            importtitle.add("Acc. Chem. Res.");
            importtitle.add("Nature Materials");
            importtitle.add("Chem. Rev.");

        }
    }

/*    @Autowired
    private Config config;*/

//    private HashMap<String, Float> impact;

    private boolean DEBUG = true;

    private void debug(String str){
        if(DEBUG){
            System.out.println("========="+str+str+str+str+str+"=========");
        }
    }

/////////////////////////////////////////////////////////////////////

//    HashMap<String, Integer> month = intializeMonth();
//    private HashMap<String, Integer> intializeMonth(){
//        HashMap<String, Integer> month = new HashMap<String, Integer>();
//        if (month.size() != 12) {
//            month.put("january", 1);
//            month.put("february", 2);
//            month.put("march", 3);
//            month.put("april", 4);
//            month.put("may", 5);
//            month.put("june", 6);
//            month.put("july", 7);
//            month.put("august", 8);
//            month.put("september", 9);
//            month.put("october", 10);
//            month.put("november", 11);
//            month.put("december", 12);
//        }
//        return month;
//    }

/*    private void init(){
        impact = config.getImpact();
    }*/

/*    private double getImpact(String str){
        double impactFactor = impact.get(str);
        return impactFactor;
    }*/


////////////////////////////////////////////////////////////////////////////////

    // this is the API to CrawIndex
    public Document processHTML(File file) {
//        month = intializeMonth();

//        init();

        initImporttitle();

        HTMLInfo htmlInfo = new HTMLInfo();

        try {
            org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(file, "UTF-8", "");

            if (file.getName().contains("_content_")) {
                science(jsoupDoc, file, htmlInfo);
            }//Science
            else {
                //read info from html and write into htmlInfo
                nature(jsoupDoc, file, htmlInfo);
            }//Nature

            //toPrint(htmlInfo);

            Document luceneDoc = getDoc(htmlInfo);
            return luceneDoc;

        } catch (Exception e) {
            return null;
        }
    }//processHTML


////////////////////////////////////////////////////////////////////////////////

    private void nature(org.jsoup.nodes.Document jsoupDoc, File file, HTMLInfo htmlInfo){
        //debug("Nature");
        try {
            //readElements(jsoupDoc, file);
            getHtmlInfo_image(jsoupDoc, file, "nature", htmlInfo);
            getHtmlInfo_keyword(jsoupDoc, file, "nature", htmlInfo);
            getHtmlInfo_url(jsoupDoc, file, "nature", htmlInfo);

        } catch (Exception ex) {
            //debug("Nature Catch Exception");

            System.out.println(file.getName());

            ex.printStackTrace();
        } finally {
            readElement(jsoupDoc, file, htmlInfo);
        }
        // debug("Nature_End");
    }//nature

////////////////////////////////////////////////////////////////////////////////

    private Document getDoc(HTMLInfo htmlInfo){
        Document luceneDoc = new Document();

        //01
        Field fieldTitle = null;
        String title = htmlInfo.getTitle();



        if(importtitle.contains(title.trim())){
            fieldTitle = new TextField("title", new BytesRef(title.toString()).utf8ToString(), Store.YES);
            fieldTitle.setBoost(1.6f);
        } else{
            fieldTitle = new TextField("title", title, Store.YES);
            fieldTitle.setBoost(1.2f);
        }
//        fieldTitle = new TextField("title", title, Store.YES);

        luceneDoc.add(fieldTitle);

        //02
        Field fieldAbstract = null;
        String summary = htmlInfo.getSummary();
        if(importtitle.contains(title.trim())){
            fieldAbstract = new TextField("abstract", new BytesRef(summary.toString()).utf8ToString(), Store.YES);
            fieldAbstract.setBoost(1.2f);
            luceneDoc.add(fieldAbstract);
        }else{
            fieldAbstract = new TextField("abstract", summary, Store.YES);
        }

//        fieldAbstract = new TextField("abstract", summary, Store.YES);
        luceneDoc.add(fieldAbstract);


        //03
        String doi = htmlInfo.getDoi();
        luceneDoc.add(new StoredField("doi", doi));

        //04
        long time = getFormatedTime("yyyy-MM-dd", htmlInfo);
        luceneDoc.add(new LegacyLongField("date", time, Store.YES));

        //05
        String url = htmlInfo.getUrl();
        luceneDoc.add(new StoredField("url", url));

        //06
        String fullurl  = htmlInfo.getFullurl();
        luceneDoc.add(new StoredField("fullurl", fullurl));

        //07
        String type = htmlInfo.getType();
        luceneDoc.add(new StoredField("type", type));

        //08
        String images = htmlInfo.getImages();
        luceneDoc.add(new StoredField("image", images.toString()));

        //09
        String journaltitle  = htmlInfo.getJournaltitle();
        luceneDoc.add(new StoredField("journaltitle", journaltitle));

        //10
        String publisher = htmlInfo.getPublisher();
        luceneDoc.add(new StoredField("publisher", publisher));

        //11
        String authors = htmlInfo.getAuthors();
        Field af = new TextField("authors", authors.toString(), Store.YES);

        if(importtitle.contains(title.trim())){
            af.setBoost(1.6f);
        }else{
            af.setBoost(1.2f);
        }

        luceneDoc.add(af);

        //12
        String keywords = htmlInfo.getKeywords();
        Field kf = new TextField("keywords", keywords.toString(), Store.YES);

        if(importtitle.contains(title.trim())){
            kf.setBoost(1.4f);
        } else{
            kf.setBoost(1.1f);
        }

        luceneDoc.add(kf);
        // if (image.length() > 0)
        // System.out.println(image);

        return luceneDoc;

    }//getDoc

////////////////////////////////////////////////////////////////////////////////

    private long getFormatedTime(String format, HTMLInfo htmlInfo){
        long time = 0l;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            time = simpleDateFormat.parse(htmlInfo.getDate()).getTime();
        }catch (ParseException pe){

        }
        return time;
    }//getFormatedTime

////////////////////////////////////////////////////////////////////////////////


    private StringBuilder deleteTheLastComma(StringBuilder sb){
        if (sb.toString().endsWith(", ")){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

////////////////////////////////////////////////////////////////////////////////

    private void getHtmlInfo_image(org.jsoup.nodes.Document jsoupDoc, File file, String journal, HTMLInfo htmlInfo){
        switch (journal){
            case "nature":{
                Elements elements = jsoupDoc.getElementsByAttribute("data-media-desc");
                StringBuilder sb = new StringBuilder("");
                for (Element element : elements) {
                    sb.append("http://www.nature.com" + element.attr("src") + ", ");
                }
                sb = deleteTheLastComma(sb);
                htmlInfo.setImages(sb.toString());
                break;
            }//"nature"

            case "science":{

            }//science

        }
    }//getHtmlInfo_image

    private void getHtmlInfo_keyword(org.jsoup.nodes.Document jsoupDoc, File file, String journal, HTMLInfo htmlInfo){
        switch (journal){
            case "nature":{
                Elements elements = jsoupDoc.getElementsByAttributeValueStarting("href", "/subjects/");
                StringBuilder keyword = new StringBuilder("");
                for (Element element : elements) {
                    keyword.append(element.text() + ", ");
                }
                keyword = deleteTheLastComma(keyword);
                htmlInfo.setKeywords(keyword.toString());
                break;
            }//"nature"

            case "science":{

            }//science

        }
    }//getHtmlInfo_keyword

    private void getHtmlInfo_url (org.jsoup.nodes.Document jsoupDoc, File file, String journal, HTMLInfo htmlInfo){
        switch (journal) {
            case "nature": {
                String url = "http://www.nature.com" + file.getName().replace("_", "/");
                String fullurl = url;
                htmlInfo.setUrl(url);
                htmlInfo.setFullurl(fullurl);
                break;
            }//nature
            case "science":{

            }//science
        }

    }//getHtmlInfo_url

////////////////////////////////////////////////////////////////////////////////

    private void readElement(org.jsoup.nodes.Document doc, File file, HTMLInfo htmlInfo){
        Elements es = doc.getElementsByTag("meta");
        try {
            for (Element e : es) {
                switch (e.attr("name")) {
                    case "description": {
                        htmlInfo.setSummary(e.attr("content").trim());
                        break;
                    }
                    case "DC.title": {
                        if (htmlInfo.getTitle().length() == 0) {
                            htmlInfo.setTitle(e.attr("content").trim());
                        }
                        break;
                    }
                    case "DC.date": {
                        htmlInfo.setDate(e.attr("content").trim());
                        break;
                    }
                    case "DC.identifier": {
                        htmlInfo.setDoi(e.attr("content").replace("doi:", " ").trim());
                        break;
                    }
/*                        case "citation_abstract_html_url": {
                            htmlInfo.setUrl(e.attr("content").trim());
                            break;
                        }

                        case "citation_full_html_url": {
                            htmlInfo.setFullurl(e.attr("content").trim());
                            break;
                        }*/

                    case "prism.section": {
                        htmlInfo.setType(e.attr("content").trim());
                        break;
                    }

                    case "citation_journal_title": {
                        htmlInfo.setJournaltitle(e.attr("content").trim());
                        break;
                    }

                    case "citation_publisher": {
                        htmlInfo.setPublisher(e.attr("content").trim());
                        break;
                    }
                    case "citation_author": {
                        StringBuilder author = htmlInfo.getAuthor();
                        author.append(e.attr("content").trim() + ", ");
                        htmlInfo.setAuthor(author);
                        break;
                    }
                }//switch
            }//for
            StringBuilder authors = htmlInfo.getAuthor();
            authors = deleteTheLastComma(authors);
            htmlInfo.setAuthors(authors.toString());

        } catch (Exception ex1) {
            System.out.println(file.getName());
            ex1.printStackTrace();
        }
    }//readElement



////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////


/*    private void readElements(org.jsoup.nodes.Document jsoupDoc, File f){

        // image
        Elements elements = jsoupDoc.getElementsByAttribute("data-media-desc");
        for (Element element : elements) {
            image.append("http://www.nature.com" + element.attr("src") + ",");
        }
        if (image.toString().endsWith(",")){
            image.deleteCharAt(image.length() - 1);
        }

        // keywords
        elements = jsoupDoc.getElementsByAttributeValueStarting("href", "/subjects/");
        for (Element element : elements) {
            keyword.append(element.text() + ", ");
        }

        // url
        // full url
        url = "http://www.nature.com" + f.getName().replace("_", "/");
        fullurl = url;

    }*/

    //delete the last ','





////////////////////////////////////////////////////////////////////////////////


//    private void getHtmlInfo_summary(Element e, File file, String journal, HTMLInfo htmlInfo){
//        switch (journal) {
//            case "nature": {
//                if (e.attr("name").equals("description")) {
//                    String summary = e.attr("content").trim();
//                    htmlInfo.setSummary(summary);
//                }
//                break;
//            }
//            case "science": {
//
//            }//science
//        }
//    }
//




////////////////////////////////////////////////////////////////////////////////


    private void science (org.jsoup.nodes.Document jsoupDoc, File file, HTMLInfo htmlInfo){
        Elements element = null;
        try {
           /* element = jsoupDoc.getElementsByAttributeValue("class", "section abstract");
            if (element.size() >= 1) {
                summary = element.get(0).text();
            }
            // no abstract, use summary
            if (summary == null || summary.length() == 0) {
                summary = jsoupDoc.getElementsByAttributeValue("class", "section summary").get(0).text();
                if (summary.contains("Summary")) {
                    summary = summary.split("Summary", 2)[1].trim();
                }
            } else {
                summary = summary.split("Abstract", 2)[1].trim();
            }

            doi = jsoupDoc.getElementsByAttributeValue("class", "meta-line")
                    .text().replace("DOI:", "\nDOI:").trim();


            getHtmlInfo_image(jsoupDoc, file, "science", htmlInfo);

            getHtmlInfo_keyword(jsoupDoc, file, "science", htmlInfo);

            getHtmlInfo_url(jsoupDoc, file, "science", htmlInfo);*/

        } catch (Exception ex) {

            System.out.println(file.getName());

            ex.printStackTrace();
        } finally {
            Elements elements = jsoupDoc.getElementsByTag("meta");

            for (Element elem : elements) {
                readElement(jsoupDoc, file, htmlInfo);
                try {


/*                    String attributeKey = elem.attr("name");
                    String content = elem.attr("content");
                    if (content != null){
                        content = content.trim();
                    }
                    switch(attributeKey){
                        case "DC.Title":
                            title = content; break;
                        case "DC.Date":
                            date = content; break;
                        case "citation_abstract_html_url":
                            url = content; break;
                        case  "citation_full_html_url":
                            fullurl = content; break;
                        case  "citation_journal_title":
                            journaltitle = content; break;
                        case "DC.Publisher":
                            publisher = content; break;
                        case "citation_section":
                            type = content; break;
                        case "DC.Contributor":
                            author.append(content + ", "); break;
                    }//switch*/
                } catch (Exception ex1) {
                    continue;
                }
            }//for
        }//finally
    }


////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////

    public void toPrint(HTMLInfo htmlInfo) {
        String title = htmlInfo.getTitle();
        String date = htmlInfo.getDate();
        String summary = htmlInfo.getSummary();
        String url = htmlInfo.getUrl();
        String fullurl = htmlInfo.getFullurl();
        String journaltitle = htmlInfo.getJournaltitle();
        String publisher = htmlInfo.getPublisher();
        String type = htmlInfo.getType();
        String doi = htmlInfo.getDoi();
        String authors = htmlInfo.getAuthors();
        String keywords = htmlInfo.getKeywords();
        String images = htmlInfo.getImages();

        if (title != null && title.length() != 0)        System.out.println("01title " + title);
        if (date != null && date.length() != 0)         System.out.println("02date " + date);
        if (summary != null && summary.length() != 0)      System.out.println("03summary " + summary);
        if (url != null && url.length() != 0)          System.out.println("04url "+ url);
        if (fullurl != null && fullurl.length() != 0)      System.out.println("05fullurl " + fullurl);
        if (journaltitle != null && journaltitle.length() != 0) System.out.println("06journaltitle "+ journaltitle);
        if (publisher != null && publisher.length() != 0)    System.out.println("07publisher" + publisher);
        if (type != null && type.length() != 0)         System.out.println("08type "+ type);
        if (doi != null && doi.length() != 0)          System.out.println("09doi " + doi);
        if (authors != null && authors.length() != 0)       System.out.println("10author "+ authors.toString());
        if (keywords != null && keywords.length() != 0)     System.out.println("11keywords "+ keywords.toString());
        if (images != null && images.length() != 0)        System.out.println("12image " + images.toString());

    }
////////////////////////////////////////////////////////////////////////////////

}
