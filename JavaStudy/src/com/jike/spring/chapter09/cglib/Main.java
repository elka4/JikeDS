package com.jike.spring.chapter09.cglib;

public class Main {
	public static void main(String[] args) {
		StudentBean stu1 = (StudentBean) (new CGlibProxyFactory()
				.createStudent(new StudentBean()));
		StudentBean stu2 = (StudentBean) (new CGlibProxyFactory()
				.createStudent(new StudentBean("whc")));
		stu1.print();
		stu2.print();
		}
}
