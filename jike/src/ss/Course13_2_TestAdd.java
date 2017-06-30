package ss;

import java.util.LinkedList;

public class Course13_2_TestAdd {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.addLast(3);
		list.addFirst(4);
		System.out.println(list);
	}
}