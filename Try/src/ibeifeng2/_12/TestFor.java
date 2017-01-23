package ibeifeng2._12;

import java.util.*;

public class TestFor {
	public static void main(String[] args) {
		System.out.println("输入循环结束值");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		for(int i = 0; i <= num; i++) {
			System.out.print(i + " ");
		}
	}
}