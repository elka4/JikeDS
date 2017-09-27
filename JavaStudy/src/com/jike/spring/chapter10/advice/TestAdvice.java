package com.jike.spring.chapter10.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdvice {
	public static void main(String[] args) {
//		testBeforeAdviceByCode();
//		testBeforeAdviceByXML();
		testThrowsAdvice();
	}
	
	private static void testBeforeAdviceByCode() {
	        Waiter target = new NaiveWaiter();
	        BeforeAdvice  advice = new GreetingBeforeAdvice();
	        ProxyFactory pf = new ProxyFactory();
	        pf.setInterfaces(target.getClass().getInterfaces());
	        pf.setOptimize(true);
	        pf.setTarget(target);
	        pf.addAdvice(advice);

	        Waiter proxy = (Waiter)pf.getProxy(); 
	        proxy.greetTo("John");
	        proxy.serveTo("Tom");
	}
	
	private static void testBeforeAdviceByXML() {
		String configPath = "/com/jike/spring/chapter10/conf/conf-advice.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		waiter.greetTo("John");
		waiter.serveTo("Tom");
	}
	
	
	private static void testThrowsAdvice() {
		String configPath = "/com/jike/spring/chapter10/conf/conf-advice.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		ForumService forumService = (ForumService)ctx.getBean("forumService");
		
		try{
			forumService.removeForum(10);
		} catch (Exception e) {}		
		
		try{
			forumService.updateForum(new Forum());
		} catch (Exception e) {}			
	}
}
