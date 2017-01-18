package jike;

import java.util.ArrayList;

public class Course13_1_TestGetAndSet {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		System.out.println(list.get(4));
		System.out.println(list.indexOf(5));
		list.set(3, 10);
		System.out.println(list);
	}
}
