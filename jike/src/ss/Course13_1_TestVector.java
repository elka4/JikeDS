package ss;

import java.util.Iterator;
import java.util.Vector;

public class Course13_1_TestVector {
	public static void main(String[] args) {
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);
		vec.add(1);
		vec.add(2);
		vec.add(3);
		vec.add(4);
		vec.add(5);
		vec.add(3, 6);
		vec.remove(new Integer(0));
		vec.remove(2);
		System.out.println(vec.get(3));
		vec.set(4, 7);
		System.out.println("--");
		Iterator<Integer> it = vec.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
