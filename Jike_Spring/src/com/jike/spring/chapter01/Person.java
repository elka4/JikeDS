package com.jike.spring.chapter01;

public class Person {

	//将IHelloMessage作为一个属性，用于向大家输出问候信息
	private IHelloMessage helloMessage;

	
	public IHelloMessage getHelloMessage() {
		return helloMessage;
	}

	public void setHelloMessage(IHelloMessage helloMessage) {
		this.helloMessage = helloMessage;
	}
	
	//用于调用IHelloMessage接口向用户输出问候信息，
	//具体的问候信息，由Spring的配置文件来分配和决定
	//1.当配置文件中分配给person的是HelloWorld的实体时，则输出“Hello World！”的信息；
	//2.当配置文件中分配给person的是HelloChina的实体时，则输出“Hello China！”的信息；
	public String sayHello() {
		return this.helloMessage.sayHello();
	}
}
