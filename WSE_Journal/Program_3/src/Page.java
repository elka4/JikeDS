import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page {
	private Float score;
	private Float newscore;
	private Float base;
	private File dataFile;
	private Integer wordCount;
	private Document doc;
	public Page(File f) throws IOException {
		dataFile = f;
		doc = Jsoup.parse(f,"UTF-8");
		StringTokenizer st = new StringTokenizer(doc.text());
		wordCount = st.countTokens();
		base = log2(wordCount.floatValue());
		score = new Float(0);
		newscore = new Float(0);
	}
	public Hashtable<String,Integer> getOutlinks(){
		Hashtable<String,Integer> outlinks = new Hashtable<String,Integer>();
		Elements links = doc.getElementsByTag("A");
		for(Element link:links){
			int s = 1;
			for(Element p: link.parents()){
				if(p.tagName().equals("H1") ||
						p.tagName().equals("H2")||
						p.tagName().equals("H3")||
						p.tagName().equals("H3")||
						p.tagName().equals("em")||
						p.tagName().equals("b")
						)
					s = 2;
			}
			if(outlinks.get(link.attr("href"))==null)
				outlinks.put(link.attr("href"), s);
			else
				outlinks.put(link.attr("href"), outlinks.get(link.attr("href"))+s);
		}
		return outlinks;
	}
	private Float log2(Float x){
		return new Float(Math.log(x)/Math.log(2.0));
	}
	
	public void setBase(Float newBase){
		base = newBase;
	}
	public Float getBase(){
		return base;
	}
	public void setScore(Float newScore){
		score = newScore;
	}
	public Float getScore(){
		return score;
	}
	public String geturl(){
		return dataFile.getName();
	}
	public Integer getWordCount(){
		return wordCount;
	}
	public void setnewScore(Float news){
		newscore = news;
	}
	public void toNew(){
		score = newscore;
	}
}
