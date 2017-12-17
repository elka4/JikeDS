import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Retriver {
	
	public static void main(String[] args) throws Exception 
	{
		String query = args[1];
		for(int i = 2; i < args.length;i++)
			query += (" "+ args[i]);
		search(args[0],query);
	}
	
	private static IndexSearcher searcher = null;
    private QueryParser parser = null;
	
	public static void search(String index, String q) throws IOException, ParseException{
	// instantiate the search engine
		Retriver se = new Retriver(index);

	// retrieve top 10 matching document list for the query
		TopDocs topDocs = se.performSearch(q, 10); 

	// obtain the ScoreDoc (= documentID, relevanceScore) array from topDocs
		ScoreDoc[] hits = topDocs.scoreDocs;

	// retrieve each matching document from the ScoreDoc arry
		for (int i = 0; i < hits.length; i++) {
		    Document doc = searcher.doc(hits[i].doc);
		    System.out.println(String.valueOf(i+1) + ":" + doc.get("title"));
		    System.out.println("\t" + doc.get("filename"));
		    }
	}

    public Retriver(String index) throws IOException {
        searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(index))));
        parser = new QueryParser("contents", new StandardAnalyzer());
    }

    public TopDocs performSearch(String queryString, int n)
    throws IOException, ParseException {
        Query query = parser.parse(queryString);
        return searcher.search(query, n);
    }

    public Document getDocument(int docId)
    throws IOException {
        return searcher.doc(docId);
    }
}