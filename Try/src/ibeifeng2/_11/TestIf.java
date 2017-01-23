package ibeifeng2._11;

import java.util.*;

public class TestIf {
	public static void main(String[] args) {
		System.out.println("请输入学生成绩");
		Scanner in = new Scanner(System.in);
		int score = in.nextInt();
		if(score > 60) {
			System.out.println("该学生已经及格");
			System.out.println("恭喜");
		} else {
			System.out.println("该学生没有及格");
			System.out.println("请补考");
		}
	}
}