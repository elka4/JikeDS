package com.atguigu_Java20_12.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * �쳣����ķ�ʽ�����ڷ���������������ʽ���׳����쳣���������
 * ��ʽ���磺public static  void method2() throws FileNotFoundException,IOException{}
 * ���ڴ˷����ڲ������쳣��ʱ�򣬻��׳�һ���쳣��Ķ����׸������ĵ����ߡ�
 * �쳣�Ķ��������������ף�ֱ��main�С���Ȼ�������׵Ĺ����У�������ͨ��try-catch-finally���д���
 * 
 * java���쳣����ץ��ģ��
 * 1.ץ���쳣�Ĵ��������ַ�ʽ(��try-catch-finally�� throws + �쳣������)
 * 2.�ף�һ��ִ�й����У������쳣�����׳�һ���쳣��Ķ��󡣣��Զ����׳�  vs �ֶ����׳���throw + �쳣��Ķ��󣩣�
 *     >�쳣�࣬�ȿ������ֳɵ��쳣�࣬Ҳ�������Լ��������쳣��
 */

public class TestException {
	public static void main(String[] args){
		try{
		method2();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		method3();
	}
	
	public static void method3(){
		try{
		method1();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static  void method2() throws FileNotFoundException,IOException{
		method1();
	}
	
	public static void method1() throws FileNotFoundException,IOException{
		FileInputStream fis = new FileInputStream(new File("hello1.txt"));
		int b;
		while((b = fis.read()) != -1){
			System.out.print((char)b);
		}
		fis.close();
	}
}
