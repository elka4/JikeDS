import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;


@SuppressWarnings("all")
public class safe {

    public static final String DISALLOW = "Disallow:";

    public static boolean robotSafe(URL url) {
        String strHost = url.getHost();

        // form URL of the robots.txt file
        String strRobot = "http://" + strHost + "/robots.txt";
        URL urlRobot;
        try { urlRobot = new URL(strRobot);
            System.out.println("urlRobot: " + urlRobot);
        } catch (MalformedURLException e) {
            // something weird is happening, so don't trust it
            System.out.println("something weird is happening, so don't trust it");
            return false;
        }

        /*if (DEBUG) System.out.println("Checking robot protocol " +
                           urlRobot.toString());*/
        String strCommands;
        try {
            InputStream urlRobotStream = urlRobot.openStream();
            System.out.println("urlRobotStream: " + urlRobotStream);
            // read in entire file
            byte b[] = new byte[1175*8];
            int numRead = urlRobotStream.read(b);
            System.out.println("before");
            System.out.println("b.length: " + b.length);
            System.out.println("numRead: " + numRead);

            if (numRead != -1){
                strCommands = new String(b, 0, numRead);
            } else {
                strCommands = "";
            }

            System.out.println("after");

            System.out.println("strCommands: " + strCommands);
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
            System.out.println("there is no robots.txt file, it is OK to search");
            return true;
        }
        // if (DEBUG) System.out.println(strCommands);

        // assume that this robots.txt refers to us and
        // search for "Disallow:" commands.
        System.out.println("search for \"Disallow:\" commands");
        String strURL = url.getFile();
        int index = 0;
        while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
            index += DISALLOW.length();
            String strPath = strCommands.substring(index);
            StringTokenizer st = new StringTokenizer(strPath);

            if (!st.hasMoreTokens()){
                System.out.println("!st.hasMoreTokens()");
                break;
            }


            String strBadPath = st.nextToken();

            // if the URL starts with a disallowed path, it is not safe
            if (strURL.indexOf(strBadPath) == 0) {
                System.out.println("if the URL starts with a disallowed path, it is not safe");
                return false;
            }

        }
        return true;
    }//robotsafe
//////////////////////////////////////////////////////////////////////////////////
// Download contents of URL
public static String getpage(URL url) {
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
        String content = new String(b, 0, numRead);
        while ((numRead != -1) && (content.length() < 20)) {
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

//////////////////////////////////////////////////////////////////////////////////
    private static void test01(){
        try{
            URL url = new URL("https://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Whale.html");
            System.out.println(robotSafe(url));
        } catch (Exception e){

        }
    }
//////////////////////////////////////////////////////////////////////////////////
private static void test02(){
    try{
        URL url = new URL("http://mv.6park.com/");
        if (robotSafe(url)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    } catch (Exception e){
        System.out.println(e);
    }
}
//////////////////////////////////////////////////////////////////////////////////
    private static void test03(){
        try{
            URL url = new URL("https://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Cetacean.html");
            System.out.println(getpage(url));
        } catch (Exception e){

        }
    }
//////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

        test01();
        System.out.println("------------------------------");
        test02();

    }
}
