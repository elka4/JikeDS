// A minimal Web Crawler written in Java
// Usage: From command line 
//     java WebCrawler <URL> [N]
//  where URL is the url to start the crawl, and N (optional)
//  is the maximum number of pages to download.

/**
 * @author Tianhui Zhu tz406@nyu.edu
 * */
import java.text.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.reflect.Array;

@SuppressWarnings("all")
public class crawler {
    /*inner class UrlWithScore is object contain url,
    the score of the url and the order of the object added to container*/

    class UrlWithScore{
        URL url;
        int score = 0;
        int order = 0;

        public UrlWithScore(URL url){
          this.url = url;
        }
        public URL getURL() {
          return url;
        }
        public void setScore(int score) {
          this.score = score;
        }
        public int getScore() {
          return this.score;
        }
        public void setOrder(int order){
          this.order = order;
        }
        public int getOrder(){
          return order;
        }
    }

    public static final int    SEARCH_LIMIT = 20;  // Absolute max pages
    public static boolean DEBUG = false;
    public static final boolean initializeDEBUG = false;
    public static final String DISALLOW = "Disallow:";
    public static final int MAXSIZE = 20000; // Max size of file

    // URLs to be searched
    PriorityQueue<UrlWithScore> newURLs;
    // Known URLs
    Hashtable<URL,Integer> knownURLs;
    // max number of pages to download
    int maxPages;
    //order of the UrlWithScore added to the priority queue
    int addOrder;
    List<String> queryWords;
    String docPath;
    //comparator of UrlWithScore priority queue based on the score and order
    /*    Comparator<UrlWithScore> urlComparator = new Comparator<UrlWithScore>() {
      public int compare(UrlWithScore left, UrlWithScore right) {
          int res = right.getScore() - left.getScore();
          if (res != 0){
            return res;
          }else{
            return left.getOrder() - right.getOrder();
          }
      }
    };*/

    // initializes data structures.  argv is the command line arguments.

    public void initialize(String[] argv) {
        URL url = null;
        UrlWithScore urlWithScore;
        knownURLs = new Hashtable<URL,Integer>();
        newURLs = new PriorityQueue<UrlWithScore>(new Comparator<UrlWithScore>() {
            public int compare(UrlWithScore left, UrlWithScore right) {
                int res = right.getScore() - left.getScore();
                if (res != 0){
                    return res;
                }else{
                    return left.getOrder() - right.getOrder();
                }
            }
        });
        queryWords = new ArrayList<>();
        //queryWord is the string just to provide print info in the first line in the console
        String queryWord = "";
        docPath = "";
        addOrder = 0;
        List<String> argArr = new ArrayList<>(Arrays.asList(argv));
        int i = 0;

        while(i < argv.length){
            //use switch case to allow parameters given in any order.
            switch (argv[i]){
                case "-u": {
                    try {
                        url = new URL(argv[i + 1]);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.println("Invalid starting URL " + argv[i + 1]);
                        return;
                    }
                    if(initializeDEBUG){
                        System.out.println("initializeDEBUG: case: " + argv[i] + " " + argv[i + 1]);
                    }
                    break;
                }
                case  "-q": {
                    if(initializeDEBUG){
                        System.out.print("initializeDEBUG: case: " + argv[i] + " ");
                    }
                    while(i < argv.length && !argv[i + 1].startsWith("-")){
                        queryWords.add(argv[i + 1]);
                        queryWord += argv[i + 1] + " ";
                        if(initializeDEBUG){
                            System.out.print(argv[i + 1] + " ");
                        }
                        i++;
                    }
                    //get rid of the extra " " at end.
                    queryWord = queryWord.trim();
                    if(initializeDEBUG){
                        System.out.println();
                    }
                    break;
                }
                case "-docs": {
                    docPath = argv[i+1];
                    if(initializeDEBUG){
                        System.out.println("initializeDEBUG: case: " + argv[i] + " " + argv[i + 1]);
                    }
                    break;
                }
                case "-m": {
                    maxPages = Math.min(Integer.parseInt(argv[i + 1]), SEARCH_LIMIT);
                    if(initializeDEBUG){
                        System.out.println("initializeDEBUG: case: " + argv[i] + " " + argv[i + 1]);
                    }
                    break;
                }
                case "-t":{
                    DEBUG = true;
                    if(initializeDEBUG){
                        System.out.println("initializeDEBUG: case: " + argv[i]);
                    }
                    break;
                }
            }//switch
            i++;
        }//while

        knownURLs.put(url,new Integer(1));
        UrlWithScore firstUrlWithScore = new UrlWithScore(url);
        //set the order of the first UrlWithScore as 1.
        firstUrlWithScore.setOrder(++addOrder);
        newURLs.add(firstUrlWithScore);
        System.out.println("Crawling for "+ maxPages + " pages relevant to "
        + "\""+ queryWord + "\""+ " starting from "+ url.toString());
        System.out.println();

        /*Behind a firewall set your proxy and port here!
        */
        Properties props= new Properties(System.getProperties());
        props.put("http.proxySet", "true");
        props.put("http.proxyHost", "webcache-cup");
        props.put("http.proxyPort", "8080");

        Properties newprops = new Properties(props);
        System.setProperties(newprops);
        /**/
    }//initialize

