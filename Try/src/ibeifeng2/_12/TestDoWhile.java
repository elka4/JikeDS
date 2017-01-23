package ibeifeng2._12;

import java.util.*;

public class TestDoWhile {
	public static void main(String[] args) {
		System.out.println("输入循环结束值");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int i = 0;
		do{
			System.out.print(i + " ");
			i++;
		}while(i <= num);
	}
}