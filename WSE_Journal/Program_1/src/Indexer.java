import org.apache.lucene.index.IndexWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.*;
import org.apache.lucene.index.IndexWriterConfig;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SuppressWarnings({ "deprecation" })
public class Indexer {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			throw new Exception("Usage: java " + Indexer.class.getName() + " <index dir> <data dir>");
		}
		//indexdir indicate dir of index, data dir indicate file  needs to be to indexed
		String indexDir = args[0];         //1
		String dataDir = args[1];          //2
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);
		int numIndexed = indexer.index(dataDir);
		indexer.close();
		long end = System.currentTimeMillis();
		System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");
	}
	
	private IndexWriter writer;
	
	public Indexer(String indexDir) throws IOException {
	Directory dir = FSDirectory.open(Paths.get(indexDir));//(new File(indexDir), null);
	writer = new IndexWriter(dir,          //3
							new IndexWriterConfig(new StandardAnalyzer()));
	}
	public void close() throws IOException {
		writer.close();                             //4
	}
	//index all file in dataDir
	//return numofDocs
	public int index(String dataDir) throws Exception {
		File[] files = new File(dataDir).listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (!f.isDirectory() &&
					!f.isHidden() &&
					f.exists() &&
					f.canRead()) {
				indexFile(f);
			}
		}
		return writer.numDocs();
	}
	//extracted the body element of this html doc
	private String HTMLparse(File f) throws IOException{
		org.jsoup.nodes.Document doc =  Jsoup.parse(f,"UTF-8");
		return doc.body().toString();
	}
	protected boolean acceptFile(File f) {
		return f.getName().endsWith(".txt");
	}
	private void indexFile(File f) throws Exception {
		System.out.println("Indexing " + f.getCanonicalPath());
		Document doc = getDocument(f);
		if (doc != null) {
			writer.addDocument(doc);                              //9
        }
	}
	
	protected Document getDocument(File f) throws Exception {
		Document doc = new Document();
		org.jsoup.nodes.Document HTMLdoc =  Jsoup.parse(f,"UTF-8");
		doc.add(new TextField("contents",  HTMLdoc.body().text(), Field.Store.NO)); 
		doc.add(new StringField("filename", f.getCanonicalPath(),
                 Field.Store.YES));
		if(HTMLdoc.title()!="")
			doc.add(new StringField("title", HTMLdoc.title(),
                Field.Store.YES));
		else{
			Element e = HTMLdoc.getElementsByTag("H1").first();
			if( e== null){
				e = HTMLdoc.getElementsByTag("H2").first();
			}
			if( e== null){
				e = HTMLdoc.getElementsByTag("H3").first();
			}
			doc.add(new StringField("title", e.text(),
	                Field.Store.YES));
		}
        return doc;
	}
}