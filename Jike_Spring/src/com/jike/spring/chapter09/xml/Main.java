package com.jike.spring.chapter09.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	 public static void main(String[] args) {  
		       ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/jike/spring/chapter09/conf/conf-xml.xml");
		         Student stu = (Student)ctx.getBean("stu");  
		 	        stu.print("whc1");  
			    }  

}
