package com.jike.spring.chapter08;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoExpression {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/jike/spring/chapter10/aop/conf/conf-spel.xml");
		AnnoExpression helloBean1 = ctx.getBean("helloBean1", AnnoExpression.class);
		AnnoExpression helloBean2 = ctx.getBean("helloBean2", AnnoExpression.class);
		System.out.println("helloBean1 : " + helloBean1.getValue());
		System.out.println("helloBean2 : " + helloBean2.getValue());
		

	}
	
	@Value("#{'Hello ' + world}")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
