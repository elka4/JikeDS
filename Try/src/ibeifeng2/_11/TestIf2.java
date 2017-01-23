package ibeifeng2._11;

import java.util.*;

public class TestIf2 {
	public static void main(String[] args) {
		System.out.println("请输入学生成绩");
		Scanner in = new Scanner(System.in);
		int score = in.nextInt();
		if(score >= 90) {
			System.out.println("等级A");
		} else if(score >= 80) {
			System.out.println("等级B");
		} else if(score >= 70) {
			System.out.println("等级C");
		} else if(score >= 60) {
			System.out.println("等级D");
		} else {
			System.out.println("不及格");
		}
	}
}