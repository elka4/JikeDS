package ss;

import java.util.ArrayList;

public class Course13_1_TestRemove {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.remove(2);
		list.remove(new Integer(4));
		System.out.println(list);
	}
}
