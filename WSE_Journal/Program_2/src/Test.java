import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test {
	static String[] query = {"ocean"};
	public static void main(String[] argv) throws FileNotFoundException{ 
		Test wc = new Test();
		String M = "<a href=\"http://cs.nyu.edu/courses/spring16/CSCI-GA.2580-001/MarineMammal/Bear.html\">Atlantic</a>";
		//BufferedReader br = new BufferedReader(new FileReader(new File("./Data/Bear")));
		//String Page = "";
		//System.out.println(M);
		//System.out.println(br.toString());
		System.out.println(wc.Score(M, wc.readToString("/Users/tianhuizhu/IdeaProjects/WSE_Program/Program_2/Data/Bear.html")));
	}
	public String readToString(String fileName) {
		String encoding = "ISO-8859-1";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}
	public Test() {
		// TODO Auto-generated constructor stub
	}
	public Integer Score(String M,String Page) {
		
    	if (query == null) return 0;
    	Element eM = Jsoup.parse(M.toLowerCase()).body().children().first();
    	
    	//case 1: 
    	String M_content = eM.text();
    	
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
    	Document dP = Jsoup.parse(Page.toLowerCase());
    	String pageText = dP.text();
    	System.out.println(pageText);
    	int startIndex = pageText.indexOf(M_content);
    	int endIndex = startIndex + M_content.length();
    	int hIndex = startIndex;
    	int tIndex = endIndex;
    	for(int j = 0; hIndex >= 0 && j <=5; hIndex--){
    		if(pageText.charAt(hIndex)=='\n' || pageText.charAt(hIndex)=='\t' ||pageText.charAt(hIndex)==' ')
    			j++;
    	}
    	for(int j = 0; tIndex < pageText.length() && j <= 5; tIndex++){
    		if(pageText.charAt(tIndex)=='\n' || pageText.charAt(tIndex)=='\t' ||pageText.charAt(tIndex)==' ')
    			j++;
    	}
    	String closeString = pageText.substring(hIndex, startIndex) + " " +pageText.substring(endIndex, tIndex);
    	System.out.println(closeString);
    	int V = 0;
    	int U = 0;
    	for(int i = 0; i < query.length;i++){
    		if(pageText.contains(query[i])) V++;
    		if(closeString.contains(query[i])) U++;
    	}
    	return 4*U + Math.abs(V-U);	
    }
}
