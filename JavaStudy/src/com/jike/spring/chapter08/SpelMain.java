package com.jike.spring.chapter08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSpelList();

	}

	
    private static void testSpelList() {
    	ApplicationContext ctx = 
				new ClassPathXmlApplicationContext
("/com/jike/spring/chapter08/conf-spel.xml");
        //��IOC�����л�ȡliteral���ʵ��
    	SpelCityList spelCityList = (SpelCityList) ctx.getBean("spelCityList");

        //��ϵͳ����̨�д�ӡ��Ϣ
        System.out.println("chosenCity1: " + spelCityList.getChosenCity1().getName());
        System.out.println("chosenCity2: " + spelCityList.getChosenCity1().getName());
        System.out.println("bigCities: " + spelCityList.getBigCities().size());
        System.out.println("aBigCity1: " + spelCityList.getaBigCity1().getName());
        System.out.println("aBigCity2: " + spelCityList.getaBigCity2().getName());
        System.out.println("cityNames1: " + spelCityList.getCityNames1().size());
        System.out.println("cityNames2: " + spelCityList.getCityNames1().size());
        System.out.println("cityNames3: " + spelCityList.getCityNames1().size());
    }
    
	private static void testSpelMath() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/jike/spring/chapter10/aop/conf/conf-spel.xml");
		SpelMath spelMath = ctx.getBean("spelMath", SpelMath.class);
		
		System.out.println("adjustedAmount:" + spelMath.getAdjustedAmount());
		System.out.println("circumference:" + spelMath.getCircumference());
		System.out.println("average:" + spelMath.getAverage());
		System.out.println("remainder:" + spelMath.getRemainder());
		System.out.println("area:" + spelMath.getArea());
		System.out.println("fullName:" + spelMath.getFullName());
		
	}
	
	
	private static void testSpelClass() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/jike/spring/chapter10/aop/conf/conf-spel.xml");
		SpelClass spelClass = ctx.getBean("spelClass", SpelClass.class);
		
		System.out.println("pi:" + spelClass.getPi());
		System.out.println("randomNumber:" + spelClass.getRandomNumber());
		
	}
	
	
	private static void testSpelLiteral() {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/jike/spring/chapter10/aop/conf/conf-spel.xml");
		SpelLiteral spelLiteral = ctx.getBean("spelLiteral", SpelLiteral.class);
		
		System.out.println("count:" + spelLiteral.getCount());
		System.out.println("message:" + spelLiteral.getMessage());
		System.out.println("frequency:" + spelLiteral.getFrequency());
		System.out.println("capacity:" + spelLiteral.getCapacity());
		System.out.println("name1:" + spelLiteral.getName1());
		System.out.println("name2:" + spelLiteral.getName2());
		System.out.println("enabled:" + spelLiteral.isEnabled());
		
	}
	
	

}
