import java.net.*;
import java.io.*;

//https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
public class URLReader {
    public static void main(String[] args)  {
        try{
            URL oracle = new URL("https://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Cetacean.html");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch ( Exception e) {
            System.out.println(e);
        }

    }
}