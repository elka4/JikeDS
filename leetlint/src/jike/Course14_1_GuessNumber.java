package jike;

import java.util.Scanner;

public class Course14_1_GuessNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请甲输入一个数字,1-100之间");
		int jia = sc.nextInt();
		int count = 0;
		System.out.println("请乙猜数字");
		while (true) {
			int yi = sc.nextInt();
			count++;
			if (yi == jia) {
				System.out.println("恭喜你，猜对了!");
				System.out.println("比较了" + count + "次");
				break;
			} else if (yi < jia) {
				System.out.println("小了");
			} else {
				System.out.println("大了");
			}
		}
	}
}
