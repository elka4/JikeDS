package com.jike.spring.chapter01;

public class HelloWorld implements IHelloMessage{
	
	//�̳к�ʵ����IHelloMessage�ӿ��е�sayHello������
	//���������Hello World����Ϣ
	@Override
	public String sayHello() {
		return "Hello World!";
	}

}
