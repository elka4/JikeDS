package com.jike.spring.chapter09.proxy;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentInterface s1 = new StudentBean("Leon");
		ProxyFactory factory = new ProxyFactory();
		StudentInterface s2 = 
				(StudentInterface)factory.createStudentProxy(s1);

		s2.print();
	}

}
