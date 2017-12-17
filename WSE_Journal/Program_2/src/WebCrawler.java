// A minimal Web Crawler written in Java
// Usage: From command line 
// java WebCrawler <URL> [N]
// where URL is the url to start the crawl, and N (optional)
// is the maximum number of pages to download.

import java.util.*;
import java.net.*;
import java.io.*;
import org.apache.commons.cli.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings("all")
public class WebCrawler {
    public static final int     SEARCH_LIMIT = 500;  // Absolute max pages 
    public static boolean DEBUG = false;
    public static final String  DISALLOW = "Disallow:";
    public static final int 	MAXSIZE = 20000; // Max size of file 

    // URLs to be searched
    PriorityQueue<Pair<URL,Integer>> newURLs;
    // Known URLs
    protected static Hashtable<URL,Integer> knownURLs;
    // max number of pages to download
    private int maxPages;
    private String[] query;
    private String storePath;
    private int order = 0;


    private Integer Score(String M, Document dPage) {
		if (query == null) return 0;
		Element eM = Jsoup.parse(M).body().children().first();

		//case 1: 
		String M_content = eM.text();
		//System.out.println("!!!!!!!!!!!!!+"+M_content);
		int occurance = 0;
		for(int i = 0; i < query.length;i++)
			if(M_content.contains(query[i])) occurance++;
		if(occurance > 0)
			return occurance*50;

		//case 2:
		String Murl = eM.attr("href");
		for(int i = 0; i < query.length;i++)
			if(Murl.contains(query[i])) return 40;

		//case 3:
		String pageText = dPage.text();
		int startIndex = pageText.indexOf(M_content);
		int endIndex = startIndex + M_content.length();
		
		StringTokenizer htokens = new StringTokenizer(pageText.substring(0,startIndex));
		StringTokenizer ttokens = new StringTokenizer(pageText.substring(endIndex));
		StringTokenizer ptokens = new StringTokenizer(pageText);
		Vector<String> w5tokens = new Vector<String>();
		for(int limit = htokens.countTokens();limit>0;limit--){
			if(limit<=5)
				w5tokens.add(htokens.nextToken().replaceAll(",|\\.|\\(|\\)", ""));
			else
				htokens.nextToken();
		}
		for(int limit = 0; ttokens.hasMoreElements() && limit<5; limit++)
				w5tokens.add(ttokens.nextToken().replaceAll(",|\\.|\\(|\\)", ""));

		Vector<String> pagetokens = new Vector<String>();

		for(;ptokens.hasMoreTokens();)
			pagetokens.add(ptokens.nextToken().replaceAll(",|\\.|\\(|\\)", ""));

		int V = 0;
		int U = 0;
		for(int i = 0; i < query.length;i++){
			for(int j = 0;j<pagetokens.size();j++)
				if(pagetokens.get(j).equals(query[i])) {V++;break;}
			for(int j = 0;j < w5tokens.size();j++)
				if(w5tokens.get(j).equals(query[i])) {U++;break;}
		}
		//System.out.println(w5tokens);
		//System.out.println(w5tokens.get(1));
		//System.out.println("U:V " + U +":" + V);
		return 4*U + Math.abs(V-U);	
    }//Score

    //-u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/PolarBear.html -q ocean -docs /Users/tianhuizhu/IdeaProjects/WSE_Program/Program_2/down -m 7 -t
    // initializes data structures.  argv is the command line arguments.
	public void initialize(String[] argv) {
		Options ops = new Options();
		ops.addOption("u", "url",true, "Startng Url");
		ops.addOption("q", "query",true, "Query");
		ops.addOption("docs", true, "Path");
		ops.addOption("m", "max", true, "Maximum Number of Pages");
		ops.addOption("t", "trace", false, "Trace flag");
		DefaultParser parser = new DefaultParser();
		CommandLine comman;
		try {
			comman = parser.parse(ops,argv);
		} catch (ParseException e) {
			e.printStackTrace();
			return ;
		}
		try{
	    	maxPages = Integer.valueOf(comman.getOptionValue('m'));
	    } catch (NumberFormatException e){
	    	e.printStackTrace();
	    	maxPages = SEARCH_LIMIT;
	    }
	    URL url;
	    knownURLs = new Hashtable<URL,Integer>();
	    newURLs = new PriorityQueue<Pair<URL,Integer>>(maxPages,new MyComparator());
	    try{ 
	    	url = new URL(comman.getOptionValue('u')); 
	    }
	    catch (MalformedURLException e){
	        System.out.println("Invalid starting URL " + comman.getOptionValue('u'));
	        return;
	    }
	    knownURLs.put(url,new Integer(0));
	    newURLs.add(new Pair<URL,Integer>(url,new Integer(order++)));
	    
	    storePath = comman.getOptionValue("docs");
	    if( maxPages > SEARCH_LIMIT ) maxPages = SEARCH_LIMIT;
	    System.out.println("Maximum number of pages:" + maxPages);
	    comman.getArgs();
	    if( comman.hasOption('t')) DEBUG = true;
	    query = new String[comman.getArgs().length+1];
	    query[0] = comman.getOptionValue('q');
	    String[] args = comman.getArgs();
	    for(int i =1 ; i <query.length;i++)
	    	query[i]=args[i-1];
	    System.out.println("Starting search: Initial URL " + url.toString() + " with query:");
	    for(int i =0;i<query.length;i++)
	    	System.out.print(query[i]+" ");
	    System.out.print('\n');
	    storePath = comman.getOptionValue("docs");
	    
		/*Behind a firewall set your proxy and port here!
		*/
	    Properties props= new Properties(System.getProperties());
	    props.put("http.proxySet", "true");
	    props.put("http.proxyHost", "webcache-cup");
	    props.put("http.proxyPort", "8080");
	
	    Properties newprops = new Properties(props);
	    System.setProperties(newprops);
	}//initialize


