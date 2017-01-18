package jike;

import java.util.LinkedList;
public class Course13_2_TestAddAndRemove {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(2, 10);
		list.remove(new Integer(4));
		list.remove(5);
		System.out.println(list);
	}
}