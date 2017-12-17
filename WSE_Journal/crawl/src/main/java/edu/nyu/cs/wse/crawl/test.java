package edu.nyu.cs.wse.crawl;

//import org.apache.commons.lang3.builder.ToStringExclude;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public class test {

    @Test
    public void test() throws  Exception{
        URL url = new URL("http://science.sciencemag.org/content/358/6370\n");
        System.out.println(robotSafe(url));
    }

    @SuppressWarnings("all")
    public boolean robotSafe(URL url) {
        String strHost = url.getHost();
        String DISALLOW = "DISALLOW:";
        // form URL of the robots.txt file
        String strRobot = "http://" + strHost + "/robots.txt";
        System.out.println(strRobot);
        // System.out.println(strRobot);
        URL urlRobot;
        try {
            urlRobot = new URL(strRobot);
        } catch (MalformedURLException e) {
            // something weird is happening, so don't trust it
            return false;
        }

        String strCommands;
        try {
            InputStream urlRobotStream = urlRobot.openStream();

            // read in entire file
            byte b[] = new byte[1000];
            int numRead = urlRobotStream.read(b);
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

        // assume that this robots.txt refers to us and
        // search for "Disallow:" commands.
        String strURL = url.getFile();
        int index = 0;
        String specific = null;
        for (String valid : strCommands.split("User-agent:")) {
            if (valid.trim().startsWith("*")) {
                specific = valid;
                break;
            }
        }
        while ((index = specific.indexOf(DISALLOW, index)) != -1) {
            index += DISALLOW.length();
            String strPath = specific.substring(index);
            StringTokenizer st = new StringTokenizer(strPath);

            if (!st.hasMoreTokens())
                break;

            String strBadPath = st.nextToken();

            // if the URL starts with a disallowed path, it is not safe
            if (strURL.indexOf(strBadPath) == 0)
                return false;
        }

        return true;
    }//robotSafe

    @Value("${config.num}")
    private int weeknum;
    @Test
    public void test02(){
        System.out.println("weeknum: " + weeknum);
    }

}
