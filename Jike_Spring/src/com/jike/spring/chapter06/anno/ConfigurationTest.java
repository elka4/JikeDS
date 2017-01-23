package com.jike.spring.chapter06.anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		
		System.out.println(ctx.getBean("message"));
	}

}
