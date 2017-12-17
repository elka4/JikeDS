import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Properties;
//javac -cp ./commons-cli-1.3.1.jar opt.java
//
@SuppressWarnings("all")
public class opt {
    public static void initialize(String[] argv) {
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
//            maxPages = Integer.valueOf(comman.getOptionValue('m'));
            System.out.println("maxPages: " + Integer.valueOf(comman.getOptionValue('m')));
        } catch (NumberFormatException e){
            e.printStackTrace();
//            maxPages = SEARCH_LIMIT;
        }

    }//initialize

    public static void main(String[] args) {
        initialize(args);
    }
}