    // Check that the robot exclusion protocol does not disallow
    // downloading url.

    public boolean robotSafe(URL url) {
        String strHost = url.getHost();

        // form URL of the robots.txt file
        String strRobot = "http://" + strHost + "/robots.txt";
        URL urlRobot;
        try { urlRobot = new URL(strRobot);
        } catch (MalformedURLException e) {
            // something weird is happening, so don't trust it
            return false;
        }

        /*if (DEBUG) System.out.println("Checking robot protocol " +
                           urlRobot.toString());*/
        String strCommands;
        try {
            InputStream urlRobotStream = urlRobot.openStream();

            // read in entire file
            byte b[] = new byte[1000];
            int numRead = urlRobotStream.read(b);
            System.out.println("numRead: " + numRead);
            System.out.println("b.length: " + b.length);
            if (numRead == -1) return true;
            strCommands = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlRobotStream.read(b);
                if (numRead != -1) {
                    String newCommands = new String(b, 0, numRead);
                    strCommands += newCommands;
                }
            }
            urlRobotStream.close();
        } catch (IOException e) {
            // if there is no robots.txt file, it is OK to search
            return true;
        }
        // if (DEBUG) System.out.println(strCommands);

        // assume that this robots.txt refers to us and
        // search for "Disallow:" commands.
        String strURL = url.getFile();
        int index = 0;
        while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
            index += DISALLOW.length();
            String strPath = strCommands.substring(index);
            StringTokenizer st = new StringTokenizer(strPath);

            if (!st.hasMoreTokens())
            break;

            String strBadPath = st.nextToken();

