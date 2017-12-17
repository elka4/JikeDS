import java.net.URL;
import java.util.Comparator;

public class MyComparator extends WebCrawler implements Comparator<Pair<URL,Integer>> {

	public MyComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Pair<URL,Integer> o1, Pair<URL,Integer> o2) {
		// TODO Auto-generated method stub
		int a = knownURLs.get(o2.getFirst()).compareTo(knownURLs.get(o1.getFirst()));
		if(a==0) return o1.getSecond().compareTo(o2.getSecond());
		else return a;
	}

}
