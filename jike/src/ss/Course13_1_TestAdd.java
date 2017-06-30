package ss;

import java.util.ArrayList;
public class Course13_1_TestAdd {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(2, 6);
		System.out.println(list);
		for (int i = 7; i < 1024; i++) {
			list.add(i);
		}
	}
}
