package com._12atguigu.exer;

public class ReturnExceptionDemo {
	static void methodA() {
		try {
			System.out.println("���뷽��A");
			throw new RuntimeException("�����쳣");
		} finally {
			System.out.println("��A������finally");
		}}
	static int methodB() {
		try {
			System.out.println("���뷽��B");
			return 1;
		} finally {
			System.out.println("����B������finally");
			return 2;
		}}
	public static void main(String[] args) {
		try {
			methodA();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		int i = methodB();
		System.out.println(i);
	}
}
