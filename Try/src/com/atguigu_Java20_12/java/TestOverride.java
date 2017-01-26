package com.atguigu_Java20_12.java;

import java.io.FileNotFoundException;
import java.io.IOException;

//������д�ĸ���ķ��������׳����쳣����ֻ���Ǳ���д�ķ������쳣���������쳣����һ����
public class TestOverride {
	public static void main(String[] args) {
		A a = new B();
		try {
			a.method1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class A {
	public void method1() throws IOException {

	}
}

class B extends A {
	public void method1() throws FileNotFoundException {

	}
}