            // if the URL starts with a disallowed path, it is not safe
            if (strURL.indexOf(strBadPath) == 0)
            return false;
        }
        return true;
    }//robotsafe



    // adds new URL to the queue. Accept only new URL's that end in
    // htm or html. oldURL is the context, newURLString is the link
    // (either an absolute or a relative URL).

    public void addnewurl(URL oldURL, String newUrlString, String angleContent) {
        URL url;
        String page = getpage(oldURL);
        int score;
        //if (DEBUG) System.out.println("URL String " + newUrlString);
        try {
            url = new URL(oldURL, newUrlString);
            score = calcScore(url, page, angleContent);
        } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return;
        }
        if(knownURLs.containsKey(url)){
            return;
        }
        if(!isUrlInQ(url)){
            UrlWithScore uws = new UrlWithScore(url);
            uws.setScore(score);
            uws.setOrder(++addOrder);
            newURLs.add(uws);
            if (DEBUG) {
                System.out.println("Adding to queue: " + url + ". Score = " + score);
            }
        } else if (score != 0){
            for(UrlWithScore u:newURLs){
                if(u.getURL().equals(url)){
                    u.setScore(score + u.getScore());
                }
            }
            if (DEBUG) {
                System.out.println("Adding " + score + " to score of " + url + ".");
            }
        }
    }//addnewurl

    private int calcScore(URL M, String P, String angleContent) {
        if(queryWords == null || queryWords.isEmpty()){
            System.out.println("Query can not be empty.");
            return 0;
        }

        //situation 1: if (K > 0 of the words in Query are substrings of M.anchor)
        int K = 0;
        String anchor ="";
        anchor = angleContent.replaceAll("<[^<>]*>", "");
        for (String q : queryWords) {
            if (anchor.toLowerCase().contains(q.toLowerCase())) {
                K++;
            }
        }
        if (K > 0) {
        return K * 50;
        }

        //situation 2: if (any word in Query is a substring of M.URL)
        for (String q : queryWords) {
            if (M.toString().toLowerCase().contains(q.toLowerCase())) {
                return 40;
            }
        }

        //
        int U = 0;
        int V = 0;
        int startAngle = P.indexOf(angleContent, 0);
        int endAngle = startAngle + angleContent.length() - 1;
        if (startAngle == -1) {
            System.out.println("ERROR: page does not contain link.");
        }
        String upStreamOfPage = P.substring(0, startAngle);
        upStreamOfPage = upStreamOfPage.replaceAll("\n|,|\\.|\\(|\\)", " ").
                replaceAll("<[^<>]*>", " ").trim();

        String[] upStreamWords = upStreamOfPage.split("\\s+");
        String downStreamOfPage = P.substring(endAngle + 1, P.length());

        downStreamOfPage = downStreamOfPage.replaceAll("\n|,|\\.|\\(|\\)", " ").
                replaceAll("<[^<>]*>", " ").trim();

        String[] downStreamWords = downStreamOfPage.split("\\s+");

        // count U
        for (String q : queryWords) {
            boolean isContained = false;
            for (int i = upStreamWords.length - 5; i <= upStreamWords.length - 1; i++) {
                if (i >= 0 && upStreamWords[i].equalsIgnoreCase(q)) {
                    isContained = true;
                }
            }
            for (int i = 0; i < 5; i++) {
                if (i < downStreamWords.length && downStreamWords[i].equalsIgnoreCase(q)) {
                    isContained = true;
                }
            }
            if (isContained == true) {
                U++;
            }
        }

        // count V
        for (String q : queryWords) {
            boolean isContained = false;
            for (String upWord:upStreamWords) {
                if (upWord.equalsIgnoreCase(q)) {
                    isContained = true;
                }
            }
            for (String downWord:downStreamWords) {
                if (downWord.equalsIgnoreCase(q)) {
                    isContained = true;
                }
            }
            if (isContained == true) {
                V++;
            }
        }
        return 4 * U + Math.abs(V - U);
    }//calcScore

    private boolean isUrlInQ(URL url){
        for(UrlWithScore u:newURLs){
            if(u.getURL().equals(url)){
                return true;
            }
        }
        return false;
    }

    // Download contents of URL
    public String getpage(URL url) {
        try {
            // try opening the URL
            URLConnection urlConnection = url.openConnection();
            //System.out.println("Downloading " + url.toString());

            urlConnection.setAllowUserInteraction(false);

            InputStream urlStream = url.openStream();
            // search the input stream for links
            // first, read in the entire URL
            byte b[] = new byte[1000];
            int numRead = urlStream.read(b);
            if (numRead == -1) return "";
            String content = new String(b, 0, numRead);
            while ((numRead != -1) && (content.length() < MAXSIZE)) {
               numRead = urlStream.read(b);
               if (numRead != -1) {
                 String newContent = new String(b, 0, numRead);
                 content += newContent;
                }
            }
            return content;
        } catch (IOException e) {
            System.out.println("ERROR: couldn't open URL ");
            return "";
        }
    }//getpage



    // Go through page finding links to URLs.  A link is signalled
    // by <a href=" ...   It ends with a close angle bracket, preceded
    // by a close quote, possibly preceded by a hatch mark (marking a
    // fragment, an internal page marker)

    public void processpage(URL url, String page) {
        knownURLs.put(url, new Integer(1));
        //save the html page to designated directory of docPath
        try {
            saveFile(url);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(DEBUG){
            System.out.println("Received: " + url + ".");
        }
        if (knownURLs.size() >= maxPages) {
            return;
        }

        String lcPage = page.toLowerCase(); // Page in lower case
        int index = 0; // position in page
        int iEndAngle, ihref, iURL, iCloseQuote, iHatchMark, iEnd;
        while ((index = lcPage.indexOf("<a",index)) != -1) {
            iEndAngle = lcPage.indexOf(">",index);
            ihref = lcPage.indexOf("href",index);
            int iFirstAngle = index;
            if (ihref != -1) {
                iURL = lcPage.indexOf("\"", ihref) + 1;
                if ((iURL != -1) && (iEndAngle != -1) && (iURL < iEndAngle)) {
                    iCloseQuote = lcPage.indexOf("\"",iURL);
                    iHatchMark = lcPage.indexOf("#", iURL);
                    if ((iCloseQuote != -1) && (iCloseQuote < iEndAngle)) {
                        iEnd = iCloseQuote;
                        if ((iHatchMark != -1) && (iHatchMark < iCloseQuote))
                            iEnd = iHatchMark;

                        String newUrlString = page.substring(iURL,iEnd);

                        //get the angleContent for score calculation
                        int istartSecondAngle = lcPage.indexOf("<", ihref);
                        int iendSecondAngle = lcPage.indexOf(">", istartSecondAngle);
                        String angleContent = page.substring(iFirstAngle, iendSecondAngle + 1);

                        addnewurl(url, newUrlString, angleContent);
                    }
                }
            }
            index = iEndAngle;
        }
        System.out.println();
    }//processpage


    //saveFile save html file to destination directory at docPath
    private void saveFile(URL url) throws FileNotFoundException {
        // TODO Auto-generated method stub
        InputStream is = null;
        BufferedReader br;
        String line;
        File dir = new File(docPath);
        //create the directory if the directory does no exist
        if(!dir.exists()){
            dir.mkdir();
        }
        String[] urlWordArr = url.getFile().split("\\/");

        //get the html file name
        File fileOut = new File(docPath + urlWordArr[urlWordArr.length -1]);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut)));

        try {
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//saveFile

    //Top-level procedure. Keep popping a url off newURLs, download
    //it, and accumulate new URLs
    public void run(String[] argv) {
        initialize(argv);
        for (int i = 0; i < maxPages; i++) {
            UrlWithScore urlwithScore = newURLs.poll();
            URL url = urlwithScore.getURL();
            if (DEBUG) System.out.println("Downloading: " + url.toString() + "." +
            " score = " + urlwithScore.getScore());
            if (robotSafe(url)) {
                String page = getpage(url);
                if (page.length() != 0){
                    processpage(url,page);
                }
                if (newURLs.isEmpty()){
                    break;
                }
            }
        }
        System.out.println("\n" + "Search complete.");
    }

    public static void main(String[] argv) {
        crawler cw = new crawler();
        cw.run(argv);
    }
}

//  -u http://www.ultimateungulate.com/artiodactyla.html -q artiodactyla -docs /Users/tianhuizhu/Downloads/WSE_Journal/WSE_Journal/prog2/download/ -m 7 -t