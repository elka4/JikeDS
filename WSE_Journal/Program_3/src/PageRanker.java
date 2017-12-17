import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class PageRanker {
	private Float epsilon;
	private Float f;
	private File doc_path;
	private Hashtable<String,Page> url_Page;
	Vector<Vector<Float>> linksVec;
	Hashtable<Integer,String> index_url;
	Hashtable<String,Integer> url_index;


	public PageRanker(Float e,String docs) throws IOException {
		f = e;
		doc_path = new File(docs);
		String[] files = doc_path.list();
		epsilon = new Float(0.01 / (files.length - 1));
		url_Page = new Hashtable<String,Page>();
		Float sum = new Float(0);
		index_url = new Hashtable<Integer,String>();
		url_index = new Hashtable<String,Integer>();
		int key = 0;
		for(String file: files){
			if(!file.startsWith(".")){
				Page temp = new Page(new File(doc_path.getPath() + "/"+file));
				sum += temp.getBase();
				url_Page.put(temp.geturl(),temp);
				
				index_url.put(key, temp.geturl());
				url_index.put(temp.geturl(), key++);
			}
		}
		linksVec = new Vector<Vector<Float>>();
		int size = url_Page.size();
		for(int i = 0; i < size; i++){
			linksVec.addElement(new Vector<Float>());
			for(int j = 0; j< size;j++){
				linksVec.get(i).add(new Float(Float.NaN));
			}
		}
		for(Page p:url_Page.values()){
			p.setBase(p.getBase()/sum);
			p.setScore(p.getBase());
		}
		
		Hashtable<String,Integer> pageOutlink;
		for(int i = 0; i < size; i++){
			pageOutlink = url_Page.get(index_url.get(i)).getOutlinks();
			sum = new Float(0);
			if(!pageOutlink.isEmpty()){
				for(String out: pageOutlink.keySet()){
					linksVec.get(i).set(url_index.get(out), new Float(pageOutlink.get(out)));
					sum += pageOutlink.get(out);
				}
				for(String out: pageOutlink.keySet())
					linksVec.get(i).set(url_index.get(out), new Float(pageOutlink.get(out)/sum));
			}
			else{
				for(int j = 0; j < size; j++)
					linksVec.get(i).set(j, url_Page.get(index_url.get(j)).getBase());
			}
		}/*
		for(int i = 0; i< size; i++){
			System.out.println(index_url.get(i)+": \t"+url_Page.get(index_url.get(i)).getWordCount()+":"+url_Page.get(index_url.get(i)).getScore());
		}
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(!linksVec.get(i).get(j).isNaN()){
					System.out.println(index_url.get(i)+"->"+index_url.get(j)+":\t"+linksVec.get(i).get(j));
				}
			}
		}*/
	}
	public void run(){
		Boolean changed = true;
		Float newscore = new Float(0);
		Float others = new Float(0);
		Page tempPage;
		while(changed){
			changed = false;
			int size = url_Page.size();
			
			for(int j = 0; j < size; j++){
				tempPage = url_Page.get(index_url.get(j));
				newscore = new Float(0);
				others = new Float(0);
				newscore = (1-f) * tempPage.getBase();
				for(int i = 0; i < size; i++){
					if(!linksVec.get(i).get(j).isNaN())
						others += linksVec.get(i).get(j)*url_Page.get(index_url.get(i)).getScore();
				}
				newscore += (others * f);
				tempPage.setnewScore(newscore);
				if(new Float(newscore - tempPage.getScore()).compareTo(epsilon) > 0)
					changed = true;
			}
			for(int i = 0; i < size; i++)
				url_Page.get(index_url.get(i)).toNew();
		}
		PriorityQueue<Pair<String,Float>> pq = new PriorityQueue<Pair<String,Float>>(new MyComparator());
		for(int i = 0; i< url_Page.size(); i++){
			pq.add(new Pair<String, Float>(index_url.get(i),url_Page.get(index_url.get(i)).getScore()));
		}
		while(!pq.isEmpty()){
			System.out.println(pq.remove());
		}
	}


	public static void main(String[] args) throws IOException {
		Options ops = new Options();
		ops.addOption("docs", true, "Path");
		ops.addOption("f", "F", true, "F value");
		DefaultParser parser = new DefaultParser();
		CommandLine comman;
		try {
			comman = parser.parse(ops,args);
		} catch (ParseException e) {
			e.printStackTrace();
			return ;
		}
		Float f = Float.valueOf(comman.getOptionValue('f'));
		PageRanker MyRanker = new PageRanker(f, comman.getOptionValue("docs"));
		MyRanker.run();	
	}
}
	/*
	-docs /Users/tianhuizhu/IdeaProjects/WSE_Program/Program_3/data/ -f 0.7


	(Arctic.html, 0.17069417)
(Atlantic.html, 0.16531157)
(Pinniped.html, 0.16317362)
(Pacific.html, 0.15824306)
(Seal.html, 0.11870147)
(Bear.html, 0.086828604)
(PolarBear.html, 0.072297)
(Walrus.html, 0.064750485)
	 */