package ibeifeng2._11;

import java.util.*;

public class TestSwitch {
	public static void main(String[] args) {
		System.out.println("请输入学生成绩");
		Scanner in = new Scanner(System.in);
		int score = in.nextInt();
		switch(score / 10){
			case 10:
			case 9:
				System.out.println("等级A");
				break;
			case 8:
				System.out.println("等级B");
				break;
			case 7:
				System.out.println("等级C");
				break;
			case 6:
				System.out.println("等级D");
				break;
			default:
				System.out.println("不及格");
				
		}
	}
}