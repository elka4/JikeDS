package jike;

import java.util.LinkedList;

public class Course13_2_TestGetAndSet {
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
		System.out.println(list.get(2));
		System.out.println(list.get(5));
		System.out.println(list.indexOf(4));
		list.set(6, 10);
		System.out.println(list);
	}
}