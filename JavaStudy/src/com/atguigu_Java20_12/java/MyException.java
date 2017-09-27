package com.atguigu_Java20_12.java;
//����Զ���һ���쳣��
//1.�Զ�����쳣��̳����е��쳣��
//2.�ṩһ�����кţ��ṩ�������صĹ�����
public class MyException extends Exception{
	
	static final long serialVersionUID = -70348975766939L;
	
	public MyException(){
		
	}
	public MyException(String msg){
		super(msg);
	}
}