    // Check that the robot exclusion protocol does not disallow
    // downloading url.
	public boolean robotSafe(URL url){
		String strHost = url.getHost();	
		// form URL of the robots.txt file
		String strRobot = "http://" + strHost + "/robots.txt";
		URL urlRobot;
		try{
			urlRobot = new URL(strRobot);
		}
		catch (MalformedURLException e) {
		    // something weird is happening, so don't trust it
		    return false;
		}
		
		//if (DEBUG) System.out.println("Checking robot protocol " + 
		//                               urlRobot.toString());
		String strCommands;
		try{
			InputStream urlRobotStream = urlRobot.openStream();
		    // read in entire file
			byte b[] = new byte[1000];
			int numRead = urlRobotStream.read(b);
//			strCommands = new String(b, 0, numRead);
			strCommands = new String(b, 0, numRead);
			while (numRead != -1){
				numRead = urlRobotStream.read(b);
				if (numRead != -1) {
					String newCommands = new String(b, 0, numRead);
					strCommands += newCommands;
				}
		    }
			urlRobotStream.close();
		}catch (Exception e){
		    // if there is no robots.txt file, it is OK to search
		    return true;
		}
		//if (DEBUG) System.out.println(strCommands);	
		// assume that this robots.txt refers to us and 
		// search for "Disallow:" commands.
		String strURL = url.getFile();
		int index = 0;
		while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
			index += DISALLOW.length();
		    String strPath = strCommands.substring(index);
		    StringTokenizer st = new StringTokenizer(strPath);
		    if (!st.hasMoreTokens())	break;
		    
		    String strBadPath = st.nextToken();
		    // if the URL starts with a disallowed path, it is not safe
		    if (strURL.indexOf(strBadPath) == 0)
			return false;
		}
		return true;
	}//robotSafe



    // adds new URL to the queue. Accept only new URL's that end in
    // htm or html. oldURL is the context, newURLString is the link
    // (either an absolute or a relative URL).

	public void addnewurl(URL oldURL, String newUrlString, Integer score){
		URL url; 
		try{
			url = new URL(oldURL,newUrlString);
			if(!knownURLs.containsKey(url)){
				String filename =  url.getFile();
				int iSuffix = filename.lastIndexOf("htm");
				if ((iSuffix == filename.length() - 3) ||
						(iSuffix == filename.length() - 4)){
					knownURLs.put(url,new Integer(score));
					newURLs.add(new Pair<URL,Integer>(url,new Integer(order++)));
					if (DEBUG) System.out.println("Adding to queue: " + url.toString() +". Score = " + score);
				} 
			}
			else if(!knownURLs.get(url).equals(-1)){
				knownURLs.replace(url, knownURLs.get(url)+score);
				if (DEBUG) System.out.println("Adding "+score+" to the score of " + url.toString()+".");
			}
		}
		catch (MalformedURLException e){ return; }
	}


    // Download contents of URL
	public String getpage(URL url, Integer score){
		try { 
		    // try opening the URL
		    URLConnection urlConnection = url.openConnection();
		    if (DEBUG) System.out.println("Downloading " + url.toString() + ". Score = " + score);
		    knownURLs.replace(url, -1);
		    urlConnection.setAllowUserInteraction(false);
		    InputStream urlStream = url.openStream();
				// search the input stream for links
				// first, read in the entire URL
		    byte b[] = new byte[1000];
		    int numRead = urlStream.read(b);
		    String content = new String(b, 0, numRead);
		    while ((numRead != -1) && (content.length() < MAXSIZE)) {
		    	numRead = urlStream.read(b);
		    	if (numRead != -1){
		    		String newContent = new String(b, 0, numRead);
		    		content += newContent;
				}
			}
		    return content;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: couldn't open URL ");
			return "";
	    }  
	}

	public void processpage(URL url, String page){ 
		String lcPage = page.toLowerCase(); // Page in lower case
		Document dPage = Jsoup.parse(lcPage);
		Elements atags = Jsoup.parse(page).getElementsByTag("a");
		for(Element atag: atags){
			addnewurl(url, atag.attr("href"),Score(atag.toString().toLowerCase(),dPage));
		}
	}

	// Top-level procedure. Keep popping a url off newURLs, download
	// it, and accumulate new URLs
	public void run(String[] argv) throws IOException{
		initialize(argv);

		for (int i = 0; i < maxPages; i++) {
			Pair<URL,Integer> url;
			try{
				url = newURLs.remove();
				}
			catch(Exception e) {return ;}

			if (robotSafe(url.getFirst())) {
				String page = getpage(url.getFirst(),knownURLs.get(url.getFirst()));
				File f = new File(storePath+"/"+url.getFirst().getFile().replace('/', '-'));
				FileWriter w = new FileWriter(f);
				w.write(page);
				w.close();
				if (page.length() != 0) processpage(url.getFirst(),page);
				if (newURLs.isEmpty()) break;
			}
			if (DEBUG) System.out.println("");
		}
		System.out.println("Search complete.");
	}

	public static void main(String[] argv) throws IOException{ 
		WebCrawler wc = new WebCrawler();
		wc.run(argv);
	}


//  -u http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/PolarBear.html -q ocean -docs /Users/tianhuizhu/IdeaProjects/WSE_Program/Program_2/down -m 7 -t
//  -u http://www.ultimateungulate.com/artiodactyla.html -q artiodactyla -docs /Users/tianhuizhu/IdeaProjects/WSE_Program/Program_2/down -m 7 -t
}