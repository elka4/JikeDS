package edu.nyu.cs.wse.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;



/*
@SpringBootApplication
public class SearcherApplication {

	public static void main(String[] args) {

	    SpringApplication.run(SearcherApplication.class, args);
	}
}

*/



@SpringBootApplication
public class SearcherApplication implements CommandLineRunner {

    @Autowired
    private Searcher searcher;

    @Override
    public void run(String[] args) {
        //Results results = new Results(args);
        List<Result> resultList = searcher.search(args);
        System.out.println("runrunrunrunrunrunrunrunrunrunrun");
        System.out.println("resultList.size(): " + resultList.size());
    }


    public static void main(String[] args) {

        SpringApplication.run(SearcherApplication.class, args);
    }
}


/*
runrunrunrunrunrunrunrunrunrunrun
results: Results(queryStr=, resultList=[])
 */

/*
java -jar target/searcher-0.0.1-SNAPSHOT.jar hahahaha

============================getQuery============================
============================getTopDocs============================
runrunrunrunrunrunrunrunrunrunrun
results: Results(queryStr=hahahaha , resultList=[])
 */