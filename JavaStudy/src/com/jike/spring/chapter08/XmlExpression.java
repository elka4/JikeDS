package com.jike.spring.chapter08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlExpression {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/jike/spring/chapter10/aop/conf/conf-spel.xml");
		String hello1 = ctx.getBean("hello1", String.class);
		String hello2 = ctx.getBean("hello2", String.class);
		String hello3 = ctx.getBean("hello3", String.class);
		System.out.println("hello1 : " + hello1);
		System.out.println("hello2 : " + hello2);
		System.out.println("hello3 : " + hello3);

	}

}
