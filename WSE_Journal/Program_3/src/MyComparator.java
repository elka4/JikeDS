import java.util.Comparator;

public class MyComparator implements Comparator<Pair<String,Float>> {

	public MyComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Pair<String,Float> o1, Pair<String,Float> o2) {
		// TODO Auto-generated method stub
		return -o1.getSecond().compareTo(o2.getSecond());
	}

}
